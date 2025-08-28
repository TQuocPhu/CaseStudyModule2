package dto;


import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserDatabase {
    private File file;

    public UserDatabase(String filename){
        this.file = new File(filename);
    }

    public String getStringData() throws IOException {
        FileReader fileReader = new FileReader(this.file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = null;
        String output = "";
        while ((line = bufferedReader.readLine()) != null){
            output += line + "\n";
        }
        bufferedReader.close();
        return output;
    }

//    public List<User> readData() {
//        List<User> list = new ArrayList<>();
//        long maxId = 0;
//        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(this.file))) {
//            String line;
//            while ((line = bufferedReader.readLine()) != null) {
//                if (line.trim().isEmpty()) continue; // bỏ dòng trống
//                String[] arr = line.split(",");
//
//                if (arr.length < 7) { // không đủ cột
//                    System.out.println("Dữ liệu không hợp lệ: " + line);
//                    continue;
//                }
//
//                try {
//                    long id = Long.parseLong(arr[0].trim());
//                    String username = arr[1].trim();
//                    String password = arr[2].trim();
//                    String phone = arr[3].trim();
//                    String email = arr[4].trim();
//                    String gender = arr[5].trim();
//                    String role = arr[6].trim();
//
//                    User user = new User(id, username, password, phone, email, gender, role);
//                    list.add(user);
//
//                    if (id > maxId) {
//                        maxId = id;
//                    }
//                } catch (NumberFormatException e) {
//                    System.out.println("Lỗi ID không hợp lệ: " + line);
//                }
//            }
//        } catch (IOException e) {
//            System.out.println("Lỗi đọc file: " + e.getMessage());
//        }
//
//        // Cập nhật AUTO_ID để tránh trùng
//        User.setAutoId(maxId + 1);
//
//        return list;
//    }


//    public List<User> readData() {
//        List<User> list = new ArrayList<>();
//        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(this.file))) {
//            String line;
//            while ((line = bufferedReader.readLine()) != null) {
//                if (line.trim().isEmpty()) continue; // bỏ dòng trống
//                String[] arr = line.split(",");
//
//                if (arr.length < 7) { // không đủ cột
//                    System.out.println("Dữ liệu không hợp lệ: " + line);
//                    continue;
//                }
//
//                try {
//                    long id = Long.parseLong(arr[0].trim());
//                    String username = arr[1].trim();
//                    String password = arr[2].trim();
//                    String phone = arr[3].trim();
//                    String email = arr[4].trim();
//                    String gender = arr[5].trim();
//                    String role = arr[6].trim();
//
//                    User user = new User(id, username, password, phone, email, gender, role);
//                    list.add(user);
//                } catch (NumberFormatException e) {
//                    System.out.println("Lỗi ID không hợp lệ: " + line);
//                }
//            }
//        } catch (IOException e) {
//            System.out.println("Lỗi đọc file: " + e.getMessage());
//        }
//        return list;
//    }


    public List<User> readData() {
        try{
            FileReader fileReader = new FileReader(this.file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;

            List<User> list = new ArrayList<>();
            while((line = bufferedReader.readLine()) != null) {
                String[] arr = line.split(",");
                long id = Long.parseLong(arr[0]);
                String username = arr[1];
                String password = arr[2];
                String phone = arr[3];
                String email = arr[4];
                String gender = arr[5];
                String role = arr[6];
                User user = new User(id, username, password,phone,email,gender,role);
                list.add(user);
            }
            bufferedReader.close();
            return list;
        } catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

//    public void writeData(User user) throws IOException {
//        String oldListData = getStringData();
//        String newLine = user.getId() + ","
//                + user.getUsername() + ","
//                + user.getPassword() + ","
//                + user.getPhone() + ","
//                + user.getEmail() + ","
//                + user.getGender() + ","
//                + user.getRole();
//        String newListData = oldListData + newLine;
//
//        FileWriter fileWriter = new FileWriter(this.file);
//        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//        bufferedWriter.write(newListData);
//        bufferedWriter.close();
//    }

    public void writeData(List<User> userList){
        try{
            String newListData = "";
            for(User user : userList){
                newListData += user.getId() + ","
                        + user.getUsername() + ","
                        + user.getPassword() + ","
                        + user.getPhone() + ","
                        + user.getEmail() + ","
                        + user.getGender() + ","
                        + user.getRole() + "\n";
            }
            FileWriter fileWriter = new FileWriter(this.file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(newListData);
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
