package menu;

import lib.Input;
import management.CategoryManagement;
import management.RoomManagement;
import management.UserManagement;
import model.Room;
import model.Category;
import model.User;

import java.util.List;

public class MainMenu {
    private final RoomManagement roomManagement = new RoomManagement();
    private final CategoryManagement categoryManagement = new CategoryManagement();
    private final User user;
//    private final UserManagement userManagement;

    public MainMenu(User user) {
        this.user = user;
    }

//    public MainMenu(User user, UserManagement userManagement) {
//        this.user = user;
//        this.userManagement = userManagement;
//    }

    public void showMainMenu() {
        while (true) {
            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. Phòng");
            System.out.println("2. Loại phòng");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");

            String choice = Input.inputString();
//            System.out.println("DEBUG choice = '" + choice + "'");
            try {
                switch (choice) {
                    case "1" -> menuRoom();
                    case "2" -> menuCategory();
                    case "0" -> {
                        return;
                    }
                    default -> System.out.println("❌ Lựa chọn không hợp lệ!");
                }

            } catch (Exception e) {
                System.out.println("❌ Lỗi: " + e.getMessage());
            }
        }
    }

    // ================== USER ACTION ==================
    private void bookRoom() {
        try {
            List<Room> emptyRooms = roomManagement.findAll().stream()
                    .filter(r -> r.getActiveId() == 0).toList();
            if (emptyRooms.isEmpty()) {
                System.out.println("❌ Không còn phòng trống!");
                return;
            }

            System.out.println("Danh sách phòng trống:");
            emptyRooms.forEach(System.out::println);

            System.out.print("Nhập ID phòng muốn đặt: ");
            long roomId = Input.inputLongNumber();
            Room room = roomManagement.findById(roomId);

            if (room == null || room.getActiveId() != 0) {
                System.out.println("❌ Phòng không hợp lệ!");
                return;
            }

            room.setActiveId(user.getId());
            roomManagement.update(roomId, room);
            System.out.println("✅ Đặt phòng thành công!");
        } catch (Exception e) {
            System.out.println("❌ Lỗi đặt phòng: " + e.getMessage());
        }
    }

    private void returnRoom() {
        try {
            List<Room> userRooms = roomManagement.findAll().stream()
                    .filter(r -> r.getActiveId() == user.getId()).toList();
            if (userRooms.isEmpty()) {
                System.out.println("❌ Bạn chưa đặt phòng nào!");
                return;
            }

            System.out.println("Danh sách phòng bạn đã đặt:");
            userRooms.forEach(System.out::println);

            System.out.print("Nhập ID phòng muốn trả: ");
            long roomId = Input.inputLongNumber();
            Room room = roomManagement.findById(roomId);

            if (room == null || room.getActiveId() != user.getId()) {
                System.out.println("❌ Bạn không được trả phòng này!");
                return;
            }

            room.setActiveId(0);
            roomManagement.update(roomId, room);
            System.out.println("✅ Trả phòng thành công!");
        } catch (Exception e) {
            System.out.println("❌ Lỗi trả phòng: " + e.getMessage());
        }
    }

    private Category getCategoryById(long id){
        try{
            return this.categoryManagement.findById(id);
        }catch (Exception e){
            return null;
        }
    }

    // ================== MENU ROOM ==================
    private void menuRoom() {
        while (true) {
            System.out.println("===== ROOM MENU =====");
            System.out.println("1. Hiển thị tất cả phòng");
            System.out.println("2. Hiển thị phòng trống");
            System.out.println("3. Tìm phòng theo số phòng");
            System.out.println("4. Đặt phòng");
            System.out.println("5. Trả phòng");
            if (isAdmin()) {
                System.out.println("6. Thêm phòng");
                System.out.println("7. Sửa phòng");
                System.out.println("8. Xóa phòng");
            }
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            String choice = Input.inputString();

            try {
                switch (choice) {
                    case "1" -> {
                        System.out.println("Danh sach phong");
                        List<Room> roomList = this.roomManagement.findAll();
                        for (Room room : roomList) {
                            Category category = getCategoryById(room.getCategoryId());
                            String categoryName = category != null ? category.getName() : "";

                            String activeInfo;
                            if (room.getActiveId() == 0) {
                                activeInfo = "Còn trống";
                            } else {
                                activeInfo = "Đã có người đặt";
                            }

                            System.out.println("Id: " + room.getId()
                                    + " | Loại phòng: " + categoryName
                                    + " | Trạng thái: " + activeInfo);
                        }
                    }

                    case "2" -> {
                        System.out.println("Danh sách phòng trống:");
                        roomManagement.findAll().stream()
                                .filter(r -> r.getActiveId() == 0)
                                .forEach(room -> {
                                    Category category = getCategoryById(room.getCategoryId());
                                    String categoryName = category != null ? category.getName() : "";
                                    System.out.println("Id: " + room.getId()
                                            + " | Loại phòng: " + categoryName
                                            + " | Trạng thái: Còn trống");
                                });
                    }
                    case "3" -> {
                        System.out.print("Nhập id phòng cần tìm: ");
                        long id = Input.inputLongNumber();
                        Room room = roomManagement.findById(id);
                        System.out.println(room);
                    }
                    case "4" -> bookRoom();
                    case "5" -> returnRoom();
                    case "6" -> {
                        if (isAdmin()) addRoom();
                    }
                    case "7" -> {
                        if (isAdmin()) updateRoom();
                    }
                    case "8" -> {
                        if (isAdmin()) deleteRoom();
                    }
                    case "0" -> {
                        return;
                    }
                    default -> System.out.println("❌ Sai lựa chọn!");
                }
            } catch (Exception e) {
                System.out.println("❌ Lỗi: " + e.getMessage());
            }
        }
    }

