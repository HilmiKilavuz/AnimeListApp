package com.kilavuzhilmi.anime_list_app.data.network.dto

import com.kilavuzhilmi.anime_list_app.domain.model.AnimeData
import com.kilavuzhilmi.anime_list_app.domain.model.Attributes
import com.kilavuzhilmi.anime_list_app.domain.model.CoverImage
import com.kilavuzhilmi.anime_list_app.domain.model.PosterImage
import com.kilavuzhilmi.anime_list_app.domain.model.Titles


data class TrendingAnimeListDto(
    val data: List<AnimeDataDto>
)

data class AnimeResponseDto(
    val data: AnimeDataDto
)

data class AnimeDataDto(
    val id: String,
    val type: String,
    val links: LinksDto,
    val attributes: AttributesDto,
    val relationships: RelationshipsDto
){
    fun toModel() : AnimeData = AnimeData(
        id,
        attributes.toModel()
    )


}

data class AttributesDto(
    val createdAt: String,
    val updatedAt: String,
    val slug: String?,
    val synopsis: String?,
    val coverImageTopOffset: Int,
    val titles: TitlesDto,
    val canonicalTitle: String?,
    val abbreviatedTitles: List<String>,
    val averageRating: String?,
    val ratingFrequencies: Map<String,String>,
    val userCount: Int?,
    val favoritesCount: Int?,
    val startDate: String?,
    val endDate: String?,
    val popularityRank: Int?,
    val ratingRank: Int?,
    val ageRating: String?,
    val ageRatingGuide: String?,
    val subtype: String,
    val status: String,
    val tba: String?,
    val posterImage: PosterImageDto,
    val coverImage: CoverImageDto,
    val episodeCount: Int?,
    val episodeLength: Int?,
    val youtubeVideoId: String?,
    val showType: String?,
    val nsfw: Boolean
){
    fun toModel(): Attributes =
        Attributes(
            createdAt = createdAt,
            updatedAt = updatedAt,
            slug = slug,
            synopsis = synopsis,
            titles = titles.toModel(),
            canonicalTitle = canonicalTitle,
            abbreviatedTitles = abbreviatedTitles,
            ageRating = ageRatingGuide,
            coverImage = coverImage.toModel(),
            subtype = subtype,
            posterImage = posterImage.toModel(),
            episodeCount = episodeCount,
            episodeLength = episodeLength,
            showType = showType,
            ageRatingGuide = ageRatingGuide,
            favoritesCount = favoritesCount,
            popularityRank = popularityRank,
            status = status,
            endDate = endDate,
            startDate = startDate,
            userCount = userCount,
            averageRating = averageRating,
            ratingRank = ratingRank
        )
}

data class TitlesDto(
    val en: String?,
    val en_jp: String?,
    val ja_jp: String?
){
    fun toModel(): Titles= Titles(en)
}

data class PosterImageDto(
    val tiny: String,
    val small: String,
    val medium: String,
    val large: String,
    val original: String,
    val meta: MetaDto?
){
    fun toModel(): PosterImage= PosterImage(tiny,small,medium,large,original)
}

data class MetaDto(
    val dimensions: DimensionsDto
)

data class DimensionsDto(
    val tiny: SizeDto,
    val small: SizeDto,
    val large: SizeDto
)

data class SizeDto(
    val width: Int? = null,
    val height: Int? = null
)

data class CoverImageDto(
    val tiny: String,
    val small: String,
    val large: String,
    val original: String,
    val meta: MetaDto?
){
    fun toModel(): CoverImage= CoverImage(tiny,small,large,original)
}

data class RelationshipsDto(
    val genres: RelationDto,
    val categories: RelationDto,
    val castings: RelationDto,
    val installments: RelationDto,
    val mappings: RelationDto,
    val reviews: RelationDto,
    val mediaRelationships: RelationDto,
    val episodes: RelationDto,
    val streamingLinks: RelationDto,
    val animeProductions: RelationDto,
    val animeCharacters: RelationDto,
    val animeStaff: RelationDto
)

data class RelationDto(
    val links: RelationLinksDto
)

data class LinksDto(
    val self: String
)

data class RelationLinksDto(
    val self: String,
    val related: String
)