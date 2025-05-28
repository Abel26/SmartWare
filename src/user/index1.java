/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import javax.swing.border.EmptyBorder;
import connection.connection;

/**
 *
 * @author Lenovo
 */
public class index1 extends javax.swing.JPanel {
    private String usernameLogin;
    private Connection connection;
    private JPanel cardPanel;
    private Color primaryColor = new Color(21, 25, 36);
    private Color accentColor = new Color(0, 102, 204);

    /**
     * Creates new form index1
     */
    public index1(String username) {
        this.usernameLogin = username;
        initializeDatabase();
        initComponents();
        setupDashboard();
        loadStatistics();
    }

    private void initializeDatabase() {
        try {
            connection conn = new connection();
            this.connection = conn.connect();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database connection failed: " + e.getMessage());
        }
    }

    private void setupDashboard() {
        // Main content panel with GridBagLayout
        cardPanel = new JPanel(new GridBagLayout());
        cardPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Welcome Panel
        JPanel welcomePanel = new JPanel();
        welcomePanel.setBackground(primaryColor);
        welcomePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
        
        JLabel welcomeLabel = new JLabel("Welcome to SmartWare Dashboard");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel userLabel = new JLabel(usernameLogin);
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        userLabel.setForeground(Color.WHITE);
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        welcomePanel.add(welcomeLabel);
        welcomePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        welcomePanel.add(userLabel);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        cardPanel.add(welcomePanel, gbc);
        
        // Reset gridwidth for cards
        gbc.gridwidth = 1;
        
        // Add statistic cards
        addCard("Sales Requests", "Pending: 0\nApproved: 0\nRejected: 0", 1, 0, gbc);
        addCard("Production Requests", "Pending: 0\nApproved: 0\nRejected: 0", 1, 1, gbc);
        addCard("Users", "Total Users: 0", 1, 2, gbc);
        addCard("Partners", "Total Partners: 0", 2, 0, gbc);
        addCard("SKUs", "Total SKUs: 0", 2, 1, gbc);
        
        // Add cardPanel to the main panel
        setLayout(new BorderLayout());
        add(cardPanel, BorderLayout.CENTER);
    }
    
    private void addCard(String title, String content, int row, int col, GridBagConstraints gbc) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(accentColor, 1),
            new EmptyBorder(15, 15, 15, 15)
        ));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(primaryColor);
        
        JLabel contentLabel = new JLabel("<html>" + content.replace("\n", "<br>") + "</html>");
        contentLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        card.add(titleLabel);
        card.add(Box.createRigidArea(new Dimension(0, 10)));
        card.add(contentLabel);
        
        gbc.gridx = col;
        gbc.gridy = row;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        cardPanel.add(card, gbc);
    }
    
    private void loadStatistics() {
        try {
            // Load Sales Requests Statistics
            String salesQuery = "SELECT " +
                "SUM(CASE WHEN status IS NULL THEN 1 ELSE 0 END) as `Menunggu Persetujuan`, " +
                "SUM(CASE WHEN status = 1 THEN 1 ELSE 0 END) as `Diterima`, " +
                "SUM(CASE WHEN status = 0 THEN 1 ELSE 0 END) as `Ditolak` " +
                "FROM tb_sales_request";
            updateCardStatistics(salesQuery, 0);

            // Load Production Requests Statistics
            String prodQuery = "SELECT " +
                "SUM(CASE WHEN status IS NULL THEN 1 ELSE 0 END) as `Menunggu Persetujuan`, " +
                "SUM(CASE WHEN status = 1 THEN 1 ELSE 0 END) as `Diterima`, " +
                "SUM(CASE WHEN status = 0 THEN 1 ELSE 0 END) as `Ditolak` " +
                "FROM tb_production_request";
            updateCardStatistics(prodQuery, 1);

            // Load Users Count
            String userQuery = "SELECT COUNT(*) as total FROM tb_users";
            updateSimpleStatistics(userQuery, 2, "Total Users: ");

            // Load Partners Count
            String partnerQuery = "SELECT COUNT(*) as total FROM tb_partner";
            updateSimpleStatistics(partnerQuery, 3, "Total Partners: ");

            // Load SKUs Count
            String skuQuery = "SELECT COUNT(*) as total FROM tb_sku";
            updateSimpleStatistics(skuQuery, 4, "Total SKUs: ");

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading statistics: " + e.getMessage());
        }
    }
    
    private void updateCardStatistics(String query, int cardIndex) throws SQLException {
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                int pending = rs.getInt("Menunggu Persetujuan");
                int approved = rs.getInt("Diterima");
                int rejected = rs.getInt("Ditolak");
                
                JPanel card = (JPanel) cardPanel.getComponent(cardIndex + 1); // +1 because of welcome panel
                JLabel contentLabel = (JLabel) card.getComponent(2);
                contentLabel.setText(String.format("<html>Menunggu Persetujuan: %d<br>Diterima: %d<br>Ditolak: %d</html>",
                    pending, approved, rejected));
            }
        }
    }
    
    private void updateSimpleStatistics(String query, int cardIndex, String prefix) throws SQLException {
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                int total = rs.getInt("total");
                JPanel card = (JPanel) cardPanel.getComponent(cardIndex + 1); // +1 because of welcome panel
                JLabel contentLabel = (JLabel) card.getComponent(2);
                contentLabel.setText("<html>" + prefix + total + "</html>");
            }
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
        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new BorderLayout());
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
