package dev.lucasnlm.arch.soc.repository

object MockPropCpuInfo {

    val propCpuInfo1 = """

        Processor       : AArch64 Processor rev 12 (aarch64)
        processor       : 0
        BogoMIPS        : 38.40
        Features        : fp asimd evtstrm aes pmull sha1 sha2 crc32 atomics fphp asimdhp
        CPU implementer : 0x51
        CPU architecture: 8
        CPU variant     : 0x7
        CPU part        : 0x803
        CPU revision    : 12

        processor       : 1
        BogoMIPS        : 38.40
        Features        : fp asimd evtstrm aes pmull sha1 sha2 crc32 atomics fphp asimdhp
        CPU implementer : 0x51
        CPU architecture: 8
        CPU variant     : 0x7
        CPU part        : 0x803
        CPU revision    : 12

        processor       : 2
        BogoMIPS        : 38.40
        Features        : fp asimd evtstrm aes pmull sha1 sha2 crc32 atomics fphp asimdhp
        CPU implementer : 0x51
        CPU architecture: 8
        CPU variant     : 0x7
        CPU part        : 0x803
        CPU revision    : 12

        processor       : 3
        BogoMIPS        : 38.40
        Features        : fp asimd evtstrm aes pmull sha1 sha2 crc32 atomics fphp asimdhp
        CPU implementer : 0x51
        CPU architecture: 8
        CPU variant     : 0x7
        CPU part        : 0x803
        CPU revision    : 12

        Hardware        : Qualcomm Technologies, Inc SDM845

    """.trimIndent()

    val propCpuInfo2 = """

        processor       : 0
        vendor_id       : AuthenticAMD
        cpu family      : 23
        model           : 1
        model name      : AMD Ryzen 7 1700 Eight-Core Processor
        stepping        : 1
        microcode       : 0xffffffff
        cpu MHz         : 3797.940
        cache size      : 512 KB
        physical id     : 0
        siblings        : 1
        core id         : 0
        cpu cores       : 1
        apicid          : 0
        initial apicid  : 0
        fpu             : yes
        fpu_exception   : yes
        cpuid level     : 13
        wp              : yes
        flags           : fpu vme de pse tsc msr pae mce cx8 apic sep mtrr pge mca cmov pat pse36 clflush mmx fxsr sse
        bugs            : fxsave_leak sysret_ss_attrs spectre_v1 spectre_v2
        bogomips        : 7595.88
        TLB size        : 2560 4K pages
        clflush size    : 64
        cache_alignment : 64
        address sizes   : 44 bits physical, 48 bits virtual
        power management:

        processor       : 1
        vendor_id       : AuthenticAMD
        cpu family      : 23
        model           : 1
        model name      : AMD Ryzen 7 1700 Eight-Core Processor
        stepping        : 1
        microcode       : 0xffffffff
        cpu MHz         : 3797.940
        cache size      : 512 KB
        physical id     : 1
        siblings        : 1
        core id         : 0
        cpu cores       : 1
        apicid          : 1
        initial apicid  : 1
        fpu             : yes
        fpu_exception   : yes
        cpuid level     : 13
        wp              : yes
        flags           : fpu vme de pse tsc msr pae mce cx8 apic sep mtrr pge mca cmov pat pse36 clflush mmx fxsr sse
        bugs            : fxsave_leak sysret_ss_attrs spectre_v1 spectre_v2
        bogomips        : 7895.27
        TLB size        : 2560 4K pages
        clflush size    : 64
        cache_alignment : 64
        address sizes   : 44 bits physical, 48 bits virtual
        power management:

        processor       : 2
        vendor_id       : AuthenticAMD
        cpu family      : 23
        model           : 1
        model name      : AMD Ryzen 7 1700 Eight-Core Processor
        stepping        : 1
        microcode       : 0xffffffff
        cpu MHz         : 3797.940
        cache size      : 512 KB
        physical id     : 2
        siblings        : 1
        core id         : 0
        cpu cores       : 1
        apicid          : 2
        initial apicid  : 2
        fpu             : yes
        fpu_exception   : yes
        cpuid level     : 13
        wp              : yes
        flags           : fpu vme de pse tsc msr pae mce cx8 apic sep mtrr pge mca cmov pat pse36 clflush mmx fxsr sse
        bugs            : fxsave_leak sysret_ss_attrs spectre_v1 spectre_v2
        bogomips        : 7482.56
        TLB size        : 2560 4K pages
        clflush size    : 64
        cache_alignment : 64
        address sizes   : 44 bits physical, 48 bits virtual
        power management:

        processor       : 3
        vendor_id       : AuthenticAMD
        cpu family      : 23
        model           : 1
        model name      : AMD Ryzen 7 1700 Eight-Core Processor
        stepping        : 1
        microcode       : 0xffffffff
        cpu MHz         : 3797.940
        cache size      : 512 KB
        physical id     : 3
        siblings        : 1
        core id         : 0
        cpu cores       : 1
        apicid          : 3
        initial apicid  : 3
        fpu             : yes
        fpu_exception   : yes
        cpuid level     : 13
        wp              : yes
        flags           : fpu vme de pse tsc msr pae mce cx8 apic sep mtrr pge mca cmov pat pse36 clflush mmx fxsr sse
        bugs            : fxsave_leak sysret_ss_attrs spectre_v1 spectre_v2
        bogomips        : 7989.14
        TLB size        : 2560 4K pages
        clflush size    : 64
        cache_alignment : 64
        address sizes   : 44 bits physical, 48 bits virtual
        power management:

        """.trimIndent()

