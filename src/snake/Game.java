package snake;

import snake.SnakeGame.Directions;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Game extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Game() {
		this.setLayout(new FlowLayout());
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jScrollPane1 = new JScrollPane();

		jTextArea1 = new JTextArea();
		jTextArea1.setBackground(new java.awt.Color(240, 240, 240));
		jTextArea1.setColumns(140);
		jTextArea1.setRows(80);
		jTextArea1.setFont(new Font("Consolas", Font.PLAIN, 15));

		jScrollPane1.setViewportView(jTextArea1);

		buttonDown = new JButton("Down");
		buttonDown.setSize(30, 30);
		buttonDown.addActionListener(this);

		buttonUp = new JButton("up");
		buttonUp.setSize(30, 30);
		buttonUp.addActionListener(this);

		buttonLeft = new JButton("left");
		buttonLeft.setSize(30, 30);
		buttonLeft.addActionListener(this);

		buttonRight = new JButton("right");
		buttonRight.setSize(30, 30);
		buttonRight.addActionListener(this);

		add(jScrollPane1);
		add(buttonDown);
		add(buttonUp);
		add(buttonLeft);
		add(buttonRight);

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

		 snakegame = new SnakeGame(140,80,Directions.right,jTextArea1);



		for(;;){
			try {
				Thread.sleep(100);
		} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		 	snakegame.newSnakePosition();
		  snakegame.food();
		}
	}

	public void actionPerformed(ActionEvent e) {
		
			if (e.getSource() == buttonUp)
				snakegame.setDirection(Directions.up);
			if (e.getSource() == buttonDown)
				snakegame.setDirection(Directions.down);
			if (e.getSource() == buttonLeft)
				snakegame.setDirection(Directions.left);
			if (e.getSource() == buttonRight)
				snakegame.setDirection(Directions.right);
	}
		
	

	private javax.swing.JScrollPane jScrollPane1;
	private static javax.swing.JTextArea jTextArea1;

	private JButton buttonUp;
	private JButton buttonDown;
	private JButton buttonLeft;
	private JButton buttonRight;
	private static SnakeGame snakegame;

}
