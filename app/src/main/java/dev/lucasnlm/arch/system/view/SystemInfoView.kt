package dev.lucasnlm.arch.system.view

import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.lucasnlm.arch.R
import dev.lucasnlm.arch.common.model.NamedInfo
import dev.lucasnlm.arch.common.view.InfoAdapter
import dev.lucasnlm.arch.common.view.setupList
import dev.lucasnlm.arch.system.Contracts
import dev.lucasnlm.arch.system.model.SystemInfo

class SystemInfoView: Contracts.View {

    private lateinit var detailsList: RecyclerView
    private lateinit var productList: RecyclerView
    private lateinit var brandList: RecyclerView
    private lateinit var versionList: RecyclerView

    override fun onViewCreated(view: View) {
        detailsList = view.setupList(R.id.details_list)
        productList = view.setupList(R.id.product_list)
        brandList = view.setupList(R.id.brand_list)
        versionList = view.setupList(R.id.version_list)
    }

    override fun showInfo(systemInfo: SystemInfo) {
        val context = detailsList.context

        with (detailsList.adapter as InfoAdapter) {
            list = listOf(
                NamedInfo(
                    name = context.getString(R.string.sys_screen_android_name),
                    value = systemInfo.androidName
                ),
                NamedInfo(
                    name = context.getString(R.string.sys_screen_android_api),
                    value = systemInfo.androidApi.toString()
                )
            ).filter {
                it.value != null
            }
            notifyDataSetChanged()
        }

        with(productList.adapter as InfoAdapter) {
            list = listOf(
                NamedInfo(
                    name = context.getString(R.string.sys_screen_product),
                    value = systemInfo.productInfo.product
                ),
                NamedInfo(
                    name = context.getString(R.string.sys_screen_device_name),
                    value = systemInfo.productInfo.deviceName
                ),
                NamedInfo(
                    name = context.getString(R.string.sys_screen_bootloader),
                    value = systemInfo.productInfo.bootloader
                ),
                NamedInfo(
                    name = context.getString(R.string.sys_screen_board_name),
                    value = systemInfo.productInfo.boardName
                )
            ).filter {
                it.value != null && it.value.isNotEmpty()
            }
            notifyDataSetChanged()
        }

        with (brandList.adapter as InfoAdapter) {
            list = listOf(
                NamedInfo(
                    name = context.getString(R.string.sys_screen_brand),
                    value = systemInfo.brand.brand
                ),
                NamedInfo(
                    name = context.getString(R.string.sys_screen_manufacturer),
                    value = systemInfo.brand.manufacturer
                )
            ).filter {
                it.value != null && it.value.isNotEmpty()
            }
            notifyDataSetChanged()
        }

        with (versionList.adapter as InfoAdapter) {
            list = listOf(
                NamedInfo(
                    name = context.getString(R.string.sys_version_codename),
                    value = systemInfo.version.codename
                ),
                NamedInfo(
                    name = context.getString(R.string.sys_version_release),
                    value = systemInfo.version.release
                ),
                NamedInfo(
                    name = context.getString(R.string.sys_version_security_path),
                    value = systemInfo.version.securityPatch
                )
            ).filter {
                it.value != null && it.value.isNotEmpty()
            }
            notifyDataSetChanged()
        }
    }
}
