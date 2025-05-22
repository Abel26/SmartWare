/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package request.production;

import request.sales.*;
import connection.connection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import reports.SalesReportGenerator;  // Perubahan import
import reports.SimpleReportViewer;    // Import untuk alternatif
import reports.ProductionReportGenerator;  // Implied import for ProductionReportGenerator

/**
 *
 * @author Lenovo
 */
public class dashboard extends javax.swing.JPanel {
    private String usernameLogin;
    private javax.swing.JPanel utama;

    // Tambahkan kelas ButtonRenderer dan ButtonEditor
    private class ButtonRenderer implements TableCellRenderer {
        private JPanel panel;
        private JButton terimaButton;
        private JButton tolakButton;

        public ButtonRenderer() {
            panel = new JPanel();
            panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
            
            terimaButton = new JButton("Terima");
            tolakButton = new JButton("Tolak");
            
            // Styling tombol Terima
            terimaButton.setBackground(new Color(40, 167, 69));
            terimaButton.setForeground(Color.WHITE);
            terimaButton.setFocusPainted(false);
            terimaButton.setPreferredSize(new Dimension(80, 30));
            terimaButton.setFont(new Font("Arial", Font.BOLD, 12));
            
            // Styling tombol Tolak
            tolakButton.setBackground(new Color(220, 53, 69));
            tolakButton.setForeground(Color.WHITE);
            tolakButton.setFocusPainted(false);
            tolakButton.setPreferredSize(new Dimension(80, 30));
            tolakButton.setFont(new Font("Arial", Font.BOLD, 12));
            
            panel.add(terimaButton);
            panel.add(tolakButton);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            return panel;
        }
    }

    private class ButtonEditor extends DefaultCellEditor {
        private JPanel panel;
        private JButton terimaButton;
        private JButton tolakButton;
        private String noProduction;
        private boolean isPushed;

