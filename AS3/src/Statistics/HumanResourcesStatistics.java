package Statistics;

import employee.*;
import payroll.PayrollEntry;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class HumanResourcesStatistics {
	//a3

	//search for Employees older than given employee and earning less than him
	public static List<Employee> olderThanAndEarnMore(List<Employee> allEmployees, Employee employee) {
		Predicate<Employee> predicate1 = e -> e.isOlder(employee);
		Predicate<Employee> predicate2 = e -> e.salLessThan(employee.getSalary());
		Predicate<Employee> predicate = predicate1.and(predicate2);

		List<Employee> res = allEmployees
					.stream()
					.filter(predicate)
					.collect(Collectors.toList());
		return res;
	}

	// search for Trainees whose practice length is longer than given number of days and raise their salary by 5%
	public static List<Trainee> practiceLengthLongerThan(List<Employee> allEmployees, int daysCount) {
		final Predicate<Trainee> practiceLongerThan = t -> t.practiceLonger(daysCount);

		List<Trainee> res = allEmployees
				.stream()
				.filter(HumanResourcesStatistics::isTrainee)
				.map(HumanResourcesStatistics::toTrainee)
				.filter(practiceLongerThan)
				.collect(Collectors.toList());

		res.forEach(t -> t.setSalary(salRise(t.getSalary(), 5)));
		return res;
	}

	// search for Workers whose seniority is longer than given number of months and give them bonus of 300 if their bonus is smaller
	public static List<Worker> seniorityLongerThan(List<Employee> allEmployees, int monthCount) {
		final Predicate<Worker> senIsLongerThan = w -> w.senIsLongerMonths(monthCount);
		Predicate<Worker> predicate1 = w -> !w.hasBonusGreater(BigDecimal.valueOf(300));

		List<Worker> res = allEmployees
				.stream()
				.filter(HumanResourcesStatistics::isWorker)
				.map(HumanResourcesStatistics::toWorker)
				.filter(senIsLongerThan)
				.collect(Collectors.toList());

		res
				.stream()
				.filter(predicate1)
				.forEach(w -> w.setBonus(BigDecimal.valueOf(300)));
		return res;
	}

	//search for Workers whose seniority is between 1 and 3 years and give them raise of salary by 10%
	public static List<Worker> seniorityBetweenOneAndThreeYears(List<Employee> allEmployees) {
		Predicate<Worker> predicate1 = w -> w.senIsLongerYears(1);
		Predicate<Worker> predicate2 = w -> !w.senIsLongerYears(3);
		Predicate<Worker> predicate = predicate1.and(predicate2);

		List<Worker> res = allEmployees
				.stream()
				.filter(HumanResourcesStatistics::isWorker)
				.map(HumanResourcesStatistics::toWorker)
				.filter(predicate)
				.collect(Collectors.toList());

		res.forEach(t -> t.setSalary(salRise(t.getSalary(), 10)));
		return res;
	}

	// search for Workers whose seniority is longer than the seniority of a given employee and earn less than him and align their salary with the given employee
	public static List<Worker> seniorityLongerThan(List<Employee> allEmployees, Employee employee) {
		Predicate<Worker> predicate1 = w -> w.senIsLongerYears(employee);
		Predicate<Worker> predicate2 = w -> w.salLessThan(employee.getSalary());
		Predicate<Worker> predicate = predicate1.and(predicate2);

		List<Worker> res = allEmployees
				.stream()
				.filter(HumanResourcesStatistics::isWorker)
				.map(HumanResourcesStatistics::toWorker)
				.filter(predicate)
				.collect(Collectors.toList());

		res.forEach(t -> t.setSalary(employee.getSalary()));
		return res;
	}

	// search for Workers whose seniority is between 2 and 4 years and whose age is greater than given number of years
	public static List<Worker> seniorityBetweenTwoAndFourYearsAndAgeGreaterThan(List<Employee> allEmployees, int age) {
		Predicate<Worker> predicate1 = w -> w.senIsLongerYears(2);
		Predicate<Worker> predicate2 = w -> !w.senIsLongerYears(4);
		Predicate<Worker> predicate3 = w -> w.compare((short)age) > 0;
		Predicate<Worker> predicate = predicate1.and(predicate2).and(predicate3);

		List<Worker> res = allEmployees
				.stream()
				.filter(HumanResourcesStatistics::isWorker)
				.map(HumanResourcesStatistics::toWorker)
				.filter(predicate)
				.collect(Collectors.toList());
		return res;
	}


	//a3-----------------
	public static BigDecimal salRise(BigDecimal s, double r){
		return s.add(s
				.multiply(BigDecimal.valueOf(r))
				.divide(BigDecimal.valueOf(100)));
	}

	private static boolean isTrainee(Employee employee) {
		return employee instanceof Trainee;
	}

	private static Trainee toTrainee(Employee employee) {
		return (Trainee)employee;
	}

	private static boolean isWorker(Employee employee) {
		return employee instanceof Worker;
	}

	private static Worker toWorker(Employee employee) {
		return (Worker)employee;
	}

}