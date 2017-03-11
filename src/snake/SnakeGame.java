package snake;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
	private JPanel p;

	/**
	 * 
	 * @param xsize
	 * @param ysize
	 * @param dir
	 */
	public SnakeGame(int xsize, int ysize, Directions dir) {
		p = new JPanel();

		this.setLayout(new FlowLayout());
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setContentPane(p);

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

		// p.add(jScrollPane1);
		p.add(buttonDown);
		p.add(buttonUp);
		p.add(buttonLeft);
		p.add(buttonRight);

		this.xsize = xsize;
		this.ysize = ysize;
		pivot = new Directions[xsize][ysize];
		display = new String[xsize][ysize];
		head = new Point(xsize/2, ysize/3);
		tail = new Point(xsize/2, ysize/3);
		this.ta = jTextArea1;
		pivot[head.x][head.y] = dir;
		this.dir = dir;
		init();
		print();

		// stap 3 : allerlei initialisaties
		this.setSize(800, 800);
		// extra opdrachten --> zoeken via de index in klasse JFrame

		this.setAlwaysOnTop(true); // venster steeds zichtbaar

		// venster.setLocation(200,150); // zet positie hoek linksboven
		this.setLocationRelativeTo(null); // zet in het midden v/h scherm

		this.setVisible(true);
		
		play();
	
	}
	
	private void play(){
		for (int i = 0; i > -1; i++) {
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			newSnakePosition();
			if (i % 20 == 0)
				food();
		}
	}

	private void init() {

		for (int i = 0; i < xsize; i++) {
			for (int j = 0; j < ysize; j++) {
				display[i][j] = " ";
				pivot[i][j] = dir;
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
		if (p.getGraphics() != null) {
			Graphics g = p.getGraphics();
			for (int i = 0; i < xsize; i++) {
				for (int j = 0; j < ysize; j++) {
					switch (display[i][j]) {
					case "-":
						g.setColor(Color.BLACK);
						break;
					case "|":
						g.setColor(Color.BLACK);
						break;
					case "*":
						g.setColor(Color.blue);
						break;
					case "+":
						g.setColor(Color.yellow);
						break;
					default:
						g.setColor(Color.white);

					}

					g.fillRect(40 * i + 20, 40 * j + 50, 40, 40);

					if (g.getColor() == Color.blue) {
						g.setColor(Color.black);
						g.drawRect(40 * i + 20, 40 * j + 50, 40, 40);
					}
					
					g.setColor(Color.white);
					g.drawString((String.valueOf(score)), 75, 75);
				}
			
			}
		}
	}

	private void setDirection(Directions dir) {
		// pivot[head.x][head.y] = dir;
		this.dir = dir;
	}

	private void food() {
		int x = (int) (Math.random() * (xsize - 1));
		int y = (int) (Math.random() * (ysize - 1));
		if (display[x][y].equals(" ") || display[x][y].equals("+")) {
			display[x][y] = "+";
		} else
			food();
	}

	private void newSnakePosition() {
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
			score += 10;
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

		display[1][1] = Integer.toString(score % 10000 / 1000);
		display[2][1] = Integer.toString(score % 1000 / 100);
		display[3][1] = Integer.toString(score % 100 / 10);
		display[4][1] = Integer.toString(score % 10);
		print();

	}

	private void tailPosition() {
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

	private void gameOver() {
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
