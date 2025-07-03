import java.sql.*;
import java.util.Scanner;

public class EmployeeCRUD {
    static Connection conn;

    public static void main(String[] args) {
        try {
            conn = DBConnection.getConnection();
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("\n1. Add\n2. View\n3. Update\n4. Delete\n5. Exit");
                System.out.print("Choose: ");
                int ch = sc.nextInt();
                sc.nextLine(); // flush

                switch (ch) {
                    case 1:
                        addEmployee(sc);
                        break;
                    case 2:
                        viewEmployees();
                        break;
                    case 3:
                        updateEmployee(sc);
                        break;
                    case 4:
                        deleteEmployee(sc);
                        break;
                    case 5:
                        conn.close();
                        System.exit(0);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void addEmployee(Scanner sc) throws SQLException {
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        System.out.print("Enter Salary: ");
        double salary = sc.nextDouble();

        PreparedStatement ps = conn.prepareStatement("INSERT INTO employees(name, email, salary) VALUES (?, ?, ?)");
        ps.setString(1, name);
        ps.setString(2, email);
        ps.setDouble(3, salary);
        ps.executeUpdate();
        System.out.println("✅ Employee Added!");
    }

    static void viewEmployees() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM employees");

        while (rs.next()) {
            System.out.println(rs.getInt("id") + " | " + rs.getString("name") + " | " +
                               rs.getString("email") + " | " + rs.getDouble("salary"));
        }
    }

    static void updateEmployee(Scanner sc) throws SQLException {
        System.out.print("Enter ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter New Name: ");
        String name = sc.nextLine();
        System.out.print("Enter New Email: ");
        String email = sc.nextLine();
        System.out.print("Enter New Salary: ");
        double salary = sc.nextDouble();

        PreparedStatement ps = conn.prepareStatement("UPDATE employees SET name=?, email=?, salary=? WHERE id=?");
        ps.setString(1, name);
        ps.setString(2, email);
        ps.setDouble(3, salary);
        ps.setInt(4, id);
        ps.executeUpdate();
        System.out.println("✅ Employee Updated!");
    }

    static void deleteEmployee(Scanner sc) throws SQLException {
        System.out.print("Enter ID to delete: ");
        int id = sc.nextInt();

        PreparedStatement ps = conn.prepareStatement("DELETE FROM employees WHERE id=?");
        ps.setInt(1, id);
        ps.executeUpdate();
        System.out.println("✅ Employee Deleted!");
    }
}
