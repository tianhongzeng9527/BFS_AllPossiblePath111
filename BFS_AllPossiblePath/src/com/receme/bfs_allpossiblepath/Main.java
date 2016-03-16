package com.receme.bfs_allpossiblepath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    //1466
//	169410
    public static Map<Integer, Vector<Node>> graph = null;
    public Vector<Node> nodes = null;
    public static HashMap v;
    static Vector<Integer> as;
    public static Set<Integer> blackNodeList = new HashSet<>();
    int a = 5;
    public static List<Integer> list = null;

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
//        for (Map.Entry<Integer, Vector<Node>> map : graph.entrySet()) {
//            System.out.print(map.getKey() + "  ");
//            for (Node node : map.getValue()) {
//                System.out.print(node.val + "  ");
//            }
//            System.out.println();
//        }
        long time = System.currentTimeMillis();
        BFS bfsAlgo = new BFS(graph, new Node(2), new Node(19));
        bfsAlgo.getAvailablePath();
        bfsAlgo.getMinPath();
        System.out.println(System.currentTimeMillis() - time);
        System.out.println(bfsAlgo.sum);
        DFS dfsAlgo = new DFS(graph, new Node(2), new Node(19), graph.size() / list.size());
        dfsAlgo.getAvailablePath();
        dfsAlgo.printMinPath();
        System.out.println(System.currentTimeMillis() - time);
        System.out.println(dfsAlgo.sum);
    }

    public static int getSplitIndex(Vector<Node> vector) {
        for (int i = 0; i < vector.size(); i++) {
            if (!list.contains(vector.get(i).val)) {
                return i;
            }
        }
        return vector.size();
    }

    public static void insertToGraph(Vector<Node> vector, Node node) {
        for (Node node1 : vector) {
            if (node1.val == node.val) {
                if (node1.weight < node.weight) {
                    return;
                } else {
                    vector.remove(node1);
                    break;
                }
            }
        }
        int splitIndex = getSplitIndex(vector);
        if (list.contains(node.val)) {
            boolean insert = false;
            for (int i = 0; i < splitIndex; i++) {
                if (node.weight < vector.get(i).weight) {
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
                if (node.weight < vector.get(i).weight) {
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
