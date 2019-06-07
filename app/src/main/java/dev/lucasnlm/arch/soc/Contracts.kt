package dev.lucasnlm.arch.soc

import dev.lucasnlm.arch.core.view.BaseView
import dev.lucasnlm.arch.soc.model.CpuClockInfo
import dev.lucasnlm.arch.soc.model.CpuInfo
import dev.lucasnlm.arch.soc.model.GpuInfo
import io.reactivex.Observable
import io.reactivex.Single

interface Contracts {

    interface View: BaseView {
        fun showInfo(cpuInfo: CpuInfo)
        fun showClocks(maxClock: Int?, minClock: Int?, clocks: List<Int>)
        fun showFlags(flags: List<String>)
        fun showGpuInfo(gpuInfo: GpuInfo)
    }

    interface Presenter {
        fun loadCpuInfo()
        fun loadGpuInfo()
    }

    interface Interactor {
        fun getCpuInfo(): Single<CpuInfo>
        fun listenClockInfo(): Observable<CpuClockInfo>
    }
}
