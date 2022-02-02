package employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Worker extends Employee{

    private Date empDate;
    private BigDecimal bonus;
    private boolean hasBonus; //a3----

    public Worker(String f, String l, Date d, BigDecimal s, Manager m, Date e, BigDecimal b) {
        super(f, l, d, s, m);
        empDate = e;
        bonus = b;
        //a3
        if(bonus != null && bonus.compareTo(BigDecimal.ZERO)>0){
            hasBonus = true;
        }
        else hasBonus = false;
    }

    public Worker(String f, String l, Date d, BigDecimal s, Date e, BigDecimal b) {
        this(f, l, d, s, null, e, b);
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    public short getLengthDays() {
        LocalDate today = LocalDate.now();
        LocalDate start = empDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        short d = (short)ChronoUnit.DAYS.between(start, today);
        return d;
    }

    //as3-----------------

    public boolean hasBonus() {
        return hasBonus;
    }

    public boolean senIsLongerYears(int y){
        return this.getLengthDays()/365 > y;
    }

    public boolean senIsLongerMonths(int m){
        return this.getLengthDays()/30 > m;
    }

    public boolean senIsLongerYears(Employee emp){
        if (emp instanceof Worker) return this.senIsLongerYears(((Worker) emp).getLengthDays()/365);
        return false;
    }

    public boolean hasBonusGreater(BigDecimal b){
        if(hasBonus){
            return bonus.compareTo(b) > 0;
        }
        return false;
    }

}