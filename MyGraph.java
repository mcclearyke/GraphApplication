package cmsc256;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * Kendall McCleary
 * Project 6
 * CMSC 256 Fall 2019
 * For this project I will extend the UnweightedGraph class by implementing a class called MyGraph that adds functionality for five methods
 */

public class MyGraph<V> extends UnweightedGraph<V> {
	
	/*********************************************************************
	 * Overriding all 5 of the constructors from the UnweightedGraph class.
	 *********************************************************************/
	  
	    /** 1 Construct an empty graph */
	    public MyGraph() {
	    	
	    	super();
	    }
	    
	    /** 2 Construct a graph from vertices and edges stored in arrays */
	    public MyGraph(V[] vertices, int[][] edges) {
	    
	    	super(vertices, edges);
	    }

	    /** 3 Construct a graph from vertices and edges stored in List */
		public MyGraph(List<V> vertices, List<Edge> edges) {
	   
			super(vertices, edges);		
	  	}

	    /** 4 Construct a graph for integer vertices 0, 1, 2 and edge list */
		public MyGraph(List<Edge> edges, int numberOfVertices) {
			
			super(edges, numberOfVertices);
		}

		/** 5 Construct a graph from integer vertices 0, 1, and edge array */
		public MyGraph(int[][] edges, int numberOfVertices) {
			
			super(edges, numberOfVertices);
		}
	 
	  
	//private helper method to read in the file
	/************************************************************************
	 * !!!!!NEEDS TO BE CHANGED TO PUBLIC FOR MY JUNIT TO PASS MY TESTS!!!!!! 
	 *************************************************************************/
	private MyGraph<Integer> inFile(String fileName) throws FileNotFoundException {
		//Variables needed
		File inputFile;
		Scanner in;
		List<Edge> edges = new ArrayList<>();
		int numberOfVertices = 0;
		

				
			//accept a file name with a string parameter 
			try {
									
				inputFile =  new File(fileName);
				in = new Scanner(inputFile);		
			} 
							
			//if the file if unable to be opened the method should throw a file not found exception
			catch (FileNotFoundException ex) {
									
				throw new FileNotFoundException("Error. File not found!");
			}
			
			//if the file has contents
			if(in.hasNext()) {				
				/******************
				* Read in the file
				******************/
								
				//Read a line
				numberOfVertices = Integer.parseInt(in.nextLine().trim()); //The first line in the file contains a number of vertices
								
					//While there are lines in the input file
					while (in.hasNextLine()) {
									
									
						//Read the next line in as an array
						String[] line = (in.nextLine()).split(" ");
									
							//Assign the origin vertex to the first element in the array
							int originVertex = Integer.parseInt(line[0]);
								
								//For every terminating vertex
								for (int i = 1; i < line.length; i++) {
											
									//Assign each next element in the line as a terminating vertex
									int terminatingVertex =  Integer.parseInt(line[i]);
											
									//Create an edge object 
									Edge edge = new Edge(originVertex, terminatingVertex);
											
									//Add it to the edge list
									edges.add(edge);		
								}	
						}
			}
			
			//if the file is null
			else {
				
				//throw a null pointer exception
				throw new NullPointerException("The file is null!");
			}
								
		UnweightedGraph<Integer> myGraph = new MyGraph<Integer>(edges, numberOfVertices);

		return (MyGraph<Integer>) myGraph;
	}

