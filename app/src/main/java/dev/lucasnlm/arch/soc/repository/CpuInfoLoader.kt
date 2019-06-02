package dev.lucasnlm.arch.soc.repository

import dev.lucasnlm.arch.core.system.InternalDataReader
import dev.lucasnlm.arch.core.system.DeviceInfo
import dev.lucasnlm.arch.soc.model.CpuInfo
import io.reactivex.Observable
import io.reactivex.Scheduler
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.math.roundToInt

class CpuInfoLoader @Inject constructor(
    private val scheduler: Scheduler,
    private val internalDataReader: InternalDataReader,
    private val deviceInfo: DeviceInfo
) {

    fun getCpuInfo(): Observable<CpuInfo> = Observable.fromCallable {
        val propCpuInfo = readPropCpuInfo()
        val clockList = readClockValues(propCpuInfo)

        CpuInfo(
            model = propCpuInfo["model", "Processor"],
            modelName = propCpuInfo["model name", "Hardware"],
            vendor = propCpuInfo["vendor_id", "CPU implementer"],
            architecture = propCpuInfo["CPU architecture"],
            stepping = propCpuInfo["stepping"],
            clocks = clockList,
            cpuCores = clockList.size,
            bogoMips = propCpuInfo["BogoMips", "BogoMIPS", "bogomips"],
            abi = deviceInfo.getPlatformAbi(),
            bigLittle = 0,
            revision = propCpuInfo["Revision", "CPU revision"],
            device = propCpuInfo["Device"],
            serial = propCpuInfo["Serial"],
            flags = parseFlags(propCpuInfo["flags", "Features"]),
            governor = readCpuGovernor()
        )
    }

    fun listenClocks(): Observable<List<Int>> = Observable.interval(1, TimeUnit.SECONDS, scheduler).map {
        val propCpuInfo = readPropCpuInfo()
        readClockValues(propCpuInfo)
    }

    private fun readClockValues(propCpuInfo: List<ProcessorInfo>): List<Int> {
        val coresCount = deviceInfo.getCpuCoresNumber()
        return IntArray(coresCount) { 0 }.apply {
            propCpuInfo.mapIndexed { index, processorInfo ->
                this[index] = processorInfo["cpu MHz"]?.toDouble()?.roundToInt() ?:
                        readCurrentCpuClock(index)?.toDoubleOrNull()?.div(1000.0)?.roundToInt() ?: 0
            }
        }.toList()
    }

    private fun readCpuGovernor(): String? =
        internalDataReader.read(CPU_GOVERNOR).use { inputStream ->
            inputStream.bufferedReader().readLines().map {
                it.replace("\t", "")
            }.firstOrNull()
        }

    private fun readCurrentCpuClock(cpuNumber: Int): String? =
        internalDataReader.read(makeClockCommand(cpuNumber)).use { inputStream ->
            inputStream.bufferedReader().readLines().map {
                it.replace("\t", "")
            }.firstOrNull()
        }

    private fun parseFlags(flags: String?): List<String> = flags?.split(" ").orEmpty()

    private fun readPropCpuInfo(): List<ProcessorInfo> {
        return ArrayList<ProcessorInfo>().apply {
            internalDataReader.read(CPU_INFO_COMMAND).use { inputStream ->
                var currentProcessor = 0
                inputStream.bufferedReader().readLines().asSequence().map {
                    it.replace("\t", "")
                }.filter {
                    it.isNotEmpty()
                }.map {
                    it.split(":").let { keyValue ->
                        keyValue[0].trim() to if (keyValue.size > 1) keyValue[1].trim() else ""
                    }
                }.groupBy {
                    if (it.first == "processor") {
                        currentProcessor = it.second.trim().toIntOrNull() ?: 0
                    }
                    currentProcessor
                }.map {
                    ProcessorInfo(HashMap<String, String>().apply {
                        putAll(it.value)
                    })
                }.toList().forEach {
                    add(it)
                }
            }
        }
    }

    private data class ProcessorInfo(
        val data: Map<String, String>
    ) {
        operator fun get(vararg keys: String): String? =
            data.filterKeys {
                keys.contains(it)
            }.map {
                it.value
            }.firstOrNull()
    }

    private operator fun List<ProcessorInfo>.get(vararg keys: String): String? = this.flatMap {
        it.data.filterKeys { key -> keys.contains(key) }.values
    }.firstOrNull()

    companion object {
        const val CPU_INFO_COMMAND = "/proc/cpuinfo"
        const val CPU_GOVERNOR = "/sys/devices/system/cpu/cpu0/cpufreq/scaling_governor"

        fun makeClockCommand(coreNumber: Int): String =
            "/sys/devices/system/cpu/cpu$coreNumber/cpufreq/scaling_cur_freq"
    }
}