        public ButtonEditor() {
            super(new JCheckBox());
            
            panel = new JPanel();
            panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
            
            terimaButton = new JButton("Terima");
            tolakButton = new JButton("Tolak");
            
            // Styling tombol Terima
            terimaButton.setBackground(new Color(40, 167, 69));
            terimaButton.setForeground(Color.WHITE);
            terimaButton.setFocusPainted(false);
            terimaButton.setPreferredSize(new Dimension(80, 30));
            terimaButton.setFont(new Font("Arial", Font.BOLD, 12));
            
            // Styling tombol Tolak
            tolakButton.setBackground(new Color(220, 53, 69));
            tolakButton.setForeground(Color.WHITE);
            tolakButton.setFocusPainted(false);
            tolakButton.setPreferredSize(new Dimension(80, 30));
            tolakButton.setFont(new Font("Arial", Font.BOLD, 12));
            
            terimaButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                    updateStatus(noProduction, true);
                }
            });
            
            tolakButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                    updateStatus(noProduction, false);
                }
            });
            
            panel.add(terimaButton);
            panel.add(tolakButton);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            noProduction = table.getValueAt(row, 0).toString();
            isPushed = true;
            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            isPushed = false;
            return "Aksi";
        }

        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }
    }

    /**
     * Creates new form dashboard
     */
    public dashboard(String username, javax.swing.JPanel utama) {
        initComponents();
        this.usernameLogin = username;
        this.utama = utama;
        table();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_sales = new javax.swing.JTable();
        btn_buat_pengajuan = new javax.swing.JToggleButton();
        btn_cetak_laporan = new javax.swing.JToggleButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/img/box.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Cambria Math", 1, 36)); // NOI18N
        jLabel1.setText("Request Production Overview");

        table_sales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(table_sales);

        btn_buat_pengajuan.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        btn_buat_pengajuan.setText("Buat Pengajuan");
        btn_buat_pengajuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buat_pengajuanActionPerformed(evt);
            }
        });

        btn_cetak_laporan.setFont(new java.awt.Font("Cambria Math", 1, 18));
        btn_cetak_laporan.setText("Cetak Laporan");
        btn_cetak_laporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cetakLaporanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_cetak_laporan)
                        .addGap(18, 18, 18)
                        .addComponent(btn_buat_pengajuan))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1078, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel1))))
                .addContainerGap(154, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel7))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_buat_pengajuan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cetak_laporan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(400, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_buat_pengajuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buat_pengajuanActionPerformed
        utama.removeAll();

        // Tambahkan form_production_request ke panel utama
        form_production_request form = new form_production_request(usernameLogin);
        utama.add(form);

        // Refresh panel utama
        utama.repaint();
        utama.revalidate();
    
        
    }//GEN-LAST:event_btn_buat_pengajuanActionPerformed

    private void btn_cetakLaporanActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            // Coba cek apakah JasperReport tersedia
            Class.forName("net.sf.jasperreports.engine.JasperReport");
            
            // Jika berhasil, tampilkan laporan dengan JasperReports
            ProductionReportGenerator reportGenerator = new ProductionReportGenerator();
            reportGenerator.showProductionReport();
            
        } catch (ClassNotFoundException e) {
            // JasperReport tidak tersedia, tampilkan pesan dan gunakan SimpleReportViewer
            int option = JOptionPane.showConfirmDialog(this,
                "Library JasperReports tidak ditemukan. Gunakan tampilan laporan sederhana?",
                "Library Tidak Tersedia",
                JOptionPane.YES_NO_OPTION);
            
            if (option == JOptionPane.YES_OPTION) {
                // Gunakan alternatif SimpleReportViewer
                SimpleReportViewer.showReport();
            } else {
                // Tampilkan petunjuk instalasi
                JOptionPane.showMessageDialog(this,
                    "Silakan instal library JasperReports terlebih dahulu.\n" +
                    "Lihat panduan di file INSTALL_JASPERREPORTS.md",
                    "Instalasi Library",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            // Error lain, tampilkan error dan gunakan SimpleReportViewer
            JOptionPane.showMessageDialog(this,
                "Error: " + e.getMessage() + "\nMenggunakan tampilan laporan sederhana.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            
            SimpleReportViewer.showReport();
        }
    }

    public void table() {
        DefaultTableModel table = new DefaultTableModel();
        table.addColumn("No Production");
        table.addColumn("Kategori");
        table.addColumn("Deskripsi");
        table.addColumn("Kuantiti");
        table.addColumn("Status");
        
        table_sales.setModel(table);
        
        // Set tinggi baris
        table_sales.setRowHeight(35);

        Connection conn = new connection().connect();
        if (conn == null) {
            System.out.println("Koneksi ke database gagal!");
            return;
        }

        try {
            String sql = "SELECT p.id AS no_production, c.name AS category_name, p.description, p.qty, p.status " +
                    "FROM tb_production_request AS p " +
                    "INNER JOIN tb_category AS c ON p.id_category = c.id";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String noProduction = rs.getString("no_production");
                Object status = rs.getObject("status");
                String statusText;
                
                if (status == null) {
                    statusText = "Menunggu Persetujuan";
                } else {
                    boolean statusBool = rs.getBoolean("status");
                    statusText = statusBool ? "Diterima" : "Ditolak";
                }
                
                Object[] row = {
                    noProduction,
                    rs.getString("category_name"),
                    rs.getString("description"),
                    rs.getString("qty"),
                    statusText
                };
                table.addRow(row);
            }

        } catch (SQLException e) {
            System.out.println("Error saat mengambil data dari database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateStatus(String noProduction, boolean status) {
        try {
            Connection conn = new connection().connect();
            if (conn != null) {
                String sql = "UPDATE tb_production_request SET status = ? WHERE id = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setBoolean(1, status);
                pstmt.setString(2, noProduction);
                
                int result = pstmt.executeUpdate();
                if (result > 0) {
                    JOptionPane.showMessageDialog(this, 
                        "Status berhasil diubah menjadi " + (status ? "Diterima" : "Ditolak"));
                    table(); // Refresh tabel
                }
                conn.close();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, 
                "Error mengubah status: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btn_buat_pengajuan;
    private javax.swing.JToggleButton btn_cetak_laporan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_sales;
    // End of variables declaration//GEN-END:variables
}
