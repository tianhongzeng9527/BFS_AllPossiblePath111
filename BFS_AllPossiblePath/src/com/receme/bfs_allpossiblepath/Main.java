package com.receme.bfs_allpossiblepath;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Main {

	public static Map<Integer, Vector<Node> > graph = null;
	public Vector<Node> nodes = null;
	public static HashMap v;
	static Vector<Integer> as;
	

	public static void main(String[] args){
		
		/*graph = {'A': ['B', 'C','E'],
	             'B': ['A','C', 'D'],
	             'C': ['D'],
	             'D': ['C'],
	             'E': ['F','D'],
	             'F': ['C']}
		*/
		InputGraph in = new InputGraph();
		graph = new HashMap<Integer, Vector<Node>>();
		graph = in.getInput();
		
		BFS bfsAlgo = new BFS(graph, new Node(1), new Node(4));
		
		bfsAlgo.getAvailablePath();
		bfsAlgo.getMinPath();
	}


	

}
