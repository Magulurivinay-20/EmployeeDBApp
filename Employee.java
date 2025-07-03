public class Employee {
    int id;
    String name;
    String email;
    double salary;

    public Employee(int id, String name, String email, double salary) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.salary = salary;
    }

    public String toString() {
        return id + " | " + name + " | " + email + " | " + salary;
    }
}
