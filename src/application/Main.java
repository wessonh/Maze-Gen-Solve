
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
	private static Group group2 = new Group();
	private Stage stage = new Stage();
	private Stage stage2 = new Stage();
	@Override
	public void start(Stage primaryStage) {
		try {
			stage.setX(300);
			stage.setY(300);
			stage2.setX(700);
			stage2.setY(300);
			Scene scene = new Scene(group,400,400);
			Scene scene2 = new Scene(group2,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage2.setScene(scene2);
			stage2.show();
			stage.show();
			
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		Node [][] maze = new Node [10][10];
		for(int i = 0; i < 10; i++) {
			
			for(int k = 0; k < 10; k++) {
				
				maze[i][k] = new Node(i, k);

			}
		}
		Gen generate = new Gen();
		generate.create(group,maze,10,10);
		Solve solve = new Solve();
		solve.create(group2,maze,10,10);
		launch(args);
	}
	
	
}