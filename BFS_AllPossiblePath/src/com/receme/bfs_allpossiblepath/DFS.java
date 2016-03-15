package com.receme.bfs_allpossiblepath;

import java.util.Map;
import java.util.Stack;
import java.util.Vector;

public class DFS {
    private Stack<Node> nodeStack;
    private Node startNode;
    private Node endNode;
    private int minPathLength;
    private Vector<Node> min_path;
    private Map<Integer, Vector<Node>> graph;
    public int sum = 0;

    public DFS(Map<Integer, Vector<Node>> graph, Node _startNode,
               Node _endNode) {
        this.graph = graph;
        minPathLength = -1;
        nodeStack = new Stack<>();
        min_path = new Vector<Node>();
        this.startNode = _startNode;
        this.endNode = _endNode;
    }

    public void getAvailablePath() {
        DFSImplementation();
    }

    private Node getUnvisitedChildNode(Node parent) {
        Node result = null;
        Vector<Node> vector = graph.get(parent.val);
        int i = 0;
        for(Node node1: vector){
            if((vistied[node1.val] != 1) && (!node1.isVisited)){
                result = node1;
                result.isVisited = true;
                vistied[node1.val] = 1;
                break;
            }
            if(i >= 2){
                break;
            }
            i++;
        }
        return result;
    }
    private void setChildUnVistited(Node parent){
        Vector<Node> vector = graph.get(parent.val);
        for(Node node1: vector){
            node1.isVisited = false;
        }
    }

    int[] vistied = new int[600];
    private void DFSImplementation() {
        vistied[startNode.val] = 1;
        nodeStack.add(startNode);
        while (!nodeStack.isEmpty()) {
            Node node = nodeStack.peek();
//            System.out.println(node.val);
            Node childNode = getUnvisitedChildNode(node);
            if (childNode != null) {
                nodeStack.add(childNode);
                if(childNode.val == endNode.val){
                    sum++;
                    printLength(nodeStack);
                    vistied[nodeStack.peek().val] = 0;
                    setChildUnVistited(nodeStack.peek());
                    nodeStack.pop();
                }
            } else {
                vistied[nodeStack.peek().val] = 0;
                setChildUnVistited(nodeStack.peek());
                nodeStack.pop();
            }
        }
    }

    private void printLength(Stack<Node> path){
        for(Node node : path){
            System.out.print(node.val+"   ");
        }
        System.out.println();
    }

    private int getPathLength(Stack<Node> path) {
        int sum = 0;
        for (Node node : path) {
            sum += node.val;
        }
        return sum;
    }
}
