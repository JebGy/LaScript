package MikeCopilot;

import java.io.*;
import java.nio.file.Path;

public class Reader {
    private static String Path="";

    public static void setPath(String path) {
        Path = path;
    }

    public static void leer() throws IOException {
        File file= new File(Path);
        BufferedReader bf = null;
        try {
            bf = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String string;
        while((string=bf.readLine()) !=null){
            Mike.LineaCode.add(string);
        }
    }

}
