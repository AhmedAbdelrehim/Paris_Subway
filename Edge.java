/**
*Written by 
*
*Ahmed Abdelrehim 8394971
*Amro Ahmed 7937646
*
* 6/12/2017
*/
public class Edge{
	public Vertex start;
	public Vertex dest; 
	public int weight;
	public boolean visited;

	public Edge(Vertex x, Vertex y, int w){
		start = x;
		dest = y;
		weight = w; 
		visited = false;
	}

	
	public void setVisited(boolean flag) {
		visited = flag;
	}


	
	public Vertex opposite(Vertex v, Edge e ) {
		if (e.start.equals(v)) {
			return e.dest;
		}
		return e.start;
		
	}
	
	public String toString() {
		return (" {"+start.value+" , "+dest.value+" , "+weight+"} ");
	}
	
}