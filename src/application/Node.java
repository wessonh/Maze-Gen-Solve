package application;

import java.util.ArrayList;
import java.util.Random;

public class Node {
	
	boolean up = true;
	boolean down = true;
	boolean right = true;
	boolean left = true;
	boolean visited;
	boolean solved;
	boolean end;
	int row;
	int col;
	ArrayList <Node> neighbors = new ArrayList<>();
	
	public Node(int row, int col) {
		visited = false;
		this.col = col;
		this.row = row;
		end = false;
		solved = false;
	}

}
