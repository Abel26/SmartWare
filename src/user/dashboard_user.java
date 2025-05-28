/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import connection.connection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
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

/**
 *
 * @author Lenovo
 */
public class dashboard_user extends javax.swing.JPanel {

    /**
     * Creates new form index
     */
    public dashboard_user(javax.swing.JPanel utama) {
        initComponents();
        table();
        this.utama = utama;
    }
    
    private javax.swing.JPanel utama;
    private javax.swing.JButton btn_buat_user;
    private javax.swing.JButton btn_cetak;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_user;
    
    public void table() {
        DefaultTableModel table = new DefaultTableModel();
        table.addColumn("ID");
        table.addColumn("Nama");
        table.addColumn("Jenis Kelamin");
        table.addColumn("Role");
        table.addColumn("Aksi");
        table_user.setModel(table);
        
        // Set tinggi baris
        table_user.setRowHeight(35);
        
        // Set lebar kolom
        table_user.getColumnModel().getColumn(0).setPreferredWidth(50);  // ID
        table_user.getColumnModel().getColumn(1).setPreferredWidth(150); // Nama
        table_user.getColumnModel().getColumn(2).setPreferredWidth(100); // Jenis Kelamin
        table_user.getColumnModel().getColumn(3).setPreferredWidth(100); // Role
        table_user.getColumnModel().getColumn(4).setPreferredWidth(100); // Aksi
        
        Connection conn = new connection().connect();
        
        try {
            String sql = "SELECT u.id, u.name, u.gender, r.name AS role_name " +
                     "FROM tb_users AS u " +
                     "INNER JOIN tb_role AS r ON u.id_role = r.id";
            
            Statement stmt = (Statement) conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                Object[] row = {
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("gender"),
                    rs.getString("role_name"),
                    "Hapus"
                };
                table.addRow(row);
            }

            // Set custom renderer dan editor untuk kolom Aksi
            table_user.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
            table_user.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor());

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteData(String id) {
        try {
            Connection conn = new connection().connect();
            if (conn != null) {
                // Konfirmasi penghapusan
                int confirm = JOptionPane.showConfirmDialog(this,
                    "Apakah Anda yakin ingin menghapus user ini?",
                    "Konfirmasi Hapus",
                    JOptionPane.YES_NO_OPTION);
                
                if (confirm == JOptionPane.YES_OPTION) {
                    String sql = "DELETE FROM tb_users WHERE id = ?";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, id);
                    
                    int result = pstmt.executeUpdate();
                    if (result > 0) {
                        JOptionPane.showMessageDialog(this, 
                            "User berhasil dihapus");
                        table(); // Refresh tabel
                    }
                }
                conn.close();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, 
                "Error menghapus user: " + ex.getMessage(), 
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
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        table_user = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btn_buat_user = new javax.swing.JButton();
        btn_cetak = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        table_user.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        table_user.setModel(new javax.swing.table.DefaultTableModel(
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
        table_user.setRowHeight(25);
        jScrollPane1.setViewportView(table_user);

        jLabel1.setFont(new java.awt.Font("Cambria Math", 1, 36));
        jLabel1.setText("User Overview");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/img/box.png")));

        btn_buat_user.setFont(new java.awt.Font("Tahoma", 0, 14));
        btn_buat_user.setText("Buat User");
        btn_buat_user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buat_userActionPerformed(evt);
            }
        });

        btn_cetak.setFont(new java.awt.Font("Tahoma", 0, 14));
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
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_cetak, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btn_buat_user, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel1))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 869, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(366, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel7)
                    .addComponent(jLabel1)
                    .addComponent(btn_cetak, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_buat_user, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(71, 71, 71)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(259, Short.MAX_VALUE))
        );
    }

    private void btn_buat_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buat_userActionPerformed
        // TODO add your handling code here:
        utama.removeAll();

        // Tambahkan form_production_request ke panel utama
        create form = new create(utama);
        utama.add(form);

        // Refresh panel utama
        utama.repaint();
        utama.revalidate();
    }//GEN-LAST:event_btn_buat_userActionPerformed

    private void btn_cetakActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String reportPath = "src/report/LaporanUser.jrxml";
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
    // End of variables declaration//GEN-END:variables
}
