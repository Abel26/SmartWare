package reports;

import connection.connection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 * Kelas untuk melakukan debugging pada masalah jasper report
 * @author Lenovo
 */
public class DebugSalesReport {
    
    /**
     * Method main untuk debugging
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // Langkah 1: Periksa koneksi database
            Connection conn = null;
            try {
                conn = new connection().connect();
                System.out.println("Koneksi database: " + (conn != null ? "BERHASIL" : "GAGAL"));
            } catch (Exception e) {
                System.err.println("Error koneksi database: " + e.getMessage());
                e.printStackTrace();
                return;
            }
            
            // Langkah 2: Periksa file JRXML
            File sourceDir = new File("src/reports");
            System.out.println("Source dir exists: " + sourceDir.exists());
            System.out.println("Source dir is dir: " + sourceDir.isDirectory());
            
            if (sourceDir.exists() && sourceDir.isDirectory()) {
                System.out.println("Files in src/reports:");
                File[] files = sourceDir.listFiles();
                if (files != null) {
                    for (File file : files) {
                        System.out.println(" - " + file.getName());
                    }
                } else {
                    System.out.println("Tidak ada file di direktori");
                }
            }
            
            // Langkah 3: Coba copy file JRXML ke tempat yang bisa diakses
            try {
                File jrxmlFile = new File("src/reports/SimpleSalesReport.jrxml");
                if (jrxmlFile.exists()) {
                    // Buat directory temp jika belum ada
                    File tempDir = new File("temp");
                    if (!tempDir.exists()) {
                        tempDir.mkdir();
                    }
                    
                    // Copy file
                    File destFile = new File("temp/SimpleSalesReport.jrxml");
                    copyFile(jrxmlFile, destFile);
                    System.out.println("File berhasil dicopy ke: " + destFile.getAbsolutePath());
                    
                    // Tampilkan pesan
                    JOptionPane.showMessageDialog(null, 
                        "File JRXML ditemukan dan dicopy ke temp folder.\n" +
                        "Path: " + destFile.getAbsolutePath() + "\n\n" +
                        "Silakan cek error di konsole NetBeans untuk detail lebih lanjut.",
                        "Debug Info",
                        JOptionPane.INFORMATION_MESSAGE);
                } else {
                    System.out.println("File JRXML tidak ditemukan");
                    JOptionPane.showMessageDialog(null, 
                        "File JRXML tidak ditemukan di src/reports/SimpleSalesReport.jrxml\n" +
                        "Silakan periksa lokasi file JRXML anda.",
                        "Debug Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                System.err.println("Error saat mencoba mengakses file: " + e.getMessage());
                e.printStackTrace();
            }
            
            // Langkah 4: Cek library Jasper Reports
            try {
                Class.forName("net.sf.jasperreports.engine.JasperReport");
                System.out.println("JasperReport class DITEMUKAN - library telah ditambahkan dengan benar");
                JOptionPane.showMessageDialog(null, 
                    "Library JasperReports ditemukan di classpath proyek.\n" +
                    "Pastikan semua library pendukung juga sudah ditambahkan.",
                    "Library Info",
                    JOptionPane.INFORMATION_MESSAGE);
            } catch (ClassNotFoundException e) {
                System.out.println("JasperReport class TIDAK DITEMUKAN - library belum ditambahkan dengan benar");
                JOptionPane.showMessageDialog(null, 
                    "Library JasperReports TIDAK ditemukan di classpath proyek.\n" +
                    "Silakan tambahkan library JasperReports dan dependensinya.\n\n" +
                    "Lihat panduan di file INSTALL_JASPERREPORTS.md",
                    "Library Error",
                    JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (Exception e) {
            System.err.println("Error umum: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Method untuk menyalin file dari satu lokasi ke lokasi lain
     * @param sourceFile file sumber
     * @param destFile file tujuan
     * @throws Exception jika terjadi error saat menyalin
     */
    private static void copyFile(File sourceFile, File destFile) throws Exception {
        try (FileInputStream fis = new FileInputStream(sourceFile);
             FileOutputStream fos = new FileOutputStream(destFile)) {
            
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
        }
    }
} 