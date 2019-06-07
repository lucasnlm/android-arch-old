package dev.lucasnlm.arch.core.repository

import io.reactivex.Observable
import io.reactivex.Single

interface Repository<T : Any> {

    fun fetchValue(): Single<T>

    fun getObservable(): Observable<T>
}
