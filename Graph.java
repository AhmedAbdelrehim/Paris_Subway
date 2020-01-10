/**
*Written by 
*
*Ahmed Abdelrehim 8394971
*Amro Ahmed 7937646
*
* 6/12/2017
*/
import java.io.IOException;
import java.util.PriorityQueue;

public class Graph{
	public  Vertex[] vertices;
	public   Edge [] edges;
	public int numberOfVertices;
	
	public Graph(int n, int m) {
		vertices = new Vertex[n];
		edges = new Edge[m]; 
	}
	
	public void insertVertex(Vertex v, int i) {
		vertices[i] = v;
	}
	
	public Vertex getVertex(int o) {
		return vertices[o];
	}
	
	public void insertEdge(Edge e, int i) {
		edges[i] = e;
	}
	
	public void associateEdgeToVertex(Edge e, int i) {
		vertices[i].list.add(e);
	}
	
	/**
	 * this method finds the line when given one of its stations, it performs DFS using recursion  
	 * 
	 * @param v vertex, starting point of the search
	 * @param print, a boolean to print the visited vertices or not
	 */
	
	public void DFS(Vertex v, boolean print) {
		v.setVisited(true);
		if(print) {
			System.out.print(v.toString()+" ");
		}
		
		
		for (Edge e : v.list) {
			if(!e.visited) {
				Vertex u = e.opposite(v, e);
				e.setVisited(true);
				
				// exclude the walking edges and already visited ones
				if (!u.visited && e.weight!=-1) {
					// recursive call 
					DFS(u,print); 
				}
			}
		}	
	}
	
	
	/**
	 * this methods implements Dijkastra algorithm, it takes in a vertex and treats it as 
	 * starting point. if you follow the previous of each vertex you will have a shortest path spanning tree. 
	 * 
	 * @param start , the value of the vertex to start the algorithm from
	 * @return Graph, shortest spanning tree graph
	 * @throws IOException
	 */
	public void dijkstra(Vertex r)  {
		
		
		Vertex root = r;
		
		// the distance from the starting point to itself is 0
		root.minDistance = 0;
		// Vertices are inserted in a priority queue in order to allow the remove min operations smoothly.
		// vertices are ordered in the priority queue according to there min distance 
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
		
		// add the root
		pq.add(root);
		
		while(!pq.isEmpty()) {
			
			// remove the minimum 
			Vertex u = pq.poll();
			
			//loop over outgoing vertices
			for(Edge e : u.list) {
				Vertex v = e.opposite(u, e);
				
				// if station is to be walked to, set distance to 90
				int time = e.weight; 
				if(time == -1) {
					time = 90;
				}
				// compute needed distance to reach the vertex through the new added edge
				int totalDistance = u.minDistance + time;
				
				// if new travel distacne is smaller, update 
				if(totalDistance<v.minDistance) {
					
					pq.remove(v);
					v.minDistance = totalDistance; 
					// link to get path later
					v.previous = u;
					pq.add(v);
				}
			}
		}
	}
	
	public void dijkstra(Vertex root, Vertex brokenLine)  {
		
		
		// finding the broken line, the DFS method marks all the edges belonging to the broken line as visited 
		
		DFS(brokenLine, false);
		root.minDistance = 0;
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
		pq.add(root);
		
		while(!pq.isEmpty()) {
			
			Vertex u = pq.poll();
			for(Edge e : u.list) {
				Vertex v = e.opposite(u, e);
				
				int time = e.weight; 
				if(time == -1) {
					time = 90;
				}
				int totalDistance = u.minDistance + time;
				
				// if the edge is previously visited by the DFS, then it belongs to the broken line and should be ignored 
				// this is the only difference between this method and the previous one
				if(!e.visited) {
					if(totalDistance<v.minDistance) {
						
						pq.remove(v);
						v.minDistance = totalDistance; 
						v.previous = u;
						pq.add(v);
						
					}
				}
				
			}
		}
	}
	
	
	public String toString() {
		StringBuffer str = new StringBuffer();
		for(Vertex v : vertices) {
			str.append(v.value + " -> " );
			for (Edge e : v.list) {
				str.append(e);
			}
			str.append("\n");
		}
		return str.toString();
	}
	
	
	
}