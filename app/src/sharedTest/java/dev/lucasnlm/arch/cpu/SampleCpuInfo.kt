package dev.lucasnlm.arch.cpu

import dev.lucasnlm.arch.soc.model.CpuInfo

internal object SampleCpuInfo {
    val cpuInfo1 = CpuInfo(
        model = "AArch64 Processor rev 12 (aarch64)",
        modelName = "Qualcomm Technologies, Inc SDM845",
        vendor = "0x51",
        revision = "12",
        architecture = "8",
        cpuCores = 4,
        clocks = listOf(1000, 1500, 500, 0),
        stepping = null,
        bogoMips = "38.40",
        bigLittle = 0,
        abi = MockCpuInfo.mockAbi,
        serial = null,
        device = null,
        governor = MockCpuInfo.mockGovernor,
        flags = "fp asimd evtstrm aes pmull sha1 sha2 crc32 atomics fphp asimdhp".split(" ")
    )

    val cpuInfo2 = CpuInfo(
        model = "1",
        modelName = "AMD Ryzen 7 1700 Eight-Core Processor",
        vendor = "AuthenticAMD",
        revision = null,
        architecture = null,
        cpuCores = 4,
        clocks = listOf(3798, 3798, 3798, 3798),
        stepping = "1",
        bogoMips = "7595.88",
        bigLittle = 0,
        abi = MockCpuInfo.mockAbi,
        serial = null,
        device = null,
        governor = MockCpuInfo.mockGovernor,
        flags = "fpu vme de pse tsc msr pae mce cx8 apic sep mtrr pge mca cmov pat pse36 clflush mmx fxsr sse".split(" ")
    )

    val cpuInfo3 = CpuInfo(
        model = "70",
        modelName = "Intel(R) Core(TM) i7-4770HQ CPU @ 2.20GHz",
        vendor = "GenuineIntel",
        revision = null,
        architecture = null,
        cpuCores = 4,
        clocks = listOf(2195, 2195, 2195, 2195),
        stepping = "1",
        bogoMips = "4389.87",
        bigLittle = 0,
        abi = MockCpuInfo.mockAbi,
        serial = null,
        device = null,
        governor = MockCpuInfo.mockGovernor,
        flags = "fpu vme de pse tsc msr pae mce cx8 apic sep mtrr pge mca cmov pat pse36 clflush dts acpi mmx".split(" ")
    )
}
