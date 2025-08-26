package dto;

import model.Active;
import model.Category;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDatabase {
    private File file;

    public CategoryDatabase(){
        this.file = new File("database/category.csv");
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

    public List<Category> readData(){
        try{
            FileReader fileReader = new FileReader(this.file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            List<Category> list = new ArrayList<>();

            while ((line = bufferedReader.readLine()) != null){
                String[] arr = line.split(",");
                long id = Long.parseLong(arr[0]);
                String name = arr[1];
                int capacity = Integer.parseInt(arr[2]);
                long money = Long.parseLong(arr[3]);
                Category category = new Category(id, name, capacity, money);
                list.add(category);
            }
            bufferedReader.close();
            return list;
        } catch (IOException e){
            System.out.println(e);
        }
        return null;
    }

    public void writeData(List<Category> categoriesList){
        try {
            String newListData = "";
            for(Category category : categoriesList){
                newListData += category.getId() + ","
                        + category.getName() + ","
                        + category.getCapacity() + ","
                        + category.getMoney() + "\n";
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
