package MikeCopilot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Mike {
    public static ArrayList<String> LineaCode = new ArrayList<>();
    static int i = 0;
    public static String path = "";
    private static boolean isRunning=true;

    public static void main(String [] args) {
        do{
            System.out.print("\nLaScript>> ");
            Scanner s = new Scanner(System.in);
            path = s.nextLine();
            Reader.setPath(path);
            try {
                Reader.leer();
            } catch (IOException e) {
                e.printStackTrace();
            }
            run();
            isRunning=true;
            String cn="";
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
                if (LineaCode.get(0).toString().equals("init;") && LineaCode.get(LineaCode.size() - 1).toString().equals("end;")) {
                    switch (LineaCode.get(i)) {
                        case "init;":
                            System.out.println("\n>>Run\n");
                            break;
                        case "sum(":
                            System.out.println("- - - initLine - - -");
                            int sum = 0;
                            String data = LineaCode.get(i + 1);

                            StringTokenizer st = new StringTokenizer(data, "+");

                            while (st.hasMoreTokens()) {
                                sum += Integer.parseInt(st.nextToken());
                            }
                            System.out.println(sum);

                            break;
                        case "sub(":
                            System.out.println("- - - initLine - - -");
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
                            System.out.println(sub);
                            break;
                        case "print(":
                            System.out.println("- - - initLine - - -");
                            System.out.println(LineaCode.get(i + 1).toString().replace('"', ' '));
                            break;
                        case "mul(":
                            System.out.println("- - - initLine - - -");
                            String dt=LineaCode.get(i+1);
                            int mul=1;
                            StringTokenizer stm= new StringTokenizer(dt,"*");

                            while(stm.hasMoreTokens()){
                                mul*=Integer.parseInt(stm.nextToken());
                            }
                            System.out.println(mul);
                            break;
                        case ")":
                            System.out.print("- - - endOfLine - - -\n");
                            break;
                        case "s(":
                            System.out.println("- - - initLine - - -");


                        case "end;":
                            System.out.println("\n>>End");
                            LineaCode.clear();
                            break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR! linea " + (i + 1) + " :" + e);
        }
    }
}