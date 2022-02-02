package payroll;

import employee.Employee;
import employee.Trainee;
import employee.Worker;

import java.math.BigDecimal;

public final class PayrollEntry {

    private Employee employee;
    private BigDecimal salaryPlusBonus;

    public PayrollEntry(Employee e, BigDecimal s, BigDecimal b) {
        employee = e;
        salaryPlusBonus = add(s, b); // validate whether salary and bonus are not null
    }

    public PayrollEntry(Worker w) {
        this(w, w.getSalary(), w.getBonus());
    }

    public PayrollEntry(Trainee t) {
        this(t, t.getSalary(), null);
    }

    //validation (null is considered as 0)
    public BigDecimal add(BigDecimal s, BigDecimal b) {
        if (s == null && b == null) return BigDecimal.valueOf(0);
        if (s == null) return b;
        if (b == null) return s;
        return s.add(b);
    }

    public BigDecimal getSalaryPlusBonus() {
        return salaryPlusBonus;
    }

    public Employee getEmployee() {
        return employee;
    }
}
