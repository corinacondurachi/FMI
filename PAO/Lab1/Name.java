import java.util.Scanner;

public class Name {

    public static void main(String [] args){
        //A simple text scanner wich can parse
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter your name");

        //here we actually read keyboard input
        String myName=scanner.nextLine();

        //close the resource!!!!
        scanner.close();

        //display the input to console
        System.out.println("My name is: "+myName);
    }
}
