package Model;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class TTable extends JTable {
    JTable table;

    public TTable(TTModel m){
        table = new JTable(m);
        tableSetup();
        setVisible(true);
    }

    private void tableSetup(){
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table.setDefaultRenderer(String.class, dtcr);
    }
}
