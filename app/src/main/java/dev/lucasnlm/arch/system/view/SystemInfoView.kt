package dev.lucasnlm.arch.system.view

import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import dev.lucasnlm.arch.R
import dev.lucasnlm.arch.common.model.Info
import dev.lucasnlm.arch.common.view.InfoAdapter
import dev.lucasnlm.arch.common.view.setupList
import dev.lucasnlm.arch.system.Contracts
import dev.lucasnlm.arch.system.model.SystemInfo

class SystemInfoView : Contracts.View {

    private lateinit var detailsList: RecyclerView
    private lateinit var productList: RecyclerView
    private lateinit var brandList: RecyclerView
    private lateinit var versionList: RecyclerView
    private lateinit var progress: ProgressBar
    private lateinit var systemLayout: ViewGroup

    override fun onViewCreated(view: View) {
        detailsList = view.setupList(R.id.details_list)
        productList = view.setupList(R.id.product_list)
        brandList = view.setupList(R.id.brand_list)
        versionList = view.setupList(R.id.version_list)
        progress = view.findViewById(R.id.progress)
        systemLayout = view.findViewById(R.id.system_layout)
    }

    override fun showInfo(systemInfo: SystemInfo) {
        val context = detailsList.context

        with(detailsList.adapter as InfoAdapter) {
            setListAndNotify(
                listOf(
                    Info.Named(
                        name = context.getString(R.string.sys_screen_android_name),
                        value = systemInfo.androidName
                    ),
                    Info.Named(
                        name = context.getString(R.string.sys_screen_android_api),
                        value = systemInfo.androidApi.toString()
                    )
                ).filter {
                    it.hasValue()
                }
            )
        }

        with(productList.adapter as InfoAdapter) {
            setListAndNotify(
                listOf(
                    Info.Named(
                        name = context.getString(R.string.sys_screen_model),
                        value = systemInfo.productInfo.model
                    ),
                    Info.Named(
                        name = context.getString(R.string.sys_screen_product),
                        value = systemInfo.productInfo.product
                    ),
                    Info.Named(
                        name = context.getString(R.string.sys_screen_device_name),
                        value = systemInfo.productInfo.deviceName
                    ),
                    Info.Named(
                        name = context.getString(R.string.sys_screen_bootloader),
                        value = systemInfo.productInfo.bootloader
                    ),
                    Info.Named(
                        name = context.getString(R.string.sys_screen_board_name),
                        value = systemInfo.productInfo.boardName
                    )
                ).filter {
                    it.hasValue()
                }
            )
        }

        with(brandList.adapter as InfoAdapter) {
            setListAndNotify(
                listOf(
                    Info.Named(
                        name = context.getString(R.string.sys_screen_brand),
                        value = systemInfo.brand.brand
                    ),
                    Info.Named(
                        name = context.getString(R.string.sys_screen_manufacturer),
                        value = systemInfo.brand.manufacturer
                    )
                ).filter {
                    it.hasValue()
                }
            )
        }

        with(versionList.adapter as InfoAdapter) {
            setListAndNotify(
                listOf(
                    Info.Named(
                        name = context.getString(R.string.sys_version_codename),
                        value = systemInfo.version.codename
                    ),
                    Info.Named(
                        name = context.getString(R.string.sys_version_release),
                        value = systemInfo.version.release
                    ),
                    Info.Named(
                        name = context.getString(R.string.sys_version_security_path),
                        value = systemInfo.version.securityPatch
                    )
                ).filter {
                    it.hasValue()
                }
            )
        }
    }

    override fun hideProgress() {
        progress.visibility = View.GONE
        systemLayout.visibility = View.VISIBLE
    }
}
