package com.example.imdbclone.DataClasses


data class ShowDetails(
    val itemType: String,
    val showType: String,
    val id: String,
    val title: String,
    val overview: String,
    val genres:List<GenreDetails?>,
    val cast: List<String>,
    val rating: Int,
    val seasonCount: Int,
    val episodeCount: Int,
    val imageSet: VerticalPoster,
    val streamingOptions: StreamingOptionsInIndia?
)
data class GenreDetails(
    val id:String,
    val name: String
)

data class VerticalPoster(
    val verticalPoster: VerticalPosterDetails?
)

data class VerticalPosterDetails(
    val w240:String,
    val w360:String,
    val w480:String,
    val w600:String,
    val w720:String

)


data class StreamingOptionsInIndia(
    val `in`:List<ServiceMetaData?>
)

data class ServiceMetaData(
    val service:Service,
    val type:String,
    val link:String,
    val videoLink:String,
    val quality:String
)

data class Service(
    val id:String,
    val name:String,
    val homePage:String,
    val imageSet:ServiceImageAsset?
)

data class ServiceImageAsset(
    val lightThemeImage:String,
    val darkThemeImage:String
)


data class ShowResponse(val shows:List<ShowDetails>)

