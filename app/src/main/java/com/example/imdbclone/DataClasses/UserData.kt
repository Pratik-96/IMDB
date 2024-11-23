package com.example.imdbclone.DataClasses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

data class GenreData(
    val genre1:String?,
    val genre2:String?,
    val genre3:String?,
    val genre4:String?,
    val genre5:String?,
    val genre6:String?,
    val genre7:String?,
    val genre8:String?,
    val genre9:String?,
    val genre10:String?,
    val genre11:String?,
    val genre12:String?,
)

data class SavedShowDetails(
    val id: String?=null,
    val title:String?=null,
    val showType: String?=null,
    val verticalImage:String?=null,
    val horizontalImage:String?=null,
    val serviceMetaData: List<ServiceMetaData?>?=null
    ){
    constructor():this(null,null,null,null,null,null)
}

@Serializable
@Parcelize
data class MetaData(
    val service:Service? = null,
    val type:String?=null,
    val link:String?=null,
    val videoLink:String?=null,
    val quality:String?=null
): Parcelable{

        constructor():this(null,null,null,null,null)

}