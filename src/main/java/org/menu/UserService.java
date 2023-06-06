package org.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class UserService {
    private List<String> employee;
    private List<String> password;
    private Scanner scanner;

    public UserService() {
        employee = new ArrayList<>();
        password = new ArrayList<>();
    }

    public UserService(List<String> employee, List<String> password, Scanner scanner) {
        this.employee = employee;
        this.password = password;
        this.scanner = scanner;
    }

    public boolean login(String username, String inputPassword) {
        for (int i = 0; i < employee.size(); i++) {
            List<String> employeeDetails = List.of(employee.get(i).split("\\("));
            String uname = employeeDetails.get(0);
            Role role = Role.valueOf(employeeDetails.get(1).replace(")", ""));

            if (username.equalsIgnoreCase(uname) && LoginSystem.check(inputPassword, password.get(i))) {
                String colorCode = (role == Role.MANAGER) ? "\u001B[34m" : "\u001B[35m";
                System.out.println("\nLogin Successful! \n" +
                        "Welcome " + colorCode + username.toUpperCase() + "\u001B[0m" + ", Role: " + role);
                return true;
            }
        }

        System.out.println("Invalid username or password! Try again");
        return false;
    }
}
