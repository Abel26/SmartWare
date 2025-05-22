package reports;

/**
 * Kelas ini berfungsi sebagai launcher untuk menjalankan laporan sales
 * secara langsung tanpa melalui UI utama
 * 
 * @author Lenovo
 */
public class RunSalesReport {
    
    /**
     * Method main untuk menjalankan aplikasi laporan
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // Tampilkan laporan sales
            SalesReportGenerator reportGenerator = new SalesReportGenerator();
            reportGenerator.showSalesReport();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 