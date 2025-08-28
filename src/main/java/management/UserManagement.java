package management;

import dto.UserDatabase;
import model.Room;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserManagement {
    private List<User> users = new ArrayList<>();
    private UserDatabase userDatabase;

    public UserManagement(UserDatabase userDatabase){
        this.userDatabase = userDatabase;
        List<User> userList = userDatabase.readData();
        if(userList != null) {
            users.addAll(userList);
        }
    }

    public List<User> findAll(){
        return users;
    }

    public void add(User user){
        this.users.add(user);
        try {
            userDatabase.writeData(users);
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public User findById(long id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }


    public User findUserByLoginIdentifier(String identifier) {
        for (User user : users) {
            if (user.getUsername().equals(identifier) ||
                    user.getEmail().equals(identifier) ||
                    user.getPhone().equals(identifier)) {
                return user;
            }
        }
        return null;
    }

    public boolean checkLogin(String identifier, String password) {
        User user = findUserByLoginIdentifier(identifier);
        return (user != null && user.getPassword().equals(password));
    }


    public User login(String identifier, String password) {
        User user = findUserByLoginIdentifier(identifier);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public User findUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public User findUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    public User findUserByPhone(String phone) {
        for (User user : users) {
            if (user.getPhone().equals(phone)) {
                return user;
            }
        }
        return null;
    }

    public long getNextId() {
        if (this.users.isEmpty()) return 1;
//        long maxId = rooms.stream().mapToLong(Room::getId).max().orElse(0);
        return this.users.get(this.users.size() - 1).getId() + 1;
    }
}