package dev.lucasnlm.arch.cpu

import dev.lucasnlm.arch.system.model.BrandInfo
import dev.lucasnlm.arch.system.model.ProductInfo
import dev.lucasnlm.arch.system.model.SystemInfo
import dev.lucasnlm.arch.system.model.VersionInfo

object MockSystemInfo {
    val systemInfo1 = SystemInfo(
        androidApi = 23,
        androidName = "Marshmallow",
        productInfo = ProductInfo(
            model = "Model A",
            product = "Product B",
            deviceName = "Awesome Device",
            boardName = "Board Name",
            bootloader = "Loader X"
        ),
        brand = BrandInfo(
            manufacturer = "Acme",
            brand = "Acme 2"
        ),
        version = VersionInfo(
            base = "Base",
            release = "REL",
            securityPatch = "a-b-c-d",
            codename = "wick"
        )
    )
}