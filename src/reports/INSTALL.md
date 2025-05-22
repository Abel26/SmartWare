# Panduan Instalasi Library untuk Sistem Laporan Sales

## Library yang Diperlukan

Untuk menjalankan sistem laporan Sales SmartWare, Anda perlu menginstal library berikut:

1. **JasperReports (6.20.0 atau terbaru)**
   - jasperreports-6.20.0.jar
   - jasperreports-fonts-6.20.0.jar
   - jasperreports-functions-6.20.0.jar
   - commons-digester-2.1.jar
   - commons-collections4-4.4.jar
   - commons-beanutils-1.9.4.jar
   - commons-logging-1.2.jar

2. **JCalendar (1.4 atau terbaru)**
   - jcalendar-1.4.jar

3. **Library pendukung untuk ekspor laporan**
   - poi-5.2.2.jar (untuk ekspor Excel)
   - itext-2.1.7.js8.jar (untuk ekspor PDF)

## Cara Instalasi Library

### Metode 1: Menggunakan NetBeans IDE

1. Buka proyek di NetBeans
2. Klik kanan pada nama proyek di Project Explorer
3. Pilih "Properties"
4. Pilih "Libraries" di panel kiri
5. Klik tombol "Add JAR/Folder"
6. Arahkan ke lokasi file JAR yang ingin ditambahkan
7. Pilih semua file JAR yang diperlukan
8. Klik "Open" untuk menambahkan library ke proyek
9. Klik "OK" untuk menyimpan perubahan

### Metode 2: Menambahkan Library secara Manual

1. Buat folder `lib` di direktori proyek jika belum ada
2. Salin semua file JAR yang diperlukan ke folder `lib`
3. Tambahkan JAR ke classpath proyek

### Pengunduhan Library

Anda dapat mengunduh library yang diperlukan dari tautan berikut:

1. **JasperReports**: [Unduh dari SourceForge](https://sourceforge.net/projects/jasperreports/files/)
2. **JCalendar**: [Unduh dari Maven Repository](https://mvnrepository.com/artifact/com.toedter/jcalendar/1.4)
3. **Apache POI**: [Unduh dari Apache POI](https://poi.apache.org/download.html)
4. **iText**: [Unduh dari Maven Repository](https://mvnrepository.com/artifact/com.lowagie/itext/2.1.7.js8)

## Verifikasi Instalasi

Untuk memverifikasi bahwa semua library telah terinstal dengan benar:

1. Jalankan aplikasi
2. Buka modul Sales
3. Klik tombol "Laporan"
4. Coba tampilkan laporan dengan mengklik "Tampilkan Semua"

Jika laporan muncul tanpa error, berarti instalasi library berhasil.

## Pemecahan Masalah

### ClassNotFoundException atau NoClassDefFoundError

Error ini menunjukkan bahwa library yang diperlukan tidak ditemukan dalam classpath:

1. Periksa apakah semua JAR yang diperlukan sudah ditambahkan ke proyek
2. Restart NetBeans dan proyek setelah menambahkan library
3. Pastikan tidak ada konflik versi library

### UnsatisfiedLinkError

Error ini mungkin muncul saat menggunakan fitur cetak:

1. Pastikan printer telah terkonfigurasi dengan benar
2. Periksa apakah driver printer sudah terinstal

## Informasi Tambahan

Library JasperReports memiliki dependensi yang kompleks. Jika Anda mengalami kesulitan, pertimbangkan untuk menggunakan manajer dependensi seperti Maven atau Gradle untuk memudahkan pengelolaan library. 