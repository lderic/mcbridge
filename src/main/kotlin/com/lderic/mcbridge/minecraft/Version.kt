package com.lderic.mcbridge.minecraft

class Version(private val version: String) {
    private val v: Triple<Int, Int, Int>

    init {
        version.split(".").also {
            v = Triple(it[0].toInt(), it[1].toInt(), it[2].toInt())
        }
    }

    operator fun compareTo(other: Version): Int {
        if (this.version == other.version) return 0
        if (this.v.first > other.v.first) return 1
        if (this.v.first < other.v.first) return -1
        if (this.v.second > other.v.second) return 1
        if (this.v.second < other.v.second) return -1
        if (this.v.third > other.v.third) return 1
        if (this.v.third < other.v.third) return -1
        return 0
    }

    override fun toString(): String {
        return version
    }
}