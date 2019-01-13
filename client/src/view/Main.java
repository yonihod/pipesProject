package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.MyModel;
import viewModel.ViewModel;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{

        MyModel m = new MyModel();
        FXMLLoader fxl=new FXMLLoader();
        BorderPane root = fxl.load(getClass().getResource("MainWindow.fxml").openStream());
        root.setId("pane");
        Scene scene = new Scene(root,1024,768);
        MainWindowController mwc = fxl.getController();
        // might need to not use CSS here
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
