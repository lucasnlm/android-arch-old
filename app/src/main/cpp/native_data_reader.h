#ifndef ARCH_NATIVE_DATA_READER_HPP
#define ARCH_NATIVE_DATA_READER_HPP

#include <jni.h>
#include <string>
#include <filesystem>

namespace arch {
    /**
     * Do the same of InternalDataReader but using native C++ code to try
     * avoid apps that hide the source info (e.g, rootcloak).
     */
    class NativeDataReader {
    public:
        NativeDataReader(const char* input_source) : source_path(input_source) {}

        std::string read();

    private:
        std::filesystem::path source_path;
    };
}

#endif //ARCH_NATIVE_DATA_READER_HPP
