package com.guardafilme.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by lucassantos on 06/08/17.
 */
data class Movie(
        var id: Int = -1,
        var title: String = "",
        var originalTitle: String = "",
        var year: String = "",
        var poster: String = "",
        var backdrop: String = ""
): Parcelable {
    companion object {
        @JvmField @Suppress("unused")
        val CREATOR: Parcelable.Creator<Movie> = object : Parcelable.Creator<Movie> {
            override fun createFromParcel(source: Parcel): Movie {
                return Movie(source)
            }

            override fun newArray(size: Int): Array<Movie?> {
                return arrayOfNulls(size)
            }
        }
    }

    private constructor(parcel: Parcel): this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(title)
        dest.writeString(originalTitle)
        dest.writeString(year)
        dest.writeString(poster)
        dest.writeString(backdrop)
    }

    override fun describeContents() = 0
}