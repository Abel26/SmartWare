# Sistem Laporan Sales SmartWare

## Ringkasan

Sistem laporan ini diimplementasikan menggunakan JasperReports untuk menghasilkan laporan terstruktur dari data transaksi sales dalam aplikasi SmartWare. Sistem ini memungkinkan pengguna melihat, mencetak, dan mengekspor laporan dalam berbagai format.

## Fitur Utama

- **Laporan Semua Transaksi**: Menampilkan seluruh data transaksi sales
- **Laporan Berdasarkan Periode**: Filter laporan berdasarkan rentang tanggal
- **Pengelompokan Data**: Data dikelompokkan berdasarkan tanggal transaksi
- **Ekspor Multi-Format**: Dukungan ekspor ke PDF, Excel, Word, dan CSV
- **UI yang Intuitif**: Antarmuka pengguna yang mudah digunakan

## Struktur Folder dan File

Berikut adalah struktur folder dan file sistem laporan:

```
src/
└── reports/
    ├── SalesReportGenerator.java      # Kelas utama untuk menghasilkan laporan
    ├── SalesReportView.java           # Form untuk menampilkan dan memilih laporan
    ├── SalesReport.jrxml              # Template laporan untuk semua transaksi
    ├── SalesReportByDate.jrxml        # Template laporan dengan filter tanggal
    ├── README.md                      # Dokumentasi umum (file ini)
    ├── INSTALL.md                     # Panduan instalasi library
    ├── JRXML_GUIDE.md                 # Panduan memahami dan memodifikasi JRXML
    └── IMPLEMENTATION_STEPS.md        # Langkah-langkah implementasi dari awal
```

## Panduan Cepat Penggunaan

### Melihat Laporan Semua Transaksi

1. Buka modul Sales
2. Klik tombol "Laporan"
3. Pada form yang muncul, klik "Tampilkan Semua"
4. Laporan akan ditampilkan dalam viewer JasperReports

### Melihat Laporan Berdasarkan Periode

1. Buka modul Sales
2. Klik tombol "Laporan"
3. Pada form yang muncul, pilih tanggal mulai dan tanggal akhir
4. Klik "Tampilkan"
5. Laporan dengan filter tanggal akan ditampilkan

### Mencetak Laporan

1. Setelah laporan ditampilkan, klik ikon Printer pada toolbar viewer
2. Pilih printer dan atur preferensi cetak
3. Klik "Print"

### Mengekspor Laporan

1. Setelah laporan ditampilkan, klik ikon Save pada toolbar viewer
2. Pilih format yang diinginkan (PDF, Excel, Word, CSV)
3. Pilih lokasi penyimpanan
4. Klik "Save"

## Dokumentasi Detail

Untuk informasi lebih detail, silakan merujuk ke file berikut:

- [INSTALL.md](INSTALL.md) - Panduan instalasi library yang diperlukan
- [JRXML_GUIDE.md](JRXML_GUIDE.md) - Panduan memahami dan memodifikasi file JRXML
- [IMPLEMENTATION_STEPS.md](IMPLEMENTATION_STEPS.md) - Langkah-langkah implementasi detail

## Persyaratan Sistem

- Java Runtime Environment (JRE) 8 atau lebih baru
- Database MySQL
- Library JasperReports dan dependensinya
- Library JCalendar

## Pemecahan Masalah

Lihat bagian pemecahan masalah di [README.md](README.md#pemecahan-masalah) untuk mengatasi masalah umum.

## Kontak Support
Jika mengalami masalah yang tidak tercantum di atas, hubungi tim IT Support di:
- Email: support@smartware.com
- Telepon: 021-XXXXXXX 