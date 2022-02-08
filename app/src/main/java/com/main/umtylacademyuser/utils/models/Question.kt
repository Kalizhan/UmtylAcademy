package com.main.umtylacademyuser.utils.models

import android.os.Parcel
import android.os.Parcelable

data class Question(
    var quest1: String? = "",
    var quest2: String? = "",
    var quest3: String? = "",
    var quest4: String? = "",
    var questName: String? = "",
    var answ: String? = "",
    var chosenAnsw: String? = ""
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }
//
//    override fun describeContents(): Int {
//
//    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeString(quest1)
        p0?.writeString(quest2)
        p0?.writeString(quest3)
        p0?.writeString(quest4)
        p0?.writeString(questName)
        p0?.writeString(answ)
        p0?.writeString(chosenAnsw)
    }

    companion object CREATOR : Parcelable.Creator<Question> {
        override fun createFromParcel(parcel: Parcel): Question {
            return Question(parcel)
        }

        override fun newArray(size: Int): Array<Question?> {
            return arrayOfNulls(size)
        }
    }
}
