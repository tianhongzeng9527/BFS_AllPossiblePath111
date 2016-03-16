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
    int a = 5;
    public static List<Integer> list = null;

    public static void main(String[] args) throws IOException {
        list = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream("/demand.csv")));
        String s = bufferedReader.readLine();
        String[] splits = s.split(",");
        System.out.println(splits[2]);
//		String[] mustNodes = splits[2].split("|");
//		for(String mustNode: mustNodes){
//			System.out.println(mustNode);
//			list.add(Integer.valueOf(mustNode));
//		}
        bufferedReader.close();
        graph = new HashMap<Integer, Vector<Node>>();
        bufferedReader = new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream("/topo.csv")));
        while ((s = bufferedReader.readLine()) != null) {
//            System.out.println(s);
            splits = s.split(",");
//			for(int i = 0; i < splits.length; i++){
//				System.out.print(splits[i]+"  ");
//			}
//            System.out.println();
            if (graph.containsKey(Integer.valueOf(splits[1]))) {
                Vector<Node> nodes = graph.get(Integer.valueOf(splits[1]));
                nodes.add(new Node(Integer.valueOf(splits[2]), Integer.valueOf(splits[3])));
                graph.put(Integer.valueOf(splits[1]), nodes);
            } else {
                Vector<Node> nodes = new Vector<>();
                nodes.add(new Node(Integer.valueOf(splits[2]), Integer.valueOf(splits[3])));
                graph.put(Integer.valueOf(splits[1]), nodes);
            }
        }
        for (Map.Entry<Integer, Vector<Node>> map : graph.entrySet()) {
            System.out.print(map.getKey() + "  ");
            for (Node node : map.getValue()) {
                System.out.print(node.val + "  ");
            }
            System.out.println();
        }
		long time = System.currentTimeMillis();
//		BFS dfs = new BFS(graph, new Node(2), new Node(19));
//		dfs.getAvailablePath();
//		System.out.println(System.currentTimeMillis() - time);
//		System.out.println(dfs.sum);
		BFS bfsAlgo = new BFS(graph, new Node(2), new Node(19));
		bfsAlgo.getAvailablePath();
		bfsAlgo.getMinPath();
		System.out.println(System.currentTimeMillis() - time);
		System.out.println(bfsAlgo.sum);
    }
}
