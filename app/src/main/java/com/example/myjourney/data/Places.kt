package com.example.myjourney.data

data class Places(var name: String, var img: Int, var description: String, var category:String, var moreImages:IntArray) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Places

        if (name != other.name) return false
        if (img != other.img) return false
        if (description != other.description) return false
        if (category != other.category) return false
        return moreImages.contentEquals(other.moreImages)
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + img
        result = 31 * result + description.hashCode()
        result = 31 * result + category.hashCode()
        result = 31 * result + moreImages.contentHashCode()
        return result
    }
}
