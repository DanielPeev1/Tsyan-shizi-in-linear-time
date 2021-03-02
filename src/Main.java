import java.util.Scanner;
import java.util.HashSet;
class Tzyan {
	
	static Scanner in = new Scanner (System.in);
	private int a, b;
	 int arr [];
	HashSet <Integer> set = new HashSet <Integer> ();
	
	public Tzyan (int i, int j) {
		a = i;
		b = j;
		arr = new int [max(a, b)];
		
		int k = 1, m = 2;
		while (m < arr.length && k < a) {
			arr [k] = m;
			set.add(k);
			set.add(m);
			if (!set.contains (k + 1) && !set.contains (m + 2)) {
				k ++;
				m += 2;
			}
			else {
				k += 2;
				m += 3;
			}
		}
	}
	public void play () {
		String first;
		int p;
		int stones;
		int player = 1;
		System.out.println (currentState ());
		System.out.println ("Would you like to play first? (YES/NO)");
		first = in.nextLine ();
		if (first.equalsIgnoreCase("no")) {
			System.out.println ("The computer makes a move");
			computerMove ();
			System.out.println (currentState ());
		}
		while (a != 0 && b != 0) {
			
			System.out.println ("Please enter the pile you wish to take from (1, 2 or both (3))");
			p = in.nextInt ();
			System.out.println ("Please enter how many stones you wish to take from pile(s) " + p);
			stones = in.nextInt ();
			
			if (!playerMove (p, stones)) {
				System.out.println("Please make a correct move. The choice of pile must "
						+ "be one of the three numbers 1, 2 or 3, "
						+ "and you cannot take more stones from a pile than there are available");
			}
			
			else player ^= 3;
			if (a == 0 && b == 0) {
				if (player == 1) System.out.println("The computer wins!");
				else System.out.println("The player wins!");
				break;
			}
			System.out.println(currentState ());
			if (player == 2) {computerMove ();System.out.println ("The computer makes a move");}
			player = 1;
			System.out.println(currentState ());
			if (a == 0 && b == 0) {
				if (player == 1) System.out.println("The computer wins!");
				else System.out.println("The player wins!");
				break;
			}	
		}
	}
	void computerMove () {
		
		if (arr[min (a, b)] ==  max(a, b)) {
			if (a > 0) a --;
			else b --;
		}
		else {
			if (a > b) a = arr [min (a, b)];
			else if (b > a) b = arr [min (a, b)];
			else {a = 0; b = 0;}
		}
	}
	boolean playerMove (int pile, int num) {
		switch (pile) {
			
		case 1: {
			if (a < num) return false;
			a -= num;
			return true;
		}
		case 2: {
			if (b < num) return false;
			b -= num;
			return true;
		}
		case 3: {
			if (min (a, b) < num) return false;
			a -= num;
			b -= num;
			return true;
			}	
		}
		return false;
	}
	public String currentState () {
		return String.format("The current state of the piles is:%nPile 1: %s stones left, Pile 2: %s stones left", a, b);
	}
	 int max (int a, int b) {
		if (a > b) return a;
		return b;	
		}
	 int min (int a, int b) {
		 if (a < b) return a;
		 return b;
	 }
}
public class Main {
	
	public static void main (String [] args) {
		int n, m;
		Scanner in = new Scanner (System.in);
		System.out.println ("Please enter the number of stones in the first pile:");
		n = in.nextInt ();
		System.out.println ("Please enter the number of stones in the second pile:");
		m = in.nextInt ();
		in.nextLine ();
	Tzyan t = new Tzyan (n, m);
	
		t.play();
}
}