package employee;

import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;

public abstract class Person {

    private String firstName;
    private String lastName;
    private Date DoB;


    public Person(String f, String l, Date d) {
        firstName = f;
        lastName = l;
        DoB = d;
    }

    public String getFirstName() { // getter
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDoB() {
        return DoB;
    }

    public short getAge() {
        LocalDate today = LocalDate.now();
        LocalDate birth = DoB.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period p = Period.between (birth, today);
        return (short)p.getYears();
    }

    //as3----------

    public boolean isOlder(Person p){
       return compare(p.getAge()) > 0;
    }

    public boolean isYounger(Person p){
        return compare(p.getAge()) < 0;
    }

    public int compare(Short a2) {
        return ((Short) this.getAge()).compareTo(a2) ;
    }
}
