package com.kirkbushman.araw.models

import android.os.Parcelable
import com.kirkbushman.araw.http.EnvelopedCommentData
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class MoreChildrenResponse(

    @Json(name = "json")
    val json: MoreChildrenResponseJSON

) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class MoreChildrenResponseJSON(

    @Json(name = "data")
    val data: MoreChildrenResponseThings

) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class MoreChildrenResponseThings(

    @Json(name = "things")
    val things: List<EnvelopedCommentData>

) : Parcelable
