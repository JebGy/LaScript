package MikeCopilot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import static MikeCopilot.Reader.*;

public class Mike {
    public static ArrayList<String> LineaCode = new ArrayList<>();
    static int i = 0;
    public static String path = "";
    private static boolean isRunning=true;
    Reader r = new Reader();

    public static void main(String [] args) {
        do{
            System.out.print("\nLaScript>> ");
            Scanner s = new Scanner(System.in);
            path = s.nextLine();
            setPath(path);
            try {
                leer();
            } catch (IOException e) {
                e.printStackTrace();
            }
            run();
            isRunning=true;
            String cn="";
            LineaCode.clear();
            System.out.print("\n\nExecute Finished | Errors = 0 | Press 'y' to continue | Press any key to exit >> ");
            cn=s.nextLine();
            switch (cn){
                case "y":
                    String a[]= new String[2];
                    main(a);
                    break;
                default:
                    System.exit(0);
                    break;
            }
        }while(isRunning);
    }


    public static void run() {
        try {

            for (i = 0; i < LineaCode.size(); i++) {
                if (LineaCode.get(0).toString().equals("init "+fileName+";") && LineaCode.get(LineaCode.size() - 1).toString().equals("end;")) {
                    //System.out.println("\n>>Run\n");
                    switch (LineaCode.get(i)) {
                        case "sum(":

                            int sum = 0;
                            String data = LineaCode.get(i + 1);

                            StringTokenizer st = new StringTokenizer(data, "+");

                            while (st.hasMoreTokens()) {
                                sum += Integer.parseInt(st.nextToken());
                            }
                            System.out.println("\n"+sum);

                            break;
                        case "sub(":

                            int sub = 0;
                            String dat = LineaCode.get(i + 1);

                            int count = 0;

                            StringTokenizer sts = new StringTokenizer(dat, "-");

                            while (sts.hasMoreTokens()) {
                                count++;
                                if (count == 1) {
                                    sub += Integer.parseInt(sts.nextToken());
                                } else {
                                    sub -= Integer.parseInt(sts.nextToken());
                                }
                            }
                            System.out.println("\n"+sub);
                            break;
                        case "print(":

                            System.out.println(LineaCode.get(i + 1).toString().replace('"', ' '));
                            break;
                        case "mul(":

                            String dt=LineaCode.get(i+1);
                            int mul=1;
                            StringTokenizer stm= new StringTokenizer(dt,"*");

                            while(stm.hasMoreTokens()){
                                mul*=Integer.parseInt(stm.nextToken());
                            }
                            System.out.println("\n"+mul);
                            break;
                        case ")":

                            break;
                        case "s(":

                            break;
                        case ">>":
                            i++;
                            break;
                        case "*":
                            break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR! linea " + (i + 1) + " :" + e);
        }
    }
}