	/* Will read a graph from a file and determine whether the graph is connected */
	public boolean isGraphConnected(String fileName) throws FileNotFoundException {			
		//Variables needed
		File inputFile;
		Scanner in;
		int numberOfVertices = 0;
		List<Edge> edges = new ArrayList<>();
			
			//accept a file name with a string parameter 
			try {
									
				inputFile =  new File(fileName);
				in = new Scanner(inputFile);
				
			} 
							
			//if the file if unable to be opened the method should throw a file not found exception
			catch (FileNotFoundException ex) {
									
				throw new FileNotFoundException("Error. File not found!");
			}
			
			//if the file has contents
			if(in.hasNext()) {
				/******************
				* Read in the file
				******************/
				
				//Read a line
				numberOfVertices = Integer.parseInt(in.nextLine().trim()); //The first line in the file contains a number of vertices
								
					//While there are lines in the input file
					while (in.hasNextLine()) {
						
						//Read the next line in as an array
						String[] line = (in.nextLine()).split(" ");
									
							//Assign the origin vertex to the first element in the array
							int originVertex = Integer.parseInt(line[0]);
								
								//For every terminating vertex
								for (int i = 1; i < line.length; i++) {
											
									//Assign each next element in the line as a terminating vertex
									int terminatingVertex =  Integer.parseInt(line[i]);
											
									//Create an edge object 
									Edge edge = new Edge(originVertex, terminatingVertex);
											
									//Add it to the edge list
									edges.add(edge);		
								}	
					}
			}
			
			//if the file is null
			else {
				
				//throw a null pointer exception
				return false;
			}
					
		// Closing input
		in.close();
					
		//Create instance g
		UnweightedGraph<Integer> g = new MyGraph<Integer>(edges, numberOfVertices);
				
			//invoke printEdges
			g.printEdges(); //displays all the data
	
			//get instance tree of UnweightedGraph.SearchTree.                               
			UnweightedGraph<Integer>.SearchTree tree = g.dfs(0); //invoke dfs()					

			//Getting the number of vertices from the search tree
			int numberOfVerticesFound = tree.getNumberOfVerticesFound();
			
				//if there is only one vertex
				if (numberOfVerticesFound == 1 && numberOfVertices == 1) {
					
					//the graph isn't connected
					return false;
				}
				
				//If the number of vertices found equals the numberOfVerticies
				else if (numberOfVerticesFound == numberOfVertices) {
					
					//the graph is connected
					return true;
				}
				
				//If the number of vertices found DOESN'T equals the numberOfVerticies
				else {
						
					//the graph isn't connected
					return false;
			}
	  }
	  
	  // finding all connected components in this graph instance
	  public List<List<Integer>>  getConnectedComponents() {
		 
		 List<List<Integer>> connectedComponents = new ArrayList<>(); //each element in the list is another list that contains all the vertices in a connected component
		  
		 List<Integer> vertex = new ArrayList<>();
		 
		 	for (int i = 0; i < vertices.size(); i++) {
			 
		 		//Add vertices to the vertex array list
		 		vertex.add((Integer) vertices.get(i));
		 	}
		
		 		//while the vertex list is not empty
		 		while(!(vertex.isEmpty())) {
			 
		 			//call the depth first search to create a search tree
		 			SearchTree st = super.dfs(vertex.get(0));
		
		 			//add the search order to the connectedComponents list
		 			connectedComponents.add(st.getSearchOrder());
			
		 				//Go through the vertex list
		 				for (int i = 0; i < vertex.size(); i++) {
				
		 					//remove the search order components from the vertex list
		 					vertex.removeAll(st.getSearchOrder());
		 				}
		 		}
		 	
		 	return connectedComponents;
	  }
	  
	public List<Integer> getPath(int u, int v) {
		ArrayList<Integer> verticesST = new ArrayList<>();
	    ArrayList<Integer> vertex = new ArrayList<>();
		ArrayList<Integer> verticesInPath = new ArrayList<>(); 
		ArrayList<Integer> verticesPath = new ArrayList<>();
		  
		  for (int i = 0; i < vertices.size(); i++) {
				 
			  //Add vertices to the vertex array list
			  vertex.add((Integer) vertices.get(i));
		  
		  } 
		  
		  	//call the depth first search to create a search tree
			SearchTree searcht = super.bfs(vertex.get(u));
			
				//add the search order 
				verticesST.addAll(searcht.getSearchOrder());
					
					//go through the search tree's search order
					for(int i = 0; i < verticesST.size(); i++) {
						
						//add it to the path
						verticesPath.add(verticesST.get(i));

							//if the search order contains the destination vertex
							if(verticesST.get(i) == v) {
								
								//Add all of the vertices to the final path list
								verticesInPath.addAll(verticesPath);
							}
							
							//if the search order dosen't contain the destination vertex
							if(!(verticesST.contains(v))) {
								
								//return null
								return null;	
							}
					}
					
					return verticesInPath;
			 	}

