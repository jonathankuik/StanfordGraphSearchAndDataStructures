package shortestPath;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

public class shortestPath {

	public static void readFile(graph graph){
		try{
            BufferedReader buf = new BufferedReader(new FileReader("dijkstraData.txt"));
            String lineJustFetched = null;
            int firstVertex;
             
            //int lastVertex = 0;
            
            while(true){
                lineJustFetched = buf.readLine();
                if(lineJustFetched == null){  
                    break; 
                }else{
                	String[] wordsArray;
                    wordsArray = lineJustFetched.split("\\s+");
                    firstVertex = Integer.parseInt(wordsArray[0]);
                   
                    for(int i = 1; i < wordsArray.length; i++){
                    	String[] endPointAndWeight;
                    	endPointAndWeight = wordsArray[i].split(",");
                    	graph.addEdge(firstVertex, Integer.parseInt(endPointAndWeight[0]), Integer.parseInt(endPointAndWeight[1]));
                    	
                    }
                }
            }

            buf.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
	
	
	public static int dijkstra(graph graph, int startingPoint, int endPoint){
		HashSet<Integer> viewed = new HashSet<Integer>();
		int[] pathLengths = new int[graph.size()];
		int v, w, l;
		weightedEdge shortest;
		viewed.add(startingPoint);
		pathLengths[startingPoint] = 0;
		v = startingPoint;
		
		while(!viewed.contains(endPoint)){
			shortest = null;
			for(int vertex: viewed){
				for(weightedEdge edge : graph.getEdges(vertex)){
					if(!viewed.contains(edge.getB())){
						if(shortest == null || edge.weight + pathLengths[edge.getA()] < shortest.weight + pathLengths[shortest.getA()]){
							shortest = edge;		
						}
					}
				}
			}
			viewed.add(shortest.getB());
			pathLengths[shortest.getB()] = pathLengths[shortest.getA()] + shortest.weight;
			v = shortest.getB();
		}
		
		return pathLengths[v];
	}
	
	public static void main(String[] args) {
		
		graph graph = new graph(200);
		readFile(graph);
		
		
		
		int[] endPoints = {7,37,59,82,99,115,133,165,188,197};
		//int[] endPoints = {1,2,3,4,5,6,7,8,9,10,11};
		//int[] endPoints = {1,2,3,4};
		int shortest;
		
		for(int x = 0; x<endPoints.length; x++){
			shortest = dijkstra(graph, 1, endPoints[x]);
			System.out.print( shortest + ",");
		}
		
	}

}
