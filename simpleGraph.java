import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;


public class simpleGraph {
	
	public static int t = 0;
	public static int s = 0;
	
	
	public static void readFile(graph graph){
		try{
            BufferedReader buf = new BufferedReader(new FileReader("SCC.txt"));
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
                    if(!graph.containsVertex(firstVertex)){
                    	graph.addVertex(firstVertex);
                    }
                   
                    for(int i = 1; i < wordsArray.length; i++){
                    	graph.addEdge(firstVertex, Integer.parseInt(wordsArray[i]));
                    	if(!graph.containsVertex(Integer.parseInt(wordsArray[i]))){
                    		graph.addVertex(Integer.parseInt(wordsArray[i]));
                    	}
                    }
                }
            }

            buf.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
	
	private static void DFSfinishing(graph graph, int vertexS, int[] finishing){
				
		graph.markViewed(vertexS);
		for(int i: graph.getEdges(vertexS)){
			if(!graph.checkViewed(i)){
				DFSfinishing(graph, i, finishing);
			}
		}
		//System.out.println(t);
		finishing[t] = vertexS;
		t++;
	}
	
	private static void DFSleader(graph graph, int vertexS){	
		graph.markViewed(vertexS);
		for(int i: graph.getEdges(vertexS)){
			if(!graph.checkViewed(i)){
				graph.addChildToLeader(s, i);
				DFSleader(graph, i);
			}
		}
	}
			

	public static void main(String[] args) {
		graph graph = new graph(true);
		readFile(graph);
		graph reverse = graph.reverse();
		
		HashMap<Integer, Integer> leaders = new HashMap<Integer, Integer>();
		int[] finishingTimes = new int[graph.size()];
		
		
		for(int vertex: reverse.getKeyList()){
			if(!reverse.checkViewed(vertex)){
				DFSfinishing(reverse, vertex, finishingTimes);
			}
		}
		
		
		for(int x = finishingTimes.length-1; x>=0; x--){
			if(!graph.checkViewed(finishingTimes[x])){
				s = finishingTimes[x];
				graph.addLeader(s);
				DFSleader(graph, s);
				graph.checkTop5(graph.getLeaderSize(s));
			}
		}
		
		//so now what I have a HashMap of a vertex, followed by all the vertexes in the
		//SCC, so I just need to loop through and determine the order of them. 
		
		graph.printTop5SCCs();
		

		
	}

}
