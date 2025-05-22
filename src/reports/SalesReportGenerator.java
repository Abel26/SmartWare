/*
 * SalesReportGenerator.java
 * Class ini digunakan untuk menghasilkan laporan sales menggunakan JasperReports
 */
package reports;

import connection.connection;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Lenovo
 */
public class SalesReportGenerator {
    
    /**
     * Menampilkan laporan sales tanpa parameter
     */
    public void showSalesReport() {
        try {
            // Mendapatkan koneksi database
            Connection conn = new connection().connect();
            
            // Load file JRXML
            InputStream reportStream = getClass().getResourceAsStream("/reports/SimpleSalesReport.jrxml");
            if (reportStream == null) {
                // Jika SimpleSalesReport tidak ditemukan, coba SalesReport.jrxml
                reportStream = getClass().getResourceAsStream("/reports/SalesReport.jrxml");
                if (reportStream == null) {
                    JOptionPane.showMessageDialog(null, "File laporan tidak ditemukan di path: /reports/SimpleSalesReport.jrxml atau /reports/SalesReport.jrxml");
                    return;
                }
            }
            
            // Kompilasi report
            JasperDesign jasperDesign = JRXmlLoader.load(reportStream);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            
            // Parameter untuk report (kosong)
            HashMap<String, Object> parameters = new HashMap<>();
            
            // Mengisi report dengan data
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
            
            // Menampilkan report dalam viewer
            JasperViewer.viewReport(jasperPrint, false);
            
        } catch (JRException e) {
            JOptionPane.showMessageDialog(null, "Gagal menampilkan laporan: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Menampilkan laporan sales dengan filter tanggal
     * @param startDate tanggal awal (format yyyy-MM-dd)
     * @param endDate tanggal akhir (format yyyy-MM-dd)
     */
    public void showSalesReportByDate(String startDate, String endDate) {
        try {
            // Mendapatkan koneksi database
            Connection conn = new connection().connect();
            
            // Load file JRXML
            InputStream reportStream = getClass().getResourceAsStream("/reports/SalesReportByDate.jrxml");
            if (reportStream == null) {
                JOptionPane.showMessageDialog(null, "File laporan tidak ditemukan. Periksa path: /reports/SalesReportByDate.jrxml");
                return;
            }
            
            // Kompilasi report
            JasperDesign jasperDesign = JRXmlLoader.load(reportStream);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            
            // Parameter untuk report
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("START_DATE", startDate);
            parameters.put("END_DATE", endDate);
            
            // Mengisi report dengan data
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
            
            // Menampilkan report dalam viewer
            JasperViewer.viewReport(jasperPrint, false);
            
        } catch (JRException e) {
            JOptionPane.showMessageDialog(null, "Gagal menampilkan laporan: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 