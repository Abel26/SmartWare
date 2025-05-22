# Panduan Memahami dan Memodifikasi File JRXML

File JRXML (JasperReports XML) adalah file yang mendefinisikan struktur dan tampilan laporan JasperReports. File ini ditulis dalam format XML dan dapat dimodifikasi untuk menyesuaikan tampilan laporan.

## Struktur Dasar File JRXML

Berikut adalah struktur dasar file JRXML dalam sistem laporan SmartWare:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<jasperReport ...>
    <!-- Properties laporan -->
    <property name="..." value="..."/>
    
    <!-- Definisi style laporan -->
    <style name="..." .../>
    
    <!-- Parameter laporan (jika ada) -->
    <parameter name="START_DATE" class="java.lang.String"/>
    
    <!-- Query SQL untuk mengambil data -->
    <queryString language="SQL">
        <![CDATA[SELECT ... FROM ... WHERE ...]]>
    </queryString>
    
    <!-- Definisi field -->
    <field name="no_sales" class="java.lang.String"/>
    ...
    
    <!-- Variabel kalkulasi -->
    <variable name="total1" calculation="Sum">
        <variableExpression><![CDATA[$F{total}]]></variableExpression>
    </variable>
    
    <!-- Bagian latar belakang -->
    <background>...</background>
    
    <!-- Bagian judul -->
    <title>...</title>
    
    <!-- Header halaman -->
    <pageHeader>...</pageHeader>
    
    <!-- Header kolom -->
    <columnHeader>...</columnHeader>
    
    <!-- Detail record -->
    <detail>...</detail>
    
    <!-- Footer kolom -->
    <columnFooter>...</columnFooter>
    
    <!-- Footer halaman -->
    <pageFooter>...</pageFooter>
    
    <!-- Ringkasan -->
    <summary>...</summary>
</jasperReport>
```

## Elemen Penting dalam JRXML

### 1. Properties Laporan
```xml
<property name="com.jaspersoft.studio.data.sql.tables" value=""/>
<property name="com.jaspersoft.studio.data.defaultdataadapter" value="Mysql Connection"/>
```
Properties ini mengatur setelan khusus laporan.

### 2. Style
```xml
<style name="Title" fontName="Times New Roman" fontSize="26" isBold="true"/>
```
Style mendefinisikan format teks dan elemen visual.

### 3. Parameter
```xml
<parameter name="START_DATE" class="java.lang.String"/>
```
Parameter memungkinkan pengguna memberikan input ke laporan.

### 4. Query SQL
```xml
<queryString language="SQL">
    <![CDATA[SELECT ... FROM ... WHERE ...]]>
</queryString>
```
Query mendefinisikan bagaimana data diambil dari database.

### 5. Fields
```xml
<field name="no_sales" class="java.lang.String">
    <property name="com.jaspersoft.studio.field.name" value="no_sales"/>
    <property name="com.jaspersoft.studio.field.label" value="no_sales"/>
    <property name="com.jaspersoft.studio.field.tree.path" value="tb_sales_request"/>
</field>
```
Fields menentukan kolom data yang diambil dari database.

### 6. Variables
```xml
<variable name="total1" class="java.lang.Double" calculation="Sum">
    <variableExpression><![CDATA[$F{total}]]></variableExpression>
</variable>
```
Variables digunakan untuk perhitungan seperti sum, average, dll.

## Bagian Laporan

### 1. Title
Bagian ini muncul di awal laporan dan biasanya berisi judul laporan dan informasi pendahuluan.

### 2. Page Header
Muncul di bagian atas setiap halaman laporan.

### 3. Column Header
Berisi judul kolom tabel.

### 4. Detail
Bagian ini berulang untuk setiap baris data dari query.

### 5. Column Footer
Muncul di bagian bawah kolom tabel.

### 6. Page Footer
Muncul di bagian bawah setiap halaman, biasanya berisi nomor halaman.

### 7. Summary
Bagian ini muncul di akhir laporan dan biasanya berisi total atau ringkasan.

## Cara Memodifikasi File JRXML

### Menggunakan Jaspersoft Studio

Cara terbaik untuk memodifikasi JRXML adalah menggunakan Jaspersoft Studio:

1. Unduh dan instal [Jaspersoft Studio](https://community.jaspersoft.com/project/jaspersoft-studio)
2. Buka file JRXML dengan Jaspersoft Studio
3. Gunakan antarmuka visual untuk memodifikasi laporan
4. Simpan perubahan

### Modifikasi Manual (Tidak Disarankan)

Jika Anda ingin memodifikasi file JRXML secara manual:

1. Buka file JRXML dengan editor teks
2. Lakukan perubahan yang diperlukan
3. Pastikan struktur XML tetap valid
4. Simpan perubahan

## Tips Memodifikasi Laporan

### Mengubah Query

Untuk mengubah data yang ditampilkan, modifikasi bagian query:

```xml
<queryString language="SQL">
    <![CDATA[SELECT s.no_sales, s.date, s.deskripsi, ...
FROM tb_sales_request AS s
LEFT JOIN ...
WHERE ...
ORDER BY ...]]>
</queryString>
```

### Menambahkan Field Baru

Jika Anda menambahkan kolom baru dalam query, Anda juga perlu mendefinisikan field:

```xml
<field name="new_field" class="java.lang.String">
    <property name="com.jaspersoft.studio.field.name" value="new_field"/>
    <property name="com.jaspersoft.studio.field.label" value="new_field"/>
    <property name="com.jaspersoft.studio.field.tree.path" value="table_name"/>
</field>
```

### Mengubah Tampilan Text

Untuk mengubah teks statis:

```xml
<staticText>
    <reportElement ... />
    <textElement ... />
    <text><![CDATA[Teks Baru]]></text>
</staticText>
```

### Mengubah Format Angka

Untuk mengubah format angka:

```xml
<textField pattern="#,##0.00;-#,##0.00">
    <reportElement ... />
    ...
</textField>
```

## Kesimpulan

File JRXML adalah dasar dari sistem laporan JasperReports. Dengan memahami strukturnya, Anda dapat menyesuaikan laporan sesuai kebutuhan. Untuk perubahan kompleks, disarankan menggunakan Jaspersoft Studio daripada memodifikasi file JRXML secara manual. 