package menu;

import management.UserManagement;
import model.User;

import java.util.Scanner;
import java.util.regex.Pattern;

public class LoginMenu {
    private UserManagement userManagement;
    private Scanner scanner = new Scanner(System.in);

    public LoginMenu(UserManagement userManagement) {
        this.userManagement = userManagement;
    }

    public void showLoginMenu() {
        while (true) {
            System.out.println("\n====== LOGIN MENU ======");
            System.out.println("1. Đăng ký");
            System.out.println("2. Đăng nhập");
            System.out.println("3. Thoát");
            System.out.print("Chọn chức năng: ");
            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        register();
                        break;
                    case "2":
                        User loggedInUser = login();
                        if (loggedInUser != null) {
                            System.out.println("✅ Đăng nhập thành công! Xin chào " + loggedInUser.getUsername());
                            new menu.MainMenu(loggedInUser, userManagement).showMainMenu();
                        } else {
                            System.out.println("❌ Sai thông tin đăng nhập!");
                        }
                        break;
                    case "3":
                        System.out.println("👋 Thoát chương trình...");
                        return;
                    default:
                        System.out.println("❌ Lựa chọn không hợp lệ, vui lòng thử lại!");
                }
            } catch (Exception e) {
                System.out.println("❌ Lỗi: " + e.getMessage());
            }
        }
    }

    private void register() {
        try {
            System.out.println("\n--- ĐĂNG KÝ ---");

            String username;
            while (true) {
                System.out.print("Nhập username (ít nhất 4 ký tự, không khoảng trắng): ");
                username = scanner.nextLine();
                if (!Pattern.matches("^[a-zA-Z0-9_]{4,}$", username)) {
                    System.out.println("❌ Username không hợp lệ!");
                    continue;
                }
                if (userManagement.findUserByUsername(username) != null) {
                    System.out.println("❌ Username đã tồn tại!");
                    continue;
                }
                break;
            }

            String password;
            while (true) {
                System.out.print("Nhập mật khẩu (ít nhất 8 ký tự, có chữ và số): ");
                password = scanner.nextLine();
                if (!Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", password)) {
                    System.out.println("❌ Mật khẩu phải có ít nhất 8 ký tự và chứa cả chữ và số!");
                    continue;
                }
                break;
            }

            String phone;
            while (true) {
                System.out.print("Nhập số điện thoại (10 số, bắt đầu bằng 0): ");
                phone = scanner.nextLine();
                if (!Pattern.matches("^0\\d{9}$", phone)) {
                    System.out.println("❌ Số điện thoại không hợp lệ!");
                    continue;
                }
                if (userManagement.findUserByPhone(phone) != null) {
                    System.out.println("❌ Số điện thoại đã tồn tại!");
                    continue;
                }
                break;
            }

            String email;
            while (true) {
                System.out.print("Nhập email: ");
                email = scanner.nextLine();
                if (!Pattern.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", email)) {
                    System.out.println("❌ Email không hợp lệ!");
                    continue;
                }
                if (userManagement.findUserByEmail(email) != null) {
                    System.out.println("❌ Email đã tồn tại!");
                    continue;
                }
                break;
            }

            String gender;
            while (true) {
                System.out.print("Nhập giới tính (Nam/Nữ/Khác): ");
                gender = scanner.nextLine();
                if (gender.equalsIgnoreCase("Nam")
                        || gender.equalsIgnoreCase("Nữ")
                        || gender.equalsIgnoreCase("Khác")) {
                    break;
                }
                System.out.println("❌ Giới tính chỉ được nhập 'Nam', 'Nữ' hoặc 'Khác'!");
            }

            String role = "user"; // mặc định là user

            User newUser = new User(username, password, phone, email, gender, role);
            userManagement.add(newUser);

            System.out.println("✅ Đăng ký thành công!");
        } catch (Exception e) {
            System.out.println("❌ Lỗi đăng ký: " + e.getMessage());
        }
    }

    private User login() {
        try {
            System.out.println("\n--- ĐĂNG NHẬP ---");
            System.out.print("Nhập username/email/số điện thoại: ");
            String identifier = scanner.nextLine();

            System.out.print("Nhập mật khẩu: ");
            String password = scanner.nextLine();

            return userManagement.login(identifier, password);
        } catch (Exception e) {
            System.out.println("❌ Lỗi đăng nhập: " + e.getMessage());
            return null;
        }
    }
}
