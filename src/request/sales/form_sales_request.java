/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package request.sales;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import stock_management.outbound.*;
import user.*;
import java.sql.*;
import connection.connection;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import org.netbeans.lib.awtextra.AbsoluteConstraints;

/**
 *
 * @author Lenovo
 */
public class form_sales_request extends javax.swing.JPanel {

    private Integer selectedPartnerId = null; // Untuk menyimpan ID partner yang dipilih
    private Integer selectedSkuId = null; // Untuk menyimpan ID sku yang dipilih
    
    private String usernameLogin;

    /**
     * Creates new form create
     */
    public form_sales_request(String username, javax.swing.JPanel utama) {
        initComponents();
        this.utama = utama;
        this.usernameLogin = username;
        
        name_operator.setText(username);
        addListeners();
        total.setEditable(false);
        
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedTime = now.format(formatter);
        
        // Set waktu ke field time
        date.setText(formattedTime);
        
        // Tambahkan document listener untuk field partner
        partner.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchPartner();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchPartner();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchPartner();
            }
        });
        
        // Tambahkan document listener untuk field SKU
        sku.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchSKU();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchSKU();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchSKU();
            }
        });
    }
    private javax.swing.JPanel utama;

    private void searchPartner() {
        String searchText = partner.getText().trim();
        selectedPartnerId = null; // Reset ID partner ketika melakukan pencarian baru
        
        if (!searchText.isEmpty()) {
            try {
                Connection conn = new connection().connect();
                String sql = "SELECT id, name, address, no_telepon FROM tb_partner WHERE name LIKE ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, "%" + searchText + "%");
                
                ResultSet rs = stmt.executeQuery();
                
                if (rs.next()) {
                    // Simpan ID partner yang ditemukan
                    selectedPartnerId = rs.getInt("id");
                    // Mengisi field address dengan data dari database
                    address.setText(rs.getString("address"));
                    no_telepon.setText(rs.getString("no_telepon"));
                } else {
                    // Jika tidak ditemukan, kosongkan field address
                    address.setText("");
                    no_telepon.setText("");
                }
                
                rs.close();
                stmt.close();
                conn.close();
                
            } catch (SQLException ex) {
                System.out.println("Error searching partner: " + ex.getMessage());
            }
        } else {
            // Jika field partner kosong, kosongkan field address
            address.setText("");
            no_telepon.setText("");
        }
    }
    
    private void searchSKU() {
    String searchText = sku.getText().trim(); // Pastikan input di-trim
    selectedSkuId = null; // Reset ID SKU ketika melakukan pencarian baru

    if (!searchText.isEmpty()) {
        try {
            Connection conn = new connection().connect();
            String sql = "SELECT id, sku, category, description, price FROM tb_sku WHERE sku LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + searchText + "%");

            // Debugging: Log query dan input
            System.out.println("Search Text: " + searchText);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Simpan ID SKU yang ditemukan
                selectedSkuId = rs.getInt("id");
                // Mengisi field dengan data dari database
                category.setText(rs.getString("category").trim());
                description.setText(rs.getString("description").trim());
                price.setText(rs.getString("price").trim());
            } else {
                // Jika tidak ditemukan, kosongkan semua field
                category.setText("");
                description.setText("");
                price.setText("");
                System.out.println("No SKU found for: " + searchText);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            System.out.println("Error searching SKU: " + ex.getMessage());
        }
    } else {
        // Jika field SKU kosong, kosongkan semua field terkait
        category.setText("");
        description.setText("");
        price.setText("");
    }
}

    // Getter untuk mendapatkan ID partner yang dipilih
    public Integer getSelectedPartnerId() {
        return selectedPartnerId;
    }

    public Integer getSelectedSkuId() {
        return selectedSkuId;
    }

    private void calculateTotal() {
        try {
            // Pastikan field qty dan price tidak kosong
            if (qty.getText().trim().isEmpty() || price.getText().trim().isEmpty()) {
                total.setText(""); // Kosongkan total jika qty atau price kosong
                return;
            }

            int quantity = Integer.parseInt(qty.getText().trim());
            int unitPrice = Integer.parseInt(price.getText().trim());
            int discount = 0; // Default nilai discount adalah 0

            // Jika field discounts tidak kosong, ambil nilainya
            if (!discounts.getText().trim().isEmpty()) {
                discount = Integer.parseInt(discounts.getText().trim());
            }

            // Validasi angka positif
            if (quantity < 0 || unitPrice < 0 || discount < 0) {
                total.setText(""); // Kosongkan total jika ada nilai negatif
                return;
            }

            int totalValue = (quantity * unitPrice) - discount;
            total.setText(String.valueOf(totalValue));
        } catch (NumberFormatException e) {
            // Jika salah satu field tidak valid, kosongkan field total
            total.setText("");
        }
    }

