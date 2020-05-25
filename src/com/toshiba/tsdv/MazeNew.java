package com.toshiba.tsdv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class MazeNew {
	
	int M, N, i0, j0;
	int[][]data;
	int line_num = 1;
	int i = 1;
	
	void readData(String filename) throws FileNotFoundException {
	      Scanner sc = new Scanner(new BufferedReader(new FileReader("input.txt")));
	      while(sc.hasNextLine()) {
	    	  String[] line = sc.nextLine().trim().split(" ");
		      if (line_num == 1) {
		    	  M = Integer.parseInt(line[0]);
		    	  N = Integer.parseInt(line[1]);
		    	  i0 = Integer.parseInt(line[2]);
		    	  j0 = Integer.parseInt(line[3]);
		    	  data = new int[M+1][N+1];
		    	  line_num = 0;
		    	 }else {
		            for (int j=0; j<line.length; j++) {
		               data[i][j+1] = Integer.parseInt(line[j]);
		            }
		            i++;
		    	 }
	      }
	      sc.close();
	}
	
	boolean isValid(int x, int y) {
		if(x<=M && y<=N && x>=0 && y>=0 && data[x][y]==0)
			return true;
		return false;
	}
	
	int step = 0;
	int max = Integer.MAX_VALUE;
	boolean check = false;
	
	boolean solve(int x, int y) {
				
		if(isValid(x, y)) {
			if((x == 1 || y == 1 || x == M || y == N)) {
				if (max > step){
					max = step;
				}
				check = true;
				return false;
			}
			data[x][y]=2;
			step++;
			
			if(solve(x+1, y)) return true;
			if(solve(x, y+1)) return true;
			if(solve(x-1, y)) return true;
			if(solve(x, y-1)) return true;
			
			data[x][y]=0;
			step--;
		}
		return false;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		MazeNew maze = new MazeNew();
		maze.readData("input.txt");
		System.out.println("Matrix:	" + maze.M +"x"+ maze.N);
		for (int i=1; i<=maze.M; i++) {
			for (int j=1; j<=maze.N; j++)
				System.out.print(maze.data[i][j] +" ");
			System.out.println();
		}
		if(maze.isValid(maze.i0, maze.j0)) {
			if(!maze.solve(maze.i0, maze.j0) && maze.check == true) System.out.println("Min steps to escape the Mazer is: " +(maze.max+1));
			else System.out.println("No path to escape the Mazer");
		}else System.out.println("Point is invalid");
		
		
	}

}
