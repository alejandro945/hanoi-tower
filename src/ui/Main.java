package ui;

import java.io.*;
import java.util.*;

public class Main {
    private static ArrayList<Integer> cases;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception {
        cases = new ArrayList<>();
        readProblem(br);
        exportSolution();
        br.close();
        bw.close();
    }

    public static void readProblem(BufferedReader br) throws IOException {
        String casesNumber = br.readLine();
        readCases(br, Integer.parseInt(casesNumber));
    }

    public static void readCases(BufferedReader br, int render) throws IOException {
        if (render > 0) {
            String occasion = br.readLine();
            cases.add(Integer.parseInt(occasion));
            readCases(br, render - 1);
        }
    }

    public static String getCases() throws IOException {
        ArrayList<String> c = new ArrayList<>();
        WriteSolution(bw, 0, c);
        String data = "";
        for (String string : c) {
            data += string;
        }
        return data;
    }

    public static ArrayList<String> WriteSolution(BufferedWriter bw, int redux, ArrayList<String> data)
            throws IOException {
        if (redux < cases.size()) {
            int[] render = new int[3];
            render[0] = cases.get(redux);
            // --------------------------------CONSOLE-------------------------------
            bw.write("\n" + render[0] + " " + render[1] + " " + render[2] + "\n");
            bw.flush();
            // --------------------------------EXPORT--------------------------------
            data.add((redux == 0) ? render[0] + " " + render[1] + " " + render[2] + "\n"
                    : "\n" + render[0] + " " + render[1] + " " + render[2] + "\n");
            ArrayList<String> concat = new ArrayList<>();
            concat = hanoi(cases.get(redux), 0, 1, 2, render, concat);
            for (String string : concat) {
                data.add(string);
            }
            WriteSolution(bw, redux + 1, data);
        }
        return data;
    }

    public static ArrayList<String> hanoi(int cases, int init, int medium, int end, int[] render,
            ArrayList<String> concat) throws IOException {
        if (cases > 0) {
            hanoi(cases - 1, init, end, medium, render, concat);
            render[init]--;
            render[end]++;
            // --------------------------------CONSOLE-------------------------------
            bw.write(render[0] + " " + render[1] + " " + render[2] + "\n");
            bw.flush();
            // --------------------------------EXPORT--------------------------------
            concat.add((render[0] + " " + render[1] + " " + render[2] + "\n"));
            hanoi(cases - 1, medium, init, end, render, concat);
        }
        return concat;
    }

    public static void exportSolution() throws IOException {
        BufferedWriter bwf = new BufferedWriter(new FileWriter("data/Output.txt"));
        bwf.write(getCases());
        bwf.close();
        bw.write("Data have been imported and exported succesfully, CHECK OUTPUT.TXT IN DATA FOLDER");
        bw.flush();
    }
}
