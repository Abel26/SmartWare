/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import connection.connection;
import com.toedter.calendar.JDateChooser;
import java.security.MessageDigest;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */
public class create extends javax.swing.JPanel {

    /**
     * Creates new form create
     */
    public create(javax.swing.JPanel utama) {
        initComponents();
        
        this.utama = utama;
    }
    
    private javax.swing.JPanel utama;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {
        jCalendar1 = new com.toedter.calendar.JCalendar();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        gender = new javax.swing.JComboBox<>();
        birth_place = new javax.swing.JTextField();
        role = new javax.swing.JComboBox<>();
        username = new javax.swing.JTextField();
        password = new javax.swing.JPasswordField();
        back = new javax.swing.JToggleButton();
        save = new javax.swing.JToggleButton();
        birth_date = new com.toedter.calendar.JDateChooser();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Cambria Math", 1, 36));
        jLabel1.setText("Create User");

        jLabel2.setFont(new java.awt.Font("Cambria Math", 0, 18));
        jLabel2.setText("Nama");

        jLabel3.setFont(new java.awt.Font("Cambria Math", 0, 18));
        jLabel3.setText("Jenis Kelamin");

        jLabel4.setFont(new java.awt.Font("Cambria Math", 0, 18));
        jLabel4.setText("Tanggal Lahir");

        jLabel5.setFont(new java.awt.Font("Cambria Math", 0, 18));
        jLabel5.setText("Tempat Lahir");

        jLabel6.setFont(new java.awt.Font("Cambria Math", 0, 18));
        jLabel6.setText("Role");

        jLabel7.setFont(new java.awt.Font("Cambria Math", 0, 18));
        jLabel7.setText("Username");

        jLabel8.setFont(new java.awt.Font("Cambria Math", 0, 18));
        jLabel8.setText("Password");

        name.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        gender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laki - Laki", "Perempuan", " " }));

        role.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sales", "Warehouse", "Production", "Staff", "Manager", "Super Admin" }));

        back.setFont(new java.awt.Font("Cambria Math", 1, 18));
        back.setText("Kembali");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        save.setFont(new java.awt.Font("Cambria Math", 1, 18));
        save.setText("Simpan");
        save.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveMouseClicked(evt);
            }
        });
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(name)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addComponent(gender, 0, 298, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addComponent(birth_place))
                            .addComponent(jLabel4)
                            .addComponent(birth_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(242, 242, 242)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(username, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(back)
                                .addGap(34, 34, 34)
                                .addComponent(save))
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(role, javax.swing.GroupLayout.Alignment.LEADING, 0, 298, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(password)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(373, 373, 373)
                        .addComponent(jLabel1)))
                .addContainerGap(366, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(role, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gender, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(birth_place, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(birth_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(259, Short.MAX_VALUE))
        );
    }

    private void saveMouseClicked(java.awt.event.MouseEvent evt) {
        Connection conn = new connection().connect();
        
        int id_role = -1;
        String role_name = role.getSelectedItem().toString();
        
        String sqlRole = "SELECT id FROM tb_role WHERE name = ?";
        try {
            PreparedStatement stmtRole = conn.prepareStatement(sqlRole);
            stmtRole.setString(1, role_name);
            ResultSet rs = stmtRole.executeQuery();

            if (rs.next()) {
                id_role = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        String sql = "INSERT INTO tb_users (name, gender, birth_place, birth_date, id_role, username, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name.getText());
            stmt.setString(2, gender.getSelectedItem().toString());
            stmt.setString(3, birth_place.getText());
            
            java.util.Date utilDate = birth_date.getDate();
            if (utilDate != null) {
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                stmt.setDate(4, sqlDate);
            } else {
                throw new Exception("Tanggal lahir harus diisi!");
            }
            
            stmt.setInt(5, id_role);
            stmt.setString(6, username.getText());
            stmt.setString(7, passwordHash(new String(password.getPassword())));
            
            stmt.execute();

            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan: " + e.getMessage());
        }
    }

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saveActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
        utama.removeAll();

        // Tambahkan form_sales_request ke panel utama
        dashboard_user form = new dashboard_user(utama);
        utama.add(form);

        // Refresh panel utama
        utama.repaint();
        utama.revalidate();
    }//GEN-LAST:event_backActionPerformed

    public static String passwordHash(String password){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(password.getBytes());
            byte[] rbt = md.digest();
            StringBuilder sb = new StringBuilder();
            
            for(byte b : rbt) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e){
            return null;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton back;
    private com.toedter.calendar.JDateChooser birth_date;
    private javax.swing.JTextField birth_place;
    private javax.swing.JComboBox<String> gender;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField name;
    private javax.swing.JPasswordField password;
    private javax.swing.JComboBox<String> role;
    private javax.swing.JToggleButton save;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
