package org.example;

import dto.UserDatabase;
import management.UserManagement;
import menu.LoginMenu;

public class Main {
    public static void main(String[] args) {
        // Khởi tạo UserDatabase với file CSV
        UserDatabase userDatabase = new UserDatabase("database/user.csv");

        // Khởi tạo UserManagement
        UserManagement userManagement = new UserManagement(userDatabase);

        // Gọi menu đăng nhập
        LoginMenu loginMenu = new LoginMenu(userManagement);
        loginMenu.showLoginMenu();
    }
}

