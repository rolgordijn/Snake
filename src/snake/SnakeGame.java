package snake;

import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class SnakeGame {

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

	/**
	 * 
	 * @param xsize
	 * @param ysize
	 * @param dir
	 */
	public SnakeGame(int xsize, int ysize, Directions dir, JTextArea ta) {
		this.xsize = xsize;
		this.ysize = ysize;
		pivot = new Directions[xsize][ysize];
		display = new String[xsize][ysize];
		head = new Point(2, 2);
		tail = new Point(2, 2);
		this.ta = ta;
		pivot[head.x][head.y] = dir;
        this.dir = dir;
		init();
		print();
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
		System.out.println("" + head.x + " " + head.y);
		System.out.println("" + pivot[head.x][head.y]);
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

}
