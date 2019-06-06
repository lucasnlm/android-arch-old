package dev.lucasnlm.arch.soc.repository

import dev.lucasnlm.arch.core.repository.Repository
import dev.lucasnlm.arch.soc.model.CpuInfo
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class CpuInfoRepository @Inject constructor(private val cpuInfoLoader: CpuInfoLoader) : Repository<CpuInfo> {

    override fun fetchValue(): Single<CpuInfo> = Single.just(null) // TODO

    override fun getObservable(): Observable<CpuInfo> = cpuInfoLoader.getCpuInfo()
}
