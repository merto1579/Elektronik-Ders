package com.example.ledproject

enum class Category(val label: String, val kicker: String) {
    TEMEL("Temel Devre Elemanları", "TEMEL DEVRE"),
    SAYISAL("Sayısal Elektronik", "SAYISAL ELEKTRONİK"),
    MCU("Mikrodenetleyici", "MİKRODENETLEYİCİ")
}

data class Part(val name: String, val desc: String)
data class Question(val text: String, val options: List<String>, val correct: Int, val note: String)

data class Project(
    val id: String,
    val category: Category,
    val number: Int,
    val title: String,
    val subtitle: String,
    val why: String,
    val parts: List<Part>,
    val steps: List<String>,
    val quiz: List<Question>
)

val projects: List<Project> = listOf(

    // ---------- TEMEL DEVRE ELEMANLARI ----------
    Project(
        id = "temel-1", category = Category.TEMEL, number = 1,
        title = "Basit LED Yakma Devresi",
        subtitle = "Bir LED'i doğru şekilde yakmak.",
        why = "LED devresi, öğrencinin akım-gerilim-direnç ilişkisini (Ohm Kanunu) somut olarak görmesini sağlayan en basit ama en öğretici ilk projedir. Yanlış direnç seçilirse LED yanar; doğru seçilirse çalışır — sonuç anında görülür.",
        parts = listOf(
            Part("9V Pil", "Güç kaynağı"),
            Part("LED (kırmızı)", "İleri gerilim ~2V"),
            Part("220Ω Direnç", "Akım sınırlayıcı"),
            Part("Breadboard", "Bağlantı tahtası")
        ),
        steps = listOf(
            "Direnç değerini hesapla: R = (Vkaynak − VLED) / ILED",
            "Direnci ve LED'i breadboard üzerinde seri bağla.",
            "LED'in kutuplarına dikkat et: uzun bacak (+), kısa bacak (−).",
            "Pili bağla ve LED'in yanıp yanmadığını gözlemle.",
            "Direnç değerini değiştirip parlaklığın nasıl etkilendiğini not et."
        ),
        quiz = listOf(
            Question("Devrede dirence neden ihtiyaç var?", listOf("LED'i süslemek için", "Akımı sınırlayıp LED'in yanmasını önlemek için", "Pilin ömrünü kısaltmak için", "Gerekli değil"), 1, "Direnç olmazsa LED üzerinden aşırı akım geçer ve LED anında yanar."),
            Question("LED'in hangi bacağı (+) kutba bağlanmalı?", listOf("Kısa bacak", "Uzun bacak", "Fark etmez", "İkisi de"), 1, "Uzun bacak anot (+), kısa bacak katot (−) ucudur."),
            Question("9V kaynak, 2V LED ve 20mA için gereken direnç yaklaşık kaçtır?", listOf("35Ω", "150Ω", "350Ω", "900Ω"), 2, "(9−2)V / 0.02A = 350Ω — en yakın standart değerdir.")
        )
    ),
    Project(
        id = "temel-2", category = Category.TEMEL, number = 2,
        title = "Seri-Paralel Direnç Devresi",
        subtitle = "Toplam direncin bağlantı şekline göre nasıl değiştiğini ölçmek.",
        why = "Dirençlerin seri ve paralel bağlandığında toplam direncin nasıl değiştiğini görmek, gerçek devre tasarımının temelidir.",
        parts = listOf(
            Part("3x 1kΩ Direnç", "Test elemanları"),
            Part("Breadboard", "Bağlantı tahtası"),
            Part("Multimetre", "Direnç ölçümü"),
            Part("9V Pil", "Güç kaynağı")
        ),
        steps = listOf(
            "Üç direnci seri bağlayıp toplam direnci multimetre ile ölç.",
            "Seri direnç formülünü uygula: R = R1+R2+R3.",
            "Aynı dirençleri paralel bağla ve tekrar ölç.",
            "Paralel direnç formülünü uygula: 1/R = 1/R1+1/R2+1/R3.",
            "Ölçtüğün değerleri hesapladığın değerlerle karşılaştır."
        ),
        quiz = listOf(
            Question("Üç adet 1kΩ direnç seri bağlanırsa toplam direnç kaç olur?", listOf("333Ω", "1kΩ", "2kΩ", "3kΩ"), 3, "Seri bağlamada dirençler toplanır: 1k+1k+1k=3kΩ."),
            Question("Aynı üç direnç paralel bağlanırsa toplam direnç kaç olur?", listOf("3kΩ", "1kΩ", "333Ω", "0Ω"), 2, "Eşit dirençlerin paralel toplamı: R/n = 1000/3 ≈ 333Ω."),
            Question("Paralel bağlamada toplam direnç neden en küçük dirençten de küçüktür?", listOf("Akım için ek yol açıldığı için", "Gerilim arttığı için", "Güç kaybolduğu için", "Kablo direnci eklendiği için"), 0, "Paralel kollar akıma ek yollar sağlar, bu da toplam direnci düşürür.")
        )
    ),
    Project(
        id = "temel-3", category = Category.TEMEL, number = 3,
        title = "Kondansatör Şarj-Deşarj Devresi",
        subtitle = "RC devresiyle zamanlama kavramını gözlemlemek.",
        why = "RC devresi, zamanlamanın (zaman sabiti τ=RC) elektronikte nasıl kullanıldığını gösteren temel bir deneydir.",
        parts = listOf(
            Part("100µF Kondansatör", "Enerji depolama"),
            Part("10kΩ Direnç", "Şarj hızını belirler"),
            Part("LED", "Görsel gösterge"),
            Part("9V Pil", "Güç kaynağı")
        ),
        steps = listOf(
            "Direnç ve kondansatörü seri bağlayarak bir RC devresi kur.",
            "Pili bağla ve LED'in yavaşça parlaklaştığını gözlemle (şarj).",
            "Zaman sabitini hesapla: τ = R × C.",
            "Pili çıkar, kondansatörün LED üzerinden yavaşça deşarj olmasını izle.",
            "Direnç değerini değiştirip şarj/deşarj süresinin nasıl değiştiğini not et."
        ),
        quiz = listOf(
            Question("τ = R × C formülünde R=10kΩ, C=100µF ise τ kaç saniyedir?", listOf("0.1s", "1s", "10s", "100s"), 1, "10.000Ω × 0.0001F = 1 saniye."),
            Question("Kondansatör tam şarj olduğunda devrede akım nasıl davranır?", listOf("Artmaya devam eder", "Sıfıra yaklaşır", "Aniden ters döner", "Sabit kalır"), 1, "Kondansatör dolduğunda gerilim farkı kalmadığı için akım sıfıra yaklaşır."),
            Question("Direnç değeri artırılırsa şarj süresi nasıl etkilenir?", listOf("Kısalır", "Değişmez", "Uzar", "Kondansatör hiç dolmaz"), 2, "τ = R×C olduğundan direnç artınca şarj süresi de uzar.")
        )
    ),
    Project(
        id = "temel-4", category = Category.TEMEL, number = 4,
        title = "Diyot Doğrultma Devresi",
        subtitle = "AC gerilimi yarım dalga doğrultarak DC'ye çevirmek.",
        why = "AC gerilimi DC'ye çevirmenin ilk adımı olan yarım dalga doğrultma, güç kaynaklarının temelidir.",
        parts = listOf(
            Part("1N4007 Diyot", "Tek yönlü akım geçişi"),
            Part("Trafo (AC kaynak)", "Alternatif gerilim"),
            Part("Yük Direnci", "Çıkışı ölçmek için"),
            Part("Osiloskop/Multimetre", "Gözlem aracı")
        ),
        steps = listOf(
            "AC kaynağı diyot ile yük direncine seri bağla.",
            "Diyotun yönünü (anot-katot) doğru yerleştirdiğinden emin ol.",
            "Çıkış gerilimini osiloskopta veya multimetrede gözlemle.",
            "Sadece pozitif yarım dalganın geçtiğini, negatifin kesildiğini not et.",
            "Diyotu ters bağlayıp çıkışın nasıl değiştiğini gözlemle."
        ),
        quiz = listOf(
            Question("Yarım dalga doğrultucu AC sinyalin hangi kısmını geçirir?", listOf("Sadece negatif yarım dalgayı", "Sadece pozitif yarım dalgayı", "Her ikisini de", "Hiçbirini"), 1, "Diyot sadece bir yönde akım geçirdiği için tek bir yarım dalga çıkışta görülür."),
            Question("Diyot ters bağlanırsa ne olur?", listOf("Çıkış iki katına çıkar", "Çıkışta hiç gerilim olmaz", "Devre kısa devre olur", "Fark etmez"), 1, "Diyot ters yönde akımı bloke eder, bu yönde çıkış sıfır olur."),
            Question("Yarım dalga doğrultmanın en büyük dezavantajı nedir?", listOf("Çok pahalı olması", "Çıkışın yarı zamanlı ve dalgalı olması", "Sadece AC ile çalışması", "Diyot gerektirmemesi"), 1, "Çıkışta sürekli DC yerine kesikli, dalgalı bir sinyal elde edilir.")
        )
    ),
    Project(
        id = "temel-5", category = Category.TEMEL, number = 5,
        title = "Potansiyometre ile Parlaklık Ayarı",
        subtitle = "Değişken direnç ile akımı manuel kontrol etmek.",
        why = "Değişken direnç kullanarak akımı manuel kontrol etmek, potansiyometrelerin ses, ışık ayarı gibi günlük kullanımının temelidir.",
        parts = listOf(
            Part("10kΩ Potansiyometre", "Ayarlanabilir direnç"),
            Part("LED", "Görsel gösterge"),
            Part("330Ω Direnç", "Güvenlik sınırlayıcı"),
            Part("9V Pil", "Güç kaynağı")
        ),
        steps = listOf(
            "Potansiyometreyi devrenin direnç kısmı olarak seri bağla.",
            "Sabit 330Ω direnci güvenlik için LED ile seri ekle.",
            "Potansiyometrenin kolunu bir uca çevirip LED'in en parlak halini gözlemle.",
            "Kolunu diğer uca çevirip LED'in sönmeye yaklaştığını gözlemle.",
            "Ara noktalarda parlaklığın kademeli değiştiğini not et."
        ),
        quiz = listOf(
            Question("Potansiyometre devrede ne işe yarar?", listOf("Sabit gerilim üretir", "Direnci manuel olarak değiştirir", "Akımı tersine çevirir", "Sadece anahtar görevi görür"), 1, "Potansiyometre, kolunun konumuna göre direnç değerini değiştiren bir ayarlanabilir dirençtir."),
            Question("Potansiyometre direnci artırıldığında LED parlaklığı nasıl değişir?", listOf("Artar", "Azalır", "Değişmez", "LED patlar"), 1, "Direnç artınca Ohm Kanunu gereği akım azalır, LED daha sönük yanar."),
            Question("Sabit 330Ω direnç neden hâlâ devrede tutulur?", listOf("Estetik için", "Potansiyometre sıfıra ayarlansa bile LED'i korumak için", "Gerilimi yükseltmek için", "Gerekli değil"), 1, "Potansiyometre sıfır dirence ayarlanırsa sabit direnç olmadan LED aşırı akımdan zarar görür.")
        )
    ),
    Project(
        id = "temel-6", category = Category.TEMEL, number = 6,
        title = "Transistör ile Anahtarlama",
        subtitle = "Küçük bir sinyalle büyük bir akımı kontrol etmek.",
        why = "Transistör, küçük bir sinyalle büyük bir akımı kontrol etmeyi sağlayan elektroniğin en temel yapı taşıdır.",
        parts = listOf(
            Part("NPN Transistör (2N2222)", "Anahtarlama elemanı"),
            Part("LED", "Yük"),
            Part("1kΩ Direnç", "Baz akımı sınırlayıcı"),
            Part("330Ω Direnç", "Kolektör akımı sınırlayıcı")
        ),
        steps = listOf(
            "Transistörün taban (B), kolektör (C), emitör (E) bacaklarını tanı.",
            "Baz ucuna 1kΩ direnç üzerinden düşük bir kontrol gerilimi bağla.",
            "Kolektör ucuna LED ve 330Ω direnci seri bağla, emitörü toprağa (GND) bağla.",
            "Baz gerilimini uygulayıp LED'in yandığını gözlemle.",
            "Baz gerilimini kesip LED'in söndüğünü doğrula — transistör anahtar gibi çalışır."
        ),
        quiz = listOf(
            Question("Transistörün bu devredeki görevi nedir?", listOf("Gerilimi yükseltmek", "Küçük bir akımla büyük bir akımı anahtarlamak", "AC'yi DC'ye çevirmek", "Işık üretmek"), 1, "Transistör, baz akımını kullanarak kolektör-emitör arasındaki daha büyük akımı açıp kapatır."),
            Question("Baz ucuna gerilim uygulanmazsa LED ne olur?", listOf("Yanar", "Söner", "Yanıp söner", "Parlaklığı artar"), 1, "Baz akımı olmadan transistör kapalı konumdadır, akım geçmez."),
            Question("Bu devre gerçek hayatta en çok neye benzer?", listOf("Bir dimmer anahtarına", "Bir röle/anahtara", "Bir kondansatöre", "Bir antene"), 1, "Transistör burada tıpkı bir röle gibi, küçük bir sinyalle büyük bir yükü açıp kapatıyor.")
        )
    ),

    // ---------- SAYISAL ELEKTRONİK ----------
    Project(
        id = "sayisal-1", category = Category.SAYISAL, number = 1,
        title = "AND-OR-NOT Kapı Deneyi",
        subtitle = "Temel mantık kapılarının doğruluk tablosunu çıkarmak.",
        why = "Mantık kapıları, tüm dijital sistemlerin (bilgisayarlar dahil) temel yapı taşlarıdır.",
        parts = listOf(
            Part("7408 / 7432 / 7404 IC", "AND / OR / NOT kapıları"),
            Part("LED'ler", "Çıkış göstergesi"),
            Part("Breadboard", "Bağlantı tahtası"),
            Part("5V Kaynak", "Güç"),
        ),
        steps = listOf(
            "IC'yi breadboard üzerine yerleştir, güç ve toprak bacaklarını bağla.",
            "Giriş bacaklarına anahtar veya jumper kablolarla 0/1 (0V/5V) uygula.",
            "Çıkış bacağına bir LED bağlayıp sonucu gözlemle.",
            "Farklı giriş kombinasyonlarını dene ve doğruluk tablosunu oluştur.",
            "AND, OR, NOT kapılarının sonuçlarını karşılaştır."
        ),
        quiz = listOf(
            Question("AND kapısının çıkışı ne zaman 1 (yüksek) olur?", listOf("Herhangi bir giriş 1 olduğunda", "Sadece tüm girişler 1 olduğunda", "Tüm girişler 0 olduğunda", "Asla"), 1, "AND kapısı sadece tüm girişler yüksek (1) olduğunda yüksek çıkış verir."),
            Question("NOT kapısı ne yapar?", listOf("Girişi olduğu gibi geçirir", "Girişin tersini verir", "İki girişi toplar", "Girişi yükseltir"), 1, "NOT kapısı, giriş 1 ise çıkışı 0, giriş 0 ise çıkışı 1 yapar."),
            Question("OR kapısının çıkışı ne zaman 0 olur?", listOf("Sadece tüm girişler 0 olduğunda", "Herhangi bir giriş 1 olduğunda", "Her zaman", "Asla 0 olmaz"), 0, "OR kapısı, en az bir giriş 1 olduğunda 1 verir.")
        )
    ),
    Project(
        id = "sayisal-2", category = Category.SAYISAL, number = 2,
        title = "555 Zamanlayıcı ile Kare Dalga",
        subtitle = "Astable modda saat sinyali üretmek.",
        why = "555 zamanlayıcı IC, saat sinyali üretmenin en yaygın ve ucuz yoludur; birçok projede kalp atışı görevi görür.",
        parts = listOf(
            Part("NE555 IC", "Zamanlayıcı"),
            Part("2x Direnç", "Frekans belirleyici"),
            Part("Kondansatör", "Zamanlama elemanı"),
            Part("LED", "Görsel gösterge")
        ),
        steps = listOf(
            "555 IC'yi astable (kararsız) mod bağlantı şemasına göre breadboard'a kur.",
            "Direnç ve kondansatör değerlerini frekans formülüne göre seç.",
            "Çıkışa bir LED bağla ve yanıp sönmeyi gözlemle.",
            "Direnç değerlerini değiştirip yanıp sönme hızının değiştiğini gözlemle.",
            "Osiloskop varsa çıkıştaki kare dalgayı görüntüle."
        ),
        quiz = listOf(
            Question("555 IC'nin astable modda ürettiği sinyal türü nedir?", listOf("Sinüs dalga", "Kare dalga", "Üçgen dalga", "DC sabit gerilim"), 1, "Astable mod, sürekli yüksek-alçak arasında geçiş yapan bir kare dalga üretir."),
            Question("Direnç değerleri artırılırsa çıkış frekansı nasıl değişir?", listOf("Artar", "Azalır", "Değişmez", "Sinyal durur"), 1, "Frekans RC değerleriyle ters orantılıdır; direnç artınca frekans düşer."),
            Question("555 zamanlayıcı gerçek projelerde en çok ne için kullanılır?", listOf("Ses yükseltmek için", "Saat/darbe sinyali üretmek için", "Gerilimi düşürmek için", "Kablosuz iletişim için"), 1, "555, birçok projede darbe/saat sinyali üretmek için kullanılır.")
        )
    ),
    Project(
        id = "sayisal-3", category = Category.SAYISAL, number = 3,
        title = "Flip-Flop ile Bit Hafızası",
        subtitle = "SR Latch ile bir bitlik bilgiyi hafızada tutmak.",
        why = "Flip-flop, bilgisayarların bellek biriminin temelidir — bir bitlik bilgiyi hafızada tutmayı öğretir.",
        parts = listOf(
            Part("2x NOR Kapı IC (7402)", "Çapraz bağlantı"),
            Part("2 Buton", "Set / Reset girişi"),
            Part("LED", "Durum göstergesi"),
            Part("Breadboard", "Bağlantı tahtası")
        ),
        steps = listOf(
            "İki NOR kapısını çapraz bağlayarak bir SR Latch (Set-Reset kilit) oluştur.",
            "Set (S) butonuna basıp çıkışın 1 olduğunu ve LED'in yandığını gözlemle.",
            "Butonu bıraktığında LED'in yanık kaldığını fark et.",
            "Reset (R) butonuna basıp çıkışın 0 olduğunu ve LED'in söndüğünü gözlemle.",
            "S ve R'nin ikisine birden basmaktan kaçın — bu yasak durum belirsiz sonuç verir."
        ),
        quiz = listOf(
            Question("SR Latch'in temel özelliği nedir?", listOf("Sadece anlık sinyali gösterir", "Son durumu hafızada tutar", "Rastgele çıkış üretir", "Sadece analog sinyalle çalışır"), 1, "Latch, giriş kesildikten sonra bile son durumunu korur — bu onu bir hafıza birimi yapar."),
            Question("Set (S) butonuna basıldığında çıkış ne olur?", listOf("0'a döner", "1 olur ve öyle kalır", "Yanıp söner", "Değişmez"), 1, "Set girişi çıkışı 1 yapar ve buton bırakılsa bile bu durum korunur."),
            Question("S ve R girişlerine aynı anda basmak neden sakıncalıdır?", listOf("Devre yanar", "Çıkış belirsiz bir duruma girer", "LED çok parlar", "Hiçbir sakıncası yoktur"), 1, "Bu yasak durum olarak bilinir; çıkışın ne olacağı belirsizleşir.")
        )
    ),
    Project(
        id = "sayisal-4", category = Category.SAYISAL, number = 4,
        title = "7 Segment Display Sürme",
        subtitle = "Segmentleri sürerek rakam görüntülemek.",
        why = "Sayısal göstergelerin (saat, hesap makinesi vb.) nasıl çalıştığını anlamanın en somut yolu.",
        parts = listOf(
            Part("7 Segment Display", "Ortak katot"),
            Part("7x 220Ω Direnç", "Segment koruma"),
            Part("Breadboard", "Bağlantı tahtası"),
            Part("5V Kaynak", "Güç")
        ),
        steps = listOf(
            "Display'in bacaklarının hangi segmente (a-g) karşılık geldiğini datasheet'ten kontrol et.",
            "Her segment bacağına bir direnç üzerinden 5V uygula, ortak katodu toprağa bağla.",
            "Farklı segment kombinasyonlarıyla 0'dan 9'a kadar rakamlar oluştur.",
            "Hangi segmentlerin hangi rakam için yanması gerektiğinin bir tablosunu çıkar.",
            "İstersen bir mikrodenetleyici ile bu segmentleri otomatik sürmeyi dene."
        ),
        quiz = listOf(
            Question("\"0\" rakamını göstermek için hangi segment yanmamalıdır?", listOf("a segmenti", "g (orta) segmenti", "b segmenti", "Hepsi yanmalı"), 1, "\"0\" rakamı ortadaki g segmenti hariç tüm segmentlerin yanmasıyla oluşur."),
            Question("Her segmente direnç bağlamanın sebebi nedir?", listOf("Parlaklığı artırmak için", "Segmentteki LED'i aşırı akımdan korumak için", "Rengi değiştirmek için", "Gerekli değil"), 1, "Her segment aslında küçük bir LED'dir; direnç olmadan aşırı akımla yanabilir."),
            Question("\"Ortak katot\" display ne anlama gelir?", listOf("Tüm segmentlerin (-) uçları birleşiktir", "Tüm segmentlerin (+) uçları birleşiktir", "Sadece bir segment çalışır", "Display'in rengi ortaktır"), 0, "Ortak katotta tüm segmentlerin katot (-) uçları tek bir noktada birleşir.")
        )
    ),
    Project(
        id = "sayisal-5", category = Category.SAYISAL, number = 5,
        title = "Çok Vibratör LED Yanıp Sönme",
        subtitle = "İki transistörlü astable multivibrator kurmak.",
        why = "İki transistörlü astable multivibrator, 555 IC'den önce kullanılan klasik bir yanıp sönme devresidir ve transistörlerin birbirini nasıl tetiklediğini gösterir.",
        parts = listOf(
            Part("2x NPN Transistör", "Anahtarlama"),
            Part("2x LED", "Görsel çıkış"),
            Part("2x 10kΩ + 2x 1kΩ Direnç", "Baz ve kolektör dirençleri"),
            Part("2x 10µF Kondansatör", "Zamanlama")
        ),
        steps = listOf(
            "İki transistörü çapraz bağlanmış kondansatörlerle (astable multivibrator şeması) kur.",
            "Her transistörün kolektörüne birer LED ve direnç bağla.",
            "Devreyi güçlendirip iki LED'in sırayla yanıp söndüğünü gözlemle.",
            "Kondansatör değerlerini değiştirip yanıp sönme hızının değiştiğini not et.",
            "Bu devrenin 555 IC'siz de aynı işi nasıl yaptığını 555 projesiyle karşılaştır."
        ),
        quiz = listOf(
            Question("Bu devrede kaç LED sırayla yanıp söner?", listOf("1", "2", "3", "4"), 1, "Devre iki transistörlü olduğu için iki LED sırayla yanıp söner."),
            Question("Kondansatör değerleri büyütülürse yanıp sönme hızı nasıl değişir?", listOf("Hızlanır", "Yavaşlar", "Değişmez", "Durur"), 1, "Daha büyük kondansatör, şarj/deşarj süresini uzatarak yanıp sönmeyi yavaşlatır."),
            Question("Bu devre hangi kavramı somut olarak gösterir?", listOf("Transistörlerin birbirini tetikleyerek salınım üretmesini", "Sadece akımı sınırlamayı", "Kondansatörün sabit gerilim ürettiğini", "LED'lerin AC ile çalıştığını"), 0, "İki transistör birbirini karşılıklı tetikleyerek kendiliğinden salınan bir devre oluşturur.")
        )
    ),
    Project(
        id = "sayisal-6", category = Category.SAYISAL, number = 6,
        title = "Kod Çözücü ile Işık Sırası",
        subtitle = "Decoder IC ile adrese göre tek çıkış seçmek.",
        why = "Decoder IC'ler, az sayıda giriş bitiyle çok sayıda çıkışı seçmeyi sağlar — bellek adresleme ve gösterge sistemlerinin temelidir.",
        parts = listOf(
            Part("74138 (3-8 Decoder) IC", "Adres çözücü"),
            Part("8x LED", "Çıkış göstergesi"),
            Part("8x 220Ω Direnç", "Akım sınırlayıcı"),
            Part("Anahtarlar", "Adres girişi")
        ),
        steps = listOf(
            "Decoder IC'nin 3 adres girişine (A0, A1, A2) anahtarlarla 0/1 uygula.",
            "8 çıkış bacağının her birine direnç üzerinden LED bağla.",
            "Farklı adres kombinasyonlarını dene, her seferinde sadece bir LED'in yandığını gözlemle.",
            "Adres-çıkış eşleşmesinin bir tablosunu çıkar.",
            "Bu mantığın bellek adresleme sistemlerinde nasıl kullanıldığını araştır."
        ),
        quiz = listOf(
            Question("3 girişli bir decoder kaç farklı çıkışı seçebilir?", listOf("3", "6", "8", "16"), 2, "3 bit ile 2³=8 farklı kombinasyon oluşturulabilir."),
            Question("Her adres kombinasyonunda kaç LED aynı anda yanar?", listOf("Hepsi birden", "Sadece bir tanesi", "İki tanesi", "Hiçbiri"), 1, "Decoder'ın görevi, verilen adrese karşılık gelen tek bir çıkışı seçip aktif etmektir."),
            Question("Decoder mantığı gerçek hayatta nerede kullanılır?", listOf("Ses yükseltmede", "Bellek adresleme ve çoklayıcı sistemlerde", "Sadece LED oyuncaklarında", "AC-DC dönüşümünde"), 1, "Decoder'lar bilgisayarların bellek adresleme ve çoklu cihaz seçme sistemlerinde kullanılır.")
        )
    ),

    // ---------- MİKRODENETLEYİCİ ----------
    Project(
        id = "mcu-1", category = Category.MCU, number = 1,
        title = "Arduino ile LED Yakma (Blink)",
        subtitle = "Kod yazıp gerçek dünyada bir sonucu kontrol etmek.",
        why = "\"Blink\", mikrodenetleyici dünyasının \"Hello World\"üdür — kod yazıp gerçek dünyada bir sonucu (ışığı) kontrol etmenin ilk adımıdır.",
        parts = listOf(
            Part("Arduino Uno", "Mikrodenetleyici kart"),
            Part("LED", "Çıkış elemanı"),
            Part("220Ω Direnç", "Akım sınırlayıcı"),
            Part("USB Kablo", "Programlama ve güç")
        ),
        steps = listOf(
            "LED'i 220Ω direnç üzerinden Arduino'nun dijital bir pinine (örn. pin 13) bağla.",
            "Arduino IDE'yi bilgisayarına kur ve kartı USB ile bağla.",
            "pinMode() ile pini OUTPUT olarak ayarla.",
            "digitalWrite() ve delay() komutlarıyla LED'i belirli aralıklarla yak-söndür.",
            "Kodu Arduino'ya yükle (Upload) ve LED'in yanıp söndüğünü gözlemle."
        ),
        quiz = listOf(
            Question("pinMode(13, OUTPUT) komutu ne işe yarar?", listOf("Pin 13'ü giriş olarak ayarlar", "Pin 13'ü çıkış olarak ayarlar", "LED'i yakar", "Arduino'yu resetler"), 1, "OUTPUT modu, o pinin sinyal göndermek için kullanılacağını Arduino'ya bildirir."),
            Question("delay(1000) komutu ne kadar bekler?", listOf("1 saniye", "1 dakika", "100 milisaniye", "1 mikrosaniye"), 0, "delay() fonksiyonu milisaniye cinsinden çalışır; 1000ms = 1 saniyedir."),
            Question("digitalWrite(13, HIGH) ne yapar?", listOf("Pine gerilim gönderir, LED'i yakar", "Pini sıfırlar", "Pini giriş yapar", "Hiçbir şey yapmaz"), 0, "HIGH komutu pine gerilim uygulayarak LED gibi bağlı elemanları aktif eder.")
        )
    ),
    Project(
        id = "mcu-2", category = Category.MCU, number = 2,
        title = "Buton ile LED Kontrolü",
        subtitle = "Dijital giriş okuyarak LED'i kontrol etmek.",
        why = "Bir butonun basılıp basılmadığını okumak, kullanıcı etkileşimli her projenin (alarm, oyun, kumanda) temelidir.",
        parts = listOf(
            Part("Arduino Uno", "Mikrodenetleyici kart"),
            Part("Buton", "Kullanıcı girişi"),
            Part("10kΩ Direnç", "Pull-down direnci"),
            Part("LED + 220Ω Direnç", "Çıkış")
        ),
        steps = listOf(
            "Butonu pull-down direnç ile bir dijital giriş pinine bağla.",
            "LED'i başka bir dijital çıkış pinine bağla.",
            "setup() içinde buton pinini INPUT, LED pinini OUTPUT yap.",
            "loop() içinde digitalRead() ile butonun durumunu oku.",
            "Buton basılıysa LED'i yak, değilse söndür — kodu buna göre yaz."
        ),
        quiz = listOf(
            Question("Pull-down direnç neden gereklidir?", listOf("LED'i korumak için", "Buton basılı değilken pini kararlı bir 0 seviyesinde tutmak için", "Arduino'yu soğutmak için", "Gerekli değildir"), 1, "Pull-down direnç olmadan pin floating kalır ve yanlış okumalara sebep olabilir."),
            Question("digitalRead() fonksiyonu ne döndürür?", listOf("Bir ondalık sayı", "HIGH veya LOW", "Bir metin", "Bir renk kodu"), 1, "digitalRead(), dijital bir pinin durumunu HIGH veya LOW olarak döndürür."),
            Question("Bu projede LED'in davranışı neye bağlıdır?", listOf("Ortam ışığına", "Butonun anlık durumuna", "Sıcaklığa", "Arduino'nun pil seviyesine"), 1, "Kod, loop() içinde sürekli butonun durumunu kontrol edip LED'i ona göre açıp kapatır.")
        )
    ),
    Project(
        id = "mcu-3", category = Category.MCU, number = 3,
        title = "Sıcaklık Sensörü Okuma",
        subtitle = "Analog sensör verisini sayısal dünyaya aktarmak.",
        why = "Analog sensör okumak, gerçek dünyadaki sürekli değişen değerleri (sıcaklık, ışık, nem) sayısal dünyaya aktarmayı öğretir.",
        parts = listOf(
            Part("Arduino Uno", "Mikrodenetleyici kart"),
            Part("LM35 Sıcaklık Sensörü", "Analog sensör"),
            Part("Jumper Kablolar", "Bağlantı"),
            Part("Bilgisayar", "Serial Monitor için")
        ),
        steps = listOf(
            "LM35 sensörünü Arduino'nun analog bir pinine (örn. A0) bağla.",
            "analogRead() ile ham sensör değerini oku (0-1023 arası).",
            "Bu ham değeri gerilime, sonra da santigrat dereceye çeviren formülü uygula.",
            "Serial.begin() ve Serial.println() ile sonucu bilgisayar ekranında göster.",
            "Sensörü elinle ısıtıp değerin nasıl değiştiğini gözlemle."
        ),
        quiz = listOf(
            Question("analogRead() kaç farklı değer döndürebilir (Arduino Uno'da)?", listOf("2", "256", "1024", "4096"), 2, "Arduino Uno 10-bit ADC kullanır, bu da 0-1023 arası (1024 farklı) değer demektir."),
            Question("LM35 sensörünün her 10mV artışı kaç santigrat dereceye karşılık gelir?", listOf("0.1°C", "1°C", "10°C", "100°C"), 1, "LM35, her 1°C için çıkışında 10mV artış üretecek şekilde tasarlanmıştır."),
            Question("Serial.println() ne işe yarar?", listOf("LED'i yakar", "Değeri bilgisayar ekranına yazdırır", "Arduino'yu resetler", "Sensörü kalibre eder"), 1, "Serial Monitor üzerinden okunan değerleri gerçek zamanlı takip etmeyi sağlar.")
        )
    ),
    Project(
        id = "mcu-4", category = Category.MCU, number = 4,
        title = "Servo Motor Kontrolü",
        subtitle = "PWM sinyaliyle hassas açısal hareket üretmek.",
        why = "Servo motorlar, robot kollarından kapı kilitlerine kadar hassas açısal hareket gerektiren projelerin vazgeçilmezidir.",
        parts = listOf(
            Part("Arduino Uno", "Mikrodenetleyici kart"),
            Part("SG90 Servo Motor", "Aktüatör"),
            Part("Jumper Kablolar", "Bağlantı"),
            Part("Potansiyometre (opsiyonel)", "Manuel kontrol")
        ),
        steps = listOf(
            "Servo motorun sinyal ucunu bir PWM destekli dijital pine bağla, güç ve toprak uçlarını da bağla.",
            "Arduino IDE'de hazır Servo kütüphanesini projene ekle.",
            "servo.attach() ile pini tanımla, servo.write() ile açı gönder (0-180 derece).",
            "Farklı açı değerleriyle servonun döndüğünü gözlemle.",
            "İstersen bir potansiyometrenin konumuna göre servo açısını değiştirecek şekilde kodu geliştir."
        ),
        quiz = listOf(
            Question("servo.write(90) komutu ne yapar?", listOf("Servoyu 90 derece pozisyona döndürür", "Servoyu 90 saniye çalıştırır", "Servoyu kapatır", "LED'i yakar"), 0, "write() fonksiyonu servo motoru belirtilen açı pozisyonuna döndürür."),
            Question("Servo motorlar hangi tür sinyalle kontrol edilir?", listOf("Analog gerilim", "PWM (darbe genişlik modülasyonu)", "AC sinyal", "Sabit DC"), 1, "Servo motorlar, darbe genişliğine göre pozisyon belirleyen PWM sinyalleriyle kontrol edilir."),
            Question("Servo motor ile normal DC motor arasındaki temel fark nedir?", listOf("Servo sadece tek yönde döner", "Servo belirli bir açıda durabilir, DC motor sürekli döner", "DC motor daha hassastır", "Aralarında fark yoktur"), 1, "Servo motorlar geri besleme mekanizmasıyla belirli bir açıda durabilir.")
        )
    ),
    Project(
        id = "mcu-5", category = Category.MCU, number = 5,
        title = "Ultrasonik Mesafe Sensörü",
        subtitle = "Ses dalgasıyla mesafe ölçmek.",
        why = "Ultrasonik sensörler, otonom robotlardan park sensörlerine kadar engel algılama gerektiren tüm projelerin temelidir.",
        parts = listOf(
            Part("Arduino Uno", "Mikrodenetleyici kart"),
            Part("HC-SR04 Sensör", "Ultrasonik mesafe ölçer"),
            Part("Jumper Kablolar", "Bağlantı"),
            Part("Bilgisayar", "Serial Monitor için")
        ),
        steps = listOf(
            "HC-SR04'ün Trig ve Echo pinlerini Arduino'nun iki dijital pinine bağla.",
            "Trig pinine kısa bir yüksek sinyal göndererek ses dalgası gönder.",
            "Echo pininde sinyalin geri dönme süresini pulseIn() ile ölç.",
            "Ölçülen süreyi mesafeye çeviren formülü uygula: mesafe = süre × ses hızı / 2.",
            "Sonucu Serial Monitor'de göster ve elini sensöre yaklaştırıp uzaklaştırarak test et."
        ),
        quiz = listOf(
            Question("HC-SR04 mesafeyi nasıl ölçer?", listOf("Işık yansımasıyla", "Ses dalgasının gidiş-dönüş süresiyle", "Sıcaklık farkıyla", "Manyetik alanla"), 1, "Sensör ultrasonik ses dalgası gönderir, yansıyıp geri dönme süresinden mesafeyi hesaplar."),
            Question("pulseIn() fonksiyonu ne ölçer?", listOf("Gerilim değerini", "Bir pindeki sinyalin yüksek kaldığı süreyi", "Sıcaklığı", "Rengi"), 1, "pulseIn(), belirtilen pinin ne kadar süre yüksek kaldığını mikrosaniye cinsinden ölçer."),
            Question("Formülde süre neden 2'ye bölünür?", listOf("Rastgele bir katsayı olduğu için", "Ses hem gidip hem geri döndüğü için", "Arduino'nun hızı yüzünden", "Bölünmesi gerekmez"), 1, "Ölçülen süre sesin gidiş-dönüş toplam süresidir; tek yön mesafe için ikiye bölünür.")
        )
    ),
    Project(
        id = "mcu-6", category = Category.MCU, number = 6,
        title = "Wi-Fi ile Uzaktan Röle Kontrolü",
        subtitle = "ESP32 ile bir cihazı internetten kontrol etmek.",
        why = "Nesnelerin İnterneti (IoT) projelerinin temeli — bir cihazı telefonundan veya internetten kontrol edebilmek.",
        parts = listOf(
            Part("ESP32 Geliştirme Kartı", "Wi-Fi destekli mikrodenetleyici"),
            Part("Röle Modülü", "Yüksek güç anahtarlama"),
            Part("Lamba/Motor", "Kontrol edilecek yük"),
            Part("Wi-Fi Ağı", "Bağlantı")
        ),
        steps = listOf(
            "ESP32'yi Arduino IDE'ye tanıt ve Wi-Fi kütüphanesini projene ekle.",
            "WiFi.begin() ile ESP32'yi ev ağına bağla.",
            "Basit bir web sunucusu kurarak aç/kapat komutlarını dinle.",
            "Gelen komuta göre röle pinini HIGH/LOW yaparak yükü kontrol et.",
            "Telefondan tarayıcıyla ESP32'nin IP adresine bağlanıp röleyi uzaktan tetikle."
        ),
        quiz = listOf(
            Question("Röle modülü bu projede ne işe yarar?", listOf("Wi-Fi sinyalini güçlendirir", "Düşük gerilimli ESP32 ile yüksek güçlü bir yükü anahtarlar", "Sıcaklığı ölçer", "Sesi yükseltir"), 1, "Röle, ESP32'nin düşük akımlı sinyaliyle yüksek gerilim gerektiren cihazları güvenle açıp kapatmayı sağlar."),
            Question("ESP32'yi Arduino Uno'dan ayıran temel özellik nedir?", listOf("Daha yavaş çalışması", "Yerleşik Wi-Fi ve Bluetooth desteği", "Analog pin olmaması", "Programlanamaması"), 1, "ESP32, üzerinde yerleşik Wi-Fi/Bluetooth modülü barındırdığı için IoT projelerinde tercih edilir."),
            Question("Telefondan röleyi kontrol edebilmek için ne gereklidir?", listOf("ESP32'nin IP adresine ağ üzerinden erişim", "Sadece USB kablosu", "Bluetooth eşleştirmesi şart", "Hiçbiri, otomatik çalışır"), 0, "Telefonun ESP32 ile aynı ağda olması ve onun IP adresine bağlanması gerekir.")
        )
    )
)
