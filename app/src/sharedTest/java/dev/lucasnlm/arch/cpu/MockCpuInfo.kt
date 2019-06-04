package dev.lucasnlm.arch.cpu

object MockCpuInfo {
    const val mockCoreCount = 4
    const val mockAbi = "testABI"
    const val mockGovernor = "mock_governor"

    val mockCpuInfo1 = Pair(RawPropCpuInfo.propCpuInfo1, SampleCpuInfo.cpuInfo1)
    val mockCpuInfo2 = Pair(RawPropCpuInfo.propCpuInfo2, SampleCpuInfo.cpuInfo2)
    val mockCpuInfo3 = Pair(RawPropCpuInfo.propCpuInfo3, SampleCpuInfo.cpuInfo3)
}
