# Dokumentasi Laporan Sales - SmartWare

## Deskripsi
Fitur laporan sales pada aplikasi SmartWare memungkinkan pengguna untuk melihat data transaksi penjualan dalam format laporan yang terstruktur. Laporan dapat ditampilkan langsung dari tombol "Laporan" pada halaman sales dashboard.

## File yang Terkait
1. **SalesReportGenerator.java** - Class untuk menampilkan laporan
2. **SimpleSalesReport.jrxml** - Template laporan sederhana
3. **SalesReport.jrxml** - Template laporan lengkap
4. **SalesReportByDate.jrxml** - Template laporan dengan filter tanggal
5. **RunSalesReport.java** - Class untuk menjalankan laporan secara langsung

## Cara Penggunaan
1. **Dari Dashboard Sales**
   - Buka halaman Dashboard Sales
   - Klik tombol "Laporan"
   - Laporan akan otomatis ditampilkan dalam Jasper Viewer

2. **Menjalankan Langsung dari Class RunSalesReport**
   - Jalankan class `RunSalesReport.java`
   - Laporan akan langsung ditampilkan dalam Jasper Viewer

## Struktur Laporan
Laporan sales menampilkan informasi berikut:
- Nomor Sales
- Tanggal Transaksi
- Deskripsi
- Kuantitas (Qty)
- Harga
- Diskon
- Total Harga
- Total keseluruhan (di bagian summary)

## Informasi Tambahan
- Library yang digunakan: JasperReports 6.19.1
- Data diambil dari tabel: tb_sales_request, tb_users, tb_partner
- Format laporan: Tabel dengan header, detail, dan summary

## Troubleshooting

### Laporan Tidak Muncul
1. Pastikan library JasperReports sudah diinstal dengan benar
   - Lihat panduan instalasi di file INSTALL_JASPERREPORTS.md
   
2. Pastikan koneksi database berfungsi
   - Periksa apakah aplikasi dapat mengakses data sales
   
3. Pastikan file template JRXML tersedia
   - Periksa apakah file SimpleSalesReport.jrxml ada di folder src/reports

### Error Kompilasi
Jika terjadi error saat kompilasi report, pastikan:
1. Format JRXML valid
2. Library JasperReports yang digunakan sesuai
3. Field pada JRXML sesuai dengan struktur database

## Pengembangan Lebih Lanjut
Untuk pengembangan lebih lanjut, Anda dapat:
1. Menambahkan filter laporan (tanggal, kategori, dll)
2. Menambahkan grafik pada laporan
3. Mengimplementasikan ekspor ke format PDF, Excel, atau Word 