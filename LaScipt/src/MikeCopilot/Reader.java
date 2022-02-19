package MikeCopilot;

import java.io.*;
import java.nio.file.Path;

public class Reader {
    private static String Path="";
    private static String [] ar= new String[1];

    public static void setPath(String path) {
        Path = path;
    }

    public static void leer() throws IOException {
        File file= new File(Path);
        String exten=file.toString();
        BufferedReader bf = null;
        try {

            if(exten.contains(".las")){
                bf = new BufferedReader(new FileReader(file));
            }else{
                System.out.println("\nError! >> El archivo no es de tipo LaScript!.");
                Mike.main(ar);
            }
        } catch (FileNotFoundException e) {
            Mike.main(ar);
            e.printStackTrace();
        }
        String string;
        while((string=bf.readLine()) !=null){
            Mike.LineaCode.add(string);
        }
    }

}
