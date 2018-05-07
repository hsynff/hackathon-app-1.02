# hackathon-app-1.02

### İstifadə olunan texnologiyalar:

Client side: JSP, Bootstrap, JSTL, Javascript(jQuery)

Server side: Java (Spring MVC/Security, JdbcTemplate), MySql

-----------------------------------------------------------------
### İstifadə qaydası:

Ümumi olaraq 3 rol müəyyən olunub - İstifadəçi (müştəri), Təmirçi və Menecer.

İşləmə ssenarisi:

Müştəri təmir mərkəzinə yaxınlaşır, cihazını təqdim edir. Menecer müştəridən məlumatları alır və hər hansı 
təmirçiyə ötürür. Müştəriyə izləmə kodu (və digər məlumatlar) verilir (həm çap olunur həm email göndərilir). Təmirçi təmir prosesində
cihazın statusu barədə qeydlər edir. Qeydlər müştəriyə göndərilir və ya Müştəri özü istədiyi vaxt sistemdən istifadə edərək status barədə məlumatlanır.

Aşağıdakı vasitələrdən hər hansı birini istifadə edərək işləmə prinsipini görmək olar:

#### 1. Bir başa olaraq http://85.132.33.254:8080/ ünvanına daxil olaraq serverdə çalışan proyekti istifadə etmək mümkündür.

                      İstifadə olunacaq keçid linkləri:
                      
                      http://85.132.33.254:8080/login - Təmirçi və menecerlər üçün sistemə giriş səhifəsi.
                      nümunə üçün təmirçi profili (username: repairer001, password: 123)
                      nümunə üçün menecer profili (username: cosqun, password: 123)
                      
                      Təmirçi əsas səhifəsində təmirçinin hazırki təmir işləri cədvəl formasında göstərilir. 
                      Cədvəldə hər hansı bir təmirə klikləyərək ətraflı məlumat səhifəsinə keçid olunur.
                      Həmin səhifədə təmirin statusunu dəyişmək və ya təmiri dayandırmaq mümkündür.
                      Hər status dəyişikliyində istifadəçiyə email (əgər varsa) göndərilir.
                      
                      Təmirçi əsas səhifəsində digər keçid (Tarixçə) təmiri bitmiş cihazlar yer alır.
                      
                      Menecer əsas səhifəsində bütün təmirçilər və onların hazırki (və bitmiş) təmir işlərinin sayı cədvəl formasında göstərilir.
                      Əsas səhifədəki ikinci keçid yeni təmirçi əlavə edir.
                      Üçüncü keçid yeni təmir işi yaradır. Təmir işi yaradılarkən müştəridən FİN kod tələb olunur, 
                      əgər həmin FİN bazada daha öncə varsa (müştəri daha öncə təmir üçün müraciət edibsə) növbəti addımda istifadəçidən digər məlumatlar soruşulmur (FİN-ə görə avtomatik gəlir).
                      Əgər yoxdursa FİN bazaya əlavə olunur və istifadəçidən digər şəxsi məlumatlar götürülür. (müştəridən FİN kod tələb etmək ideyası prosesi nisbətən uzatsa da bu yol ilə müştərilərin unikallığını izləmək və gələcəkdə həmin koda əsasən müəyyən biznes proseslər yerinə yetirilə bilər. Məsələn 2 və daha artıq təmirə gələnlərə endirimlər və s.)
                      Növbəti addımda təmir üçün təmirçi seçilir və yekunda təmirin izləmə kodu generasiya olunur.
                      Menecer əsas səhifəsində digər keçid hazırki müştərilərin cədvəlinə baxmaq üçündür.
                      
                      http://85.132.33.254:8080/user/search   - İstifadəçilər üçün statusu izləmə səhifəsi. Bu interfeys Azercell terminallarına (və ya vebsayta) 
                      ineqrasiya üçün nəzərdə tutulub. İstifadəçi izləmə kodunu daxil edərək cihazının təmir statusu barədə məlumat ala bilir.
                      Və ya istifadəçiyə verilən QR kodu scan edərək bir başa status səhifəsinə də keçid ala bilər.
                       
--------------------------------------------------------------------------------------------------------------------

#### 2. Git repository'ni yükləyərək öz lokal mühitinizdə işlədə bilərsiniz.

    İstifadədən öncə aşağıdakı vasitələrin təmin olunduğundan əmin olun:
    - Java JDK 1.8+
    - Apache Tomcat (tövsiyyə olunan versiya 8.0.30)
    - Mysql (tövsiyyə olunan versiya 8.0 +)
      repositorydəki sql dump faylını bazaya import edirsiniz (https://github.com/hsynff/hackathon-app-1.02/blob/master/hackathon-db.sql)
      proyekt konfiqurasiyasında bazaya qoşulmaq üçün user: 'root' / password: 'security' kimi qeyd olunub. 
      Qoşulma üçün öz Mysql server konfiqinizi proyetkə uyğun dəyişə bilərsiniz və ya proyektdəki applicationContext.xml 
      faylından (https://github.com/hsynff/hackathon-app-1.02/blob/master/src/main/webapp/WEB-INF/applicationContext.xml) 
      proyetkin qoşulma konfiqini öz bazanıza uyğun dəyişə bilərsiniz.
    
    
    
    
                        
