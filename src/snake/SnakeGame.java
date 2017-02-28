package snake;

import java.awt.Point;
import java.io.Console;
import java.io.IOException;

public class SnakeGame {

	public enum Directions {
		up,right,down,left,
	}

	private Directions dir;
	private Directions tailDir; 
	private int xsize;
	private int ysize;
	private boolean[][] pivot;
	private char[][] display;
	private Point head;
	private Point tail;

	public SnakeGame(int xsize,int ysize, Directions dir){
		this.dir=dir;
		this.xsize = xsize;
		this.ysize = ysize; 
		pivot = new boolean[xsize][ysize];
		display = new char[xsize][ysize];
		head = new Point(xsize/2,ysize/2);
		tail = new Point(xsize/2,ysize/2);
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
		this.dir=dir;
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
		switch (tailDir) {
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
		
		switch (dir) {
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
