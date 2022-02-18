package MikeCopilot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Mike {
    public static ArrayList<String> LineaCode = new ArrayList<>();
    static int i = 0;
    public static String path="";

    public static void main(String[] args) {
        System.out.println("Bienvenido a MikeCopilot, Soy el interprete del Lenguaje LaScript," +
                "\nporfavor ingresa el path del archivo Lascript para ejecutar:");
        Scanner s= new Scanner(System.in);
        path=s.nextLine();
        Reader.setPath(path);
        try {
            Reader.leer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        run();
    }


    public static void run() {
        try {
            for (i = 0; i < LineaCode.size(); i++) {
                if (LineaCode.get(0).toString().equals("init;") && LineaCode.get(LineaCode.size() - 1).toString().equals("end;")) {
                    switch (LineaCode.get(i)) {
                        case "init;":
                            System.out.println("Corriendo . . .");
                            break;
                        case "sum(":

                            int sum=0;
                            String data=LineaCode.get(i+1);

                            StringTokenizer st= new StringTokenizer(data,"+");

                            while (st.hasMoreTokens()){
                                sum+= Integer.parseInt(st.nextToken());
                            }
                            System.out.println(sum);

                            break;
                        case "sub(":

                            int sub=0;
                            String dat=LineaCode.get(i+1);

                            int count=0;

                            StringTokenizer sts= new StringTokenizer(dat,"-");

                            while (sts.hasMoreTokens()){
                                count++;
                                if(count==1){
                                    sub+=Integer.parseInt(sts.nextToken());
                                }else{
                                    sub-=Integer.parseInt(sts.nextToken());
                                }
                            }
                            System.out.println(sub);
                            break;
                        case "print(":
                            System.out.println(LineaCode.get(i+1).toString().replace('"',' '));
                            break;
                        case "end;":
                            System.out.println("Fin del programa! . ");
                            System.exit(0);
                            break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR! linea " + (i + 1) + " :" + e);
        }
    }
}
