package employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
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
        short d = (short) ChronoUnit.DAYS.between(start, today);
        return d;
    }

    public boolean practiceShorter(int d){
        return this.getLengthDays() < d;
    }

    public boolean practiceLonger(int d){
        return this.getLengthDays() > d;
    }
}
