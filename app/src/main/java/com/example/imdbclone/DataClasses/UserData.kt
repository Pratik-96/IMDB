package com.example.imdbclone.DataClasses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

data class UserData(
    val savedShow:SavedShowDetails
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