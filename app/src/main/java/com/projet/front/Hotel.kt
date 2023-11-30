package com.ismin.android

import java.io.Serializable

data class Hotel(
    val _id: String,
    val X: Double = 0.0,
    val Y: Double = 0.0,
    val NAME: String = "",
    val ADDRESS: String = "",
    val PHONE: String = "",
    val NUMROOMS: Int = 0,
    val WEB_URL: String = "",
    val GIS_ID: String = "",
    val OBJECTID: Int = 0,
    val ZIPCODE: Int = 0): Serializable
