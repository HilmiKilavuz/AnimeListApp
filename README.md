# Anime List App

## Proje Hakkında

Anime List App, Kitsu.io API'sini kullanarak trend olan animeleri listeleyen ve detaylarını gösteren bir Android uygulamasıdır. Modern Android geliştirme teknolojileri ve mimarisi kullanılarak oluşturulmuştur.

## Screenshots

<p float="left">
 <img src="/screenshots/ss2.jpg" width="250" />
<img src="/screenshots/ss1.jpg" width="250" />
</p>


## Kullanılan Teknolojiler ve Mimari

### Mimari ve Tasarım Desenleri

- **Clean Architecture**: Uygulama katmanlı mimariye sahiptir - data, domain ve ui katmanları
- **MVVM (Model-View-ViewModel)**: UI katmanında kullanılan mimari desen
- **Repository Pattern**: Veri kaynaklarına erişim için kullanılan tasarım deseni
- **Dependency Injection**: Bağımlılıkların yönetimi için kullanılan tasarım deseni

### Temel Teknolojiler

- **Kotlin**: Modern ve güvenli programlama dili
- **Jetpack Compose**: Deklaratif UI toolkit
- **Kotlin Coroutines & Flow**: Asenkron programlama için kullanılan yapılar
- **Dagger Hilt**: Bağımlılık enjeksiyonu için kullanılan kütüphane
- **Retrofit & OkHttp**: REST API ile iletişim için HTTP client
- **Moshi**: JSON verileri ile Kotlin nesneleri arasında dönüşüm için kullanılan kütüphane
- **Sandwich**: API yanıtlarını daha iyi yönetmek için kullanılan kütüphane
- **Coil**: Görsel yükleme ve önbellekleme kütüphanesi
- **Compose Navigation**: Ekranlar arası geçiş için kullanılan kütüphane
- **Compose Shared Element Transition**: Ekranlar arası geçişte animasyonlu görsel geçişi için kullanılan API

## Uygulama Yapısı

### data katmanı

- **network**: API ile iletişim için gerekli sınıflar
  - **dto**: API yanıtları için Data Transfer Object sınıfları
  - **KitsuApi.kt**: Retrofit API interface'i
- **repository**: Repository implementasyonları
  - **KitsuRepositoryImpl.kt**: KitsuRepository interface'inin gerçekleştirimi
- **di**: Dependency Injection modülleri
  - **AppModule.kt**: Dagger Hilt modülü

### domain katmanı

- **model**: Uygulama içinde kullanılan veri modelleri
  - **AnimeData.kt**: Anime verilerini temsil eden model sınıfları
- **repository**: Repository interface'leri
  - **KitsuRepository.kt**: Anime verilerine erişim için repository interface'i

### ui katmanı

- **screen**: Uygulama ekranları
  - **anime**: Anime detay ekranı
  - **anime_list**: Anime listesi ekranı
  - **composable**: Yeniden kullanılabilir UI bileşenleri
- **theme**: Uygulama teması

### Uygulama Ana Bileşenleri

- **AnimeApp.kt**: Uygulama sınıfı, Hilt entegrasyonu
- **MainActivity.kt**: Ana aktivite, Navigation kurulumu

## Uygulama Akışı

1. Uygulama başladığında, `TrendingAnimeListScreen` görüntülenir ve trend olan animeler listelenir
2. Liste henüz yüklenmediyse, kullanıcıya bir yükleme göstergesi (CircularProgressIndicator) gösterilir
3. Kullanıcı bir anime'ye tıkladığında, `AnimeScreen` açılır ve seçilen anime'nin detayları gösterilir
4. Geçiş sırasında, anime posteri animasyonlu bir şekilde liste ekranından detay ekranına geçer

## Animasyonlar ve Geçişler

Uygulama, Jetpack Compose'un `SharedTransitionLayout` özelliğini kullanarak ekranlar arası geçişlerde animasyonlu bir deneyim sunar. Anime posteri, liste ekranından detay ekranına geçişte animasyonlu bir şekilde büyür ve pozisyon değiştirir.

## Veri Akışı

1. Kullanıcı uygulamayı açtığında, `TrendingAnimeListViewModel` içindeki `init` bloğu çalışır ve `KitsuRepository` üzerinden trend olan animeleri çeker
2. Veri başarıyla yüklendiğinde, `MutableStateFlow` güncellenir ve UI otomatik olarak yenilenir
3. Kullanıcı bir anime'ye tıkladığında, seçilen anime'nin ID'si ve kapak görseli URL'si ile birlikte detay ekranına geçilir
4. Detay ekranında, `AnimeViewModel` içindeki `getAnimeById` metodu çağrılır ve `KitsuRepository` üzerinden anime detayları çekilir
5. Veriler başarıyla yüklendiğinde, UI güncellenir ve anime detayları gösterilir

## API ve Veri Modelleri

Uygulama, Kitsu.io API'sini kullanarak anime verilerini çeker. API'den gelen JSON verileri önce DTO (Data Transfer Object) sınıflarına dönüştürülür, ardından uygulama içinde kullanılan domain modellerine dönüştürülür. Bu yaklaşım, API yanıt yapısının uygulama iç yapısından ayrılmasını sağlar ve değişikliklerden etkilenmemesini garanti eder.

## Kullanılan Kütüphaneler ve Versiyonları

- Jetpack Compose
- Dagger Hilt
- Retrofit & OkHttp
- Moshi
- Sandwich
- Coil
- Kotlin Coroutines & Flow
- Compose Navigation
- Kotlin Serialization

## Geliştirme Ortamı

- Android Studio
- Min SDK: 24
- Target SDK: 34
- Kotlin version: 1.9.0

## Katkıda Bulunma

1. Bu repo'yu fork edin
2. Feature branch oluşturun (`git checkout -b feature/amazing-feature`)
3. Değişikliklerinizi commit edin (`git commit -m 'Add some amazing feature'`)
4. Branch'inizi push edin (`git push origin feature/amazing-feature`)
5. Pull Request oluşturun

## Lisans

Bu proje MIT Lisansı altında lisanslanmıştır. 
