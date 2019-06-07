package dev.lucasnlm.arch.system.data

import android.os.Build
import dev.lucasnlm.arch.common.data.InfoLoader
import dev.lucasnlm.arch.system.model.BrandInfo
import dev.lucasnlm.arch.system.model.ProductInfo
import dev.lucasnlm.arch.system.model.SystemInfo
import dev.lucasnlm.arch.system.model.VersionInfo
import io.reactivex.Single

class SystemInfoLoader: InfoLoader<SystemInfo> {

    override fun load(): Single<SystemInfo> = Single.just(
        SystemInfo(
            androidApi = Build.VERSION.SDK_INT,
            androidName = AndroidName.fromApi(Build.VERSION.SDK_INT),
            productInfo = ProductInfo(
                model = Build.MODEL,
                product = Build.PRODUCT,
                deviceName = Build.DEVICE,
                boardName = Build.BOARD,
                bootloader = Build.BOOTLOADER
            ),
            brand = BrandInfo(
                manufacturer = Build.MANUFACTURER,
                brand = Build.BRAND
            ),
            version = VersionInfo(
                base = takeIfApi(Build.VERSION_CODES.M) { Build.VERSION.BASE_OS } ,
                release = Build.VERSION.RELEASE,
                securityPatch = takeIfApi(Build.VERSION_CODES.M) { Build.VERSION.SECURITY_PATCH },
                codename = Build.VERSION.CODENAME
            )
        )
    )

    private fun takeIfApi(minApi: Int, action:() -> String): String? =
        if (Build.VERSION.SDK_INT >= minApi) action() else null
}
