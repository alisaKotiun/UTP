package employee;

import java.math.BigDecimal;
import java.util.Date;

public abstract class Employee extends Person{
    private BigDecimal salary;
    private Manager manager;
    public Employee(String f, String l, Date d, BigDecimal s, Manager m) {
        super(f, l, d);
        salary = s;
        manager = m;
    }

    public Employee(String f, String l, Date d, BigDecimal s) {
        this(f, l, d, s, null);
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
        manager.add(this);
    }

    //as3-----------------

    public boolean salGreaterThan(BigDecimal s){
        return compare(this.getSalary(), s) > 0;
    }

    public boolean salLessThan(BigDecimal s){
        return compare(this.getSalary(), s) < 0;
    }

    public int compare(BigDecimal s1, BigDecimal s2) {
        return s1.compareTo(s2);
    }

}
