package employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class Trainee extends Employee {
    private Date startDate;

    public Trainee(String f, String l, Date d, BigDecimal s, Manager m, Date sd) {
        super(f, l, d, s, m);
        startDate = sd;
    }

    public Trainee(String f, String l, Date d, BigDecimal s, Date sd) {
        this(f, l, d, s, null, sd);
    }

    public short getLengthDays(){
        LocalDate today = LocalDate.now();
        LocalDate start = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period p = Period.between(today, start);
        return (short)p.getDays();
    }

    public boolean practiceShorter(short d){
        return this.getLengthDays() < d;
    }

    public boolean practiceLonger(short d){
        return this.getLengthDays() > d;
    }
}
