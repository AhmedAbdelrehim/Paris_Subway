/**
*Written by 
*
*Ahmed Abdelrehim 8394971
*Amro Ahmed 7937646
*
* 6/12/2017
*/


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;


public class ParisMetro {
	
	
	Graph parisSub; 
	 
	/**
	 * this static method takes a text file name as input and return a graph created by the data in the txt file 
	 * 
	 * @param filename
	 * @return Graph
	 * @throws IOException
	 */
	public static Graph readMetro(String filename) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader(filename)) ;
		
		String[] line1 = br.readLine().split(" ");
		// creating a graph n x m
		Graph subway = new Graph(Integer.parseInt(line1[0]),Integer.parseInt(line1[1]));
		
		String line = br.readLine();  
		// adding vertices into our graph by reading the file till the $ character
		while((line.length())>1) {
			String stationNumber = line.substring(0, 4);
			String stationName = line.substring(4);
			Vertex v = new Vertex(Integer.parseInt(stationNumber),stationName);
			subway.insertVertex(v, Integer.parseInt(stationNumber));
			line = br.readLine();	
		}
		
		// adding edges into our graph
		int j =0;
		while((line = br.readLine())!= null) {
			String[] parts = line.split(" ");
			Vertex start = subway.getVertex(Integer.parseInt(parts[0]));
			Vertex dest = subway.getVertex(Integer.parseInt(parts[1]));
			Edge e  = new Edge(start,dest,Integer.parseInt(parts[2]));
			subway.insertEdge(e, j);
			subway.associateEdgeToVertex(e,Integer.parseInt(parts[0])); 
			j++;
		}		
		return subway;			
	}
	

	
	public ParisMetro(int x) throws IOException {
		parisSub = readMetro("metro.txt");
		System.out.print("Line: ");
		// call to the recursive method
		parisSub.DFS(parisSub.getVertex(x),true); // print  
		
	}
	
	public ParisMetro(int from, int to) throws IOException {
		parisSub = readMetro("metro.txt");
		List<String> list = shortestPath(from,to);
		
		for (String station: list){
			System.out.print(station + " ");
		}
	}
	
	public ParisMetro(int from, int to, int broken ) throws IOException {
		parisSub = readMetro("metro.txt");
		List<String> list = shortestPath(from,to,broken);
		
		for (String station: list){
			System.out.print(station + " ");
		}
	}
	
	/**
	 * This method tracks back the shortest path from destination to the starting point, 
	 * it does so after getting a shortest path spanning tree using Dijkstras algorithm
	 * 
	 * these are the values of the starting and ending vertices that we need to find the path between
	 * @param start 
	 * @param destination
	 * @return
	 * @throws IOException
	 */
	private List<String> shortestPath(int start, int destination){
		
		parisSub.dijkstra(parisSub.getVertex(start));
		
		Vertex dest = parisSub.getVertex(destination);
		
		List<String> path = new ArrayList<String>();
		
		// if the graph is disconnected, there is no path
		if (dest.minDistance==Integer.MAX_VALUE){
			path.add("No path found");
			return path;
		}
		// The time needed will be the minimum distance stored at the destination vertex computed by Dijkstra's Algorithm
		System.out.println("Time = "+ dest.minDistance);
		
		// track back using the previous pointer until the previous is null, this is the shortest path 
		for (Vertex v = dest; v!=null; v=v.previous) {
			path.add(v.value+" ");
		}
		path.add("Path: ");
		
		// flips the path
		Collections.reverse(path);
		return path;
		
	}
	
	/**
	 *
	 * same as the other method, except it takes the broken line into account.
	 * 
	 * @param start
	 * @param destination
	 * @param broken, one end point of the broken line
	 * @return
	 * @throws IOException
	 */
	
	private List<String> shortestPath(int start, int destination, int broken) {
		
		parisSub.dijkstra(parisSub.getVertex(start),parisSub.getVertex(broken)); // This line is the only difference between this method ad the previous one
		Vertex dest = parisSub.getVertex(destination);
		
		List<String> path = new ArrayList<String>();
		
		if (dest.minDistance==Integer.MAX_VALUE){
			path.add("No path found");
			return path;
		}
		System.out.println("Time = "+ dest.minDistance);
		
		for (Vertex v = dest; v!=null; v=v.previous) {
			path.add(v.value+" ");
		}
		path.add("Path: ");
		Collections.reverse(path);
		return path;
		
	}
	
	public static void main(String args[]) throws IOException {
		
					if(args.length == 1) {
						ParisMetro metro = new ParisMetro(Integer.parseInt(args[0]));
					} else if (args.length == 2) {
						ParisMetro metro = new ParisMetro(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
					} else if (args.length == 3){
						ParisMetro metro = new ParisMetro(Integer.parseInt(args[0]),Integer.parseInt(args[1]),Integer.parseInt(args[2]));
					}else {
						System.out.println("please enter max three stations");
					}
			
		}
}
