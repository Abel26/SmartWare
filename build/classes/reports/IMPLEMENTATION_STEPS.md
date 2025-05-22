# Langkah-Langkah Implementasi iReport untuk Pemula

Berikut adalah langkah-langkah detail untuk mengimplementasikan sistem laporan iReport pada aplikasi Java. Panduan ini ditujukan untuk pengguna yang baru pertama kali menggunakan iReport/JasperReports.

## 1. Persiapan Lingkungan

### 1.1 Mengunduh dan Menyiapkan Library
1. Unduh library JasperReports dari [SourceForge](https://sourceforge.net/projects/jasperreports/files/)
2. Unduh library JCalendar dari [Maven Repository](https://mvnrepository.com/artifact/com.toedter/jcalendar/1.4)
3. Buat folder `lib` di dalam proyek
4. Tambahkan semua library JAR yang diperlukan ke folder `lib`

### 1.2 Mengunduh Jaspersoft Studio
1. Unduh Jaspersoft Studio dari [Community Portal](https://community.jaspersoft.com/project/jaspersoft-studio)
2. Instal sesuai petunjuk untuk sistem operasi Anda

## 2. Membuat Struktur Folder Report

1. Buat folder `reports` di dalam `src`
2. Dalam folder ini kita akan menyimpan semua file JRXML dan class Java untuk report generator

## 3. Membuat File JRXML dengan Jaspersoft Studio

### 3.1 Memulai Proyek Baru
1. Buka Jaspersoft Studio
2. Pilih File > New > Jasper Report
3. Pilih template "Blank A4"
4. Beri nama "SalesReport.jrxml"

### 3.2 Mengatur Koneksi Database
1. Klik kanan pada "Data Adapters" di Repository Explorer
2. Pilih "Create Data Adapter"
3. Pilih "Database JDBC Connection"
4. Isi informasi koneksi:
   - Name: MySQL Connection
   - JDBC Driver: com.mysql.cj.jdbc.Driver
   - JDBC URL: jdbc:mysql://localhost:3308/db_smartware
   - Username: root
   - Password: (kosong/sesuai password)
5. Test koneksi dan klik Finish

### 3.3 Membuat Query
1. Klik kanan pada SalesReport.jrxml
2. Pilih "Create Dataset"
3. Masukkan query SQL yang sama dengan yang ada di `dashboard.java`:
   ```sql
   SELECT s.no_sales, s.date, s.deskripsi, 
          u.name AS operator_name, 
          p.name AS partner_name, 
          s.qty, s.price, s.discount, s.total
   FROM tb_sales_request AS s
   LEFT JOIN tb_users AS u ON s.id_operator = u.id
   LEFT JOIN tb_partner AS p ON s.id_partner = p.id
   ```
4. Klik "Read Fields" untuk membaca semua field dari query

### 3.4 Mendesain Laporan
1. Drag and drop band Title dan tentukan tingginya
2. Tambahkan Static Text, Image, dan elemen lain di Title
3. Desain Column Header dengan menambahkan Static Text untuk setiap kolom
4. Desain Detail band dengan menambahkan Text Field untuk setiap field data
5. Tambahkan Page Footer dengan nomor halaman
6. Tambahkan Summary band dengan total penjualan

### 3.5 Grouping Data (Opsional)
1. Buka "Outline" view
2. Klik kanan pada report root > Create Group
3. Pilih field date dan gunakan ekspresi untuk grouping berdasarkan tanggal

### 3.6 Menyimpan File JRXML
1. Simpan file JRXML di folder `src/reports`
2. Buat versi kedua untuk filter berdasarkan tanggal: "SalesReportByDate.jrxml"
3. Pada file kedua, tambahkan parameter START_DATE dan END_DATE
4. Modifikasi query dengan menambahkan WHERE clause untuk filter tanggal

## 4. Membuat Class Report Generator di Java

### 4.1 Membuat SalesReportGenerator
1. Buat class baru `SalesReportGenerator.java`
2. Import library JasperReports yang diperlukan
3. Buat method untuk menampilkan report tanpa parameter
4. Buat method untuk menampilkan report dengan filter tanggal

### 4.2 Membuat Form untuk Report
1. Buat JFrame baru: `SalesReportView.java`
2. Tambahkan komponen UI:
   - Label dan judul
   - Button untuk menampilkan semua data
   - DateChooser untuk memilih tanggal mulai dan akhir
   - Button untuk menampilkan berdasarkan filter tanggal
3. Implementasikan event handler untuk button

## 5. Mengintegrasikan dengan Aplikasi Utama

1. Modifikasi class `dashboard.java`
2. Tambahkan tombol "Laporan" di UI
3. Implementasikan event handler untuk menampilkan form laporan

## 6. Uji Coba dan Debugging

### 6.1 Uji Coba Laporan
1. Jalankan aplikasi
2. Buka form Sales Dashboard
3. Klik tombol "Laporan"
4. Test kedua jenis laporan: semua data dan berdasarkan tanggal

### 6.2 Debugging Umum
1. Pastikan semua library JasperReports sudah diinclude di classpath
2. Jika laporan tidak muncul, cek koneksi database
3. Jika ada error, periksa struktur JRXML dan query
4. Log semua error untuk analisis

## 7. Dokumentasi dan Penyelesaian

1. Buat dokumentasi cara penggunaan (README.md)
2. Buat dokumentasi teknis (JRXML_GUIDE.md)
3. Buat panduan installasi (INSTALL.md)
4. Pastikan semua file di commit ke repositori

## Bonus: Tips untuk Pengembangan Lanjutan

1. **Chart dan Grafik**: Tambahkan chart untuk visualisasi data penjualan
2. **Subreports**: Tambahkan subreport untuk detail yang lebih spesifik
3. **Crosstab**: Buat laporan pivot/crosstab untuk analisis multi-dimensi
4. **Export Formatter**: Tambahkan opsi untuk mengekspor ke format lain (Excel, PDF, Word)
5. **Parameter Lebih Kompleks**: Tambahkan filter berdasarkan operator, partner, atau nilai penjualan

---

Dengan mengikuti langkah-langkah di atas, Anda akan berhasil mengimplementasikan sistem laporan iReport/JasperReports yang komprehensif untuk data penjualan SmartWare. 