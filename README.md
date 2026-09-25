# Proje Dersi — LED Devresi (Android)

Bu, "LED Yakma Devresi" ders + quiz uygulamasının Kotlin/Jetpack Compose ile yazılmış Android proje kodudur.

## APK'ya dönüştürme adımları

1. **Android Studio'yu indir** (ücretsiz): https://developer.android.com/studio
2. Android Studio'yu aç → **Open** → bu `LedProjectApp` klasörünü seç.
3. Android Studio ilk açılışta Gradle wrapper'ı ve bağımlılıkları otomatik indirip senkronize edecek (birkaç dakika sürebilir, internet gerekir).
4. Sync tamamlanınca üst menüden **Build → Build Bundle(s) / APK(s) → Build APK(s)** seç.
5. Derleme bitince sağ altta çıkan bildirimden **locate** linkine tıkla — APK dosyası şurada olacak:
   `app/build/outputs/apk/debug/app-debug.apk`
6. Bu `.apk` dosyasını telefonuna aktarıp kurabilirsin (Android'de "Bilinmeyen kaynaklardan yükleme" iznini açman gerekebilir).

## Doğrudan telefonunda test etmek istersen

Android Studio'da telefonunu USB ile bağla, geliştirici modunu ve USB hata ayıklamayı aç, sonra üstteki **Run ▶** butonuna bas — uygulama doğrudan telefonuna kurulup açılır.

## Notlar

- `minSdk = 24` (Android 7.0 ve üzeri telefonlarda çalışır)
- Uygulama artık 3 kategoride (Temel Devre Elemanları, Sayısal Elektronik, Mikrodenetleyici) toplam 18 proje içerir ve bir liste ekranından açılır.
- Yeni bir proje eklemek için: `Data.kt` dosyasındaki `projects` listesine yeni bir `Project(...)` girişi eklemen yeterli — liste ve detay ekranları otomatik günceller.
- Dosya yapısı:
  - `Data.kt` — tüm ders içeriği (proje listesi, sorular)
  - `ProjectList.kt` — ana liste ekranı
  - `ProjectDetail.kt` — ders detay ekranı ve şema çizimi
  - `QuizCard.kt` — tekrar kullanılabilir quiz bileşeni
  - `Theme.kt` / `Background.kt` — renk paleti ve arka plan
  - `MainActivity.kt` — giriş noktası ve ekranlar arası geçiş
