package application;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main extends Application {

    static final Group group = new Group(); // group to store nodes for scene display
    static Node[][] maze;
    AtomicBoolean solverDone = new AtomicBoolean(false); // boolean values that can...
    AtomicBoolean mazeGenerated = new AtomicBoolean(false); // ...update automatically...

    @Override // entry point for javaFX                   // ...good for using them for multiple things
    public void start(Stage primaryStage) {

        try {
            Label instructions = Menu.instructions();
            group.getChildren().add(instructions); // Add the instructions to the group

            Scene scene = new Scene(group, 425, 425); // creates a new scene with group as the root, this is where you adjust window size
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("application.css")).toExternalForm()); // adds the CSS style sheet
            primaryStage.setScene(scene); // sets primary stage to our scene
            primaryStage.show(); // displays the window

            scene.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.Q) {
                    System.exit(0);
                } else if (!mazeGenerated.get()) {  // If the maze is not generated
                    instructions.setVisible(false);
                    maze = Gen.animateGen(group);
                    mazeGenerated.set(true);
                } else if (mazeGenerated.get() && !solverDone.get()) {  // If the maze is generated and not solved
                    instructions.setVisible(false);
                    Solve solver = new Solve();
                    solver.animateSolve(maze, group, solverDone);
                } else if (mazeGenerated.get() && solverDone.get()) {  // If the maze is generated and solved
                    group.getChildren().clear(); // clear all nodes from group
                    solverDone.set(false); // reset the solver finished flag
                    mazeGenerated.set(false); // set maze generated to false, but don't generate a new maze yet
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        maze = new Node[0][0]; // might change this, or try to remove it 
        launch(args);
    }
}
