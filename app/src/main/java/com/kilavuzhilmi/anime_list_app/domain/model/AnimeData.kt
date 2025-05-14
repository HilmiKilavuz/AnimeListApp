package com.kilavuzhilmi.anime_list_app.domain.model

/**
 * Domain Model Sınıfları
 * 
 * Bu dosya, uygulamanın ana iş mantığında kullanılan domain model sınıflarını içerir.
 * Domain modeller, API'den gelen verilerin temizlenmiş ve iş mantığı için hazırlanmış versiyonlarıdır.
 * Clean Architecture'da, bu modeller API yanıt formatına (DTO) bağlı değildir ve uygulama geliştikçe
 * değişen API yapısından etkilenmezler.
 */

/**
 * Anime verisini temsil eden domain modeli.
 * 
 * @property id Anime'nin benzersiz kimliği
 * @property attributes Anime'nin tüm özellikleri (başlık, açıklama, görseller, vb.)
 */
data class AnimeData(
    val id: String,
    val attributes: Attributes,
)

/**
 * Anime'nin özelliklerini temsil eden domain modeli.
 * 
 * Bu model API'den gelen detayları uygulama için kullanılabilir hale getirir.
 * İş mantığı, kullanıcı arayüzü ve diğer bileşenler bu model ile çalışır.
 * 
 * @property createdAt Anime'nin oluşturulma tarihi
 * @property updatedAt Anime'nin son güncellenme tarihi
 * @property slug URL-dostu tanımlayıcı
 * @property synopsis Anime'nin özeti/konusu
 * @property titles Anime başlıkları
 * @property canonicalTitle Anime'nin resmi başlığı
 * @property abbreviatedTitles Anime'nin kısaltılmış başlıkları
 * @property averageRating Ortalama puan
 * @property userCount İzleyen kullanıcı sayısı
 * @property favoritesCount Favorilere eklenme sayısı
 * @property startDate Yayın başlangıç tarihi
 * @property endDate Yayın bitiş tarihi
 * @property popularityRank Popülerlik sıralaması
 * @property ratingRank Puan sıralaması
 * @property ageRating Yaş sınırı
 * @property ageRatingGuide Yaş sınırı açıklaması
 * @property subtype Anime alt türü (TV, film, OVA, vb.)
 * @property status Durumu (devam ediyor, tamamlandı, vb.)
 * @property posterImage Poster görseli
 * @property coverImage Kapak görseli
 * @property episodeCount Bölüm sayısı
 * @property episodeLength Bölüm uzunluğu (dakika)
 * @property showType Gösteri tipi
 */
data class Attributes(
    val createdAt: String?,
    val updatedAt: String?,
    val slug: String?,
    val synopsis: String?,
    val titles: Titles,
    val canonicalTitle: String?,
    val abbreviatedTitles: List<String>,
    val averageRating: String?,
    val userCount: Int?,
    val favoritesCount: Int?,
    val startDate: String?,
    val endDate: String?,
    val popularityRank: Int?,
    val ratingRank: Int?,
    val ageRating: String?,
    val ageRatingGuide: String?,
    val subtype: String?,
    val status: String?,
    val posterImage: PosterImage,
    val coverImage: CoverImage,
    val episodeCount: Int?,
    val episodeLength: Int?,
    val showType: String?
)

/**
 * Anime başlıklarını temsil eden domain modeli.
 * 
 * @property en İngilizce başlık
 */
data class Titles(
    val en: String?
)

/**
 * Anime poster görselini temsil eden domain modeli.
 * 
 * Poster görselin farklı boyutlarda URL'lerini içerir.
 * 
 * @property tiny Çok küçük boyutlu görsel URL
 * @property small Küçük boyutlu görsel URL
 * @property medium Orta boyutlu görsel URL
 * @property large Büyük boyutlu görsel URL
 * @property original Orijinal boyutlu görsel URL
 */
data class PosterImage(
    val tiny: String,
    val small: String,
    val medium: String,
    val large: String,
    val original: String
)

/**
 * Anime kapak görselini temsil eden domain modeli.
 * 
 * Kapak görselin farklı boyutlarda URL'lerini içerir.
 * 
 * @property tiny Çok küçük boyutlu görsel URL
 * @property small Küçük boyutlu görsel URL
 * @property large Büyük boyutlu görsel URL
 * @property original Orijinal boyutlu görsel URL
 */
data class CoverImage(
    val tiny: String,
    val small: String,
    val large: String,
    val original: String
)