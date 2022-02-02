package Model;

import Task.Task;
import Task.TQueue;
import Task.TStatus;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class TTModel extends DefaultTableModel {
    private static String[] columns = {"Name", "Status", "Result"};
    private TQueue queue;
    private List<Task> list;
    private static int pool = 5;

    public TTModel (){
        super(columns, 20);
        queue = new TQueue(pool);
        list = new ArrayList<>();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public int getRowCount() {
        return 20;
    }

    public synchronized void addTask(int i){
        Task task = new Task();
        list.add(task);
        Object[] object = new Object[]{task.getName(), task.getStatus().getName(), task.getResult()};
        insertRow(i, object);
        fireTableDataChanged();
        queue.process(task);
    }

    public synchronized int removeTask(){
        for (int i = 0; i < list.size(); i++){
            if(done(i)){
                removeRow(i);
                list.remove(i);
                return i;
            }
        }
        fireTableDataChanged();
        return -1;
    }

    public boolean done(int r){
        if(getValueAt(r, 1) != null) {
            boolean res = getValueAt(r, 1).toString().equals(TStatus.ACCOMPLISHED.getName()) ||
                    getValueAt(r, 1).toString().equals(TStatus.FAILED.getName());
        return res;
        }
        return false;
    }

    @Override
    public synchronized void fireTableDataChanged() {
        for(int i = 0; i < list.size(); i++){
            Task task = list.get(i);
                if(task != null && task.getName().equals(getValueAt(i,0)));{
                    Object ob = task.getResult();
                    Object ob1 = task.getStatus().getName();
                    setValueAt(ob1, i, 1);
                    setValueAt(ob, i, 2);
                }
        }
        super.fireTableDataChanged();
    }

    public List<Task> getList() {
        return list;
    }
}
