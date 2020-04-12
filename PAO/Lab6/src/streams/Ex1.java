package streams;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Ex1 {

    private  static final String BASE_ATH = "./src/streams";

    public static void main(String[] args) {

        //get file separator
        System.out.println(System.getProperty("file.separator"));
        System.out.println(File.separator);

        //create director
        File dir = new File(BASE_ATH, "/dir");
        System.out.println(dir.mkdir());

        File dir2 = new File(BASE_ATH, "/a/b/c/dir2");
        System.out.println(dir2.mkdirs());

        //create new file
        File file1 = new File(BASE_ATH ,   "file1.txt");
        try {
            if(file1.createNewFile()){
                System.out.println("file created");
            } else{
                System.out.println(file1.exists());
                System.out.println(file1 + " already exists");
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        System.out.println(file1.getName());
        System.out.println(file1.length());
        System.out.println(file1.getAbsoluteFile());

        File f1 = new File("./src/exceptions");
        System.out.println(Arrays.toString(f1.listFiles()));
    }
}
