package dev.lucasnlm.arch.soc

import dev.lucasnlm.arch.core.view.BaseView
import dev.lucasnlm.arch.soc.model.CpuInfo
import dev.lucasnlm.arch.soc.model.GpuInfo

abstract class CpuInfoViewContract: BaseView() {
    abstract fun showInfo(cpuInfo: CpuInfo)
    abstract fun showClocks(clocks: List<Int>)
    abstract fun showFlags(flags: List<String>)
    abstract fun showGpuInfo(gpuInfo: GpuInfo)
}

interface CpuInfoPresenterContract {
    fun loadCpuInfo()
    fun loadGpuInfo()
}
