package dev.lucasnlm.arch.soc.view

import android.content.Context
import android.opengl.GLSurfaceView
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.*
import com.google.android.flexbox.FlexboxLayout
import dev.lucasnlm.arch.common.model.NamedInfo
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
import dev.lucasnlm.arch.soc.Contract
import io.reactivex.Observable

class SocInfoView: Contract.View {

    private lateinit var vendorName: TextView
    private lateinit var modelName: TextView
    private lateinit var detailsList: RecyclerView
    private lateinit var clockList: RecyclerView
    private lateinit var gpuList: RecyclerView
    private lateinit var flagsList: FlexboxLayout
    private lateinit var glSurface: GLSurfaceView

    private val gpuInfoPublisher = PublishSubject.create<Map<Int, String>>()
    val gpuInfoObservable: Observable<Map<Int, String>> = gpuInfoPublisher.hide()

    override fun onViewCreated(view: View) {
        vendorName = view.findViewById(R.id.vendor_name)
        modelName = view.findViewById(R.id.model_name)

        detailsList = view.findViewById<RecyclerView>(R.id.details_list).apply {
            adapter = InfoAdapter()
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        clockList = view.findViewById<RecyclerView>(R.id.clock_list).apply {
            adapter = InfoAdapter()
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        gpuList = view.findViewById<RecyclerView>(R.id.gpu_list).apply {
            adapter = InfoAdapter()
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        flagsList = view.findViewById(R.id.flags_list)
        glSurface = view.findViewById<GLSurfaceView>(R.id.gl_surface).apply {
            setRenderer(
                object : GLSurfaceView.Renderer {
                    override fun onDrawFrame(gl: GL10?) {}

                    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {}

                    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
                        gl?.let {
                            // This code must run on UI / View part, otherwise GL won't return GL values.

                            gpuInfoPublisher.onNext(
                                mapOf(
                                    GL10.GL_RENDERER to it.glGetString(GL10.GL_RENDERER),
                                    GL10.GL_VENDOR to it.glGetString(GL10.GL_VENDOR),
                                    GL10.GL_VERSION to getVersionFromActivityManager(glSurface.context),
                                    GL10.GL_EXTENSIONS to it.glGetString(GL10.GL_EXTENSIONS)
                                )
                            )
                        }
                    }
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
                NamedInfo(
                    name = context.getString(R.string.soc_screen_abi),
                    value = cpuInfo.abi
                ),
                NamedInfo(
                    name = context.getString(R.string.soc_screen_model),
                    value = cpuInfo.model
                ),
                NamedInfo(
                    name = context.getString(R.string.soc_screen_cores),
                    value = cpuInfo.cpuCores.toString()
                ),
                NamedInfo(
                    name = context.getString(R.string.soc_screen_device),
                    value = cpuInfo.device
                ),
                NamedInfo(
                    name = context.getString(R.string.soc_screen_revision),
                    value = cpuInfo.revision
                ),
                NamedInfo(
                    name = context.getString(R.string.soc_screen_governor),
                    value = cpuInfo.governor
                ),
                NamedInfo(
                    name = context.getString(R.string.soc_screen_serial),
                    value = cpuInfo.serial?.toUpperCase()
                )
            ).filter {
                it.value != null
            }
            notifyDataSetChanged()
        }
    }

    override fun showClocks(maxClock: Int?, minClock: Int?, clocks: List<Int>) {
        val context = detailsList.context

        val clockItems = ArrayList<NamedInfo>(clocks.size + 2)
        minClock?.let {
            clockItems.add(
                NamedInfo(
                    name = context.getString(R.string.soc_screen_min_clock),
                    value = context.getString(R.string.soc_screen_value_mhz, it)
                )
            )
        }

        maxClock?.let {
            clockItems.add(
                NamedInfo(
                    name = context.getString(R.string.soc_screen_max_clock),
                    value = context.getString(R.string.soc_screen_value_mhz, it)
                )
            )
        }

        with(clockList.adapter as InfoAdapter) {
            list = clockItems.plus(
                    clocks.mapIndexed { index, cpuClock ->
                        NamedInfo(
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

    override fun showGpuInfo(gpuInfo: GpuInfo) {
        val context = detailsList.context

        with(gpuList.adapter as InfoAdapter) {
            list = listOf(
                NamedInfo(
                    name = context.getString(R.string.soc_gpu_vendor),
                    value = gpuInfo.vendor
                ),
                NamedInfo(
                    name = context.getString(R.string.soc_gpu_renderer),
                    value = gpuInfo.renderer
                ),
                NamedInfo(
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
}
