package employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class Worker extends Employee{

    private Date empDate;
    private BigDecimal bonus;
    private boolean hasBonus; //a3----

    public Worker(String f, String l, Date d, BigDecimal s, Manager m, Date e, BigDecimal b) {
        super(f, l, d, s, m);
        empDate = e;
        bonus = b;
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

    public short getLengthDays() {
        LocalDate today = LocalDate.now();
        LocalDate start = empDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period p = Period.between(today, start);
        return (short) p.getDays();
    }

    //as3-----------------

    public boolean hasBonus() {
        return hasBonus;
    }

    public boolean senIsLongerYears(short y){
        return this.getLengthDays()/365 > y;
    }

    public boolean senIsLongerMonths(short m){
        return this.getLengthDays()/30 > m;
    }

    public boolean hasBonusGreater(BigDecimal b){
        if(hasBonus){
            return bonus.compareTo(b) > 0;
        }
        return false;
    }

}