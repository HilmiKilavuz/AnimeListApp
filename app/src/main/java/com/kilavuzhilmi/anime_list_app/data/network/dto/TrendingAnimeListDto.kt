package com.kilavuzhilmi.anime_list_app.data.network.dto

import com.kilavuzhilmi.anime_list_app.domain.model.AnimeData
import com.kilavuzhilmi.anime_list_app.domain.model.Attributes
import com.kilavuzhilmi.anime_list_app.domain.model.CoverImage
import com.kilavuzhilmi.anime_list_app.domain.model.PosterImage
import com.kilavuzhilmi.anime_list_app.domain.model.Titles

/**
 * DTO (Data Transfer Object) Sınıfları
 * 
 * Bu dosya API'den gelen JSON verilerini temsil eden DTO sınıflarını içerir.
 * DTO'lar, API'den gelen veri yapısını uygulama içinde kullanılan domain model yapısına
 * dönüştürmek için bir ara katman görevi görür.
 * 
 * Her DTO sınıfı API'den dönen JSON verisinin belirli bir bölümünü temsil eder 
 * ve bunları uygulamanın kendi domain modellerine dönüştürmek için toModel() metodları içerir.
 */

/**
 * Trend olan anime listesi API yanıtını temsil eden DTO sınıfı.
 * 
 * @property data API'den gelen anime verilerinin listesi
 */
data class TrendingAnimeListDto(
    val data: List<AnimeDataDto>
)

/**
 * Tek bir anime detayı için API yanıtını temsil eden DTO sınıfı.
 * 
 * @property data API'den gelen tek bir anime verisi
 */
data class AnimeResponseDto(
    val data: AnimeDataDto
)

/**
 * Bir anime veri nesnesini temsil eden DTO sınıfı.
 * 
 * @property id Anime'nin benzersiz ID'si
 * @property type Veri tipini belirten değer (genellikle "anime")
 * @property links Bu anime nesnesine ait linkler
 * @property attributes Anime'nin başlık, açıklama, görseller gibi özellikleri
 * @property relationships Anime'nin diğer veri tipleriyle ilişkileri (karakterler, türler, vb.)
 */
data class AnimeDataDto(
    val id: String,
    val type: String,
    val links: LinksDto,
    val attributes: AttributesDto,
    val relationships: RelationshipsDto
){
    /**
     * DTO'yu domain modeline dönüştüren metod.
     * Bu metod, API yanıtında gelen veriyi uygulama içinde kullanılacak modele dönüştürür.
     * 
     * @return Dönüştürülmüş AnimeData domain modeli
     */
    fun toModel() : AnimeData = AnimeData(
        id,
        attributes.toModel()
    )


}

/**
 * Anime'nin özelliklerini içeren DTO sınıfı.
 * 
 * @property createdAt Anime'nin oluşturulma tarihi
 * @property updatedAt Anime'nin son güncellenme tarihi
 * @property slug URL-dostu tanımlayıcı
 * @property synopsis Anime'nin özeti/konusu
 * @property coverImageTopOffset Kapak görseli için üst kenar boşluğu
 * @property titles Anime'nin farklı dillerdeki başlıkları
 * @property canonicalTitle Anime'nin resmi başlığı
 * @property abbreviatedTitles Anime'nin kısaltılmış başlıkları
 * @property averageRating Ortalama puan
 * @property ratingFrequencies Farklı puanların dağılımı
 * @property userCount İzleyen kullanıcı sayısı
 * @property favoritesCount Favorilere ekleyen kullanıcı sayısı
 * @property startDate Yayın başlangıç tarihi
 * @property endDate Yayın bitiş tarihi
 * @property popularityRank Popülerlik sıralaması
 * @property ratingRank Puan sıralaması
 * @property ageRating Yaş sınırı (PG, R, vb.)
 * @property ageRatingGuide Yaş sınırı açıklaması
 * @property subtype Anime alt türü (TV, film, OVA, vb.)
 * @property status Durumu (devam ediyor, tamamlandı, vb.)
 * @property tba Duyurulacak
 * @property posterImage Poster görseli
 * @property coverImage Kapak görseli
 * @property episodeCount Bölüm sayısı
 * @property episodeLength Bölüm uzunluğu (dakika)
 * @property youtubeVideoId Tanıtım videosu YouTube ID
 * @property showType Gösteri tipi
 * @property nsfw +18 içerik olup olmadığı
 */
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
    /**
     * AttributesDto'yu domain Attributes modeline dönüştüren metod.
     * 
     * @return Dönüştürülmüş Attributes domain modeli
     */
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

