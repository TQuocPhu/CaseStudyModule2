package lib;

import java.util.Scanner;

public class Input {
    private static Scanner scanner = new Scanner(System.in);

    public static String inputString(){
        String output = scanner.nextLine();
        return output;
    }

    public static int inputIntNumber(){
        do{
            try{
                int output = Integer.parseInt(scanner.nextLine());
                return output;
            } catch(NumberFormatException e){
                System.out.println("Sai dinh dang vui long nhap lai ");
            }
        } while(true);
    }

    public static int inputLongNumber(){
        do{
            try{
                int output = Integer.parseInt(scanner.nextLine());
                return output;
            } catch(NumberFormatException e){
                System.out.println("Sai dinh dang vui long nhap lai ");
            }
        } while(true);
    }
}
