package com.receme.bfs_allpossiblepath;



//This will be used as the Queue elemen in the Holder.java

public class Node {
	
  public int val;
//  public int pos;
  public boolean visited;
  public int weight;

  public Node(){
	  
  }
  
  public Node(int _val, int weight){
	  
	  this.val = _val;
//	  this.pos = _val-64;
	  this.visited = false;
	  this.weight = weight;
  }

  public Node(int _val){

    this.val = _val;
//	  this.pos = _val-64;
    this.visited = false;
    this.weight = 1;
  }

  public boolean isVisited(){
	  
	  return visited;
  }
  
  public int getNodeVal(){
	  return val;
  }

}
