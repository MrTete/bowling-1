import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Game {
	
	public static int readInt(Scanner reader) {
		boolean ok = false;
		int integer = -1;
		while(!ok) {
			try {
				integer = reader.nextInt();
				//reader.nextLine();//empty the line because nextInt doesn't read all the line
				ok = true;
			}
			catch(InputMismatchException e) {
				ok = false;
				System.out.println("Veuillez entrer un entier !");
				reader.nextLine();
			}
		}
		return integer;
	}
	
	public static void play() {
		Scanner reader = new Scanner(System.in);
		System.out.println("==== Bowling ===");
		System.out.println();
		System.out.print("Nombre de joueurs : ");
		int nbPlayers = readInt(reader);
		reader.nextLine(); //empty the line because nextInt doesn't read all the line
		List<Player> players = new ArrayList<Player>();
		
		//Create the players
		for(int i = 1; i <= nbPlayers; ++i) {
			System.out.print("Nom joueur " +i+": ");
			String name = reader.nextLine();
			players.add(new Player(name));
			System.out.println();
		}
		
		boolean end = false;
		while(!end) { //if end == true => end of the game
			end = true;
			for(Player p : players) {
				if(p.getGame().canPlay()) {
					int score1;
					int score2;
					System.out.println(p.getName() + " :");
					do {
						System.out.println("lancer 1 :");
						score1 = readInt(reader);
						reader.nextLine(); //empty the line because nextInt doesn't read all the line
						System.out.println("lancer 2 :");
						score2 = readInt(reader);
						reader.nextLine(); //empty the line because nextInt doesn't read all the line
					}while(!p.getGame().addScore(new Pair<Integer, Integer>(score1, score2)));
					end = false;
				}
				
			}
		}
		Collections.sort(players, Collections.reverseOrder());
		System.out.println("Classement : ");
		int i = 1;
		for(Player p : players) {
			
			System.out.println(i + " " + p);
			++i;
		}
	}
	
	public static void main(String[] args) {
		
		play();
		
	}

}
