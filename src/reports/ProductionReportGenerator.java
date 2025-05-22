package reports;

import connection.connection;
import java.sql.Connection;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class ProductionReportGenerator {
    
    public void showProductionReport() {
        try {
            // Mendapatkan koneksi database
            Connection conn = new connection().connect();
            if (conn == null) {
                throw new Exception("Koneksi database gagal");
            }

            // Load file JRXML
            String reportPath = "src/reports/production_report.jrxml";
            
            // Compile report
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);
            
            // Parameter untuk report (jika diperlukan)
            HashMap<String, Object> parameters = new HashMap<>();
            
            // Mengisi report dengan data
            JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);
            
            // Menampilkan report menggunakan JasperViewer
            JasperViewer viewer = new JasperViewer(print, false);
            viewer.setTitle("Laporan Request Production");
            viewer.setVisible(true);
            
            // Tutup koneksi
            conn.close();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                "Error saat membuat laporan: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
} 