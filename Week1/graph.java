import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;



public class graph {
	
	private HashMap<Integer,ArrayList<Integer>> adjacencyList;
	private boolean directed;
	private int size;
	private HashSet<Integer> viewed;
	private HashMap<Integer, ArrayList<Integer>> leaders;
	private int[] top5SCCs;
	private int tracker;
	private int trackerTemp;

	public graph(boolean directed){
		adjacencyList = new HashMap<Integer,ArrayList<Integer>>();
		viewed = new HashSet<Integer>();
		leaders = new HashMap<Integer, ArrayList<Integer>>();
		this.directed = directed;
		top5SCCs = new int[5];
		size = 0;
		tracker = 5;
	}
	
	public void addLeader(int leader){
		ArrayList<Integer> vertexList = new ArrayList<Integer>();
		vertexList.add(leader);
		leaders.put(leader, vertexList);
	}
	
	public void checkTop5(int size){
		if(size>=top5SCCs[4]){
			tracker = 4;		
			while(tracker>0){
				if(size>=top5SCCs[tracker-1]){
					tracker--;
				} else{
					break;
				}
			}
		}
		while(tracker<5){
			trackerTemp = top5SCCs[tracker];
			top5SCCs[tracker] = size;
			size = trackerTemp;
			tracker++;
		}
			
		tracker = 5;
	}
	
	public void printTop5SCCs(){
		System.out.print("[");
		for(int x = 0; x<top5SCCs.length; x++){
			System.out.print(top5SCCs[x]+",");
			
		}
		System.out.print(top5SCCs[4]);
		System.out.print("]");
	}
	
	public void addChildToLeader(int leader, int childVertex){
		leaders.get(leader).add(childVertex);
	}
	
	public HashMap<Integer, ArrayList<Integer>> getLeadersList(){
		return leaders;
	}
	
	public int getLeaderSize(int vertex){
		return leaders.get(vertex).size();
	}
	
	public void addVertex(int x){
		ArrayList<Integer> vertexList = new ArrayList<Integer>();
		adjacencyList.put(x, vertexList);
		size++;
	}
	
	public boolean checkViewed(int vertex){
		return viewed.contains(vertex);
	}
	
	public void markViewed(int vertex){
		viewed.add(vertex);
	}
	
	public HashSet<Integer> getViewed(){
		return viewed;
	}
	
	public void addEdge(int firstVertex, int secondVertex){
		adjacencyList.get(firstVertex).add(secondVertex);
		if (!directed){
			if(!adjacencyList.containsKey(secondVertex)){
				this.addVertex(secondVertex);
			}
			adjacencyList.get(secondVertex).add(firstVertex);
		}
	}
	
	public boolean containsVertex(int x){
		return adjacencyList.containsKey(x);
	}
	
	public String toString(){
		return adjacencyList.toString();
	}
	
	public String printEdgeList(int x){
		String edgeList = "[";
		for(int i: adjacencyList.get(x)){
			edgeList += i + " ";
		}
		edgeList += "]";
		return edgeList;
	}
	
	public Set<Integer> getKeyList(){
		return adjacencyList.keySet();
	}
	
	public ArrayList<Integer> getEdges(int vertex){
		if(adjacencyList.get(vertex) == null){
			ArrayList<Integer> emptyList = new ArrayList<Integer>();
			return emptyList;
		}
		return adjacencyList.get(vertex);
	}
	
	public int size(){
		return size;
	}
	
	public graph reverse(){
		graph reverse = new graph(true);
		for(int x=1; x<=this.size; x++){
			if(adjacencyList.containsKey(x)){
				for(int y: adjacencyList.get(x)){
					if(!reverse.containsVertex(y)){
						reverse.addVertex(y);
					}
					reverse.addEdge(y, x);
				}
			}
		}
		return reverse;
	}
	
}
