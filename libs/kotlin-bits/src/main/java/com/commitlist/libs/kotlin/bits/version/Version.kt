package com.commitlist.libs.kotlin.bits.version

data class Version(val major: Int, val minor: Int, val patch: Int = 0): Comparable<Version> {
    override fun compareTo(other: Version): Int {
        return if (major == other.major) {
            if (minor == other.minor) {
                if (patch == other.patch) {
                    0
                } else {
                    patch - other.patch
                }
            } else {
                minor - other.minor
            }
        } else {
            major - other.major
        }
    }

    companion object {
        fun fromString(version: String): Version {
             val split = version.split(".")
            require(split.size == 3)
            return Version(
                major = split[0].toInt(),
                minor = split[1].toInt(),
                patch = split[2].toInt()
            )
        }

        fun toString(version: Version): String {
            return "${version.major}.${version.minor}.${version.patch}"
        }
    }
}
