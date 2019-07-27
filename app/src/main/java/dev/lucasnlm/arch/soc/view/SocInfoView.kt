package dev.lucasnlm.arch.soc.view

import android.content.Context
import android.opengl.GLSurfaceView
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.*
import com.google.android.flexbox.FlexboxLayout
import dev.lucasnlm.arch.common.view.InfoAdapter
import dev.lucasnlm.arch.soc.model.CpuInfo
import android.widget.LinearLayout
import dev.lucasnlm.arch.R
import dev.lucasnlm.arch.soc.model.GpuInfo
import io.reactivex.subjects.PublishSubject
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10
import android.content.pm.ConfigurationInfo
import android.app.ActivityManager
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isEmpty
import dev.lucasnlm.arch.common.model.Info
import dev.lucasnlm.arch.common.view.setupList
import dev.lucasnlm.arch.soc.Contracts
import io.reactivex.Observable

class SocInfoView: Contracts.View {

    private lateinit var progress: ProgressBar
    private lateinit var infoLayout: ViewGroup
    private lateinit var vendorName: TextView
    private lateinit var modelName: TextView
    private lateinit var detailsList: RecyclerView
    private lateinit var clockList: RecyclerView
    private lateinit var gpuList: RecyclerView
    private lateinit var flagsList: FlexboxLayout
    private lateinit var glSurface: GLSurfaceView

    private val gpuInfoPublisher = PublishSubject.create<GpuInfo>()
    val gpuInfoObservable: Observable<GpuInfo> = gpuInfoPublisher.hide()

