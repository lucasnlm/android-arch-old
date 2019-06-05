package dev.lucasnlm.arch.soc

import dev.lucasnlm.arch.core.view.BaseView
import dev.lucasnlm.arch.soc.model.CpuInfo
import dev.lucasnlm.arch.soc.model.GpuInfo

interface Contract {

    interface View: BaseView {
        fun showInfo(cpuInfo: CpuInfo)
        fun showClocks(clocks: List<Int>)
        fun showFlags(flags: List<String>)
        fun showGpuInfo(gpuInfo: GpuInfo)
    }

    interface Presenter {
        fun loadCpuInfo()
        fun loadGpuInfo()
    }
}
