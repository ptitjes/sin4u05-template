package fr.univ.amu.sin4u05.igl.transit.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 800;

    @Override
    public void start(Stage stage) {

        MainPane mainPane = new MainPane();

        Scene scene = new Scene(mainPane, WIDTH, HEIGHT);
        scene.getStylesheets().add("style/base.css");
        stage.setScene(scene);
        stage.setTitle("Transport Route Planner");
        stage.setMinWidth(WIDTH);
        stage.setMinHeight(HEIGHT);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