	 //The method returns true if there is a cycle in this instance of MyGraph
	 public boolean isCyclic() {
			
			//List to keep track of the stack
			Stack<Integer> stack = new Stack<>();

			 //Initialize list of vertices
			 List<Integer> allVertices = new ArrayList<>();
		 
		 	 	for (int i = 0; i < vertices.size(); i++) {
				 
		 	 		//Add vertices to the vertex array list
		 	 		allVertices.add((Integer) vertices.get(i));
		 	 	}
		 	 	
		 	 	System.out.println("the vertex list is: " + allVertices);
		 	 	
		 	 //Initialize parent array like in dfs method
		 	 int[] parent = new int[vertices.size()];
		  
		 	 	for (int i = 0; i < parent.length; i++) {
		 	 		
		 	 		parent[i] = -1; // Initialize parent[i] to -1
		 	 	}
		   
		 	//Initialize visited array
		    boolean[] isVisited = new boolean[vertices.size()];

		    int i = 0;
		    
				//while there are unvisited vertices 
				while(!(allVertices.isEmpty())) {
		    	
					//get a vertex
					int nextVertex = allVertices.get(i);
				
					//push it to the stack
					stack.push(nextVertex);
						
					if(isVisited[allVertices.get(i)] == false) {
		    		
						//mark as true
						isVisited[allVertices.get(i)] = true;
					}
									
					//remove from the vertices list
					allVertices.remove(allVertices.get(i));
								
					while(!(stack.isEmpty())) {
											
						int topOfStack = stack.peek();										
											
						//get its neighbors
						List <Integer> tosNeighbors = super.getNeighbors(topOfStack);
	          			 				
							//if it has no neighbors
							if(tosNeighbors == null) {
	           			 					
								//remove
								stack.pop();
							}
											
							//if it has neighbors
							else {
												
								for (int j = 0; j < tosNeighbors.size(); j++) {
		           			 					
		           			 		//find the next unvisited neighbor
		           			 		if (isVisited[tosNeighbors.get(j)] == false) {
		           			 							
		           			 			//set the parent of this vertex
		           			 			parent[tosNeighbors.get(j)] = topOfStack;
		           			 							
		           			 			//set vertex to visited
		           			 			isVisited[tosNeighbors.get(j)] = true;
		           			 		}
		   
		           			 		//if there is an neighbor that is all ready visited and is not the parent of the vertex
		           			 		else if (isVisited[tosNeighbors.get(j)] == true && (!(tosNeighbors.get(j) == parent[topOfStack]))) {
	           			 							
	           			 				return true;
	       			 				}
	       			 						
	       			 			}
		           			 }
						stack.pop();
		           		}
					}		 		

		 //if no cycles are found
		 return false; 
	 }    
                    
