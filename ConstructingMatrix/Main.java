import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import java.io.BufferedReader;
import java.util.Comparator;
import java.io.InputStream;
import java.util.*;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class Main {

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskA solver = new TaskA();
        solver.solve(in, out);
        out.close();
    }

    static class TaskA {

        public static int[][] matrixConstruction(int[] R, int[] C) {
            int n = R.length;
            int[][] matrix = new int[n][n];
            int[] columnSums = new int[n];
            int[] gap = new int[n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < R[i]; j++) {
                    matrix[i][j] = 1;
                    columnSums[j]++;
                }
            }

            for (int i = 0; i < n; i++) {
                gap[i] = columnSums[i] - C[i];
            }
            // System.out.println("Column sums: " + Arrays.toString(columnSums));


            int firstPositive;
            int firstNegative;

            while (!allZeros(gap)){
            firstPositive = findFirstPositive(gap);
            firstNegative = findFirstNegative(gap);


                int correctRow=correctRow(matrix,firstPositive,firstNegative,n);

                if (correctRow>=0 && firstPositive>=0 &&firstNegative>=0) {
                    matrix[correctRow][firstNegative] = 1;
                    matrix[correctRow][firstPositive] = 0;
                    gap[firstPositive]--;
                    gap[firstNegative]++;
                }


            }


            return matrix;
        }



        public static int correctRow(int[][] matrix ,int firstPositive, int firstNegative, int n){
            for (int i = 0; i < n; i++) {
                if (matrix[i][firstPositive] == 1 && matrix[i][firstNegative] == 0) {
                    return i;
                }


            }
            return -1;
        }

        public static boolean allZeros(int[] arr) {
            for (int j : arr) {
                if (j != 0) {
                    return false;
                }
            }
            return true;
        }


        public static int findFirstPositive(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] > 0) {
                    return i;
                }
            }
            return -1;
        }

        public static int findFirstNegative(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] < 0) {
                    return i;
                }
            }
            return -1;
        }





        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int r[] = new int[n];
            int c[] = new int[n];
            for (int i = 0; i < n; i++)
                r[i] = in.nextInt();
            for (int i = 0; i < n; i++)
                c[i] = in.nextInt();
            int[][] result = matrixConstruction(r, c);
            for (int i = 0; i < Objects.requireNonNull(result).length; i++) {
                for (int j = 0; j < result[i].length; j++) {
                    System.out.print(result[i][j] + " ");
                }
                System.out.print("\n");
            }
        }

    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }


        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }


    }
}