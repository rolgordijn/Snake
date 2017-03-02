package snake;

import javax.swing.*;
import snake.SnakeGame.Directions;

public class Game extends JFrame {

	public Game() {
		jScrollPane1 = new JScrollPane();
		jTextArea1 = new JTextArea();

		jTextArea1.setBackground(new java.awt.Color(240, 240, 240));
		jTextArea1.setColumns(20);
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
		game.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		game.setTitle( "Mooi hee !" );
		// extra opdrachten --> zoeken via de index in klasse JFrame

		game.setAlwaysOnTop(true); // venster steeds zichtbaar

		//venster.setLocation(200,150); // zet positie hoek linksboven
		game.setLocationRelativeTo(null); // zet in het midden v/h scherm

		game.setVisible( true );

		SnakeGame snakegame = new SnakeGame(80,40,Directions.left,jTextArea1);



		for(int i=0;i<10;i++){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			snakegame.newSnakePosition();
			if(i%3==0){
				snakegame.setDirection(Directions.up);
				snakegame.food();
			}

		}
	}


	private javax.swing.JScrollPane jScrollPane1;
	private static javax.swing.JTextArea jTextArea1;

}
