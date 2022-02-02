package Statistics;

import employee.Employee;
import employee.Manager;
import employee.Trainee;
import employee.Worker;
import payroll.PayrollEntry;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class HumanResourcesStatistics {

	//payroll for all the employees
	public static List<PayrollEntry> payroll(List<Employee> employees) {
		if(employees == null) return new ArrayList<>();
		List<PayrollEntry> res = employees
				.stream()
				.map(HumanResourcesStatistics::payrollEntry)
				.filter(p -> p!=null)
				.collect(Collectors.toList());
		return res;
	}

	// payroll for all subordinates
	public static List<PayrollEntry> subordinatesPayroll(Manager manager) {
		if(manager == null) return new ArrayList<>();
		return payroll(manager.getSub()); //all subs or just subs?
	}

	//calculating the total cost of bonuses
	public static BigDecimal bonusTotal(List<Employee> employees) {
		BigDecimal result = employees
				.stream()
				.map(HumanResourcesStatistics::bonus)
				.filter(b -> (b != null))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		return result;
	}

	//searching for an employee with the longest seniority;
	public static Employee longestSeniority(List<Employee> employees){
		Optional<Employee> optionalEmployee = employees
				.stream()
				.filter(emp -> (emp instanceof Worker))
				.max(Comparator.comparingInt(emp -> ((Worker)emp).getLengthDays()));
		Employee result = optionalEmployee.get();
		return result;
	}

	//searching for the highest salary without bonus (a number should be returned);
	public static BigDecimal highestSalary(List<Employee> employees){
		BigDecimal result = employees
				.stream()
				.map(Employee::getSalary)
				.reduce(BigDecimal.ZERO, BigDecimal::max);
		return result;
	}

	//searching for the highest salary with bonus (a number should be returned)
	public static BigDecimal highestSalaryBonus(List<Employee> employees){
		BigDecimal res = employees
				.stream()
				.map(emp -> salaryPlusBonus(emp))
				.reduce(BigDecimal.ZERO, BigDecimal::max);
		return res;
	}

	//searching for employees whose surnames begin with ‘A’ subordinates of the given manager
	public static List<Employee> employeeA(Manager manager){
		List<Employee> result = manager.getSub()
				.stream()
				.filter(emp -> emp.getLastName().startsWith("A"))
				.collect(Collectors.toList());;

		return result;
	}

	//Searching for employees who earn more than 1000 PLN
	public static List<Employee> earnMoreThan(List<Employee> employees){
		List<Employee> result = employees
				.stream()
				.filter(HumanResourcesStatistics::earnsMoreThan1000)
				.collect(Collectors.toList());

		return result;
	}

	private static boolean earnsMoreThan1000(Employee employee) {
		final BigDecimal ONE_THOUSAND = BigDecimal.valueOf(1000);
		return salaryPlusBonus(employee).compareTo(ONE_THOUSAND) > 0;
	}

//------------------------------------
	private static PayrollEntry payrollEntry(Employee emp){
		if(emp == null) {return null;}
		if(emp instanceof Worker) {
			return new PayrollEntry((Worker) emp);
		}

		return new PayrollEntry((Trainee)emp);
	}

	private static BigDecimal bonus(Employee emp){
		if(emp == null) {return BigDecimal.ZERO;}
		if(emp instanceof Worker){
			Worker worker = (Worker) emp;
			return worker.getBonus();
		}
		return BigDecimal.ZERO;
	}

	private static BigDecimal salaryPlusBonus(Employee employee) {
		return employee.getSalary().add(bonus(employee));
	}
}