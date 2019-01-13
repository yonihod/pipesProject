//package viewModel;
//
//import javafx.beans.property.SimpleStringProperty;
//import javafx.beans.property.StringProperty;
//import javafx.stage.FileChooser;
//import model.Model;
//import model.MyModel;
//import view.PipeBoard;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.util.*;
//
//
//public class PipeGameViewModel implements ViewModel, Observer {
//
//    Model pipeGameModel;
//    public PipeBoard pb;
//    public StringProperty[][] boardData;
//    public StringProperty time;
//    private Timer timer;
//    private int index;
//
//    /**
//     * Images
//     */
//    public StringProperty horizontal = new SimpleStringProperty();
//    public StringProperty vertical = new SimpleStringProperty();
//    public StringProperty curveUpLeft= new SimpleStringProperty();
//    public StringProperty curveUpRight = new SimpleStringProperty();
//    public StringProperty curveDownLeft = new SimpleStringProperty();
//    public StringProperty curveDownRight = new SimpleStringProperty();
//    public StringProperty start = new SimpleStringProperty();
//    public StringProperty end= new SimpleStringProperty();
//
//    public PipeGameViewModel(){
//        this.pipeGameModel= new MyModel();
//    }
//
//    public void openFile() throws FileNotFoundException {
////        FileChooser fc = new FileChooser();
////        fc.setTitle("Open File");
////        fc.setInitialDirectory(new File("./resources"));
////        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"));
////        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml"));
////        File chosen = fc.showOpenDialog(null);
////        if (chosen != null) {
////            String board;
////            Scanner scanner = new Scanner(chosen);
////            board = scanner.nextLine();
////            ArrayList<Character> boardData = new ArrayList<Character>();
////            while(scanner.hasNextLine()){
////                board += "\n" + scanner.nextLine();
////            }
////
//////            char[] boardData = board.toCharArray();
////        }
//        // TODO fill logic
//
//        char[][] data = {{'s', '-', '7', ' '}, {' ', '|', 'L', '7'}, {'-', 'F', ' ', '|'}, {'7', 'F', '-', 'J'}, {' ', 'g', ' ', '-'}};
//        boardData = new SimpleStringProperty[data.length][data[0].length];
//        for (int i = 0; i < data.length; i++) {
//            for (int j = 0; j < data[i].length; j++) {
//                boardData[i][j] = new SimpleStringProperty(String.valueOf(data[i][j]));
//            }
//        }
//    }
//    public void save() {
//        //save config if not Save level progression
//        if (boardData != null) {
//            FileChooser fc = new FileChooser();
//            fc.setTitle("Save file");
//            fc.setInitialDirectory(new File("./resources"));
//            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"));
//            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml"));
//            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Object files (*.obj)", "*.obj"));
//            File chosen = fc.showSaveDialog(null);
//            if (chosen != null) {
//
//            }
//        }
//    }
//    public void rotate(int row,int col){
//        String c = boardData[row][col].get();
//        switch(boardData[row][col].get()) {
//            case "F":
//                c = "7";
//                break;
//            case "7":
//                c = "J";
//                break;
//            case "J":
//                c = "L";
//                break;
//            case "L":
//                c = "F";
//                break;
//            case "|":
//                c = "-";
//                break;
//            case "-":
//                c = "|";
//                break;
//        }
//        boardData[row][col].setValue(c);
//    }
//    public void solve(){
//        // solve
//    }
//}
