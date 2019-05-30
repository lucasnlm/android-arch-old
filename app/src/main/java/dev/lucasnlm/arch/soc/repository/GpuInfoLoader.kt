package dev.lucasnlm.arch.soc.repository

import dev.lucasnlm.arch.soc.model.GpuInfo
import io.reactivex.Single
import javax.microedition.khronos.opengles.GL10

class GpuInfoLoader {

    fun fetchInfo(gl: GL10): Single<GpuInfo> = with(gl) {

        return Single.just(GpuInfo("", "", "", "".split(" ")))
    }
}
