package com.example.final_mobile.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.serialization.Serializable

@Serializable
data class Embedded(
    val events: List<Events>
) : Parcelable {
    // Implement the Parcelable methods
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        // Write data to the parcel
        parcel.writeTypedList(events)
    }

    override fun describeContents(): Int {
        return 0
    }

    // Companion object to create instances of your Parcelable class
    companion object CREATOR : Parcelable.Creator<Embedded> {
        override fun createFromParcel(parcel: Parcel): Embedded {
            // Read data from the parcel and create an instance of Embedded
            val events = mutableListOf<Events>()
            parcel.readTypedList(events, Events.CREATOR)
            return Embedded(events)
        }

        override fun newArray(size: Int): Array<Embedded?> {
            return arrayOfNulls(size)
        }
    }
}
