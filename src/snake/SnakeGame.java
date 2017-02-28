package snake;

import java.awt.Point;
import java.io.Console;
import java.io.IOException;

public class SnakeGame {

	public enum Directions {
		up,right,down,left,
	}

	private int xsize;
	private int ysize;
	private Directions[][] pivot; //keeps track of the snake's direction in each point on the grid
	private char[][] display;     //the "graphics" the wall is made of '-' and '|', food is a '+' and the snake is made of '*'; 
	private Point head;
	private Point tail;

	public SnakeGame(int xsize,int ysize, Directions dir){
		this.xsize = xsize;
		this.ysize = ysize; 
		pivot = new Directions[xsize][ysize];
		display = new char[xsize][ysize];
		head = new Point(xsize/2,ysize/2);
		tail = new Point(xsize/2,ysize/2);
		pivot[xsize/2][ysize/2] = dir; 
		init();
		print();
	}

	public void init(){
		for(int i=0;i<xsize;i++){
			display[i][0] = '-';
			display[i][ysize-1] = '-';
		}
		for(int i=0;i<ysize;i++){
			display[0][i] ='|';
			display[xsize-1][i] = '|';
		}
		display[head.x][head.y]='*';
	}

	public void print(){




		for(int i=0;i<ysize;i++){
			for(int j=0;j<xsize;j++){
				System.out.print(display[j][i]);
			}  
			System.out.println();
		}
	}

	public void setDirection(Directions dir){
		pivot[head.x][head.y] = dir;
	}

	public void food(){
		int x = (int) (Math.random()*(xsize-1));
		int y = (int) (Math.random()*(ysize-1));

		if(display[x][y] == '*' || display[x][y] ==  '-'  ||  display[x][y] != '|' ){
			display[x][y] = '+';
			print();
		}
		else{
			food(); 
		}
	}

	
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
		case down: head.y++  ;
		break;
		default: break;
		}
		
		pivot[head.x][head.y] = currentDirection; 
		//when the snake eats food, the tail stays on the current location. 
		//Moving the tail is only required when the head doesn't eat food. 
		//When the head moves to a position where a * | or - sign is, than you're game over. 

		switch (display[head.x][head.y]) {
		case '+': display[head.x][head.y]='*';
		break;
		case ('-'): System.out.println("gameOver!"); 
		break;
		case ('*'): System.out.println("gameOver!"); 
		break;
		case ('|'): System.out.println("gameOver!"); 
		break;
		default: tailPosition();
	    break;
		}
		print();
	}







}
