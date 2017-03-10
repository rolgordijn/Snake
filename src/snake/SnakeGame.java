package snake;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class SnakeGame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum Directions {
		up, right, down, left,
	}

	private Directions dir;
	private int xsize;
	private int ysize;
	private int score;
	private Directions[][] pivot;
	private String[][] display;
	private Point head;
	private Point tail;
	private JTextArea ta;
	
	
	private javax.swing.JScrollPane jScrollPane1;
	private static javax.swing.JTextArea jTextArea1;

	private JButton buttonUp;
	private JButton buttonDown;
	private JButton buttonLeft;
	private JButton buttonRight;

	/**
	 * 
	 * @param xsize
	 * @param ysize
	 * @param dir
	 */
	public SnakeGame(int xsize, int ysize, Directions dir) {
		this.setLayout(new FlowLayout());
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jScrollPane1 = new JScrollPane();

		jTextArea1 = new JTextArea();
		jTextArea1.setBackground(new java.awt.Color(240, 240, 240));
		jTextArea1.setColumns(150);
		jTextArea1.setRows(70);
		jTextArea1.setFont(new Font("Consolas", Font.PLAIN, 15));
		jTextArea1.setEditable(false);
		jTextArea1.setBorder(null);

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

		
		
		
		this.xsize = xsize;
		this.ysize = ysize;
		pivot = new Directions[xsize][ysize];
		display = new String[xsize][ysize];
		head = new Point(2, 2);
		tail = new Point(2, 2);
		this.ta = jTextArea1;
		pivot[head.x][head.y] = dir;
        this.dir = dir;
		init();
		print();
		
		
		// stap 3 : allerlei initialisaties
		this.setSize( 800, 800 );
		// extra opdrachten --> zoeken via de index in klasse JFrame

		this.setAlwaysOnTop(true); // venster steeds zichtbaar

		//venster.setLocation(200,150); // zet positie hoek linksboven
		this.setLocationRelativeTo(null); // zet in het midden v/h scherm

		this.setVisible( true );
	}

	public void init() {

		for (int i = 0; i < xsize; i++) {
			for (int j = 0; j < ysize; j++) {
				display[i][j] = " ";
				pivot[i][j]=dir;
			}
		}
		for (int i = 0; i < xsize; i++) {
			display[i][0] = "-";
			display[i][ysize - 1] = "-";
		}
		for (int i = 0; i < ysize; i++) {
			display[0][i] = "|";
			display[xsize - 1][i] = "|";
		}
		display[head.x][head.y] = "*";

	}

	public void print() {
		ta.setText("");
		for (int i = 0; i < ysize; i++) {
			for (int j = 0; j < xsize; j++) {
				ta.append(display[j][i]);

			}
			ta.append("\r\n");

		}
	}

	public void setDirection(Directions dir) {
		//pivot[head.x][head.y] = dir;
		this.dir = dir;
	}


	public void food() {
		int x = (int) (Math.random() * (xsize - 1));
		int y = (int) (Math.random() * (ysize - 1));
		if (display[x][y].equals(" ") || display[x][y].equals("+")) {
			display[x][y] = "+";
		} else
			food();
	}

	public void newSnakePosition() {
		pivot[head.x][head.y] = dir;
		
		switch (pivot[head.x][head.y]) {
		case up:
			head.y--;
			break;
		case down:
			head.y++;
			break;
		case left:
			head.x--;
			break;
		case right:
			head.x++;
			break;
		default:
			break;
		}
		pivot[head.x][head.y] = dir;
		// when the snake eats food, the tail stays on the current location.
		// Moving the tail is only required when the head doesn't eat food.
		// When the head moves to a position where a * | or - sign is, than
		// you're game over.

		switch (display[head.x][head.y]) {
		case "+":
			display[head.x][head.y] = "*";
			score+=10;
			break;
		case " ":
			display[head.x][head.y] = "*";
			display[tail.x][tail.y] = " ";
			tailPosition();
			break;
		default:
			gameOver();
			break;
		}
		
		display[1][1] = Integer.toString(score%10000/1000);
		display[2][1] = Integer.toString(score%1000/100);
		display[3][1] = Integer.toString(score%100/10);
		display[4][1] = Integer.toString(score%10);
		print();
	

	}

	public void tailPosition() {
		switch (pivot[tail.x][tail.y]) {
		case up:
			tail.y--;
			break;
		case down:
			tail.y++;
			break;
		case left:
			tail.x--;
			break;
		case right:
			tail.x++;
			break;
		default:
			break;
		}
	}

	public void gameOver() {
		JFrame frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.setVisible(false);
		JOptionPane.showMessageDialog(frame, "Game over!", "Game over!", JOptionPane.WARNING_MESSAGE);
		System.exit(0);

	}

	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == buttonUp)
			setDirection(Directions.up);
		if (e.getSource() == buttonDown)
			setDirection(Directions.down);
		if (e.getSource() == buttonLeft)
			setDirection(Directions.left);
		if (e.getSource() == buttonRight)
			setDirection(Directions.right);
}

}
