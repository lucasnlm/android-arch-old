package dev.lucasnlm.arch.system.repository

import dev.lucasnlm.arch.core.repository.Repository
import dev.lucasnlm.arch.system.data.SystemInfoLoader
import dev.lucasnlm.arch.system.model.SystemInfo
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class SystemInfoRepository @Inject constructor(
    private val systemInfoLoader: SystemInfoLoader
): Repository<SystemInfo> {

    override fun fetchValue(): Single<SystemInfo> = systemInfoLoader.load()

    override fun getObservable(): Observable<SystemInfo> = systemInfoLoader.load().toObservable()
}