    val propCpuInfo3 = """

        processor       : 0
        vendor_id       : GenuineIntel
        cpu family      : 6
        model           : 70
        model name      : Intel(R) Core(TM) i7-4770HQ CPU @ 2.20GHz
        stepping        : 1
        cpu MHz         : 2194.935
        cache size      : 6144 KB
        physical id     : 0
        siblings        : 4
        core id         : 0
        cpu cores       : 2
        apicid          : 0
        initial apicid  : 0
        fpu             : yes
        fpu_exception   : yes
        cpuid level     : 13
        wp              : yes
        flags           : fpu vme de pse tsc msr pae mce cx8 apic sep mtrr pge mca cmov pat pse36 clflush dts acpi mmx
        bugs            : cpu_meltdown spectre_v1 spectre_v2
        bogomips        : 4389.87
        clflush size    : 64
        cache_alignment : 64
        address sizes   : 39 bits physical, 48 bits virtual
        power management:

        processor       : 1
        vendor_id       : GenuineIntel
        cpu family      : 6
        model           : 70
        model name      : Intel(R) Core(TM) i7-4770HQ CPU @ 2.20GHz
        stepping        : 1
        cpu MHz         : 2194.935
        cache size      : 6144 KB
        physical id     : 0
        siblings        : 4
        core id         : 0
        cpu cores       : 2
        apicid          : 1
        initial apicid  : 1
        fpu             : yes
        fpu_exception   : yes
        cpuid level     : 13
        wp              : yes
        flags           : fpu vme de pse tsc msr pae mce cx8 apic sep mtrr pge mca cmov pat pse36 clflush dts acpi mmx
        bugs            : cpu_meltdown spectre_v1 spectre_v2
        bogomips        : 4389.87
        clflush size    : 64
        cache_alignment : 64
        address sizes   : 39 bits physical, 48 bits virtual
        power management:

        processor       : 2
        vendor_id       : GenuineIntel
        cpu family      : 6
        model           : 70
        model name      : Intel(R) Core(TM) i7-4770HQ CPU @ 2.20GHz
        stepping        : 1
        cpu MHz         : 2194.935
        cache size      : 6144 KB
        physical id     : 0
        siblings        : 4
        core id         : 1
        cpu cores       : 2
        apicid          : 2
        initial apicid  : 2
        fpu             : yes
        fpu_exception   : yes
        cpuid level     : 13
        wp              : yes
        flags           : fpu vme de pse tsc msr pae mce cx8 apic sep mtrr pge mca cmov pat pse36 clflush dts acpi mmx
        bugs            : cpu_meltdown spectre_v1 spectre_v2
        bogomips        : 4389.87
        clflush size    : 64
        cache_alignment : 64
        address sizes   : 39 bits physical, 48 bits virtual
        power management:

        processor       : 3
        vendor_id       : GenuineIntel
        cpu family      : 6
        model           : 70
        model name      : Intel(R) Core(TM) i7-4770HQ CPU @ 2.20GHz
        stepping        : 1
        cpu MHz         : 2194.935
        cache size      : 6144 KB
        physical id     : 0
        siblings        : 4
        core id         : 1
        cpu cores       : 2
        apicid          : 3
        initial apicid  : 3
        fpu             : yes
        fpu_exception   : yes
        cpuid level     : 13
        wp              : yes
        flags           : fpu vme de pse tsc msr pae mce cx8 apic sep mtrr pge mca cmov pat pse36 clflush dts acpi mmx
        bugs            : cpu_meltdown spectre_v1 spectre_v2
        bogomips        : 4389.87
        clflush size    : 64
        cache_alignment : 64
        address sizes   : 39 bits physical, 48 bits virtual
        power management:

        """.trimIndent()

}