private void addListeners() {
    DocumentListener listener = new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            calculateTotal();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            calculateTotal();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            calculateTotal();
        }
    };

    qty.getDocument().addDocumentListener(listener);
    price.getDocument().addDocumentListener(listener);
    discounts.getDocument().addDocumentListener(listener);
}



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPaneMain = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        name_operator = new javax.swing.JTextField();
        partner = new javax.swing.JTextField();
        no_telepon = new javax.swing.JTextField();
        back = new javax.swing.JToggleButton();
        btn_simpan = new javax.swing.JToggleButton();
        date = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        address = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        sku = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        category = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        description = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        qty = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        price = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        discounts = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();

        jScrollPaneMain.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPaneMain.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1020, 1180));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Cambria Math", 1, 36)); // NOI18N
        jLabel1.setText("Create Request");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 37, -1, -1));

        jLabel2.setFont(new java.awt.Font("Cambria Math", 0, 18)); // NOI18N
        jLabel2.setText("Nama Operator");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 222, -1, -1));

        jLabel3.setFont(new java.awt.Font("Cambria Math", 0, 18)); // NOI18N
        jLabel3.setText("Waktu");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 302, -1, -1));

        jLabel4.setFont(new java.awt.Font("Cambria Math", 0, 18)); // NOI18N
        jLabel4.setText("Alamat");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(606, 222, -1, -1));

        jLabel5.setFont(new java.awt.Font("Cambria Math", 0, 18)); // NOI18N
        jLabel5.setText("Tujuan");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 389, -1, -1));

        jLabel6.setFont(new java.awt.Font("Cambria Math", 0, 18)); // NOI18N
        jLabel6.setText("Nomor Telepon");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 478, -1, -1));
        jPanel1.add(name_operator, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 255, 302, 29));
        jPanel1.add(partner, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 431, 302, 29));

        no_telepon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                no_teleponActionPerformed(evt);
            }
        });
        jPanel1.add(no_telepon, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 511, 298, 29));

        back.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        back.setText("Kembali");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });
        jPanel1.add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(376, 1117, -1, 36));

        btn_simpan.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        btn_simpan.setText("Simpan");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });
        jPanel1.add(btn_simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(489, 1117, -1, 36));
        jPanel1.add(date, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 342, 302, 29));

        address.setColumns(20);
        address.setRows(5);
        jScrollPane2.setViewportView(address);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(606, 262, 302, 163));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/img/request.png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 37, -1, -1));

        jLabel8.setFont(new java.awt.Font("Cambria Math", 1, 20)); // NOI18N
        jLabel8.setText("Data Request");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 151, -1, -1));

        jLabel9.setFont(new java.awt.Font("Cambria Math", 1, 20)); // NOI18N
        jLabel9.setText("Data Barang");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 612, -1, -1));

        jLabel10.setFont(new java.awt.Font("Cambria Math", 0, 18)); // NOI18N
        jLabel10.setText("SKU");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 670, -1, -1));
        jPanel1.add(sku, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 710, 302, 29));

        jLabel11.setFont(new java.awt.Font("Cambria Math", 0, 18)); // NOI18N
        jLabel11.setText("Kategori");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 779, -1, -1));

        category.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryActionPerformed(evt);
            }
        });
        jPanel1.add(category, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 819, 302, 29));

        jLabel12.setFont(new java.awt.Font("Cambria Math", 0, 18)); // NOI18N
        jLabel12.setText("Deskripsi");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 876, -1, -1));

        description.setColumns(20);
        description.setRows(5);
        jScrollPane3.setViewportView(description);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 904, 302, 163));

        jLabel13.setFont(new java.awt.Font("Cambria Math", 0, 18)); // NOI18N
        jLabel13.setText("Kuantiti");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 670, -1, -1));
        jPanel1.add(qty, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 710, 298, 29));

        jLabel14.setFont(new java.awt.Font("Cambria Math", 0, 18)); // NOI18N
        jLabel14.setText("Harga");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 771, -1, -1));
        jPanel1.add(price, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 811, 298, 29));

        jLabel15.setFont(new java.awt.Font("Cambria Math", 0, 18)); // NOI18N
        jLabel15.setText("Potongan Harga");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 876, -1, -1));
        jPanel1.add(discounts, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 904, 298, 29));

        jLabel16.setFont(new java.awt.Font("Cambria Math", 0, 18)); // NOI18N
        jLabel16.setText("Total");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 963, -1, -1));
        jPanel1.add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 1003, 298, 29));

        jScrollPaneMain.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneMain, javax.swing.GroupLayout.DEFAULT_SIZE, 1040, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneMain, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void no_teleponActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_no_teleponActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_no_teleponActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
        // Validasi input
        if (selectedPartnerId == null || selectedSkuId == null || name_operator.getText().trim().isEmpty() || total.getText().trim().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Pastikan semua data telah diisi dengan benar!", "Peringatan", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Koneksi ke database
            Connection conn = new connection().connect();

            // Ambil ID operator berdasarkan nama operator
            String operatorName = name_operator.getText().trim();
            Integer operatorId = null;

            String operatorQuery = "SELECT id FROM tb_users WHERE username = ?";
            PreparedStatement operatorStmt = conn.prepareStatement(operatorQuery);
            operatorStmt.setString(1, operatorName);

            ResultSet operatorRs = operatorStmt.executeQuery();
            if (operatorRs.next()) {
                operatorId = operatorRs.getInt("id");
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Operator tidak ditemukan!", "Peringatan", javax.swing.JOptionPane.WARNING_MESSAGE);
                operatorRs.close();
                operatorStmt.close();
                conn.close();
                return;
            }
            operatorRs.close();
            operatorStmt.close();

            // Query untuk menyimpan data ke tb_sales_request
            String sql = "INSERT INTO tb_sales_request (no_sales, date, deskripsi, id_operator, id_partner, id_sku, price, qty, discount, total) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Generate nomor sales (no_sales) secara unik
            String noSales = "SR-" + System.currentTimeMillis();

            // Konversi format tanggal ke yyyy-MM-dd
            DateTimeFormatter dbFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();
            String formattedDate = now.format(dbFormatter);

            // Set nilai parameter
            stmt.setString(1, noSales); // no_sales
            stmt.setString(2, formattedDate); // date (format yyyy-MM-dd)
            stmt.setString(3, description.getText().trim()); // deskripsi
            stmt.setInt(4, operatorId); // id_operator (diambil dari tabel tb_operator)
            stmt.setInt(5, selectedPartnerId); // id_partner
            stmt.setInt(6, selectedSkuId); // id_sku
            stmt.setInt(7, price.getText().trim().isEmpty() ? 0 : Integer.parseInt(price.getText().trim())); // qty
            stmt.setInt(8, qty.getText().trim().isEmpty() ? 0 : Integer.parseInt(qty.getText().trim())); // qty
            stmt.setInt(9, discounts.getText().trim().isEmpty() ? 0 : Integer.parseInt(discounts.getText().trim())); // discount
            stmt.setInt(10, Integer.parseInt(total.getText().trim())); // total

            // Eksekusi query
            stmt.executeUpdate();

            // Tampilkan pesan sukses
            // Membuat custom notification yang modern untuk sukses menyimpan
            JOptionPane pane = new JOptionPane(
                "✓ Data pengajuan sales berhasil disimpan!\nNo. Sales: " + noSales + "\nTotal: Rp " + total.getText(),
                JOptionPane.INFORMATION_MESSAGE
            );
            
            // Konfigurasi dialog agar lebih modern
            javax.swing.JDialog dialog = pane.createDialog("Berhasil Disimpan");
            dialog.setModal(false);
            dialog.setAlwaysOnTop(true);
            
            // Timer untuk menutup dialog dan pindah ke dashboard setelah 2 detik
            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                    dialog.dispose();
                    
                    // Pindah ke dashboard di thread utama
                    java.awt.EventQueue.invokeLater(() -> {
                        utama.removeAll();
                        dashboard dash = new dashboard(usernameLogin, utama);
                        utama.add(dash);
                        utama.repaint();
                        utama.revalidate();
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            
            // Tampilkan dialog
            dialog.setVisible(true);

            // Reset form setelah data disimpan
            resetForm();

            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menyimpan data: " + ex.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void resetForm() {
        partner.setText("");
        address.setText("");
        no_telepon.setText("");
        sku.setText("");
        category.setText("");
        description.setText("");
        qty.setText("");
        price.setText("");
        discounts.setText("");
        total.setText("");
        selectedPartnerId = null;
        selectedSkuId = null;
    }
    
    private void backActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        utama.removeAll();

        // Tambahkan form_sales_request ke panel utama
        dashboard form = new dashboard(usernameLogin, utama);
        utama.add(form);

        // Refresh panel utama
        utama.repaint();
        utama.revalidate();
    }

    private void categoryActionPerformed(java.awt.event.ActionEvent evt) {
        // Method ini dipanggil ketika ada aksi pada field category
        // Bisa dikosongkan karena kita menggunakan DocumentListener untuk memantau perubahan
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea address;
    private javax.swing.JToggleButton back;
    private javax.swing.JToggleButton btn_simpan;
    private javax.swing.JTextField category;
    private javax.swing.JTextField date;
    private javax.swing.JTextArea description;
    private javax.swing.JTextField discounts;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPaneMain;
    private javax.swing.JTextField name_operator;
    private javax.swing.JTextField no_telepon;
    private javax.swing.JTextField partner;
    private javax.swing.JTextField price;
    private javax.swing.JTextField qty;
    private javax.swing.JTextField sku;
    private javax.swing.JTextField total;
    // End of variables declaration//GEN-END:variables
}
