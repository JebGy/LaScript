package MikeCopilot;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

import static MikeCopilot.Reader.*;

public class Mike {

    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static ArrayList<String> LineaCode = new ArrayList<>();
    public static HashMap<String, Integer> variablesEnteras = new HashMap<>();
    public static HashMap<String, String> variablesString = new HashMap<>();
    public static HashMap<String, Double> variablesDouble = new HashMap<>();
    static int i = 0;
    public static String path = "";
    private static boolean isRunning = true;
    Reader r = new Reader();

    public static void main(String[] args) {
        do {
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
            isRunning = true;
            String cn = "";
            LineaCode.clear();
            System.out.print("\n\nExecute Finished | Errors = 0 | Press 'y' to continue | Press any key to exit >> ");
            cn = s.nextLine();
            switch (cn) {
                case "y":
                    String a[] = new String[2];
                    main(a);
                    break;
                default:
                    System.exit(0);
                    break;
            }
        } while (isRunning);
    }


    public static void run() {
        try {

            for (i = 0; i < LineaCode.size(); i++) {
                if (LineaCode.get(0).toString().equals("init " + fileName + ";") && LineaCode.get(LineaCode.size() - 1).toString().equals("end;")) {
                    //System.out.println("\n>>Run\n");
                    switch (LineaCode.get(i)) {

                        case "sum(":

                            int sum = 0;
                            String data = LineaCode.get(i + 1);

                            StringTokenizer st = new StringTokenizer(data, "+");

                            while (st.hasMoreTokens()) {
                                sum += Integer.parseInt(st.nextToken());
                            }
                            System.out.println("\n" + sum);

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
                            System.out.println("\n" + sub);
                            break;
                        case "print(":
                            String va[] = new String[1];
                            for (int j = 0; j < va.length; j++) {
                                va[0] = LineaCode.get(i + 1);
                                if (variablesString.containsKey(va[j])) {
                                    System.out.println("\n" + variablesString.get(va[j]));
                                    break;
                                } else {
                                    System.out.println("\n" + LineaCode.get(i + 1).toString().replace('"', ' '));
                                }
                                break;
                            }
                            break;
                        case "mul(":
                            String dt = LineaCode.get(i + 1);
                            int mul = 1;
                            StringTokenizer stm = new StringTokenizer(dt, "*");

                            while (stm.hasMoreTokens()) {
                                mul *= Integer.parseInt(stm.nextToken());
                            }
                            System.out.println(ANSI_RED + "\n" + mul + ANSI_RESET);
                            //System.out.println("\n" + mul);
                            break;
                        case "div(":
                            String datos[] = new String[2];
                            String dv = LineaCode.get(i + 1);
                            double div = 1;
                            StringTokenizer stdiv = new StringTokenizer(dv, "/");
                            int x = 0;
                            while (stdiv.hasMoreTokens()) {
                                datos[x] = stdiv.nextToken();
                                x++;
                            }
                            div = Double.parseDouble(datos[0]) / Double.parseDouble(datos[1]);
                            System.out.println(div);

                            break;
                        case ")":

                            break;
                        case "mulVarInt(":
                            int nums = 1;
                            String variables[] = new String[LineaCode.get(i + 1).length()];
                            StringTokenizer token = new StringTokenizer(LineaCode.get(i + 1), "*");
                            int l = 0;
                            while (token.hasMoreTokens()) {
                                variables[l] = token.nextToken();
                                l++;
                            }
                            for (int j = 0; j < variables.length; j++) {
                                if (variablesEnteras.containsKey(variables[j])) {
                                    nums *= variablesEnteras.get(variables[j]);
                                }
                            }
                            System.out.println(nums);
                            break;
                        case "sumVarInt(":
                            int nm = 0;
                            String vars[] = new String[LineaCode.get(i + 1).length()];
                            StringTokenizer tok = new StringTokenizer(LineaCode.get(i + 1), "+");
                            int a = 0;
                            while (tok.hasMoreTokens()) {
                                vars[a] = tok.nextToken();
                                a++;
                            }
                            for (int j = 0; j < vars.length; j++) {
                                if (variablesEnteras.containsKey(vars[j])) {
                                    nm += variablesEnteras.get(vars[j]);
                                }
                            }
                            System.out.println(nm);
                            break;
                        case ">>":
                            String values[] = new String[2];
                            StringTokenizer toke = new StringTokenizer(LineaCode.get(i + 1), "=");
                            int k = 0;
                            while (toke.hasMoreTokens()) {
                                values[k] = toke.nextToken();
                                k++;
                            }
                            variablesEnteras.put(values[0], Integer.parseInt(values[1]));
                            break;
                        case "<<":
                            String dats[] = new String[2];
                            StringTokenizer stString = new StringTokenizer(LineaCode.get(i + 1), "=");
                            int z = 0;
                            while (stString.hasMoreTokens()) {
                                dats[z] = stString.nextToken();
                                z++;
                            }
                            variablesString.put(dats[0], dats[1]);
                            break;
                        case ">":
                            String inf[] = new String[2];
                            StringTokenizer fni = new StringTokenizer(LineaCode.get(i + 1), "=");
                            int s = 0;
                            while (fni.hasMoreTokens()) {
                                inf[s] = fni.nextToken();
                                s++;
                            }
                            variablesDouble.put(inf[0], Double.parseDouble(inf[1]));
                            break;
                        case "sumVarDouble(":
                            double n=0.0;
                            String varis[] = new String[LineaCode.get(i + 1).length()];
                            StringTokenizer tk = new StringTokenizer(LineaCode.get(i + 1), "+");
                            int q = 0;
                            while (tk.hasMoreTokens()) {
                                varis[q] = tk.nextToken();
                                q++;
                            }
                            for (int j = 0; j < varis.length; j++) {
                                if (variablesDouble.containsKey(varis[j])) {
                                    n += variablesDouble.get(varis[j]);
                                }
                            }
                            System.out.println(n);
                            break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR! linea " + (i + 1) + " :" + e);
        }
    }
}