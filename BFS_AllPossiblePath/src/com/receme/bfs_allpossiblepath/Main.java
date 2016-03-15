package com.receme.bfs_allpossiblepath;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;

public class Main {
	//1466
//	169410
	public static Map<Integer, Vector<Node> > graph = null;
	public Vector<Node> nodes = null;
	public static HashMap v;
	static Vector<Integer> as;
	int a = 5;

	public static void main(String[] args){
		
		/*graph = {'A': ['B', 'C','E'],
	             'B': ['A','C', 'D'],
	             'C': ['D'],
	             'D': ['C'],
	             'E': ['F','D'],
	             'F': ['C']}
		*/
//		Stack<Main> stack = new Stack<>();
//		stack.add(new Main());
//		Main main = stack.peek();
//		main.a = 10;
//		System.out.println(stack.peek().a);
		InputGraph in = new InputGraph();
		graph = new HashMap<Integer, Vector<Node>>();
		graph = in.getInput();
		long time = System.currentTimeMillis();
		DFS dfs = new DFS(graph, new Node(1), new Node(10));
		dfs.getAvailablePath();
		System.out.println(System.currentTimeMillis() - time);
		System.out.println(dfs.sum);
//		BFS bfsAlgo = new BFS(graph, new Node(1), new Node(10));
//		bfsAlgo.getAvailablePath();
//		bfsAlgo.getMinPath();
//		System.out.println(System.currentTimeMillis() - time);
//		System.out.println(bfsAlgo.sum);
	}


	

}
