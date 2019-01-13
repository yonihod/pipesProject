package view;



import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class PipeBoard extends GridPane {

    public StringProperty[][] boardData;

    private IntegerProperty counter;
    private StringProperty horizontal;
    private StringProperty vertical;
    private StringProperty curveUpLeft;
    private StringProperty curveUpRight;
    private StringProperty curveDownLeft;
    private StringProperty curveDownRight;
    private StringProperty start;
    private StringProperty end;
    private int port;
    private String ip;

    /**
     * Images
     */
       private Image horizontalImg;
       private Image verticalImg;
       private Image curveUpLeftImg;
       private Image curveUpRightImg;
       private Image curveDownLeftImg;
       private Image curveDownRightImg;
       private Image startImg;
       private Image endImg;

       private boolean initalize = false;

       private Location2D startLocation;


    private BooleanProperty gameStatus;
    private ArrayList<String> solution;


    public PipeBoard() {
        horizontal = new SimpleStringProperty();
        vertical = new SimpleStringProperty();
        curveUpLeft = new SimpleStringProperty();
        curveUpRight = new SimpleStringProperty();
        curveDownLeft = new SimpleStringProperty();
        curveDownRight = new SimpleStringProperty();
        start = new SimpleStringProperty();
        end = new SimpleStringProperty();
        counter = new SimpleIntegerProperty();
        this.startLocation = new Location2D();
        this.gameStatus = new SimpleBooleanProperty();
    }

    public void solve() throws IOException {
        Socket s = new Socket(this.ip,this.port);
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream());
        char[][] charBoard =  dataToCharArray();
        String outString = boardToString(charBoard);
        out.println(outString);
        out.println("done");
        out.flush();

        String response = in.readLine();
        ArrayList<String> solution = new ArrayList<>();

        while(!response.equals("done")) {
//            System.out.println(response);
            solution.add(response);
            response = in.readLine();
        }
        in.close();
        out.close();
        s.close();
        this.solution = solution;
        ArrayList<Solution> interpretedSolutionArray = new ArrayList<>();
        for (String str: solution) {
            Solution newSolution = new Solution(str);
            interpretedSolutionArray.add(newSolution);
        }
        for (Solution sol: interpretedSolutionArray) {
            rotate(sol.getPoint().getI(),sol.getPoint().getJ(),sol.getTimesToRotate(),true);
        }
    }

    public String boardToString(char[][] board){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<board.length;i++){
            if(i!=0){
                sb.append("\n");
            }
            for (int j=0;j<board[0].length;j++){
                sb.append(board[i][j]);
            }
        }
        return sb.toString();
    }

    private StringProperty[][] cloneBoard(StringProperty[][] board){
        StringProperty[][] newBoard = new StringProperty[board.length][board[0].length];
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                newBoard[i][j] = new SimpleStringProperty(board[i][j].get());
            }
        }
        return newBoard;
    }

    private boolean findPath(int i,int j,StringProperty[][] board ){
        // in order for us not to loop forever we need to mark the places we checked by changing values
        StringProperty[][] tempBoard = cloneBoard(board);
        StringProperty currentPosition = new SimpleStringProperty(tempBoard[i][j].get());
        // change to checked
        tempBoard[i][j].set("CHECKED");
        switch (currentPosition.get()) {
            case "-":
                return  left(i, j, tempBoard) && findPath(i, j - 1, tempBoard) || right(i, j, tempBoard) && findPath(i, j + 1, tempBoard);
            case "|":
                return  up(i, j, tempBoard) && findPath(i - 1, j, tempBoard) || down(i, j, tempBoard) && findPath(i + 1, j, tempBoard);
            case "7":
                return  down(i, j, tempBoard) && findPath(i + 1, j, tempBoard) || left(i, j, tempBoard) && findPath(i, j - 1, tempBoard);
            case "F":
                return  down(i, j, tempBoard) && findPath(i + 1, j, tempBoard) || right(i, j, tempBoard) && findPath(i, j + 1, tempBoard);
            case "J":
                return  up(i, j, tempBoard) && findPath(i - 1, j, tempBoard) || left(i, j, tempBoard) && findPath(i, j - 1, tempBoard);
            case "L":
                return  right(i, j, tempBoard) && findPath(i, j + 1, tempBoard) || up(i, j, tempBoard) && findPath(i - 1, j, tempBoard);
            case "g":
               return true;
            case "s":
                return  up(i, j, tempBoard) && findPath(i - 1, j, tempBoard) || down(i, j, tempBoard) && findPath(i + 1, j, tempBoard) ||
                        left(i, j, tempBoard) && findPath(i, j - 1, tempBoard) || right(i, j, tempBoard) && findPath(i, j + 1, tempBoard);
            // any other case we return false
                default:
                return false;
        }
    }

    private boolean isInBound(int i, int j){
        return (i >= 0 && i < this.boardData.length &&
                j >= 0 && j < this.boardData[0].length);
    }
    private boolean up ( int i, int j, StringProperty[][] currentBoard){
        if (isInBound(i - 1, j)) {
            switch (currentBoard[i - 1][j].get()) {
                case "7":
                case "|":
                case "F":
                case "g":
                    return true;
            }
        }
        return false;
    }
    private boolean right ( int i, int j, StringProperty[][] currentBoard){
        if (isInBound(i, j + 1)) {
            switch (currentBoard[i][j + 1].get()) {
                case "7":
                case "J":
                case "-":
                case "g":
                    return true;
            }
        }
        return false;
    }
    private boolean left ( int i, int j, StringProperty[][] currentBoard){
        if (isInBound(i, j - 1)) {
            switch (currentBoard[i][j - 1].get()) {
                case "F":
                case "L":
                case "-":
                case "g":
                    return true;
            }
        }
        return false;
    }
    private boolean down ( int i, int j, StringProperty[][] currentBoard){
        if (isInBound(i + 1, j)) {
            switch (currentBoard[i + 1][j].get()) {
                case "J":
                case "L":
                case "|":
                case "g":
                    return true;
            }
        }
        return false;
    }
    //redraw one specific node for animation
    public void redraw(int row,int col){
        Node currentNode = null;
        ObservableList<Node> childrens = this.getChildren();
        for (Node node : childrens) {
            if(this.getRowIndex(node) == row && this.getColumnIndex(node) == col) {
                currentNode = node;
                break;
            }
        }
        applyPhoto((MyButton)currentNode,boardData[row][col].get());
        currentNode.getStyleClass().add("colored");
    }

    public void redraw() {
        this.getChildren().clear();
        if (boardData != null) {
            for(int i = 0 ; i < boardData.length ; i++){
                addRow(i);
                for (int j = 0 ; j < boardData[0].length ;j ++){
                    addColumn(j);
                    Node clickableNodeButton = new MyButton(boardData[i][j].get());
                    applyPhoto((MyButton)clickableNodeButton,boardData[i][j].get());
                    final int finalI = i;
                    final int  finalj = j;
                    clickableNodeButton.setOnMouseClicked(e->this.rotate(finalI,finalj,1,false));
                    add(clickableNodeButton,j,i);
                    // need to manage resize
//                    this.setHgrow(clickableNodeButton,Priority.ALWAYS);
//                    this.setVgrow(clickableNodeButton,Priority.ALWAYS);
                }
            }
        }
    }
    private void applyPhoto(MyButton btn,String pipeShape){
        ImageView image;
        try {
            if(!initalize) {
                this.horizontalImg = new Image(new FileInputStream(getHorizontal()));
                this.verticalImg = new Image(new FileInputStream(getVertical()));
                this.curveUpLeftImg = new Image(new FileInputStream(getCurveUpLeft()));
                this.curveUpRightImg = new Image(new FileInputStream(getCurveUpRight()));
                this.curveDownLeftImg = new Image(new FileInputStream(getCurveDownLeft()));
                this.curveDownRightImg = new Image(new FileInputStream(getCurveDownRight()));
                this.startImg = new Image(new FileInputStream(getStart()));
                this.endImg = new Image(new FileInputStream(getEnd()));
                this.initalize = true;
            }
        switch (pipeShape) {
            case "-":
                image = new ImageView(horizontalImg);
                break;
            case "|":
                image = new ImageView(verticalImg);
                break;
            case "7":
                image = new ImageView(curveUpLeftImg);
                break;
            case "F":
                image = new ImageView(curveUpRightImg);
                break;
            case "J":
                image = new ImageView(curveDownLeftImg);
                break;
            case "L":
                image = new ImageView(curveDownRightImg);
                break;
            case "g":
                image = new ImageView(endImg);
                break;
            case "s":
                image = new ImageView(startImg);
                break;
                default: image = null;

        }
        if(image != null) {
            image.setFitHeight(60);
            image.setFitWidth(60);
        }
        btn.setGraphic(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public char[][] dataToCharArray() {
        int r = boardData.length;
        int c = boardData[0].length;
        char[][] result = new char[r][c];
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                result[i][j] = boardData[i][j].get().charAt(0);
            }
        }
        return result;
    }

    /**
     * Set the board
     * @param otherBoard
     */
    public void setBoardData(String[][] otherBoard){
        if(otherBoard != null) {
            this.boardData = new SimpleStringProperty[otherBoard.length][otherBoard[0].length];
            for (int i = 0; i < otherBoard.length; i++) {
                for (int j = 0; j < otherBoard[0].length; j++) {
                    this.boardData[i][j] = new SimpleStringProperty(otherBoard[i][j]);
                    // set position for start for future use
                    if(otherBoard[i][j].equals("s")) {
                        this.startLocation.set(i, j);
                    }
                }
            }
        }
        redraw();
        // Set Solver Server
        try{
            Scanner s = new Scanner(new File("gameConf.txt"));
            String configurations = s.nextLine();
            String[] c = configurations.split(":");
            this.ip = c[0];
            this.port = Integer.valueOf(c[1]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void rotate(int row,int col,int times,boolean serverSolution){
        String c = boardData[row][col].get();
        for(int i=0;i<times;i++) {
            switch (boardData[row][col].get()) {
                case "F":
                    c = "7";
                    break;
                case "7":
                    c = "J";
                    break;
                case "J":
                    c = "L";
                    break;
                case "L":
                    c = "F";
                    break;
                case "-":
                    c = "|";
                    break;
                case "|":
                    c = "-";
                    break;
            }
            boardData[row][col].setValue(c);
            //add delay for animation
            if(serverSolution){
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                    redraw(row,col);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        if(!serverSolution) {
            redraw();
            counter.setValue(counter.get() + 1);
            this.gameStatus.set(findPath(startLocation.getI(), startLocation.getJ(), this.boardData));
        }
    }
    
    public String getHorizontal() {
        return horizontal.get();
    }

    public void setHorizontal(String horizontal) {
        this.horizontal.set(horizontal);
    }

    public String getVertical() {
        return vertical.get();
    }

    public void setVertical(String vertical) {
        this.vertical.set(vertical);
    }

    public String getCurveUpLeft() {
        return curveUpLeft.get();
    }



    public void setCurveUpLeft(String curveUpLeft) {
        this.curveUpLeft.set(curveUpLeft);
    }

    public String getCurveUpRight() {
        return curveUpRight.get();
    }

    public void setCurveUpRight(String curveUpRight) {
        this.curveUpRight.set(curveUpRight);
    }

    public String getCurveDownLeft() {
        return curveDownLeft.get();
    }


    public void setCurveDownLeft(String curveDownLeft) {
        this.curveDownLeft.set(curveDownLeft);
    }

    public String getCurveDownRight() {
        return curveDownRight.get();
    }


    public void setCurveDownRight(String curveDownRight) {
        this.curveDownRight.set(curveDownRight);
    }

    public String getStart() {
        return start.get();
    }


    public void setStart(String start) {
        this.start.set(start);
    }

    public String getEnd() {
        return end.get();
    }

    public void setEnd(String end) {
        this.end.set(end);
    }
    public IntegerProperty getCounter() {
        return counter;
    }
    public boolean isGameStatus() {
        return gameStatus.get();
    }

    public void setGameStatus(boolean gameStatus) {
        this.gameStatus.set(gameStatus);
    }
    public BooleanProperty getGameStatusProperty() {
        return gameStatus;
    }
}
