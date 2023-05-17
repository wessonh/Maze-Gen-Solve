package application;

import java.util.LinkedList;
import java.util.Queue;

import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class Solve {
	
	public void search() {
		
	}
	
	public void create(Group group, Node[][]maze,int row, int col) {
		
		Queue<Node> list = new LinkedList<Node>();
		maze[0][1].visited = false;
		list.add(maze[0][1]);
		while(!list.isEmpty()) {
			Node current = list.poll();
			if(current.end) {
				break;
			}
			else {
				for(Node neighbor : current.neighbors) {
					
					if(neighbor.visited) {
						
						
						if(neighbor.row < current.row) {
								
								if(neighbor.down == false && current.up == false) {
									neighbor.visited = false;
									list.add(neighbor);								
								}
								
								
							}
							else if(neighbor.row > current.row) {
								if(neighbor.up == false && current.down == false) {
									neighbor.visited = false;
									list.add(neighbor);			
								}
								
							}
							else if(neighbor.col < current.col) {
								
								if(neighbor.right == false && current.left == false) {
									neighbor.visited = false;
									list.add(neighbor);			
								}
								
							}
							else if(neighbor.col > current.col) {
								if(neighbor.left == false && current.right == false) {
									neighbor.visited = false;
									list.add(neighbor);			
								}
							}
					}
					}
				}
		}
		print(group, 10, 10, maze);
	}
	public void print(Group group, int row, int col, Node[][]maze ) {
		
		for(int y = 0; y < row; y++) {
			for(int x = 0; x < col; x++) {
				if(maze[y][x].visited == false) {
					Text text = new Text(x*25 + 37.5, y*25 + 37.5, "*");
					group.getChildren().add(text);
				}
				if(maze[y][x].up) {
					Line top = new Line(x*25 + 25,y*25 + 25,x*25+25 + 25,y*25 + 25);
					
					group.getChildren().add(top);
				}
				if(maze[y][x].right) {
					Line right = new Line(x*25+25 + 25,y*25 +25,x*25+25 + 25,y*25+25 + 25);
					group.getChildren().add(right);
				}
				if(maze[y][x].down) {
					Line bottom =new Line(x*25+25 + 25,y*25+25 + 25,x*25 + 25,y*25+25 + 25);
					group.getChildren().add(bottom);
				}
				if(maze[y][x].left) {
					Line left = new Line(x*25 + 25,y*25+25 + 25,x*25 + 25,y*25+25);
					group.getChildren().add(left);
				}
				
				
			}
		}
	}
}
