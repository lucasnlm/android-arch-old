#include "native_data_reader.h"

#include <fstream>
#include <streambuf>

using namespace arch;

std::string NativeDataReader::read() {
    std::ifstream ifs(source_path);
    return ifs ? std::string((std::istreambuf_iterator<char>(ifs)), std::istreambuf_iterator<char>()) : "";
}
