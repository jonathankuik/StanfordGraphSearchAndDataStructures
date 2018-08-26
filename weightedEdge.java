package shortestPath;

public class weightedEdge {
	private int a;
	private int b;
	public int weight;
	
	
	
	weightedEdge(int a, int b, int weight){
		this.a = a;
		this.b = b;
		this.weight = weight;
	}
	
	
	public int getWeight(){
		return weight;
	}
	
	public int getA(){
		return a;
	}
	
	public int getB(){
		return b;
	}
}
