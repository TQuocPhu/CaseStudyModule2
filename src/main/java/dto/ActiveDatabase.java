package dto;

import model.Active;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ActiveDatabase {
    private File file;

    public ActiveDatabase(){
        this.file = new File("database/active.csv");
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

    public List<Active> readData(){
        try{
            FileReader fileReader = new FileReader(this.file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            List<Active> list = new ArrayList<>();

            while ((line = bufferedReader.readLine()) != null){
                String[] arr = line.split(",");
                long id = Long.parseLong(arr[0]);
                String name = arr[1];
                Active active = new Active(id, name);
                list.add(active);
            }
            bufferedReader.close();
            return list;
        } catch (IOException e){
            System.out.println(e);
        }
        return null;
    }

    public void writeData(List<Active> activeList){
        try {
            String newListData = "";
            for(Active active : activeList){
                newListData += active.getId() + "," + active.getName() + "\n";
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
