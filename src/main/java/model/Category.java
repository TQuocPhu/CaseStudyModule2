package model;

public class Category {
    private long id;
    private String name;
    private int capacity; //suc chua
    private long money;

    public Category(long id, String name, int capacity, long money) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.money = money;
    }

    public Category(long id, String name, double money) {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Loai phong" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", money=" + money +
                '}';
    }
}
