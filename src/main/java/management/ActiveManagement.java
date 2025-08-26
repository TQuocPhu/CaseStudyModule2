package management;

import dto.ActiveDatabase;
import model.Active;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ActiveManagement implements IManagement <Active> {
    private ActiveDatabase activeDatabase = new ActiveDatabase();
    List<Active> list = new ArrayList<>();

    public ActiveManagement(){
        this.list = activeDatabase.readData();
    }

    @Override
    public void add(Active active) {
        this.list.add(active);
        activeDatabase.writeData(this.list);
    }

    @Override
    public void delete(long id) throws Exception {
        int index = this.findIndexById(id);
        this.list.remove(index);
        activeDatabase.writeData(this.list);
    }

    @Override
    public void update(long id, Active newActive) throws Exception {
        int index = this.findIndexById(id);
        Active oldData = this.findById(id);
        newActive.setId(oldData.getId());
        this.list.set(index, newActive);
        activeDatabase.writeData(this.list);
    }

    @Override
    public Active findById(long id) throws Exception {
        int index = this.findIndexById(id);
        return this.list.get(index);
    }

    @Override
    public List<Active> findAll() {
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
}
