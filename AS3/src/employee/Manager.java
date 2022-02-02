package employee;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Manager extends Worker {
    private List<Employee> sub;
    private List<Employee> allSub;

    public Manager(String f, String l, Date d, BigDecimal s,
                   Manager m, Date e, BigDecimal b) {
        super(f, l, d, s, m, e, b);
        sub = new ArrayList<>();
        allSub = new ArrayList<>();
    }

    public Manager(String f, String l, Date d, BigDecimal s, Date e, BigDecimal b) {
        this(f, l, d, s, null, e, b);
    }

    public List<Employee> getSub(){ return sub; }

    public List<Employee> getAllSub(){
        return allSub;
    }

    public void add(Employee emp){
        sub.add(emp);
        allSub.add(emp);
        //only to allSub, because sub's of your sub is not your sub's
        if(emp instanceof Manager){
            Manager man = (Manager) emp;
            for(Employee e: man.sub){
                allSub.add(e);
            }
        }
    }
}