    override fun onViewCreated(view: View) {
        vendorName = view.findViewById(R.id.vendor_name)
        modelName = view.findViewById(R.id.model_name)
        progress = view.findViewById(R.id.progress)
        infoLayout = view.findViewById(R.id.info_layout)

        detailsList = view.setupList(R.id.details_list)
        clockList = view.setupList(R.id.clock_list)
        gpuList = view.setupList(R.id.gpu_list)

        flagsList = view.findViewById(R.id.flags_list)
        glSurface = view.findViewById<GLSurfaceView>(R.id.gl_surface).apply {
            setRenderer(
                object : GLSurfaceView.Renderer {
                    override fun onDrawFrame(gl: GL10?) {
                        gl?.let {
                            // This code must run on UI / View part, otherwise GL won't return GL values.
                            val gpuInfo = GpuInfo(
                                it.glGetString(GL10.GL_RENDERER),
                                it.glGetString(GL10.GL_VENDOR),
                                getVersionFromActivityManager(glSurface.context),
                                it.glGetString(GL10.GL_EXTENSIONS).split(" ").filter { ext -> ext.isNotEmpty() }
                            )

                            gpuInfoPublisher.onNext(gpuInfo)
                        }
                    }

                    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {}

                    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) { }
                }
            )
        }
    }

    override fun showInfo(cpuInfo: CpuInfo) {
        val context = detailsList.context

        vendorName.text = cpuInfo.getReadableVendorName(context)
        modelName.text = cpuInfo.modelName

        with (detailsList.adapter as InfoAdapter) {
            list = listOf(
                Info.Named(
                    name = context.getString(R.string.soc_screen_abi),
                    value = cpuInfo.abi
                ),
                Info.Named(
                    name = context.getString(R.string.soc_screen_model),
                    value = cpuInfo.model
                ),
                Info.Named(
                    name = context.getString(R.string.soc_screen_cores),
                    value = cpuInfo.cpuCores.toString()
                ),
                Info.Named(
                    name = context.getString(R.string.soc_screen_device),
                    value = cpuInfo.device
                ),
                Info.Named(
                    name = context.getString(R.string.soc_screen_revision),
                    value = cpuInfo.revision
                ),
                Info.Named(
                    name = context.getString(R.string.soc_screen_governor),
                    value = cpuInfo.governor
                ),
                Info.Named(
                    name = context.getString(R.string.soc_screen_serial),
                    value = cpuInfo.serial?.toUpperCase()
                )
            ).filter {
                it.hasValue()
            }
            notifyDataSetChanged()
        }
    }

    override fun showClocks(maxClock: Int?, minClock: Int?, clocks: List<Int>) {
        val context = detailsList.context

        val clockItems = mutableListOf<Info.Named>()
        minClock?.let {
            clockItems.add(
                Info.Named(
                    name = context.getString(R.string.soc_screen_min_clock),
                    value = context.getString(R.string.soc_screen_value_mhz, it)
                )
            )
        }

        maxClock?.let {
            clockItems.add(
                Info.Named(
                    name = context.getString(R.string.soc_screen_max_clock),
                    value = context.getString(R.string.soc_screen_value_mhz, it)
                )
            )
        }

        with(clockList.adapter as InfoAdapter) {
            list = clockItems.plus(
                    clocks.mapIndexed { index, cpuClock ->
                        Info.Named(
                            name = context.getString(R.string.soc_screen_core_n, index),
                            value = if (cpuClock == 0) {
                                context.getString(R.string.soc_screen_inactive_core)
                            } else {
                                context.getString(R.string.soc_screen_value_mhz, cpuClock)
                            }
                        )
                    }
                )
            notifyDataSetChanged()
        }
    }

    override fun showFlags(flags: List<String>) {
        if (flagsList.isEmpty()) {
            flags.sortedBy { it }.forEach {
                flagsList.addView(
                    TextView(flagsList.context).apply {
                        text = it.toUpperCase()
                        setBackgroundResource(R.drawable.background_round_tag)
                        layoutParams = LinearLayout.LayoutParams(
                            FlexboxLayout.LayoutParams.WRAP_CONTENT,
                            FlexboxLayout.LayoutParams.WRAP_CONTENT
                        )
                    }
                )
            }
        }
    }

    override fun showGpuInfo(gpuInfo: GpuInfo) {
        val context = detailsList.context

        with(gpuList.adapter as InfoAdapter) {
            list = listOf(
                Info.Named(
                    name = context.getString(R.string.soc_gpu_vendor),
                    value = gpuInfo.vendor
                ),
                Info.Named(
                    name = context.getString(R.string.soc_gpu_renderer),
                    value = gpuInfo.renderer
                ),
                Info.Named(
                    name =context.getString(R.string.soc_gpu_version),
                    value = gpuInfo.version
                )
            )

            notifyDataSetChanged()
        }

        glSurface.visibility = View.GONE
    }

    private fun CpuInfo.getReadableVendorName(context: Context): String = when (vendor) {
        "AuthenticAMD" -> "Advanced Micro Devices"
        "GenuineIntel" -> "Intel"
        "Microsoft Hv" -> "Microsoft Emulator"
        "VMwareVMware" -> "VMware"
        else -> parseCpuImplementer(context, vendor)
    }

    private fun parseCpuImplementer(context: Context, cpuImplementer: String?): String = when(cpuImplementer) {
        "0x51" -> "Qualcomm Technologies, Inc"
        "0x41" -> "ARM Ltd."
        "0x50" -> "Applied Micro Circuits Corporation (APM)"
        "0x42", "0x43" -> "Cavium"
        "0x4e" -> "NVIDIA"
        "0x53" -> "Samsung"
        "0x67" -> "Apple"
        "0x56" -> "Marvell"
        "0x69" -> "Intel"
        else -> context.getString(R.string.unknown)
    }

    private fun getVersionFromActivityManager(context: Context): String {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val configInfo = activityManager.deviceConfigurationInfo
        return if (configInfo.reqGlEsVersion != ConfigurationInfo.GL_ES_VERSION_UNDEFINED) {
            "OpenGL ES ${configInfo.glEsVersion}"
        } else {
            "OpenGL ES 1.0"
        }
    }

    override fun hideProgress() {
        progress.visibility = View.GONE
        infoLayout.visibility = View.VISIBLE
    }
}
