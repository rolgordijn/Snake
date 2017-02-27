package snake;

import java.awt.Point;
import java.io.Console;
import java.io.IOException;

public class SnakeGame {
	
public enum Directions {
  up,right,down,left,
}
	
	private Directions dir;
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
	
	public void newSnakePosition(){
		display[tail.x][tail.y] = ' ';
		if(dir.equals(Directions.left)){
			head.x--;
			tail.x--;
		}
		if(dir.equals(Directions.right)){
			head.x++;
			tail.x++;
		}
		if(dir.equals(Directions.up)){
			head.y--;
			tail.y--;
		}
		if(dir.equals(Directions.down)){
			head.y++;
			tail.y++;
		}
		
		display[head.x][head.y] = '*';
		print();
		
		
	}







}
