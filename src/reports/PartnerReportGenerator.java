package reports;

import connection.connection;
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

public class PartnerReportGenerator {
    
    public void showPartnerReport() {
        try {
            // Mendapatkan koneksi database
            Connection conn = new connection().connect();
            if (conn == null) {
                throw new Exception("Koneksi database gagal!");
            }
            
            // Load file JRXML
            InputStream reportStream = getClass().getResourceAsStream("/reports/PartnerReport.jrxml");
            if (reportStream == null) {
                JOptionPane.showMessageDialog(null, "File laporan tidak ditemukan di path: /reports/PartnerReport.jrxml");
                return;
            }
            
            // Kompilasi report
            JasperDesign jasperDesign = JRXmlLoader.load(reportStream);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            
            // Parameter untuk report (kosong karena tidak ada parameter)
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
} 