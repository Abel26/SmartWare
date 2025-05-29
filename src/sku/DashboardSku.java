/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sku;

import connection.connection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import utils.TableUtil;

/**
 *
 * @author Lenovo
 */
public class DashboardSku extends javax.swing.JPanel {

    /**
     * Creates new form dashboard
     */
    public DashboardSku(javax.swing.JPanel utama) {
        initComponents();
        table();
        this.utama = utama;
    }
    private javax.swing.JPanel utama;
    
    public void table() {
        DefaultTableModel table = new DefaultTableModel();
        table.addColumn("SKU");
        table.addColumn("Category");
        table.addColumn("Deskripsi");
        table.addColumn("Price");
        table.addColumn("Aksi");
        table_sku.setModel(table);

        // Apply custom table styling
        utils.TableUtil.applyCustomTable(table_sku, jScrollPane1);
        
        // Set lebar kolom
        table_sku.getColumnModel().getColumn(0).setPreferredWidth(120); // SKU
        table_sku.getColumnModel().getColumn(1).setPreferredWidth(150); // Category
        table_sku.getColumnModel().getColumn(2).setPreferredWidth(250); // Deskripsi
        table_sku.getColumnModel().getColumn(3).setPreferredWidth(120); // Price
        table_sku.getColumnModel().getColumn(4).setPreferredWidth(100); // Aksi

        Connection conn = new connection().connect();
        if (conn == null) {
            System.out.println("Koneksi ke database gagal!");
            return;
        }

        try {
            String sql = "SELECT sku, category, description, price FROM tb_sku";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Object[] row = {
                    rs.getString("sku"),
                    rs.getString("category"),
                    rs.getString("description"),
                    String.format("Rp %,d", rs.getInt("price")), // Format currency
                    "Hapus"
                };
                table.addRow(row);
            }

            // Set custom renderer dan editor untuk kolom Aksi
            table_sku.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
            table_sku.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor());

        } catch (SQLException e) {
            System.out.println("Error saat mengambil data dari database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteData(String sku) {
        try {
            Connection conn = new connection().connect();
            if (conn != null) {
                // Konfirmasi penghapusan
                int confirm = JOptionPane.showConfirmDialog(this,
                    "Apakah Anda yakin ingin menghapus SKU ini?",
                    "Konfirmasi Hapus",
                    JOptionPane.YES_NO_OPTION);
                
                if (confirm == JOptionPane.YES_OPTION) {
                    String sql = "DELETE FROM tb_sku WHERE sku = ?";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, sku);
                    
                    int result = pstmt.executeUpdate();
                    if (result > 0) {
                        JOptionPane.showMessageDialog(this, 
                            "SKU berhasil dihapus");
                        table(); // Refresh tabel
                    }
                }
                conn.close();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, 
                "Error menghapus SKU: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    // Inner class untuk render button
    private class ButtonRenderer implements TableCellRenderer {
        private JButton button;

        public ButtonRenderer() {
            button = new JButton("Hapus");
            button.setBackground(new Color(220, 53, 69)); // Red color #DC3545
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.setPreferredSize(new Dimension(70, 30));
            button.setFont(new Font("Arial", Font.BOLD, 11));
            button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            return button;
        }
    }

    // Inner class untuk edit button
    private class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String sku;
        private boolean isPushed;

        public ButtonEditor() {
            super(new JCheckBox());
            
            button = new JButton("Hapus");
            button.setBackground(new Color(220, 53, 69)); // Red color #DC3545
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.setPreferredSize(new Dimension(70, 30));
            button.setFont(new Font("Arial", Font.BOLD, 11));
            button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                    deleteData(sku);
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            sku = table.getValueAt(row, 0).toString();
            isPushed = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            isPushed = false;
            return "Hapus";
        }

        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_sku = new javax.swing.JTable();
        btn_buat_sku = new javax.swing.JButton();
        btn_cetak = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Cambria Math", 1, 36)); // NOI18N
        jLabel1.setText("SKU Overview");

        table_sku.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(table_sku);

        btn_buat_sku.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        btn_buat_sku.setText("Buat SKU");
        btn_buat_sku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buat_skuActionPerformed(evt);
            }
        });

        btn_cetak.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        btn_cetak.setText("Cetak Laporan");
        btn_cetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cetakActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_cetak)
                        .addGap(10, 10, 10)
                        .addComponent(btn_buat_sku))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 869, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(366, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_buat_sku, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cetak, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(259, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_buat_skuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buat_skuActionPerformed
        utama.removeAll();
        
        sku form_sku = new sku(utama);
        utama.add(form_sku);
        
        // Refresh panel utama
        utama.repaint();
        utama.revalidate();
    }//GEN-LAST:event_btn_buat_skuActionPerformed

    private void btn_cetakActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String reportPath = "src/report/LaporanSKU.jrxml";
            Connection conn = new connection().connect();
            
            // Compile terlebih dahulu jrxml menjadi jasper
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);
            
            // Parameter, jika tidak ada parameter gunakan null atau HashMap kosong
            Map<String, Object> parameters = new HashMap<>();
            
            // Mengisi Report dengan data dari database
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
            
            // Menampilkan jasperviewer
            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setVisible(true);
            
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_buat_sku;
    private javax.swing.JButton btn_cetak;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_sku;
    // End of variables declaration//GEN-END:variables
}