/**
 * Anime başlıklarını farklı dillerde içeren DTO sınıfı.
 * 
 * @property en İngilizce başlık
 * @property en_jp İngilizce transliterasyonu (Romaji)
 * @property ja_jp Japonca başlık
 */
data class TitlesDto(
    val en: String?,
    val en_jp: String?,
    val ja_jp: String?
){
    /**
     * TitlesDto'yu domain Titles modeline dönüştüren metod.
     * 
     * @return Dönüştürülmüş Titles domain modeli
     */
    fun toModel(): Titles= Titles(en)
}

/**
 * Anime poster görselini içeren DTO sınıfı.
 * 
 * @property tiny Çok küçük boyutlu görsel URL
 * @property small Küçük boyutlu görsel URL
 * @property medium Orta boyutlu görsel URL
 * @property large Büyük boyutlu görsel URL
 * @property original Orijinal boyutlu görsel URL
 * @property meta Görsel meta verileri
 */
data class PosterImageDto(
    val tiny: String,
    val small: String,
    val medium: String,
    val large: String,
    val original: String,
    val meta: MetaDto?
){
    /**
     * PosterImageDto'yu domain PosterImage modeline dönüştüren metod.
     * 
     * @return Dönüştürülmüş PosterImage domain modeli
     */
    fun toModel(): PosterImage= PosterImage(tiny,small,medium,large,original)
}

/**
 * Görsel meta verilerini içeren DTO sınıfı.
 * 
 * @property dimensions Görsel boyutları
 */
data class MetaDto(
    val dimensions: DimensionsDto
)

/**
 * Görsel boyutlarını içeren DTO sınıfı.
 * 
 * @property tiny Çok küçük boyut bilgileri
 * @property small Küçük boyut bilgileri
 * @property large Büyük boyut bilgileri
 */
data class DimensionsDto(
    val tiny: SizeDto,
    val small: SizeDto,
    val large: SizeDto
)

/**
 * Görsel boyut bilgilerini içeren DTO sınıfı.
 * 
 * @property width Genişlik (piksel)
 * @property height Yükseklik (piksel)
 */
data class SizeDto(
    val width: Int? = null,
    val height: Int? = null
)

/**
 * Anime kapak görselini içeren DTO sınıfı.
 * 
 * @property tiny Çok küçük boyutlu görsel URL
 * @property small Küçük boyutlu görsel URL
 * @property large Büyük boyutlu görsel URL
 * @property original Orijinal boyutlu görsel URL
 * @property meta Görsel meta verileri
 */
data class CoverImageDto(
    val tiny: String,
    val small: String,
    val large: String,
    val original: String,
    val meta: MetaDto?
){
    /**
     * CoverImageDto'yu domain CoverImage modeline dönüştüren metod.
     * 
     * @return Dönüştürülmüş CoverImage domain modeli
     */
    fun toModel(): CoverImage= CoverImage(tiny,small,large,original)
}

/**
 * Anime'nin ilişkili verilerini içeren DTO sınıfı.
 * 
 * @property genres Anime türleri
 * @property categories Anime kategorileri
 * @property castings Anime seslendirmeleri
 * @property installments Anime serisi bilgileri
 * @property mappings Harici eşleştirmeler
 * @property reviews Kullanıcı incelemeleri
 * @property mediaRelationships Medya ilişkileri
 * @property episodes Bölümler
 * @property streamingLinks Yayın linkleri
 * @property animeProductions Anime yapım şirketleri
 * @property animeCharacters Anime karakterleri
 * @property animeStaff Anime ekibi
 */
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

/**
 * İlişki bilgilerini içeren DTO sınıfı.
 * 
 * @property links İlişki linkleri
 */
data class RelationDto(
    val links: RelationLinksDto
)

/**
 * Kaynak linkleri içeren DTO sınıfı.
 * 
 * @property self Kendisine referans veren link
 */
data class LinksDto(
    val self: String
)

/**
 * İlişki linklerini içeren DTO sınıfı.
 * 
 * @property self Kendisine referans veren link
 * @property related İlişkili veriye ait link
 */
data class RelationLinksDto(
    val self: String,
    val related: String
)