package model;

public class Room {
    private long id;
    private long categoryId;
    private long activeId;

    public Room(long id, long categoryId, long activeId) {
        this.id = id;
        this.categoryId = categoryId;
        this.activeId = activeId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public long getActiveId() {
        return activeId;
    }

    public void setActiveId(long activeId) {
        this.activeId = activeId;
    }

    @Override
    public String toString() {
        return "Phong: " + id +
                ", categoryId=" + categoryId +
                ", activeId=" + activeId +
                '}';
    }


}
