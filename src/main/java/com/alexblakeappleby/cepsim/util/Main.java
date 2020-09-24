package com.alexblakeappleby.cepsim.util;

import com.alexblakeappleby.cepsim.view.MainWindow;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Don't run main() directly, use Launcher.main() instead.
 * @see Launcher
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        MainWindow.launch();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
