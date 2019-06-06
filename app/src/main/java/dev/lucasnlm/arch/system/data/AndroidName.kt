package dev.lucasnlm.arch.system.data

object AndroidName {

    internal fun fromApi(api: Int): String? = when (api) {
        2 -> "Petit Four"
        3 -> "Cupcake"
        4 -> "Donut"
        5,6,7 -> "Eclair"
        8 -> "Froyo"
        9, 10 -> "Gingerbread"
        11, 12, 13 -> "Honeycomb"
        14, 15 -> "Ice Cream Sandwich"
        16, 17, 18 -> "Jelly Bean"
        19, 20 -> "KitKat"
        21, 22 -> "Lollipop"
        23 -> "Marshmallow"
        24, 25 -> "Nougat"
        26, 27 -> "Oreo"
        28 -> "Pie"
        29 -> "Q"
        else -> null
    }
}
