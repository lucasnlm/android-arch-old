package dev.lucasnlm.arch.device

import dev.lucasnlm.arch.R
import dev.lucasnlm.arch.core.BaseFragment
import dev.lucasnlm.arch.device.presenter.DeviceInfoPresenter
import dev.lucasnlm.arch.device.view.DeviceInfoView

class DeviceInfoFragment: BaseFragment<DeviceInfoView, DeviceInfoPresenter>() {
    override val fragmentLayout: Int = R.layout.fragment_device_info
}
