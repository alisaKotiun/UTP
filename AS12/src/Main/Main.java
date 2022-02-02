package Main;


import Model.CellRenderer;
import Model.TTModel;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class Main extends JFrame{

    public static void main(String[] args) { new Main();}

    private static TTModel model;
    private static JTable table;
    private static JTableHeader header;
    private static JButton button;
    private static JPanel p;

    private static int count = 0;


    Main(){
        super("UTP");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        model = new TTModel();
        table = new JTable(model);
        header = table.getTableHeader();
        button = new JButton("Add task");
        p = new JPanel();
        p.setLayout(new BorderLayout());
        //table.setDefaultRenderer(Object.class, new CellRenderer());

        button.addActionListener(e -> addTask());
        p.add(header, BorderLayout.NORTH);
        p.add(table, BorderLayout.CENTER);
        p.add(button, BorderLayout.SOUTH);
        add(p);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        refresh();
    }

    public static void addTask(){
        if(model.getList().size() < 20){
            model.addTask(count++%20);
        }
        else {
            int i = model.removeTask();
            if(i != -1){
                model.addTask(i);
            }
        }
    }

    public static void refresh(){
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(200);
                    model.fireTableDataChanged();
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
