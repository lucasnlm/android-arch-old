package dev.lucasnlm.arch.core.repository

import io.reactivex.Observable

interface Repository<T : Any> {

    fun getObservable(): Observable<T>
}
