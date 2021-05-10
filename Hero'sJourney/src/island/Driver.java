package island;

public class Driver {
	
	public static void main(String arg[]) {
		//launches main program
		Menu m = new Menu();
		
		boolean start = false;
		
		while (!start) {
			start = m.getStart();
			System.out.println(start);
		}
		
		System.out.println("game start");
		
		Island panel = new Island();
	}
}