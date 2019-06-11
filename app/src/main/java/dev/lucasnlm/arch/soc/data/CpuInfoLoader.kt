package dev.lucasnlm.arch.soc.data

import dev.lucasnlm.arch.core.system.InternalDataReader
import dev.lucasnlm.arch.core.system.DeviceInfo
import dev.lucasnlm.arch.soc.model.CpuClockInfo
import dev.lucasnlm.arch.soc.model.CpuInfo
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.math.roundToInt

class CpuInfoLoader @Inject constructor(
    private val scheduler: Scheduler,
    private val internalDataReader: InternalDataReader,
    private val deviceInfo: DeviceInfo
) {

    fun getCpuInfo(): Single<CpuInfo> = Single.fromCallable {
        val propCpuInfo = readPropCpuInfo()
        val clockInfo = readClockInfo(propCpuInfo)

        CpuInfo(
            model = propCpuInfo["model", "Processor"],
            modelName = propCpuInfo["model name", "Hardware"],
            vendor = propCpuInfo["vendor_id", "CPU implementer"],
            architecture = propCpuInfo["CPU architecture"],
            stepping = propCpuInfo["stepping"],
            clockInfo = clockInfo,
            cpuCores = clockInfo.clocks.size,
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

    fun listenClockInfo(): Observable<CpuClockInfo> = Observable.interval(1, TimeUnit.SECONDS, scheduler).map {
        val propCpuInfo = readPropCpuInfo()
        readClockInfo(propCpuInfo)
    }

    private fun readClockInfo(propCpuInfo: List<ProcessorInfo>): CpuClockInfo {
        val coresCount: Int = deviceInfo.getCpuCoresNumber()
        val clocks = IntArray(coresCount) { 0 }.apply {
            propCpuInfo.mapIndexed { index, processorInfo ->
                this[index] = processorInfo["cpu MHz"]?.toDouble()?.roundToInt()
                    ?: readCurrentCpuClock(index)?.toDoubleOrNull()?.div(1000.0)?.roundToInt() ?: 0
            }
        }.toList()

        val maxClock = (0 until coresCount).mapNotNull {
            readMaxCpuClock(it)?.toDoubleOrNull()?.div(1000.0)?.roundToInt()
        }.max()

        val minClock = (0 until coresCount).mapNotNull {
            readMinCpuClock(it)?.toDoubleOrNull()?.div(1000.0)?.roundToInt()
        }.min()

        return CpuClockInfo(clocks, maxClock, minClock)
    }

    private fun readCommand(command: String): String? = internalDataReader.read(command).removeTabsAndNewLines()

    private fun readCpuGovernor(): String? = readCommand(CPU_GOVERNOR)

    private fun readCurrentCpuClock(cpuNumber: Int): String? = readCommand(makeClockCommand(cpuNumber))

    private fun readMinCpuClock(cpuNumber: Int): String? = readCommand(makeMinClockCommand(cpuNumber))

    private fun readMaxCpuClock(cpuNumber: Int): String? = readCommand(makeMaxClockCommand(cpuNumber))

    private fun parseFlags(flags: String?): List<String> = flags?.split(" ").orEmpty()

    private fun readPropCpuInfo(): List<ProcessorInfo> {
        return mutableListOf<ProcessorInfo>().apply {
            var currentProcessor = 0
            internalDataReader.read(CPU_INFO_COMMAND).lines().asSequence().map {
                it.removeTabsAndNewLines()
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

    private fun String.removeTabsAndNewLines() = this.replace("\t", "").replace("\n","")

    companion object {
        const val CPU_INFO_COMMAND = "/proc/cpuinfo"
        const val CPU_GOVERNOR = "/sys/devices/system/cpu/cpu0/cpufreq/scaling_governor"

        fun makeClockCommand(coreNumber: Int): String =
            "/sys/devices/system/cpu/cpu$coreNumber/cpufreq/scaling_cur_freq"

        fun makeMaxClockCommand(coreNumber: Int): String =
            "/sys/devices/system/cpu/cpu$coreNumber/cpufreq/cpuinfo_max_freq"

        fun makeMinClockCommand(coreNumber: Int): String =
            "/sys/devices/system/cpu/cpu$coreNumber/cpufreq/cpuinfo_min_freq"
    }
}
