package reports;

import connection.connection;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.util.Date;

/**
 * Kelas alternatif untuk menampilkan tabel data penjualan
 * Digunakan jika JasperReports bermasalah
 * 
 * @author Lenovo
 */
public class SimpleReportViewer extends JFrame {
    
    private JTable table;
    private DefaultTableModel tableModel;
    private JLabel totalLabel;
    private DecimalFormat currencyFormat = new DecimalFormat("#,##0.00");
    
    /**
     * Konstruktor untuk membuat viewer sederhana
     */
    public SimpleReportViewer() {
        initComponents();
        loadData();
        
        setSize(800, 600);
        setLocationRelativeTo(null);
        setTitle("Laporan Penjualan - SmartWare");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    /**
     * Inisialisasi komponen UI
     */
    private void initComponents() {
        // Panel utama dengan BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        
        // Panel judul
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("LAPORAN PENJUALAN SMARTWARE");
        titleLabel.setFont(new java.awt.Font("Calibri", 1, 24));
        titlePanel.add(titleLabel);
        
        // Panel untuk tanggal
        JPanel datePanel = new JPanel();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        JLabel dateLabel = new JLabel("Tanggal: " + sdf.format(new java.util.Date()));
        dateLabel.setFont(new java.awt.Font("Calibri", 0, 14));
        datePanel.add(dateLabel);
        
        // Panel header (gabungan judul dan tanggal)
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.add(titlePanel, BorderLayout.CENTER);
        headerPanel.add(datePanel, BorderLayout.SOUTH);
        
        // Tabel untuk data
        String[] columnNames = {
            "No Sales", "Tanggal", "Deskripsi", "Operator", "Tujuan", 
            "Qty", "Harga", "Diskon", "Total"
        };
        
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Membuat semua sel tidak bisa diedit
            }
        };
        
        table = new JTable(tableModel);
        table.getTableHeader().setReorderingAllowed(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        
        // Buat tampilan kolom lebih baik
        table.getColumnModel().getColumn(0).setPreferredWidth(80);  // No Sales
        table.getColumnModel().getColumn(1).setPreferredWidth(100); // Tanggal
        table.getColumnModel().getColumn(2).setPreferredWidth(150); // Deskripsi
        table.getColumnModel().getColumn(3).setPreferredWidth(100); // Operator
        table.getColumnModel().getColumn(4).setPreferredWidth(100); // Tujuan
        table.getColumnModel().getColumn(5).setPreferredWidth(50);  // Qty
        table.getColumnModel().getColumn(6).setPreferredWidth(80);  // Harga
        table.getColumnModel().getColumn(7).setPreferredWidth(70);  // Diskon
        table.getColumnModel().getColumn(8).setPreferredWidth(100); // Total
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(750, 400));
        
        // Panel untuk total
        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalLabel = new JLabel("TOTAL: Rp 0");
        totalLabel.setFont(new java.awt.Font("Calibri", 1, 16));
        totalPanel.add(totalLabel);
        
        // Panel untuk tombol
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton printButton = new JButton("Cetak PDF");
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(SimpleReportViewer.this, 
                    "Fitur cetak PDF memerlukan JasperReports.\n" +
                    "Silakan instal library terlebih dahulu.");
            }
        });
        
        JButton closeButton = new JButton("Tutup");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        buttonPanel.add(printButton);
        buttonPanel.add(closeButton);
        
        // Menambahkan komponen ke panel utama
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(totalPanel, BorderLayout.SOUTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Menambahkan panel utama ke frame
        setContentPane(mainPanel);
    }
    
    /**
     * Memuat data dari database
     */
    private void loadData() {
        try {
            Connection conn = new connection().connect();
            if (conn == null) {
                JOptionPane.showMessageDialog(this, 
                    "Koneksi database gagal.\nSilakan periksa konfigurasi database.",
                    "Error Database", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String sql = "SELECT s.no_sales, s.date, s.deskripsi, u.name AS operator_name, " +
                         "p.name AS partner_name, s.qty, s.price, s.discount, s.total " +
                         "FROM tb_sales_request AS s " +
                         "LEFT JOIN tb_users AS u ON s.id_operator = u.id " +
                         "LEFT JOIN tb_partner AS p ON s.id_partner = p.id " +
                         "ORDER BY s.date DESC";
            
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            double grandTotal = 0;
            
            while (rs.next()) {
                String noSales = rs.getString("no_sales");
                String date = dateFormat.format(rs.getTimestamp("date"));
                String deskripsi = rs.getString("deskripsi");
                String operatorName = rs.getString("operator_name");
                String partnerName = rs.getString("partner_name");
                int qty = rs.getInt("qty");
                double price = rs.getDouble("price");
                double discount = rs.getDouble("discount");
                double total = rs.getDouble("total");
                
                Object[] row = {
                    noSales,
                    date,
                    deskripsi,
                    operatorName,
                    partnerName,
                    qty,
                    currencyFormat.format(price),
                    currencyFormat.format(discount),
                    currencyFormat.format(total)
                };
                
                tableModel.addRow(row);
                grandTotal += total;
            }
            
            // Update total label
            totalLabel.setText("TOTAL: Rp " + currencyFormat.format(grandTotal));
            
            rs.close();
            stmt.close();
            conn.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, 
                "Error saat mengambil data: " + e.getMessage(),
                "Error Database", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    /**
     * Method untuk menampilkan laporan penjualan
     */
    public static void showReport() {
        // Method lama tetap dipertahankan untuk kompatibilitas
        JOptionPane.showMessageDialog(null, 
            "Menampilkan laporan dalam format sederhana", 
            "Simple Report", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void showTableReport(JTable table, String title) {
        // Buat frame untuk menampilkan laporan
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Panel utama dengan layout border
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Label judul
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Cambria Math", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        
        // Scroll pane untuk tabel
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Panel tombol
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        // Tombol cetak
        JButton printButton = new JButton("Cetak");
        printButton.addActionListener(e -> {
            try {
                MessageFormat header = new MessageFormat(title);
                MessageFormat footer = new MessageFormat("Halaman {0} - Dicetak pada: " + new Date());
                table.print(JTable.PrintMode.FIT_WIDTH, header, footer);
            } catch (PrinterException ex) {
                JOptionPane.showMessageDialog(frame,
                    "Error saat mencetak: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        });
        
        // Tombol tutup
        JButton closeButton = new JButton("Tutup");
        closeButton.addActionListener(e -> frame.dispose());
        
        buttonPanel.add(printButton);
        buttonPanel.add(closeButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Setel frame
        frame.add(mainPanel);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    /**
     * Method main untuk testing
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        showReport();
    }
} 