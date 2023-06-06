package org.menu;

import java.util.List;

public class UserAuthentication {
    public static String authenticateUser(String username, List<String> employee, List<String> password) {
        for (int i = 0; i < employee.size(); i++) {
            String storedUsername = employee.get(i);
            String storedPassword = password.get(i);

            if (storedUsername.equals(username)) {
                if (storedPassword.equals(password.get(i))) {
                    if (storedUsername.contains("(MANAGER)")) {
                        return "manager";
                    } else if (storedUsername.contains("(STAFF)")) {
                        return "staff";
                    }
                }
                break;
            }
        }
        return "unknown";
    }
}


