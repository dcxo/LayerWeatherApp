package dev.dcxo.weatherapp.models

import com.google.gson.annotations.SerializedName


data class Main (

  @SerializedName("temp"     ) var temp     : Double? = null,
  @SerializedName("pressure" ) var pressure : Int?    = null,
  @SerializedName("humidity" ) var humidity : Int?    = null,
  @SerializedName("temp_min" ) var tempMin  : Double? = null,
  @SerializedName("temp_max" ) var tempMax  : Double? = null

)