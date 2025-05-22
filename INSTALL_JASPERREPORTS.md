# Panduan Instalasi JasperReports untuk SmartWare

## Langkah 1: Persiapan Library JasperReports

Anda telah mengunduh JasperReports versi 6.19.1. Berikut adalah langkah-langkah untuk mengintegrasikannya ke dalam proyek SmartWare:

1. Ekstrak file zip JasperReports-6.19.1 yang telah Anda unduh
2. Salin semua file JAR dari folder `jasperreports-6.19.1/lib` dan `jasperreports-6.19.1/dist` ke folder `lib` di proyek SmartWare Anda

## Langkah 2: Menambahkan Library ke Proyek di NetBeans

1. Buka proyek SmartWare di NetBeans
2. Klik kanan pada proyek di Project Explorer
3. Pilih "Properties"
4. Pilih "Libraries" di panel kiri
5. Klik tab "Compile"
6. Klik tombol "Add JAR/Folder"
7. Arahkan ke folder `lib` di proyek SmartWare Anda
8. Pilih semua file JAR dan klik "Open"
9. Klik "OK" untuk menutup jendela Properties

## File JAR yang Diperlukan

Berikut ini adalah daftar library utama yang diperlukan untuk menjalankan JasperReports:

1. `jasperreports-6.19.1.jar` (wajib)
2. `commons-beanutils-1.9.4.jar`
3. `commons-collections4-4.4.jar`
4. `commons-digester-2.1.jar`
5. `commons-logging-1.2.jar`
6. `itext-2.1.7.js9.jar` (untuk ekspor PDF)
7. `commons-lang3-3.4.jar`
8. `servlet-api.jar`
9. `groovy-all-2.4.21.jar`
10. `jfreechart-1.0.19.jar` (untuk membuat grafik)
11. `ecj-3.21.0.jar` (kompiler Java)

## Catatan Pengguna

* Library JasperReports harus ditambahkan ke proyek sebelum menjalankan fitur laporan
* File JRXML (template laporan) telah tersedia di folder `src/reports`
* Jika ada error saat kompilasi atau runtime, pastikan semua library yang diperlukan sudah ditambahkan dengan benar
* Jika menggunakan jenis font khusus dalam laporan, pastikan font tersebut juga ditambahkan ke proyek

## Troubleshooting

### Masalah: NoClassDefFoundError atau ClassNotFoundException

Jika Anda melihat error seperti:
```
java.lang.NoClassDefFoundError: net/sf/jasperreports/engine/JasperReport
```

Artinya library JasperReports belum ditambahkan ke classpath proyek. Pastikan semua file JAR yang diperlukan sudah ditambahkan seperti pada Langkah 2 di atas.

### Masalah: Tidak Dapat Mengkompilasi Laporan

Jika melihat error:
```
net.sf.jasperreports.engine.JRException: Error compiling report
```

Pastikan format file JRXML sudah benar dan kompatibel dengan versi JasperReports yang digunakan. JasperReports 6.19.1 memerlukan file JRXML yang kompatibel dengan versinya. 