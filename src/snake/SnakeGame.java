/**
 * 
 */
package snake;

import java.awt.Point;
import java.io.Console;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 * 
 * @author Rian De Rous
 *
 */
public class SnakeGame extends JFrame {

	/**
	 * 
	 * @author Rian De Rous
	 *
	 */
	public enum Directions {
		up,right,down,left,
	}

	/**
	 * 
	 */
	private int xsize;
	private int ysize;
	private Directions[][] pivot; //keeps track of the snake's direction in each point on the grid
	private String[][] display;     //the "graphics" the wall is made of '-' and '|', food is a '+' and the snake is made of '*'; 
	private Point head;
	private Point tail;
	private JTextArea ta;

	/**
	 * 
	 * @param xsize
	 * @param ysize
	 * @param dir
	 */
	public SnakeGame(int xsize,int ysize, Directions dir, JTextArea ta){
		this.xsize = xsize;
		this.ysize = ysize; 
		pivot = new Directions[xsize][ysize];
		display = new String[xsize][ysize];
		head = new Point(xsize/2,ysize/2);
		tail = new Point(xsize/2,ysize/2);
		pivot[xsize/2][ysize/2] = dir; 
		this.ta = ta;
		init();
		print();
	}
	
	
	/**
	 * 
	 */
	public void init(){
		for(int i=0;i<ysize;i++){
			for(int j=0;j<xsize;j++){
					display[j][i] = " "; 			
			}  
		}
	
		for(int i=0;i<xsize;i++){
			display[i][0] = "-";
			display[i][ysize-1] = "-";
		}
		for(int i=0;i<ysize;i++){
			display[0][i] ="|";
			display[xsize-1][i] = "|";
		}
		display[head.x][head.y]="*";
		
	
	}
	
	public Directions HeadDirection(){
		return pivot[head.x][head.y];
	}

	public void print(){
		ta.setText("");
		for(int i=0;i<ysize;i++){
			for(int j=0;j<xsize;j++){
				ta.append(""+ display[j][i]);
			
			}  
			ta.append("\r\n");

		}
	}

	public void setDirection(Directions dir){
		pivot[head.x][head.y] = dir;
		System.out.println("direction is" + dir.name());
	}

	
	public void testfood(){
		display[head.x][head.y-2] = "+";
		print();
	}
	
	public void food(){
    int x = (int) (Math.random()*(xsize-1));
    int y = (int) (Math.random()*(ysize-1));

		
		if(display[x][y].equals(" ")){
			display[x][y] = "+";
			print();
		}
		else{
			food(); 
		}
	}

	
	/**
	 * 
	 */
	private void tailPosition(){

		switch (pivot[tail.x][tail.y]) {
		case left: tail.x--;
		break;
		case right: tail.x++;
		break;
		case up: tail.y--; 
		break;
		case down: tail.y++  ;
		break;
		default: break;
		}
	};
	
	public void newSnakePosition(){
		
		Directions currentDirection = pivot[head.x][head.y];
		switch (pivot[head.x][head.y]) {
		case left: head.x--;
		break;
		case right: head.x++;
		break;
		case up: head.y--; ;
		break;
		case down: head.y++ ;
		break;
		default: break;
		}
		
		pivot[head.x][head.y] = currentDirection; 
		//when the snake eats food, the tail stays on the current location. 
		//Moving the tail is only required when the head doesn't eat food. 
		//When the head moves to a position where a * | or - sign is, than you're game over. 

		switch (display[head.x][head.y]) {
		case "+": display[head.x][head.y]="*";
		break;
		case " ": 	display[head.x][head.y]="*";
					display[tail.x][tail.y]=" ";
					tailPosition();
		break;
		case ("-"): gameOver();
		break;
		case ("*"): gameOver(); 
		break;
		case ("|"):gameOver();
		break;
		default: 
	    break;
		}
		print();
	}

   public void gameOver(){
	   JFrame frame = new JFrame();
	   frame.setAlwaysOnTop(true);
	   frame.setVisible(false);
	   JOptionPane.showMessageDialog(frame,
			    "Game over!",
			    "Game over!",
			    JOptionPane.WARNING_MESSAGE);
   }





}