	 public List<Integer> findCycle(int u) {		
		//List to hold the cycle
		List<Integer> cycle = new ArrayList<>();
		
		//List to get the path
		List<Integer> path = new ArrayList<>();
		
		//List to keep track of the stack
		Stack<Integer> stack = new Stack<>();

		 //Initialize list of vertices
		 List<Integer> allVertices = new ArrayList<>();
	 
	 	 	for (int i = 0; i < vertices.size(); i++) {
			 
	 	 		//Add vertices to the vertex array list
	 	 		allVertices.add((Integer) vertices.get(i));
	 	 	}
	 	 	
	 	 	System.out.println("the vertex list is: " + allVertices);
	 	 	
	 	 //Initialize parent array like in dfs method
	 	 int[] parent = new int[vertices.size()];
	  
	 	 	for (int i = 0; i < parent.length; i++) {
	 	 		
	 	 		parent[i] = -1; // Initialize parent[i] to -1
	 	 	}
	   
	 	//Initialize visited array
	    boolean[] isVisited = new boolean[vertices.size()];

			
	    //while there are unvisited vertices 
	    while(!(allVertices.isEmpty())) {
	    	
	    	//get a vertex
			int nextVertex = allVertices.get(u);
			
			//push it to the stack
			stack.push(nextVertex);
					
	    	if(isVisited[allVertices.get(u)] == false) {
	    		
	    		//mark as true
				isVisited[allVertices.get(u)] = true;
				
				//add it to the cycle
				path.add(allVertices.get(u));

	    	}
								
			//remove from the vertices list
	    	allVertices.remove(allVertices.get(u));
														
							
				while(!(stack.isEmpty())) {
										
					int topOfStack = stack.peek();										
										
					//get its neighbors
					List <Integer> tosNeighbors = super.getNeighbors(topOfStack);
          			 				
						//if it has no neighbors
						if(tosNeighbors == null) {
           			 					
							//remove
							stack.pop();
						}
										
						//if it has neighbors
						else {
											
							for (int j = 0; j < tosNeighbors.size(); j++) {
	           			 					
	           			 		//find the next unvisited neighbor
	           			 		if (isVisited[tosNeighbors.get(j)] == false) {
	           			 							
	           			 			//set the parent of this vertex
	           			 			parent[tosNeighbors.get(j)] = topOfStack;
	           			 							
	           			 			//set vertex to visited
	           			 			isVisited[tosNeighbors.get(j)] = true;
	           			 						    
	           			 			//add it to the path
	           			 			path.add(tosNeighbors.get(j));
	           			 		}
	   
	           			 		//if there is an neighbor that is all ready visited and is not the parent of the vertex
	           			 		else if (isVisited[tosNeighbors.get(j)] == true && (!(tosNeighbors.get(j) == parent[topOfStack]))) {
           			 							
           			 					path.add(parent[topOfStack]);
           			 					
           			 						//last element in path
           			 						int lastElement = path.get(path.size() - 1);
           			 						System.out.println("THE LAST ELEMENT IS A " + lastElement);
       			 						
           			 						for (int i = path.size() - 1; i >= 0 ; i--) {
           			 				    
           			 						  if (path.get(i).equals(lastElement)) {
           			 						
           			 							  int startingVertex = path.get(i);
           			 							  System.out.println("THE STARTING VERTEX IS AT " + startingVertex);
           			 							  
           			 							for (int k = startingVertex; k < path.size() - 1; k ++) {
       			 								
           			 									cycle.add(path.get(k));
     					  			 			}	
       			 							
           			 							return cycle;
       			 						}
       			 						
       			 					}
	           			 		}					
	           			 	}
						}
           			 					
					stack.pop();		 				
				}				
	    }
	    
	    return null;
	 }
	 
	 
	  public static void main(String args[]) throws FileNotFoundException {
		 
		  MyGraph<Integer> myGraph = new MyGraph<>();
		  
		  MyGraph<Integer> myGraph1 = myGraph.inFile("ex2.txt");

		  List<List<Integer>> components = myGraph1.getConnectedComponents();
		  
		  	System.out.println("Is the graph connected: " + myGraph.isGraphConnected("ex2.txt"));
		  	System.out.println("The connected components are: " + components);
		  	System.out.println("A path from 0 to 1 is: " + myGraph1.getPath(1, 4));
	        System.out.println("Does the graph contain a cycle? " + myGraph1.isCyclic());
	        System.out.println("The cycle starting from 0 is: " + myGraph1.findCycle(0));
	  }	  
}