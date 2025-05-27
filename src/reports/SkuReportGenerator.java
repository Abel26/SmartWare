package reports;

import connection.connection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import java.util.Date;
import java.text.SimpleDateFormat;

public class SkuReportGenerator {
    
    public void showSkuReport() {
        try {
            Connection conn = new connection().connect();
            
            // Create parameters map for the report
            HashMap<String, Object> parameters = new HashMap<>();
            
            // Add current date for footer
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss");
            parameters.put("printDate", "Dicetak pada: " + sdf.format(new Date()));
            
            // Add company header information
            parameters.put("companyName", "SmartWare");
            parameters.put("companyAddress", "Jl. Example No. 123");
            parameters.put("companyContact", "Telp: (021) 555-0123");
            
            // Load and compile the report template
            String reportPath = "reports/SkuReport.jrxml";
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);
            
            // Fill the report with data
            JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);
            
            // View the report
            JasperViewer viewer = new JasperViewer(print, false);
            viewer.setVisible(true);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error generating report: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 