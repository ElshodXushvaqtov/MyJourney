package com.example.myjourney.data

data class Places(
    var name: String? = "",
    var img: String? = "",
    var description: String? = "",
    var category: String? = "",
    var moreImages: Array<String>? = arrayOf("")
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Places

        if (name != other.name) return false
        if (img != other.img) return false
        if (description != other.description) return false
        if (category != other.category) return false
        if (!moreImages.contentEquals(other.moreImages)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + img.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + category.hashCode()
        result = 31 * result + moreImages.contentHashCode()
        return result
    }

}
