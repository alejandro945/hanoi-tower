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
        // WriteSolution(bw, 0);
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

    public static String WriteSolution(BufferedWriter bw, int redux, String data) throws IOException {
        if (redux < cases.size()) {
            int[] render = new int[3];
            render[0] = cases.get(redux);
            // bw.write("\n" + render[0] + " " + render[1] + " " + render[2] + "\n");
            // bw.flush();
            data += "\n" + render[0] + " " + render[1] + " " + render[2] + "\n";
            ArrayList<String> concat = new ArrayList<>();
            concat = hanoi(cases.get(redux), 0, 1, 2, render, concat);
            for (String string : concat) {
                data += string;
            }
            WriteSolution(bw, redux + 1, data);
        }
        return data;
    }

    public static ArrayList<String> hanoi(int cases, int init, int medium, int end, int[] render,
            ArrayList<String> concat) {
        if (cases > 0) {
            hanoi(cases - 1, init, end, medium, render, concat);
            render[init]--;
            render[end]++;
            // bw.write(render[0] + " " + render[1] + " " + render[2] + "\n");
            // bw.flush();
            concat.add((render[0] + " " + render[1] + " " + render[2] + "\n"));
            hanoi(cases - 1, medium, init, end, render, concat);
        }
        return concat;
    }

    public static void exportSolution() throws IOException {
        BufferedWriter bwf = new BufferedWriter(new FileWriter("data/Output.txt"));
        String data = "";
        WriteSolution(bw, 0, data);
        // bwf.write(WriteSolution(bw, 0, data));
        bwf.close();
        bw.write("Data have been imported and exported succesfully, CHECK OUTPUT.TXT IN DATA FOLDER");
        bw.flush();
    }
}
