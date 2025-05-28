package utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.JTableHeader;

public class TableUtil {
    
    public static void applyCustomTable(JTable table, JScrollPane scrollPane) {
        // Buat instance TableCustom baru
        TableCustom customTable = new TableCustom();
        
        // Basic table settings
        table.setRowHeight(40);
        table.setShowHorizontalLines(true);
        table.setGridColor(new Color(230, 230, 230));
        table.setBackground(Color.WHITE);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        
        // Header settings - ensure consistent styling
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(33, 33, 33));
        header.setForeground(new Color(255, 255, 255));
        header.setFont(new Font("Segoe UI", Font.BOLD, 12));
        header.setPreferredSize(new Dimension(0, 35));
        header.setReorderingAllowed(false);
        header.setOpaque(true);
        header.setDefaultRenderer(new TableCustom().getTableHeader().getDefaultRenderer());
        
        // Apply custom renderers from TableCustom
        table.setDefaultRenderer(Object.class, customTable.getDefaultRenderer(Object.class));
        
        // ScrollPane settings
        scrollPane.setBackground(Color.WHITE);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(null);
        
        // Auto adjust column widths
        autoAdjustColumns(table);
    }
    
    private static void autoAdjustColumns(JTable table) {
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        
        for (int column = 0; column < table.getColumnCount(); column++) {
            TableColumn tableColumn = table.getColumnModel().getColumn(column);
            int preferredWidth = tableColumn.getMinWidth();
            int maxWidth = tableColumn.getMaxWidth();
 
            for (int row = 0; row < table.getRowCount(); row++) {
                TableColumn col = table.getColumnModel().getColumn(column);
                Component comp = table.getDefaultRenderer(table.getColumnClass(column)).
                        getTableCellRendererComponent(table, table.getValueAt(row, column), false, false, row, column);
                preferredWidth = Math.max(comp.getPreferredSize().width + 1, preferredWidth);
            }
 
            tableColumn.setPreferredWidth(Math.min(preferredWidth + 20, maxWidth));
        }
    }
} 