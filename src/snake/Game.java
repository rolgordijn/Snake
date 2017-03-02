package snake;

import javax.swing.*;
import snake.SnakeGame.Directions;
import java.awt.Font;

public class Game extends JFrame {

	public Game() {
		jScrollPane1 = new JScrollPane();
		jTextArea1 = new JTextArea();

		jTextArea1.setBackground(new java.awt.Color(240, 240, 240));
		jTextArea1.setColumns(20);
		jTextArea1.setFont(new Font("Consolas", Font.PLAIN, 15));
		jTextArea1.setRows(5);
		jScrollPane1.setViewportView(jTextArea1);  

		add(jScrollPane1);
	}

	public static void main(String[] args) {


		// Maak een venster
		Game game;  		// stap 1 : declareer een referentie

		game = new Game(); // stap 2 : een instantie maken (de Constructor oproepen)

		// stap 3 : allerlei initialisaties
		game.setSize( 800, 800 );
		// extra opdrachten --> zoeken via de index in klasse JFrame

		game.setAlwaysOnTop(true); // venster steeds zichtbaar

		//venster.setLocation(200,150); // zet positie hoek linksboven
		game.setLocationRelativeTo(null); // zet in het midden v/h scherm

		game.setVisible( true );

		SnakeGame snakegame = new SnakeGame(80,40,Directions.left,jTextArea1);



		for(int i=0;i<400;i++){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			snakegame.newSnakePosition();
			
		    if(i%2==0) snakegame.food();
			
			if(i%15==0){
				if(snakegame.HeadDirection()==Directions.left) snakegame.setDirection(Directions.down);
				else if(snakegame.HeadDirection()==Directions.right) snakegame.setDirection(Directions.up);
				else if(snakegame.HeadDirection()==Directions.down) snakegame.setDirection(Directions.right);
				else if(snakegame.HeadDirection()==Directions.up) snakegame.setDirection(Directions.left);
				
			}

		}
	}


	private javax.swing.JScrollPane jScrollPane1;
	private static javax.swing.JTextArea jTextArea1;

}
