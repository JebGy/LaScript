package MikeCopilot;

import java.io.*;
import java.util.StringTokenizer;

public class Reader {
    private static String Path="";
    private static String [] ar= new String[1];
    public static String fileName="";

    public static void setPath(String path) {
        Path = path;
    }

    public static String getFileName() {
        return fileName;
    }

    public static void leer() throws IOException {
        File file= new File(Path);
        String exten=file.toString();
        BufferedReader bf = null;
        try {

            fileName=file.getName();
            clearName(fileName);
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
    public static void clearName(String fName){
        String name = "";
        StringTokenizer st= new StringTokenizer(fName," ");
        while (st.hasMoreTokens()){
            name+=st.nextToken();
        }
        char[] arr = name.toCharArray();
        name="";
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]!='.'){
                name+=arr[i];
            }else{
                break;
            }
        }
        fileName=name;
    }

}
