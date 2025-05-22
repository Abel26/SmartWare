package reports;

import net.sf.jasperreports.engine.JasperCompileManager;

public class JasperCompiler {
    public static void main(String[] args) {
        try {
            // Compile PartnerReport.jrxml
            System.out.println("Compiling PartnerReport.jrxml...");
            JasperCompileManager.compileReportToFile(
                "src/reports/PartnerReport.jrxml",
                "src/reports/PartnerReport.jasper"
            );
            
            // Compile SalesReport.jrxml
            System.out.println("Compiling SalesReport.jrxml...");
            JasperCompileManager.compileReportToFile(
                "src/reports/SalesReport.jrxml",
                "src/reports/SalesReport.jasper"
            );
            
            // Compile LaporanSKU.jrxml
            System.out.println("Compiling LaporanSKU.jrxml...");
            JasperCompileManager.compileReportToFile(
                "src/report/LaporanSKU.jrxml",
                "src/report/LaporanSKU.jasper"
            );
            
            // Compile LaporanUser.jrxml
            System.out.println("Compiling LaporanUser.jrxml...");
            JasperCompileManager.compileReportToFile(
                "src/report/LaporanUser.jrxml",
                "src/report/LaporanUser.jasper"
            );
            
            // Compile production_report.jrxml
            System.out.println("Compiling production_report.jrxml...");
            JasperCompileManager.compileReportToFile(
                "src/reports/production_report.jrxml",
                "src/reports/production_report.jasper"
            );
            
            System.out.println("All reports compiled successfully!");
            
        } catch (Exception e) {
            System.err.println("Error compiling report: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 