package com.practice.edgeecomviewmodel.data.products

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Rating(
    val count: Int?,
    val rate: Double?
):Parcelable