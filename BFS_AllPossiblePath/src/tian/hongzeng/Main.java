package tian.hongzeng;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static Map<Integer, Vector<Node>> graph = null;
    public static List<Integer> list = null;
    public static final long time = System.currentTimeMillis();
    public static void main(String[] args) throws IOException {
        list = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream("/demand.csv")));
        String s = bufferedReader.readLine();
        String[] splits = s.split(",");
        System.out.println(splits[2]);
        String[] mustNodes = splits[2].split("\\|");
        for (String mustNode : mustNodes) {
            list.add(Integer.valueOf(mustNode));
        }
        bufferedReader.close();
        graph = new HashMap<Integer, Vector<Node>>();
        bufferedReader = new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream("/topo.csv")));
        while ((s = bufferedReader.readLine()) != null) {
            splits = s.split(",");
            if (graph.containsKey(Integer.valueOf(splits[1]))) {
                Vector<Node> nodes = graph.get(Integer.valueOf(splits[1]));
                insertToGraph(nodes, new Node(Integer.valueOf(splits[2]), Integer.valueOf(splits[3])));
                graph.put(Integer.valueOf(splits[1]), nodes);
            } else {
                Vector<Node> nodes = new Vector<>();
                nodes.add(new Node(Integer.valueOf(splits[2]), Integer.valueOf(splits[3])));
                graph.put(Integer.valueOf(splits[1]), nodes);
            }
        }
        long time = System.currentTimeMillis();
        BFS bfsAlgo = new BFS(graph, new Node(2), new Node(19));
        bfsAlgo.getAvailablePath();
        bfsAlgo.getMinPath();
        System.out.println(System.currentTimeMillis() - time);
        time = System.currentTimeMillis();
        System.out.println(bfsAlgo.sum);
        DFS dfsAlgo2 = new DFS(graph, new Node(2), new Node(19));
        dfsAlgo2.getAvailablePath();
        dfsAlgo2.printMinPath();
        System.out.println(System.currentTimeMillis() - time);
        time = System.currentTimeMillis();
        System.out.println(dfsAlgo2.sum);
        DFSOptimization dfsAlgo = new DFSOptimization(graph, new Node(2), new Node(19), 2);
        dfsAlgo.getAvailablePath();
        dfsAlgo.printMinPath();
        System.out.println(System.currentTimeMillis() - time);
        System.out.println(dfsAlgo.sum);
    }

    private static int getSplitIndex(Vector<Node> vector) {
        for (int i = 0; i < vector.size(); i++) {
            if (!list.contains(vector.get(i).getVal())) {
                return i;
            }
        }
        return vector.size();
    }

    private static void handleSameNodeWithDifferentWeight(Vector<Node> vector, Node node) {
        for (Node node1 : vector) {
            if (node1.getVal() == node.getVal()) {
                if (node1.getWeight() < node.getWeight()) {
                    return;
                } else {
                    vector.remove(node1);
                    break;
                }
            }
        }
    }

    private static void insertToGraph(Vector<Node> vector, Node node) {
        handleSameNodeWithDifferentWeight(vector, node);
        int splitIndex = getSplitIndex(vector);
        if (list.contains(node.getVal())) {
            boolean insert = false;
            for (int i = 0; i < splitIndex; i++) {
                if (node.getWeight() < vector.get(i).getWeight()) {
                    vector.add(i, node);
                    insert = true;
                    break;
                }
            }
            if (!insert) {
                vector.add(splitIndex, node);
            }

        } else {
            boolean insert = false;
            for (int i = splitIndex; i < vector.size(); i++) {
                if (node.getWeight() < vector.get(i).getWeight()) {
                    vector.add(i, node);
                    insert = true;
                    break;
                }
            }
            if (!insert)
                vector.add(node);
        }
    }
}
