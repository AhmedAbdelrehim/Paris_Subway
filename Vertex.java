/**
*Written by 
*
*Ahmed Abdelrehim 8394971
*Amro Ahmed 7937646
*
* 6/12/2017
*/
import java.util.ArrayList;

public class Vertex implements Comparable<Vertex>{
	public int value;
	public String name; 
	public ArrayList<Edge> list;
	public boolean visited;
	public int minDistance;
	Vertex previous;

	public Vertex(int v, String n){
		value = v; 
		name = n;
		list = new ArrayList<Edge>(); 
		visited = false;
		minDistance = Integer.MAX_VALUE;
	}
	
	public void setVisited(boolean flag) {
		visited = flag;
	}
	
	

	/**
	 * This is necessary to allow vertices to be entered into a priority queue  in the Dijkstra algorithm 
	 * it compares vertices according to there minDistance integer variable
	 *  
	 *  @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Vertex other){
		return Integer.compare(minDistance, other.minDistance);
	}
	
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append(this.value);
		return str.toString();
		
	}
}