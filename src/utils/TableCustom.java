package utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

public class TableCustom extends JTable {
    
    public TableCustom() {
        setShowHorizontalLines(true);
        setGridColor(new Color(230, 230, 230));
        setRowHeight(40);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new TableHeaderCustom());
        setDefaultRenderer(Object.class, new TableCellCustom());
    }

    private class TableHeaderCustom extends DefaultTableCellRenderer {

        public TableHeaderCustom() {
            setBackground(new Color(33, 33, 33)); // Changed to match black header
            setForeground(new Color(255, 255, 255)); // Pure white text
            setFont(new Font("Segoe UI", Font.BOLD, 12));
            setPreferredSize(new Dimension(0, 35));
            setBorder(new EmptyBorder(10, 5, 10, 5));
            setOpaque(true); // Ensure background is fully opaque
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            com.setBackground(new Color(33, 33, 33)); // Ensure background color is applied
            com.setForeground(new Color(255, 255, 255)); // Ensure text color is applied
            return com;
        }
    }

    private class TableCellCustom extends DefaultTableCellRenderer {

        private Color whiteColor = new Color(255, 255, 255);
        private Color alternateColor = new Color(245, 245, 245);
        private Color selectedColor = new Color(230, 240, 255);

        public TableCellCustom() {
            setFont(new Font("Segoe UI", Font.PLAIN, 12));
            setBorder(new EmptyBorder(5, 5, 5, 5));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            
            if (isSelected) {
                com.setBackground(selectedColor);
                com.setForeground(new Color(21, 25, 36));
            } else {
                if (row % 2 == 0) {
                    com.setBackground(whiteColor);
                } else {
                    com.setBackground(alternateColor);
                }
                com.setForeground(new Color(50, 50, 50));
            }

            // Special handling for status column
            if (value != null) {
                String columnName = table.getColumnName(column).toLowerCase();
                if (columnName.contains("status")) {
                    String status = value.toString().toLowerCase();
                    if (status.contains("diterima")) {
                        com.setForeground(new Color(0, 150, 62)); // Green for approved
                    } else if (status.contains("ditolak")) {
                        com.setForeground(new Color(211, 47, 47)); // Red for rejected
                    } else if (status.contains("menunggu")) {
                        com.setForeground(new Color(255, 153, 0)); // Orange for pending
                    }
                }
            }

            return com;
        }
    }
} 