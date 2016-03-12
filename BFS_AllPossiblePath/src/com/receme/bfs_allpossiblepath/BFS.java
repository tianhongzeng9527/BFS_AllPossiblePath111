package com.receme.bfs_allpossiblepath;


import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class BFS {
	
	//private Queue<Integer> path_queue = new LinkedList<Integer>();
	private MyQueue q;
	private Vector<Node> temp_path, tmp_path;
	private Map<Integer, Vector<Node>> graph;
	public Vector<Node> all_linkNodes;
	private Node startNode;
	private Node endNode;
	private int minPathLength;
	private Vector<Node> min_path;

	public BFS(Map<Integer, Vector<Node>> graph2, Node _startNode,
			Node _endNode){
		minPathLength = -1;
		min_path = new Vector<Node>();
		q = new MyQueue();
		temp_path = new Vector<Node>();
		tmp_path = new Vector<Node>();
		graph = new HashMap<Integer, Vector<Node>>();
		all_linkNodes = new Vector<Node>();
		
		this.graph = graph2;
		this.startNode = _startNode;
		this.endNode = _endNode;
		
			
	}
	
	public void getAvailablePath(){
		bfs_Implementation();
	}
	
	private void bfs_Implementation(){
		
		temp_path.add(startNode);
		//print startNode;
		q.enQueue(temp_path);
		
		
		while(!q.isEmpty()) {
			tmp_path = q.deQueue();
			//System.out.println(tmp_path.size());
			
			Node last_node = new Node();
			last_node = q.getLastNode(tmp_path);
						
			if(last_node.val == endNode.val){
				System.out.print("Valid path: "); 
				printPath(tmp_path);
				System.out.println(tmp_path.size());
				int tmpPathLength = 0;
				for(Node node : tmp_path){
					tmpPathLength += node.weight;
					System.out.print(node.val);
				}
				if(minPathLength == -1){
					minPathLength = tmpPathLength;
					min_path = tmp_path;
				}
				else if (minPathLength > tmpPathLength){
					minPathLength = tmpPathLength;
					min_path = tmp_path;
				}
				System.out.println(minPathLength);
				continue;
			}
		
			Vector<Node> all_linkNodes = graph.get(last_node.val);
						
			for(int i=0; i< all_linkNodes.size(); i++){
				
				Node linknode = all_linkNodes.get(i);
				
				if(!isIn_tmp_path(linknode)){
					Vector<Node> newPath = new Vector<Node>();
					newPath.addAll(tmp_path);
					newPath.add(linknode);
		
					q.enQueue(newPath);
					
				}
			}		
			
		}		
	}
	
	
	private void printPath(Vector<Node> path){
		
		for(int i = 0;i<path.size(); i++){
			System.out.print(path.elementAt(i).val+" ");
			
		}
		System.out.print("\n");
	}
	
	
	private boolean isIn_tmp_path(Node linkNode){
		
		for(int j = 0; j<tmp_path.size(); j++){
			Node n = tmp_path.get(j);
			
			if(linkNode.val == n.val )
				return true;
		}
		
		return false;
		
	}

	public void getMinPath(){
		System.out.println("------------------");
		printPath(min_path);
	}
	

}
