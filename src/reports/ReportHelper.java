package reports;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import java.util.HashMap;
import java.util.Map;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.io.File;

public class ReportHelper {
    
    public static void showReport(String reportPath, String reportTitle, Connection conn) {
        try {
            // Compile terlebih dahulu jrxml menjadi jasper
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);
            
            // Parameter untuk judul laporan
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("REPORT_TITLE", reportTitle);
            
            // Mendapatkan path absolut untuk logo
            String currentDir = new File(reportPath).getParent();
            String logoPath = new File(currentDir, "logo_perusahaan.png").getAbsolutePath();
            parameters.put("LOGO_PATH", logoPath);
            
            System.out.println("Logo path: " + logoPath); // Untuk debugging
            
            // Mengisi Report dengan data dari database
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
            
            // Menampilkan report
            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error menampilkan report: " + e.getMessage());
        }
    }
} 