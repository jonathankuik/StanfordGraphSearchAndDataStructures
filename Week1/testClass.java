
public class testClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		graph graph = new graph(true);
		
		graph.printTop5SCCs();
		graph.checkTop5(100);
		graph.printTop5SCCs();
		graph.checkTop5(100);
		graph.printTop5SCCs();
		graph.checkTop5(101);
		graph.printTop5SCCs();
		graph.checkTop5(5);
		graph.printTop5SCCs();
		
		
	}

}
