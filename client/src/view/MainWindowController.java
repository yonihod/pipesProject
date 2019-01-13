package view;

import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.*;

public class MainWindowController implements Initializable, View {

    char[][] boardData = {{}};
    private MediaPlayer mp;

    public StringProperty timeLeft;
    private TimerTask timerTask;
    private IntegerProperty moves;
    private BooleanProperty completedProperty;

    private String bgImg;

    @FXML
    Text countdown;
    @FXML
    Text moveCounter;

    private Timer timer;

    URL location;
    ResourceBundle resources;

    @FXML
    PipeBoard pipeBoardDisplayer;

    @FXML
    BorderPane rootPane;

    private String[][] charMatrixToString(char[][] charM){
        String[][] stringBoard = new String[charM.length][charM[0].length];
        for(int i=0; i<charM.length;i++){
            for(int j=0; j<charM[0].length;j++){
                stringBoard[i][j] = String.valueOf(charM[i][j]);
            }
        }
        return stringBoard;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // create solve
        this.location = location;
        this.resources = resources;
        pipeBoardDisplayer.setBoardData(charMatrixToString(boardData));
        this.moves = new SimpleIntegerProperty();
        this.completedProperty = new SimpleBooleanProperty();
        completedProperty.bind(pipeBoardDisplayer.getGameStatusProperty());
    }


    private void startTimer(){
        timer = new Timer();
        timeLeft = new SimpleStringProperty();
        timeLeft.setValue("20");
        timerTask = new TimerTask() {
            @Override
            public void run() {
                timeLeft.set(String.valueOf(Integer.valueOf(timeLeft.get())-1));
                countdown.setText("Time: " + timeLeft.get());
                moveCounter.setText(" Moves: " + pipeBoardDisplayer.getCounter().get());
                if(Integer.valueOf(timeLeft.get())  <= 0) {
                   // implement pop up window
                    //stop timer
                    Button lost = new Button("You Lost :(");
                    lost.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            pipeBoardDisplayer.setBoardData(charMatrixToString(boardData));
                        }
                    });
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            rootPane.setCenter(lost);
                        }
                    });
                }
            }
        };
        timer.scheduleAtFixedRate(timerTask, 1000, 1000);
        this.completedProperty.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                // Only if completed
                if (newValue) {
                    Button w = new Button("You Won :)");
                    w.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            initialize(location,resources);
                        }
                    });
                    rootPane.setCenter(w);
                    timerTask.cancel();
                }
            }
        });
    }

    public void openFile() throws FileNotFoundException {
        FileChooser fc = new FileChooser();
        fc.setTitle("Open File");
        fc.setInitialDirectory(new File("./client/resources"));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml"));
        File chosen = fc.showOpenDialog(null);
        if (chosen != null) {
            String board;
            Scanner scanner = new Scanner(chosen);
            board = scanner.nextLine();
            while(scanner.hasNextLine()){
                board += "\n" + scanner.nextLine();
            }
            String[] lineArray = board.split("\n");
            char[][] boardData = new char[lineArray.length][lineArray[0].length()];
            for(int i=0;i<lineArray.length;i++) {
                for (int j = 0; j < lineArray[0].length(); j++) {
                    boardData[i][j] = lineArray[i].charAt(j);
                }
            }
            this.boardData = boardData;
            initialize(this.location,this.resources);
            startTimer();
        }
    }
    public void solve() throws IOException {
        pipeBoardDisplayer.solve();
    }

    public void openConfigurationWindow() {
        ConfigWindowController cwc = new ConfigWindowController(this);
        cwc.showStage();
    }
    public void save() {
        //Save level progression
        if (boardData != null) {
            FileChooser fc = new FileChooser();
            fc.setTitle("Save file");
            fc.setInitialDirectory(new File("./client/resources"));
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"));
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml"));
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Object files (*.obj)", "*.obj"));
            File chosen = fc.showSaveDialog(null);
            if (chosen != null) {
                try {
                    FileWriter fw =  new FileWriter(chosen.getPath());
                    String boardData = pipeBoardDisplayer.boardToString(pipeBoardDisplayer.dataToCharArray());
                    fw.write(boardData +"\n");
                    fw.write(this.countdown.getText() +"\n");
                    fw.write(this.moveCounter.getText().substring(1));
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public MediaPlayer getMediaPlayer() {
        return this.mp;
    }
    public void setMediaPlayer(MediaPlayer other) {
        this.mp = other;
        mp.setAutoPlay(true);
        mp.setVolume(0.1);
    }
    public String getCountdown() {
        return timeLeft.get();
    }
    public void setCountdown(String countdown) {
        this.timeLeft.set(countdown);
    }

    public void setBgImg(File bgImg) {
        URI uri = bgImg.toURI();
        rootPane.setStyle("-fx-background-image: url('" + uri + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;");
    }
}
