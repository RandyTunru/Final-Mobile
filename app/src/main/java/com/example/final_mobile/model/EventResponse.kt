package com.example.final_mobile.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EventResponse(
//    @SerialName("_links") val links: Links,
    @SerialName("_embedded") val embedded: Embedded,
//    val page: Page
): Parcelable {
    // Implement the Parcelable methods
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        // Write data to the parcel
        // For example, if Embedded is Parcelable, you can write it like this:
        parcel.writeParcelable(embedded, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    // Companion object to create instances of your Parcelable class
    companion object CREATOR : Parcelable.Creator<EventResponse> {
        override fun createFromParcel(parcel: Parcel): EventResponse {
            // Read data from the parcel and create an instance of EventResponse
            // For example, if Embedded is Parcelable, you can read it like this:
            val embedded = parcel.readParcelable<Embedded>(Embedded::class.java.classLoader)
            return EventResponse(embedded!!)
        }

        override fun newArray(size: Int): Array<EventResponse?> {
            return arrayOfNulls(size)
        }
    }
}










