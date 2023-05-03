package application;
import java.util.Collections;
import java.util.Stack;

import javafx.application.Application; 
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Line;


public class Main extends Application {
	
	private static Group group = new Group();
	private Stage stage = new Stage();
	@Override
	public void start(Stage primaryStage) {
		try {
			
			
			this.stage = primaryStage;
			Scene scene = new Scene(group,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		create(10,10);
		launch(args);
	}
	
	public static void create(int row, int col) {
		
		Node [][] maze = new Node [row][col];
		for(int i = 0; i < row; i++) {
			
			for(int k = 0; k < col; k++) {
				
				maze[i][k] = new Node(i, k);

			}
		}
		
		maze[0][1].neighbors.add(maze[1][1]);
		maze[0][1].down = false;
		maze[row-1][col-2].down = false;
		maze[row-1][col-2].end = true;
		
		for(int i = 0; i < row; i++) {
			
			for(int k = 0; k < col; k++) {
				
				if((i>0 && i < row - 1) && (k > 0 && k < col-1)) {
					
					maze[i][k].neighbors.add(maze[i][k-1]);
					maze[i][k].neighbors.add(maze[i+1][k]);
					maze[i][k].neighbors.add(maze[i-1][k]);
					maze[i][k].neighbors.add(maze[i][k+1]);
				}
				
				else if(i == 0 && k == 0) {
					maze[i][k].neighbors.add(maze[i+1][k]);
					maze[i][k].neighbors.add(maze[i][k+1]);
				}
				else if(i==0 && k == col - 1) {
					maze[i][k].neighbors.add(maze[i+1][k]);
					maze[i][k].neighbors.add(maze[i][k-1]);
				}
				else if(i == row-1 && k == 0) {
					maze[i][k].neighbors.add(maze[i][k+1]);
					maze[i][k].neighbors.add(maze[i-1][k]);
				}
				else if(i == row - 1 && k == col -1) {
					maze[i][k].neighbors.add(maze[i-1][k]);
					maze[i][k].neighbors.add(maze[i][k-1]);
				}
				else if(i == 0 && (k < col -1 && k > 0)) {
					maze[i][k].neighbors.add(maze[i+1][k]);
					maze[i][k].neighbors.add(maze[i][k-1]);
					maze[i][k].neighbors.add(maze[i][k+1]);
				}
				else if(k == 0 && (i > 0 && i < row -1)) {
					
					maze[i][k].neighbors.add(maze[i][k+1]);
					maze[i][k].neighbors.add(maze[i+1][k]);
					maze[i][k].neighbors.add(maze[i-1][k]);
				}
				else if (k == col -1 && (i > 0 && i < row-1)) {
					maze[i][k].neighbors.add(maze[i-1][k]);
					maze[i][k].neighbors.add(maze[i+1][k]);
					maze[i][k].neighbors.add(maze[i][k-1]);
				}
				else if (i == row -1 && (k < col -1 && k > 0)) {
					maze[i][k].neighbors.add(maze[i-1][k]);
					maze[i][k].neighbors.add(maze[i][k-1]);
					maze[i][k].neighbors.add(maze[i][k+1]);
				}
				
				
			}
		}	
		
		maze[0][1].visited = true;
		maze[0][1].up = false;
		Stack <Node> stack = new Stack<Node>();
		stack.push(maze[0][1]);
		
		while(!(stack.isEmpty())) {
			
			Node current = stack.pop();
			
			if(current.end == true) {
				break;
			}
			
			search(current, stack, maze);
			
		}
		
		print(row, col, maze);
		
	}
	
	public static void search(Node current, Stack <Node> stack, Node [][] maze) {
		
		Collections.shuffle(current.neighbors);
		for(Node neighbor : current.neighbors) {
			
			 if(neighbor.visited == false){
				
				if(neighbor.row < current.row) {
						
						neighbor.down = false;
						current.up = false;
						
					}
					else if(neighbor.row > current.row) {
						neighbor.up = false;
						current.down = false;
						
					}
					else if(neighbor.col < current.col) {
						neighbor.right = false;
						current.left = false;
					}
					else if(neighbor.col > current.col) {
						neighbor.left = false;
						current.right = false;
					}
					neighbor.visited = true;
					if(!neighbor.end) {
					stack.push(current);
					stack.push(neighbor);
					break;
				}
			}
		}
	}
	
	public static void print(int row, int col, Node[][]maze ) {
		
		for(int y = 0; y < row; y++) {
			for(int x = 0; x < col; x++) {
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