    private void addRoom() {
        try {
            long id = roomManagement.getNextId();

            System.out.print("Nhập categoryId: ");
            long categoryId = Input.inputLongNumber();

            Room room = new Room(id, categoryId, 0);
            roomManagement.add(room);
            System.out.println("✅ Đã thêm phòng mới!");
        } catch (Exception e) {
            System.out.println("❌ Lỗi thêm phòng: " + e.getMessage());
        }
    }

    private void updateRoom() throws Exception {
        System.out.print("Nhập ID phòng cần sửa: ");
        long id = Input.inputLongNumber();
        System.out.print("Nhập categoryId mới: ");
        long categoryId = Input.inputLongNumber();
        System.out.print("Nhập activeId: ");
        long activeId = Input.inputLongNumber();

        Room newRoom = new Room(id, categoryId, activeId);
        roomManagement.update(id, newRoom);
        System.out.println("✅ Đã cập nhật phòng!");
    }

    private void deleteRoom() throws Exception {
        System.out.print("Nhập ID phòng cần xóa: ");
        long id = Input.inputLongNumber();
        roomManagement.delete(id);
        System.out.println("✅ Đã xóa phòng!");
    }

    // ================== MENU CATEGORY ==================
    private void menuCategory() {
        while (true) {
            System.out.println("===== CATEGORY MENU =====");
            System.out.println("1. Hiển thị tất cả loại phòng");
            System.out.println("2. Tìm kiếm loại phòng");
            if (isAdmin()) {
                System.out.println("3. Thêm loại phòng");
                System.out.println("4. Sửa loại phòng");
                System.out.println("5. Xóa loại phòng");
            }
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            String choice = Input.inputString();

            try {
                switch (choice) {
                    case "1" -> categoryManagement.findAll().forEach(System.out::println);
                    case "2" -> searchCategory();
                    case "3" -> {
                        if (isAdmin()) addCategory();
                    }
                    case "4" -> {
                        if (isAdmin()) updateCategory();
                    }
                    case "5" -> {
                        if (isAdmin()) deleteCategory();
                    }
                    case "0" -> {
                        return;
                    }
                    default -> System.out.println("❌ Sai lựa chọn!");
                }
            } catch (Exception e) {
                System.out.println("❌ Lỗi: " + e.getMessage());
            }
        }
    }

    private void addCategory() {
        try {
            long id = categoryManagement.getNextId();

            System.out.print("Nhập tên category: ");
            String name = Input.inputString();
            System.out.print("Nhập sức chứa: ");
            int capacity = Input.inputIntNumber();
            System.out.print("Nhập giá tiền: ");
            long money = Input.inputLongNumber();

            Category category = new Category(id, name, capacity, money);
            categoryManagement.add(category);
            System.out.println("✅ Đã thêm loại phòng!");
        } catch (Exception e) {
            System.out.println("❌ Lỗi thêm category: " + e.getMessage());
        }
    }

    private void updateCategory() throws Exception {
        System.out.print("Nhập ID category cần sửa: ");
        long id = Input.inputLongNumber();
        System.out.print("Nhập tên mới: ");
        String name = Input.inputString();
        System.out.print("Nhập sức chứa mới: ");
        int capacity = Input.inputIntNumber();
        System.out.print("Nhập giá mới: ");
        long money = Input.inputLongNumber();

        Category newCate = new Category(id, name, capacity, money);
        categoryManagement.update(id, newCate);
        System.out.println("✅ Đã cập nhật loại phòng!");
    }

    private void deleteCategory() throws Exception {
        System.out.print("Nhập ID category cần xóa: ");
        long id = Input.inputLongNumber();
        categoryManagement.delete(id);
        System.out.println("✅ Đã xóa loại phòng!");
    }

    // ================== SEARCH CATEGORY ==================
    private void searchCategory() {
        System.out.println("=== TÌM KIẾM LOẠI PHÒNG ===");
        System.out.println("1. Theo sức chứa");
        System.out.println("2. Theo giá (±1,000,000)");
        System.out.println("3. Theo tên gần đúng");
        System.out.print("Chọn: ");
        String choice = Input.inputString();

        try {
            switch (choice) {
                case "1" -> {
                    System.out.print("Nhập sức chứa: ");
                    int capacity = Input.inputIntNumber();
                    categoryManagement.findAll().stream()
                            .filter(c -> c.getCapacity() == capacity)
                            .forEach(System.out::println);
                }
                case "2" -> {
                    System.out.print("Nhập giá muốn tìm: ");
                    long price = Input.inputLongNumber();
                    System.out.printf("Các phòng trong khoảng giá %d - %d:\n", price - 1_000_000, price + 1_000_000);
                    categoryManagement.findAll().stream()
                            .filter(c -> Math.abs(c.getMoney() - price) <= 1_000_000)
                            .forEach(System.out::println);
                }
                case "3" -> {
                    System.out.print("Nhập tên gần đúng: ");
                    String keyword = Input.inputString().toLowerCase();
                    categoryManagement.findAll().stream()
                            .filter(c -> c.getName().toLowerCase().contains(keyword))
                            .forEach(System.out::println);
                }
                default -> System.out.println("❌ Sai lựa chọn!");
            }
        } catch (Exception e) {
            System.out.println("❌ Lỗi tìm kiếm: " + e.getMessage());
        }
    }

    // ================== HELPER ==================
    private boolean isAdmin() {
        return "admin".equalsIgnoreCase(user.getRole());
    }
}
