package shortestPath;
import java.util.HashSet;
import java.util.Set;


public class graph {
	
	private int size;
	private int E;
	private HashSet<weightedEdge>[] adj;
	
	graph(int size){
		this.size = size + 1;
		adj = (HashSet<weightedEdge>[]) new HashSet[this.size];
		for(int x = 0; x<this.size; x++){
			adj[x] = new HashSet<weightedEdge>();
		}
		
	}
	
	public void addEdge(int a, int b, int length){
		weightedEdge edge = new weightedEdge(a, b, length);
		adj[a].add(edge);
		E++;
	}
	
	public Set<weightedEdge> getEdges(int x){
		return adj[x];
	}
	
	public int size(){
		return size;
	}
	

}
