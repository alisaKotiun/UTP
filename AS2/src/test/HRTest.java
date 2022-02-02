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
import payroll.PayrollEntry;


public class HRTest {
    private List<Employee> allEmployees;

    @Before
    public void before(){
        allEmployees = employees();
        Assert.assertFalse(allEmployees.isEmpty());
    }

    @Test
    public void payroll() {
        List<PayrollEntry> entry = HumanResourcesStatistics.payroll(allEmployees);
        Assert.assertFalse(entry.isEmpty());
        entry = HumanResourcesStatistics.payroll(null);
        Assert.assertTrue(entry.isEmpty());
    }

    @Test
    public void subordinatesPayroll() {
        List<PayrollEntry> entrySub = HumanResourcesStatistics.subordinatesPayroll(null);
        Assert.assertTrue(entrySub.isEmpty());
        Manager director = (Manager) allEmployees.get(0);
        entrySub = HumanResourcesStatistics.subordinatesPayroll(director);
        Assert.assertFalse(entrySub.isEmpty());
    }

    @Test
    public void bonusTotal() {
        BigDecimal total = HumanResourcesStatistics.bonusTotal(allEmployees);
        Assert.assertEquals(new BigDecimal("1200"), total);
    }

    @Test
    public void longestSeniority(){
        Employee emp = HumanResourcesStatistics.longestSeniority(allEmployees);
        Assert.assertEquals(allEmployees.get(0), emp);
    }

    @Test
    public void highestSalary(){
        BigDecimal sal = HumanResourcesStatistics.highestSalary(allEmployees);
        Assert.assertEquals(BigDecimal.valueOf(5000), sal);
    }

    @Test
    public void highestSalaryBonus(){
        BigDecimal salBonus = HumanResourcesStatistics.highestSalaryBonus(allEmployees);
        Assert.assertEquals(BigDecimal.valueOf(5100), salBonus);
    }

    @Test
    public void employeeA(){
        List<Employee> emp = HumanResourcesStatistics.employeeA((Manager)allEmployees.get(0));
        Assert.assertTrue(emp.isEmpty());
    }

    @Test
    public void earnMoreThan(){
        List<Employee> emp = HumanResourcesStatistics.earnMoreThan(allEmployees);
        Assert.assertFalse(emp.isEmpty());
        Assert.assertEquals(12, emp.size());
    }

    public static List<Employee> employees(){
        List<Employee> employees = new ArrayList<>();
        Manager director = new Manager("Director", "Director", CalDate.getDate(1990, 1, 1),
                BigDecimal.valueOf(5000), CalDate.getDate(2001, 1, 1), BigDecimal.valueOf(100));
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
        return new Manager("Name"+ind, "Surname"+ ind, CalDate.getDate(1980, 1, 1),
                BigDecimal.valueOf(2000), CalDate.getDate(2005, 1, 1), BigDecimal.valueOf(100));
    }
    public static Trainee createT(int ind){
        return new Trainee("Name"+ind, "Surname"+ ind, CalDate.getDate(1980, 1, 1),
                BigDecimal.valueOf(900), CalDate.getDate(2002, 1, 1));
    }
}