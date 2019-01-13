package view;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.net.URI;

public class ConfigWindowController {

    MediaPlayer mp;
    private Stage thisStage;
    private final MainWindowController mwc;

    @FXML
    TextField address;

    @FXML
    TextField port;
    @FXML
    Button submitAddress;

    public ConfigWindowController(MainWindowController mwc){
        this.mwc = mwc;
        thisStage = new Stage();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ConfigWindow.fxml"));
            loader.setController(this);
            thisStage.setScene(new Scene(loader.load()));
            thisStage.setTitle("Game Settings");

        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showStage() {
        thisStage.showAndWait();
    }

    public MediaPlayer getMp() {
        return mp;
    }

    // Configuration set address
    public void setAddress(){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new File("./client/gameConf.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        writer.println(address.getText() + ":" + port.getText());
        writer.close();
        this.submitAddress.setText("Submitted");
        this.submitAddress.setDisable(true);
    }
    public void setMusic(){
        FileChooser fc = new FileChooser();
        fc.setTitle("Open Audio File");
        fc.setInitialDirectory(new File("./client/resources"));
        File chosen = fc.showOpenDialog(null);
        URI uri = chosen.toURI();
        if(chosen!=null) {
            Media musicFile = new Media(uri.toString());
            mp = new MediaPlayer(musicFile);
            mwc.setMediaPlayer(mp);
        }
    }
    public void setBackgroundImage() throws FileNotFoundException {
        FileChooser fc = new FileChooser();
        fc.setTitle("Open Img File");
        fc.setInitialDirectory(new File("./client/resources"));
        File chosen = fc.showOpenDialog(null);
        if(chosen!=null) {
            mwc.setBgImg(chosen);
        }
    }
}
