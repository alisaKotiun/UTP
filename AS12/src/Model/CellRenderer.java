package Model;

import Task.TStatus;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        Color back;
        TStatus status = (TStatus) table.getModel().getValueAt(row, column);
        switch (status){
            case ACCOMPLISHED:
                back = Color.GREEN;
                break;
            case FAILED:
                back = Color.RED;
                break;
            case PENDING:
                back = Color.BLUE;
                break;
            case RUNNING:
                back = Color.DARK_GRAY;
                break;
            default:
                back = Color.WHITE;
        }
        component.setBackground(back);
        return component;
    }

    private boolean status(String s){
        for (TStatus status: TStatus.values()) {
            if(s.equals(status.getName())) return true;
        }
        return false;
    }
}
