package dev.lucasnlm.arch.system.interactor

import dev.lucasnlm.arch.core.repository.Repository
import dev.lucasnlm.arch.system.Contracts
import dev.lucasnlm.arch.system.model.SystemInfo
import io.reactivex.Single
import javax.inject.Inject

class SystemInfoInteractor @Inject constructor(
    private val systemInfoRepository: Repository<SystemInfo>
): Contracts.Interactor {

    override fun getSystemInfo(): Single<SystemInfo> = systemInfoRepository.fetchValue()
}
