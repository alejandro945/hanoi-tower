package ui;

import java.io.*;
import java.util.*;

public class Main {
    private static ArrayList<Integer> cases;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception {
        cases = new ArrayList<>();
        int option;
        bw.write("1. CLI Console" + "\n");
        bw.write("2. Import and Export" + "\n");
        bw.write("Enter the option: ");
        bw.flush();
        option = Integer.parseInt(br.readLine());
        menu(option);
        br.close();
        bw.close();
    }

    public static void readProblem() throws IOException {
        String casesNumber = br.readLine();
        readCases(Integer.parseInt(casesNumber));
    }

    public static void readCases(int render) throws IOException {
        if (render > 0) {
            String occasion = br.readLine();
            cases.add(Integer.parseInt(occasion));
            readCases(render - 1);
        }
    }

    public static ArrayList<String> WriteSolution(int redux, ArrayList<String> data, int option) throws IOException {
        if (redux < cases.size()) {
            int[] render = new int[3];
            render[0] = cases.get(redux);
            if (option == 1) {
                // --------------------------------CONSOLE-------------------------------
                bw.write("\n" + render[0] + " " + render[1] + " " + render[2] + "\n");
                bw.flush();
                hanoi(cases.get(redux), 0, 1, 2, render, null, option);
            }
            if (option == 2) {
                // --------------------------------EXPORT--------------------------------
                data.add((redux == 0) ? render[0] + " " + render[1] + " " + render[2] + "\n"
                        : "\n" + render[0] + " " + render[1] + " " + render[2] + "\n");
                ArrayList<String> concat = new ArrayList<>();
                concat = hanoi(cases.get(redux), 0, 1, 2, render, concat, option);
                for (String string : concat) {
                    data.add(string);
                }
            }
            WriteSolution(redux + 1, data, option);
        }
        return data;
    }

    public static ArrayList<String> hanoi(int cases, int init, int medium, int end, int[] render,
            ArrayList<String> concat, int option) throws IOException {
        if (cases > 0) {
            hanoi(cases - 1, init, end, medium, render, concat, option);
            render[init]--;
            render[end]++;
            if (option == 1) {
                // --------------------------------CONSOLE-------------------------------
                bw.write(render[0] + " " + render[1] + " " + render[2] + "\n");
                bw.flush();
                hanoi(cases - 1, medium, init, end, render, concat, option);
            }
            if (option == 2) {
                // --------------------------------EXPORT--------------------------------
                concat.add((render[0] + " " + render[1] + " " + render[2] + "\n"));
                hanoi(cases - 1, medium, init, end, render, concat, option);
            }
        }
        return concat;
    }

    public static void importData() throws NumberFormatException, IOException {
        // IMPORT
        BufferedReader brf = new BufferedReader(new FileReader("data/Hanoi_input.txt"));
        int numCases = Integer.parseInt(brf.readLine());
        while (numCases > 0) {
            cases.add(Integer.parseInt(brf.readLine()));
            numCases--;
        }
        brf.close();
    }

    public static void exportSolution() throws IOException {
        // EXPORT
        BufferedWriter bwf = new BufferedWriter(new FileWriter("data/Output.txt"));
        bwf.write(getCases());
        bwf.close();
        bw.write("Data have been imported and exported succesfully, CHECK OUTPUT.TXT IN DATA FOLDER");
        bw.flush();
    }

    public static String getCases() throws IOException {
        ArrayList<String> c = new ArrayList<>();
        WriteSolution(0, c, 2);
        String data = "";
        for (String string : c) {
            data += string;
        }
        return data;
    }

    public static void menu(int option) throws IOException {
        switch (option) {
        case 1:
            readProblem();
            WriteSolution(0, null, 1);
            break;
        case 2:
            importData();
            exportSolution();
            break;
        }
    }
}
