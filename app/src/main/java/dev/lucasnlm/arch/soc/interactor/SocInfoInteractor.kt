package dev.lucasnlm.arch.soc.interactor

import dev.lucasnlm.arch.soc.Contracts
import dev.lucasnlm.arch.soc.data.CpuInfoLoader
import dev.lucasnlm.arch.soc.model.CpuClockInfo
import dev.lucasnlm.arch.soc.model.CpuInfo
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class SocInfoInteractor @Inject constructor(
    private val cpuInfoLoader: CpuInfoLoader
): Contracts.Interactor {

    override fun getCpuInfo(): Single<CpuInfo> = cpuInfoLoader.getCpuInfo()

    override fun listenClockInfo(): Observable<CpuClockInfo> = cpuInfoLoader.listenClockInfo()
}
