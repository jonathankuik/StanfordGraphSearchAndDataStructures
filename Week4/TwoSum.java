package TwoSum;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class TwoSum {
	
	private static void readFile(String fileName, HashSet<Long> allNumbers, HashSet<Long> negativeNumbers, HashSet<Long> positiveNumbers ){
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line;
			line = br.readLine();
			int index = 0;
			long numberToAdd;
			long min = 0;
			long max = 0;
			
			while(line != null){
				numberToAdd = Long.parseLong(line);	
				allNumbers.add(numberToAdd);
				if(numberToAdd>0){
					positiveNumbers.add(numberToAdd);
				} else{
					negativeNumbers.add(numberToAdd);
				}
				index++;
				line = br.readLine();
			}
			br.close();
		} catch (IOException e) {}
	}

	public static void main(String[] args) {
		HashSet<Long> allNumbers = new HashSet<Long>();
		HashSet<Long> negativeNumbers = new HashSet<Long>();
		HashSet<Long> positiveNumbers = new HashSet<Long>();
		String fileName = "integers.txt";	
		
		//read in the values of all the medians from the file
		readFile(fileName, allNumbers, negativeNumbers, positiveNumbers);

		long x,y;
		int count = 0;
		int reviewed = 0;
		
		
		for(long z = -10000; z<=0; z++){
			for(Long each: negativeNumbers){			
				y = z-each;
				if(allNumbers.contains(y) && y != each){
					count += 1;
					break;
				}
			}
		}
		System.out.println("Done with first half");
		
		for(long z = 1; z<=10000; z++){
			for(Long each: positiveNumbers){			
				y = z-each;
				if(allNumbers.contains(y) && y != each){
					count += 1;
					break;
				}
			}
		}
		
		System.out.println(count);
		
	}

}
