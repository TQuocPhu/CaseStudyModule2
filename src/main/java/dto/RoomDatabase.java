package dto;

import model.Category;
import model.Room;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDatabase {
    private File file;

    public RoomDatabase(){
        this.file = new File("database/room.csv");
    }

    public String getStringData() throws IOException {
        FileReader fileReader = new FileReader(this.file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = null;
        String output = "";
        while((line = bufferedReader.readLine()) != null){
            output += line;
        }
        bufferedReader.close();
        return output;
    }

    public List<Room> readData(){
        try{
            FileReader fileReader = new FileReader(this.file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            List<Room> list = new ArrayList<>();

            while ((line = bufferedReader.readLine()) != null){
                String[] arr = line.split(",");
                long id = Long.parseLong(arr[0]);
                long categoryId = Long.parseLong(arr[1]);
                long activeId = Long.parseLong(arr[2]);
                Room room = new Room(id, categoryId, activeId);
                list.add(room);
            }
            bufferedReader.close();
            return list;
        } catch (IOException e){
            System.out.println(e);
        }
        return null;
    }

    public void writeData(List<Room> roomList){
        try {
            String newListData = "";
            for(Room room : roomList){
                newListData += room.getId() + ","
                        + room.getCategoryId() + ","
                        + room.getActiveId() + "\n";
            }
            FileWriter fileWriter = new FileWriter(this.file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(newListData);
            bufferedWriter.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
