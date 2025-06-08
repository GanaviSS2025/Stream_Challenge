import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.*;

public class StreamChallenge {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("employees.csv"))) {
            br.readLine(); // skip header
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1);
                String name = parts[0];
                String department = parts[1];
                double salary = Double.parseDouble(parts[2]);
                LocalDate startDate = LocalDate.parse(parts[3]);
                List<String> skills = Arrays.asList(parts[4].split(";"));

                employees.add(new Employee(name, department, salary, startDate, skills));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("1.Employees earning > $75,000:");
        employees.stream().filter(e -> e.getSalary() > 75000).forEach(System.out::println);

        System.out.println("\n2.Departments (Alphabetical):");
        employees.stream().map(Employee::getDepartment).distinct().sorted().forEach(System.out::println);

        double totalSalary = employees.stream().mapToDouble(Employee::getSalary).sum();
        System.out.println("\n3.Total Salary: $" + totalSalary);

        System.out.println("\n4.Average Salary per Department:");
        employees.stream()
            .collect(Collectors.groupingBy(Employee::getDepartment,
                    Collectors.averagingDouble(Employee::getSalary)))
            .forEach((k, v) -> System.out.println(k + ": $" + v));

        System.out.println("\n5.Unique Skills (Sorted):");
        employees.stream()
            .flatMap(e -> e.getSkills().stream())
            .distinct().sorted()
            .forEach(System.out::println);

        System.out.println("\n6.Top 3 Earners:");
        employees.stream()
            .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
            .limit(3)
            .map(Employee::getName)
            .forEach(System.out::println);

        System.out.println("\n7.Department → Employee Names:");
        employees.stream()
            .collect(Collectors.groupingBy(Employee::getDepartment,
                    Collectors.mapping(Employee::getName, Collectors.joining(", "))))
            .forEach((k, v) -> System.out.println(k + ": " + v));

        System.out.println("\n8.Salary Bands:");
        employees.stream()
            .collect(Collectors.groupingBy(e -> {
                if (e.getSalary() <= 50000) return "0-50k";
                else if (e.getSalary() <= 80000) return "50k-80k";
                else return "80k+";
            }))
            .forEach((k, v) -> System.out.println(k + ": " + v));

        System.out.println("\n9.Highest Paid per Department:");
        employees.stream()
            .collect(Collectors.groupingBy(Employee::getDepartment,
                    Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))))
            .forEach((k, v) -> System.out.println(k + ": " + v.orElse(null)));
    }
}

