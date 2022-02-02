package test;

import DateCal.CalDate;
import employee.Employee;
import Statistics.HumanResourcesStatistics;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import employee.Manager;
import employee.Trainee;
import employee.Worker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class HRTest {
    private List<Employee> allEmployees;

    @Before
    public void before(){
        allEmployees = employees();
        Assert.assertFalse(allEmployees.isEmpty());
    }

    @Test
    public void olderThanAndEarnMore(){
        Employee dir = allEmployees.get(0);
        List<Employee> res = HumanResourcesStatistics.olderThanAndEarnMore(allEmployees, dir);
        Assert.assertTrue(res.isEmpty());

        dir =  new Manager("Director2", "Director2", CalDate.getDate(1990, 1, 1),
                BigDecimal.valueOf(50000), CalDate.getDate(2001, 1, 1), BigDecimal.valueOf(100));
        res = HumanResourcesStatistics.olderThanAndEarnMore(allEmployees, dir);
        Assert.assertFalse(res.isEmpty());

    }

    @Test
    public void practiceLengthLongerThan(){
        List<Trainee> res = HumanResourcesStatistics.practiceLengthLongerThan(allEmployees, 1);
        Assert.assertFalse(res.isEmpty());
        BigDecimal sal = res.get(0).getSalary();
        Assert.assertEquals(BigDecimal.valueOf(945.0), sal);
    }

    @Test
    public void seniorityLongerThan(){
        List<Worker> res = HumanResourcesStatistics.seniorityLongerThan(allEmployees, 1);
        Assert.assertFalse(res.isEmpty());
        BigDecimal bon = res.get(0).getBonus();
        Assert.assertEquals(BigDecimal.valueOf(300), bon);
    }

    @Test
    public void seniorityBetweenOneAndThreeYears(){
        List<Worker> res = HumanResourcesStatistics.seniorityBetweenOneAndThreeYears(allEmployees);
        Assert.assertTrue(res.isEmpty());

        Manager emp =  new Manager("Director2", "Director2", CalDate.getDate(1990, 1, 1),
                BigDecimal.valueOf(50000), CalDate.getDate(2018, 6, 1), BigDecimal.valueOf(100));
        allEmployees.add(emp);
        res = HumanResourcesStatistics.seniorityBetweenOneAndThreeYears(allEmployees);
        Assert.assertFalse(res.isEmpty());
        Assert.assertNotEquals(50000, res.get(0).getSalary());
    }

    @Test
    public void seniorityLongerThan2(){
        Manager emp =  new Manager("Director2", "Director2", CalDate.getDate(1990, 1, 1),
                BigDecimal.valueOf(50000), CalDate.getDate(2018, 6, 1), BigDecimal.valueOf(100));
        List<Worker> res = HumanResourcesStatistics.seniorityLongerThan(allEmployees, emp);
        Assert.assertEquals(BigDecimal.valueOf(50000), res.get(0).getSalary());
        Assert.assertEquals(12, res.size());
    }

    @Test
    public void seniorityBetweenTwoAndFourYearsAndAgeGreaterThan(){
        List<Worker> res = HumanResourcesStatistics.seniorityBetweenTwoAndFourYearsAndAgeGreaterThan(
                allEmployees, 18);
        Assert.assertTrue(res.isEmpty());

        Manager emp =  new Manager("Director2", "Director2", CalDate.getDate(1990, 1, 1),
                BigDecimal.valueOf(50000), CalDate.getDate(2017, 6, 1), BigDecimal.valueOf(100));
        allEmployees.add(emp);
        res = HumanResourcesStatistics.seniorityBetweenTwoAndFourYearsAndAgeGreaterThan(
                allEmployees, 18);
        Assert.assertEquals(1, res.size());

    }


    public static List<Employee> employees(){
        List<Employee> employees = new ArrayList<>();
        Manager director = new Manager("Director", "Director", CalDate.getDate(1970, 1, 1),
                BigDecimal.valueOf(5000), CalDate.getDate(1990, 1, 1), BigDecimal.valueOf(100));
        //
        List<Manager> managers = new ArrayList<>();
        managers.add(director);
        for (int i = 1; i < 22; i++){
            if(i<6){
                Manager m = createM(i);
                managers.add(m); //5
            }
            else
                if(i>15) {
                    Worker w = createM(i);
                    Manager m = managers.get(i%6);
                    w.setManager(m); //6
                }
                else {
                    Trainee t = createT(i);
                    Manager m = managers.get(i%6);
                    t.setManager(m); //10
                }
        }

        for (Manager m:managers) {
            if(m != director) {
                m.setManager(director);
            }
        }

        //
        employees.add(director);
        employees.addAll(director.getAllSub());
        return employees;
    }

    public static Manager createM(int ind){
        return new Manager("Name"+ind, "Surname"+ ind, CalDate.getDate(1978, 1, 1),
                BigDecimal.valueOf(2000), CalDate.getDate(2005, 1, 1), BigDecimal.valueOf(100));
    }
    public static Trainee createT(int ind){
        return new Trainee("Name"+ind, "Surname"+ ind, CalDate.getDate(1980, 1, 1),
                BigDecimal.valueOf(900), CalDate.getDate(2002, 1, 1));
    }
}