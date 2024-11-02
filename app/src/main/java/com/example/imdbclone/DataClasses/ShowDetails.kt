package com.example.imdbclone.DataClasses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Show(
    val shows:List<ShowDetails>
):Parcelable
@Parcelize
data class ShowDetails(
    val itemType: String,
    val showType: String,
    val id: String,
    val title: String,
    val overview: String,
    val firstAirYear:String?,
    val releaseYear:String?,
    val genres:List<GenreDetails?>,
    val creators:List<String?>,
    val directors:List<String?>,
    val cast: List<String>,
    val rating: Int,
    val runtime:Int,
    val seasonCount: Int?,
    val episodeCount: Int?,
    val imageSet: VerticalPoster,
    val streamingOptions: StreamingOptionsInIndia?
):Parcelable

@Parcelize
data class GenreDetails(
    val id:String,
    val name: String
):Parcelable


@Parcelize
data class VerticalPoster(
    val verticalPoster: VerticalPosterDetails?,
    val horizontalPoster : HorizontalPosterDetails?,
    val horizontalBackdrop: HorizontalBackDropPosterDetails?
):Parcelable

@Parcelize
data class HorizontalBackDropPosterDetails(
    val w360:String,
    val w480:String,
    val w720:String,
):Parcelable

@Parcelize
data class VerticalPosterDetails(
    val w240:String,
    val w360:String,
    val w480:String,
    val w600:String,
    val w720:String

):Parcelable


@Parcelize
data class HorizontalPosterDetails(
    val w360:String,
    val w480:String,
    val w720:String,
    val w1080:String,
    val w1440:String


):Parcelable

@Parcelize
data class StreamingOptionsInIndia(
    val `in`:List<ServiceMetaData?>
):Parcelable


@Parcelize
data class ServiceMetaData(
    val service:Service,
    val type:String,
    val link:String,
    val videoLink:String,
    val quality:String
):Parcelable


@Parcelize
data class Service(
    val id:String,
    val name:String,
    val homePage:String,
    val imageSet:ServiceImageAsset?
):Parcelable

@Parcelize
data class ServiceImageAsset(
    val lightThemeImage:String,
    val darkThemeImage:String
):Parcelable


data class ShowResponse(val shows:List<ShowDetails>)

