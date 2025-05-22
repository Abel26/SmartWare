# Cara Memperbaiki Masalah Laporan di SmartWare

## Masalah

Saat tombol "Laporan" diklik, tidak ada laporan yang muncul. Ini disebabkan oleh:
1. Library JasperReports belum diinstal dengan benar
2. File JRXML mungkin tidak berada di lokasi yang benar

## Solusi

### Solusi 1: Menggunakan Tampilan Laporan Sederhana (SimpleReportViewer)

Anda sudah bisa menggunakan fitur laporan meskipun JasperReports belum diinstal. Saat mengklik tombol Laporan, sistem akan memeriksa ketersediaan JasperReports dan akan menampilkan laporan sederhana jika library tidak ditemukan.

### Solusi 2: Menginstal JasperReports dengan Benar

Untuk mendapatkan tampilan laporan yang lebih baik dan fitur ekspor PDF, ikuti langkah-langkah berikut:

1. **Pastikan folder `lib` sudah berisi file JAR JasperReports**
   - Buka folder `lib` di direktori proyek
   - Salin semua file JAR dari JasperReports-6.19.1 yang sudah Anda unduh
   - File utama yang diperlukan: `jasperreports-6.19.1.jar`
   - Jangan lupa file pendukung seperti:
     - `commons-beanutils-1.9.4.jar`
     - `commons-collections4-4.4.jar` 
     - `commons-digester-2.1.jar`
     - `commons-logging-1.2.jar`
     - `itext-2.1.7.js9.jar` (untuk ekspor PDF)
     - Dan lain-lain (lihat file INSTALL_JASPERREPORTS.md)

2. **Tambahkan library ke classpath di NetBeans**
   - Klik kanan pada proyek di Project Explorer
   - Pilih "Properties"
   - Pilih "Libraries" di panel kiri
   - Klik tab "Compile"
   - Klik tombol "Add JAR/Folder"
   - Pilih semua file JAR dari folder `lib`
   - Klik "OK"

3. **Pastikan file JRXML sudah berada di lokasi yang benar**
   - File `SimpleSalesReport.jrxml` harus ada di folder `src/reports`
   - Jika file tidak ada, Anda dapat mencoba mengekstraknya dari JAR proyek
   - Atau gunakan file yang telah disediakan di proyek

4. **Build ulang proyek**
   - Pilih menu "Clean and Build Project" di NetBeans
   - Tunggu hingga proses build selesai

5. **Jalankan aplikasi**
   - Klik tombol "Laporan" untuk menampilkan laporan

## Alat Bantuan Debugging

Kami telah menyediakan kelas bantuan untuk debugging:

1. **DebugSalesReport.java**
   - Jalankan kelas ini untuk memeriksa masalah dengan JasperReports
   - Kelas ini akan memeriksa:
     - Koneksi database
     - Keberadaan file JRXML
     - Instalasi JasperReports

2. **RunSalesReport.java**
   - Jalankan kelas ini untuk langsung menampilkan laporan tanpa melalui UI utama

3. **SimpleReportViewer.java**
   - Tampilan laporan sederhana yang tidak memerlukan JasperReports
   - Digunakan sebagai fallback jika JasperReports tidak tersedia

## Troubleshooting

### Error "JasperReport class cannot be resolved"
- Library belum ditambahkan ke classpath
- Solusi: Ikuti langkah 1 dan 2 di atas

### Error "File JRXML tidak ditemukan"
- File template tidak berada di lokasi yang benar
- Solusi: Pastikan file ada di folder `src/reports` dan pastikan nama file sesuai

### Error saat kompilasi report
- Pastikan versi JasperReports yang digunakan sesuai dengan file JRXML
- Coba gunakan versi JasperReports yang lain jika masih bermasalah

## Kontak Bantuan

Jika masih mengalami masalah setelah mengikuti panduan ini, silakan hubungi tim support. 