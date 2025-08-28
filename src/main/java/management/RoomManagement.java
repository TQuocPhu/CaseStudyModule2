package management;

import dto.RoomDatabase;
import model.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RoomManagement implements IManagement <Room> {
    private RoomDatabase roomDatabase = new RoomDatabase();
    List<Room> list = new ArrayList<>();

    public RoomManagement(){
        this.list = roomDatabase.readData();
    }

    @Override
    public void add(Room room) {
        this.list.add(room);
        roomDatabase.writeData(this.list);
    }

    @Override
    public void delete(long id) throws Exception {
        int index = this.findIndexById(id);
        this.list.remove(index);
        roomDatabase.writeData(this.list);
    }

    @Override
    public void update(long id, Room newRoom) throws Exception {
        int index = this.findIndexById(id);
        Room oldData = this.findById(id);
        newRoom.setId(oldData.getId());
        this.list.set(index, newRoom);
        roomDatabase.writeData(this.list);
    }

    @Override
    public Room findById(long id) throws Exception {
        return this.list.get(this.findIndexById(id));
    }

    @Override
    public List<Room> findAll() {
        return this.list;
    }

    @Override
    public int findIndexById(long id) throws Exception {
        for(int i = 0; i < this.list.size(); i++){
            if(Objects.equals(this.list.get(i).getId(),id)){
                return i;
            }
        }
        throw new Exception("Data Not Found");
    }

    public long getNextId() {
        List<Room> rooms = findAll();
        if (rooms.isEmpty()) return 1;
        long maxId = rooms.stream().mapToLong(Room::getId).max().orElse(0);
        return maxId + 1;
    }

}
