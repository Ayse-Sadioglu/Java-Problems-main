import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.util.*;

import java.io.BufferedReader;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class Main2Q2 {

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

        public static int[][] findPathToTreasure(String[][] map) {
            Node source = new Node(0, 0);
            Queue<Node> queue = new LinkedList<Node>();

            int numOfRows = map.length;
            int numOfColumns = map[0].length;
            boolean[][] visited = new boolean[numOfRows][numOfColumns];
            int[][] distances = new int[numOfRows][numOfColumns];

            queue.add(source);
            visited[source.x][source.y] = true;

            while (!queue.isEmpty()) {
                Node popped = queue.poll();

                if (Objects.equals(map[popped.x][popped.y], "t")) {
                    List<Node> path = new LinkedList<>();
                    path.add(popped);
                    while (popped.parent != null) {
                        path.add(0, popped.parent);
                        popped = popped.parent;
                    }
                    int[][] result = new int[path.size()][2];
                    for (int i = 0; i < path.size(); i++) {
                        Node node = path.get(i);
                        result[i][0] = node.x;
                        result[i][1] = node.y;
                    }
                    return result;
                } else {
                    map[popped.x][popped.y] = "#";

                    List<Node> neighbourList = addNeighbours(popped, map, numOfRows, numOfColumns);

                    for (Node neighbour : neighbourList) {
                        if (!visited[neighbour.x][neighbour.y]) {
                            visited[neighbour.x][neighbour.y] = true;
                            neighbour.parent = popped;
                            queue.add(neighbour);
                            distances[neighbour.x][neighbour.y] = distances[popped.x][popped.y] + 1;
                        }
                    }
                }
            }
            return null;
        }

        private static List<Node> addNeighbours(Node popped, String[][] matrix, int numOfRows, int numOfColumns) {

            List<Node> list = new LinkedList<>();

            //left
            for (int i = 1; i <= 2 && popped.y - i >= 0 && !Objects.equals(matrix[popped.x][popped.y - i], "#"); i++) {
                list.add(new Node(popped.x, popped.y - i));
            }

            //right
            for (int i = 1; i <= 2 && popped.y + i < numOfColumns && !Objects.equals(matrix[popped.x][popped.y + i], "#"); i++) {
                list.add(new Node(popped.x, popped.y + i));
            }

            //up
            for (int i = 1; i <= 2 && popped.x - i >= 0 && !Objects.equals(matrix[popped.x - i][popped.y], "#"); i++) {
                list.add(new Node(popped.x - i, popped.y));
            }

            //down
            for (int i = 1; i <= 2 && popped.x + i < numOfRows && !Objects.equals(matrix[popped.x + i][popped.y], "#"); i++) {
                list.add(new Node(popped.x + i, popped.y));
            }

            return list;
        }

        static class Node {
            int x;
            int y;
            Node parent;

            Node(int x, int y) {
                this.x = x;
                this.y = y;
            }


        }


        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            String mat[][] = new String[n][m];
            for (int i = 0; i < n; i++) {
                String line = in.next();
                for (int j = 0; j < m; j++)
                    mat[i][j] = String.valueOf(line.charAt(j));
            }

            int[][] path = findPathToTreasure(mat);

            System.out.println(path.length);
            for (int[] node : path)
                System.out.println(node[0] + " " + node[1]);
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