package dev.lucasnlm.arch.common.data

import io.reactivex.Single

interface InfoLoader<T> {
    fun load(): Single<T>
}
