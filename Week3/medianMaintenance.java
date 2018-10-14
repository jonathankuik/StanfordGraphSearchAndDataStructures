package medianMaintenance;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class medianMaintenance {
	
	private static void readFile(String fileName, ArrayList<Integer> numbers){
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line;
			line = br.readLine();
			
			while(line != null){
				numbers.add(Integer.parseInt(line));
				line = br.readLine();
			}
			
			br.close();
			
		} catch (IOException e) {
			
		}
			
	}
	private static void balanceHeaps(int currentHeap, int otherHeap, Queue[] heaps){
		int balanceNumber = 0;
		balanceNumber = (int) heaps[currentHeap].poll() * -1;
		heaps[otherHeap].add(balanceNumber);
	}

	public static void main(String[] args) {
		
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		String fileName = "medians.txt";	
		
		//read in the values of all the medians from the file
		readFile(fileName, numbers);
		
		//Low supports extract max
		Queue<Integer> low = new PriorityQueue<Integer>();
		
		//High supports extract min
		Queue<Integer> high = new PriorityQueue<Integer>();
		
		//Create an array of the heaps to make balancing easier
		Queue<Integer>[] heaps = new Queue[2];
		heaps[0] = low;
		heaps[1] = high;
		
		
		//As the numbers come in, is the number greater than the max of low, bigger than the min of high?
		//initialize everything
		int median = numbers.get(0);
		int index = 1;
		low.add(median * -1);
		int currentHeap = 0;
		int otherHeap = 1;
		int newNumber = 0;
		
		while(index<numbers.size()){
			newNumber = numbers.get(index);
			if(newNumber<=low.peek()*-1){
				low.add(newNumber * -1);
				currentHeap = 0;
				otherHeap = 1;
			} else{
				high.add(newNumber);
				currentHeap = 1;
				otherHeap = 0;
			}
			if(Math.abs(low.size() - high.size()) > 1){
				balanceHeaps(currentHeap, otherHeap, heaps);
				
			}
			//update median with the assumption that the larger heap contains the median and if they're equal in size, we
			//take the bottom
			if(high.size()>low.size()){
				median += high.peek();
			} else{
				median += low.peek() * -1;
			}
			index++;
			
		}
	System.out.println(median % 10000);

		
		
	

	}

}
