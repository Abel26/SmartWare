/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partner;

import connection.connection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;
import reports.PartnerReportGenerator;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Lenovo
 */
public class dashboard_partner extends javax.swing.JPanel {

    /**
     * Creates new form dashboard_partner
     */
    public dashboard_partner(javax.swing.JPanel utama) {
        initComponents();
        table();
        this.utama = utama;
    }
    private javax.swing.JPanel utama;
    
    public void table() {
        DefaultTableModel table = new DefaultTableModel();
        table.addColumn("ID");
        table.addColumn("Nama");
        table.addColumn("Alamat");
        table.addColumn("Nomor Telepon");
        table.addColumn("Aksi");
        table_partner.setModel(table);

        // Set tinggi baris
        table_partner.setRowHeight(35);
        
        // Set lebar kolom
        table_partner.getColumnModel().getColumn(0).setPreferredWidth(50);  // ID
        table_partner.getColumnModel().getColumn(1).setPreferredWidth(150); // Nama
        table_partner.getColumnModel().getColumn(2).setPreferredWidth(200); // Alamat
        table_partner.getColumnModel().getColumn(3).setPreferredWidth(120); // Nomor Telepon
        table_partner.getColumnModel().getColumn(4).setPreferredWidth(100); // Aksi

        Connection conn = new connection().connect();
        if (conn == null) {
            System.out.println("Koneksi ke database gagal!");
            return;
        }

        try {
            String sql = "SELECT id, name, address, no_telepon FROM tb_partner";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Object[] row = {
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("no_telepon"),
                    "Hapus"
                };
                table.addRow(row);
            }

            // Set custom renderer dan editor untuk kolom Aksi
            table_partner.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
            table_partner.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor());

        } catch (SQLException e) {
            System.out.println("Error saat mengambil data dari database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteData(String id) {
        try {
            Connection conn = new connection().connect();
            if (conn != null) {
                // Konfirmasi penghapusan
                int confirm = JOptionPane.showConfirmDialog(this,
                    "Apakah Anda yakin ingin menghapus partner ini?",
                    "Konfirmasi Hapus",
                    JOptionPane.YES_NO_OPTION);
                
                if (confirm == JOptionPane.YES_OPTION) {
                    String sql = "DELETE FROM tb_partner WHERE id = ?";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, id);
                    
                    int result = pstmt.executeUpdate();
                    if (result > 0) {
                        JOptionPane.showMessageDialog(this, 
                            "Partner berhasil dihapus");
                        table(); // Refresh tabel
                    }
                }
                conn.close();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, 
                "Error menghapus partner: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    // Inner class untuk render button
    private class ButtonRenderer implements TableCellRenderer {
        private JButton button;

        public ButtonRenderer() {
            button = new JButton("Hapus");
            button.setBackground(new Color(220, 53, 69));
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.setFont(new Font("Arial", Font.BOLD, 12));
            button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
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
        private String id;
        private boolean isPushed;

        public ButtonEditor() {
            super(new JCheckBox());
            
            button = new JButton("Hapus");
            button.setBackground(new Color(220, 53, 69));
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.setFont(new Font("Arial", Font.BOLD, 12));
            button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                    deleteData(id);
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            id = table.getValueAt(row, 0).toString();
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
        table_partner = new javax.swing.JTable();
        btn_buat_partner = new javax.swing.JButton();
        btn_cetak = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Cambria Math", 1, 36)); // NOI18N
        jLabel1.setText("Partner Overview");

        table_partner.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(table_partner);

        btn_buat_partner.setText("Buat Partner");
        btn_buat_partner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buat_partnerActionPerformed(evt);
            }
        });

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
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_cetak, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btn_buat_partner, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 998, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(152, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_buat_partner, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cetak, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(80, 80, 80)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(128, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_buat_partnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buat_partnerActionPerformed
        // TODO add your handling code here:
        utama.removeAll();
        
        index_partner form_partner = new index_partner(utama);
        utama.add(form_partner);
        
        // Refresh panel utama
        utama.repaint();
        utama.revalidate();
    }//GEN-LAST:event_btn_buat_partnerActionPerformed

    private void btn_cetakActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            // Coba cek apakah JasperReport tersedia
            Class.forName("net.sf.jasperreports.engine.JasperReport");
            
            // Jika berhasil, tampilkan laporan dengan JasperReports
            PartnerReportGenerator reportGenerator = new PartnerReportGenerator();
            reportGenerator.showPartnerReport();
            
        } catch (ClassNotFoundException e) {
            // JasperReport tidak tersedia, tampilkan pesan error
            javax.swing.JOptionPane.showMessageDialog(this,
                "Library JasperReports tidak ditemukan.\nSilakan instal library JasperReports terlebih dahulu.",
                "Library Tidak Tersedia",
                javax.swing.JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            // Error lain
            javax.swing.JOptionPane.showMessageDialog(this,
                "Error: " + e.getMessage(),
                "Error",
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_buat_partner;
    private javax.swing.JButton btn_cetak;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_partner;
    // End of variables declaration//GEN-END:variables
}
