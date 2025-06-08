import java.time.LocalDate;
import java.util.List;

public class Employee {
    private String name;
    private String department;
    private double salary;
    private LocalDate startDate;
    private List<String> skills;

    public Employee(String name, String department, double salary, LocalDate startDate, List<String> skills) {
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.startDate = startDate;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public List<String> getSkills() {
        return skills;
    }

    @Override
    public String toString() {
        return name + " (" + department + ", $" + salary + ")";
    }
}
