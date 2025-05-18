package NoughtsAndCrosses;

import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class NoughtsAndCrossesApplication extends Application {
    private Button[][] buttonArray;
    private int gridSize = 3;
    public GameBoard gameBoard = new GameBoard(gridSize);
    //Label label = new Label("");
    private IntegerProperty xScore = new SimpleIntegerProperty(0);
    private IntegerProperty oScore = new SimpleIntegerProperty(0);
    //Label scoreLabel = new Label();
    BorderPane startPane = new BorderPane();
    BorderPane mainPane = new BorderPane();
    VBox vBox = new VBox();
    TextField playerOne = new TextField();
    TextField playerTwo = new TextField();
    Button startButton = new Button("Start");
    Button cancelButton = new Button("Cancel");
    String playerOneName;
    String playerTwoName;
    String message;
    private IntegerProperty draw = new SimpleIntegerProperty(0);
    Label currentPlaying = new Label();
    //TextField textField = new TextField();
    //Button createGrid = new Button("Create Grid");
    //TextField textField2 = new TextField();


    public class ResetButtonEventHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            gameBoard.resetBoard();
            for (int i = 0; i < gridSize; i++) {
                for (int j = 0; j < gridSize; j++) {

                    buttonArray[i][j].setText("");
                    buttonArray[i][j].setDisable(false);
                }
            }
            //label.setText("");

        }
    }
    /*
    public class ButtonEventHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Button clickedButton = (Button) event.getSource();
            if (!clickedButton.getText().isEmpty() || checkWinner()) {
                return;
            }
            if (turn % 2 == 0) {
                ((Button) event.getSource()).setText("O");
            }
            else {
                ((Button) event.getSource()).setText("X");

            }
            turn++;
            System.out.println("Turn: " + turn);

            if (checkWinner()) {
                disableButtons();
            } else if (turn == (userColumn * userRow) && !checkWinner()) {
                System.out.println("Draw!");
            }


        }

    }

     */

    public void updateScoring(String winner) {

        if (winner.equals("O")) {
            oScore.set(oScore.get() + 1);
        }
        if (winner.equals("X")) {
            xScore.set(xScore.get() + 1);
        }
        //scoreLabel.setText(playerOneName + ": " + xScore + " " + playerTwoName +": " + oScore);
    }
    public void disableButtons() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                buttonArray[i][j].setDisable(true);
            }
        }
    }
    /*
    public boolean checkWinner() {

        if (buttonArray[0][0].getText().equals(buttonArray[1][1].getText()) && buttonArray[0][0].getText().equals(buttonArray[2][2].getText()) && !buttonArray[0][0].getText().isEmpty()) {
            System.out.println(buttonArray[0][0].getText() + " Wins!");
            winner = buttonArray[0][0].getText();
            displayMessage(buttonArray[0][0].getText());
            return true;
        }
        if (buttonArray[0][2].getText().equals(buttonArray[1][1].getText()) && buttonArray[0][2].getText().equals(buttonArray[2][0].getText() ) && !buttonArray[0][2].getText().isEmpty()) {
            System.out.println(buttonArray[0][2].getText() + " Wins!");
            winner = buttonArray[0][2].getText();
            displayMessage(buttonArray[0][2].getText());
            return true;
        }
        else {
            for (int i = 0; i < 3; i++) {
                if (buttonArray[i][0].getText().equals(buttonArray[i][1].getText()) && buttonArray[i][0].getText().equals(buttonArray[i][2].getText()) && !buttonArray[i][0].getText().isEmpty()) {
                    System.out.println(buttonArray[i][0].getText() + " Wins!");

                    displayMessage(buttonArray[i][0].getText());
                    winner = buttonArray[i][0].getText();

                    return true;
                }
            }
            for (int i = 0; i < 3; i++) {
                if (buttonArray[0][i].getText().equals(buttonArray[1][i].getText()) && buttonArray[0][i].getText().equals(buttonArray[2][i].getText()) && !buttonArray[0][i].getText().isEmpty()) {
                    System.out.println(buttonArray[0][i].getText() + " Wins!");
                    winner = buttonArray[0][i].getText();
                    displayMessage(buttonArray[0][i].getText());

                    return true;
                }
            }
        }
        return false;
    }

     */

    private void handleMove(Button button, int row, int col) {
        gameBoard.makeMove(row, col);
        button.setText(gameBoard.board[row][col]);
        String winner = gameBoard.checkWinner();
// this design is not fast at all => by constantly checking conditions that are for sure gonna happen
        if (!winner.isEmpty()) {
            if (winner.equals("O")) {
                System.out.println(playerTwoName + " Wins!");
                message = playerTwoName + " Wins!";
                //label.setText(message);
                displayEndScreenPopup();
            }
            if (winner.equals("X")) {
                System.out.println(playerOneName + " Wins!");
                message = playerOneName + " Wins!";
                //label.setText(message);
                displayEndScreenPopup();
            }

            disableButtons();
        } else if (gameBoard.isDraw()) {
            System.out.println("Draw!");
            message = "Draw!";
            draw.set(draw.get() + 1);
            //label.setText(message);
            displayEndScreenPopup();
            disableButtons();
        }
        else {
            if (gameBoard.currentPlayer.equals("X")) {
                currentPlaying.setText("Currently playing: " + playerOneName);
            } else {
                currentPlaying.setText("Currently playing: " + playerTwoName);
            }

        }
        updateScoring(winner);

    }
    private void displayScoreboard() {
        Stage scoreboardStage = new Stage();
        scoreboardStage.setTitle("Scoreboard");

        VBox scoreboardLayout = new VBox(15);
        scoreboardLayout.setAlignment(Pos.CENTER);
        scoreboardLayout.setPadding(new Insets(20));
        Label xScoreLabel = new Label();
        xScoreLabel.textProperty().bind(xScore.asString(playerOneName + " (X): %d"));
        xScoreLabel.setStyle("-fx-font-size: 24px;");
        Label oScoreLabel = new Label();
        oScoreLabel.textProperty().bind(oScore.asString(playerTwoName + " (O): %d"));
        oScoreLabel.setStyle("-fx-font-size: 24px;");
        Label drawsLabel = new Label();
        drawsLabel.textProperty().bind(draw.asString("Draws: %d"));
        drawsLabel.setStyle("-fx-font-size: 24px;");
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> scoreboardStage.close());
        scoreboardLayout.getChildren().addAll(xScoreLabel, oScoreLabel, drawsLabel, closeButton);
        Scene scoreboardScene = new Scene(scoreboardLayout);
        scoreboardStage.setScene(scoreboardScene);
        scoreboardStage.show();
    }
    private void displayEndScreenPopup() {
        Stage popupStage = new Stage();
        popupStage.setTitle("Game Over");
        BorderPane endPane = new BorderPane();
        Label resultLabel = new Label(message);
        resultLabel.setStyle("-fx-font-size: 50px;");
        Button returnButton = new Button("Return to the game");
        returnButton.setStyle("-fx-font-size: 16px; -fx-background-color: lightblue;");
        returnButton.setOnAction(e -> {
            popupStage.close();
        });
        endPane.setCenter(resultLabel);
        endPane.setBottom(returnButton);
        Scene endScene = new Scene(endPane);
        popupStage.setScene(endScene);
        popupStage.show();

    }

    private void createGameGrid(BorderPane pane) {
        GridPane gridPane = new GridPane(1000, 1000);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(3);
        gridPane.setVgap(3);
        buttonArray = new Button[gridSize][gridSize];
        System.out.println("Grid Size: " + gridSize);
        gameBoard = new GameBoard(gridSize);
        for (int row = 0; row < gridSize ; row++) {
            for (int col = 0; col < gridSize; col++) {
                Button button = new Button();
                button.setPrefSize(100, 100); // Size of each button
                button.setStyle("-fx-font-size: 24px; -fx-background-color: lightgrey;");
                final int r = row;
                final int c = col;

                buttonArray[row][col] = button;
                gridPane.add(button, col, row);
                button.setOnAction(e -> handleMove(button, r, c));
            }
        }

        //scoreLabel.setText(playerOneName + ": " + xScore + " " + playerTwoName +": " + oScore);
        Button resetButton = new Button("Reset");
        resetButton.setOnAction(new ResetButtonEventHandler());
        Button scoreBoardButton = new Button("Scoreboard");
        scoreBoardButton.setOnAction(e -> displayScoreboard());
        VBox centerBox = new VBox(10, currentPlaying, gridPane, resetButton, scoreBoardButton);
        centerBox.setAlignment(Pos.CENTER);
        pane.setCenter(centerBox);
    }
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);


        playerOne.setPromptText("Enter Player One Name:");
        playerTwo.setPromptText("Enter Player Two Name:");
        TextField gridSizeInput = new TextField();
        gridSizeInput.setPromptText("Enter grid size (Default: 3)");
        gridSizeInput.setPrefWidth(200);
        stage.setTitle("Noughts and Crosses!");
        playerOne.setPrefWidth(200);
        playerTwo.setPrefWidth(200);
        playerOne.setAlignment(Pos.CENTER);
        playerTwo.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(playerOne, playerTwo, gridSizeInput);
        startPane.setCenter(vBox);
        HBox hBox = new HBox(30, startButton, cancelButton);
        hBox.setAlignment(Pos.CENTER);
        cancelButton.setOnAction(e -> stage.close());
        startPane.setBottom(hBox);


        startButton.disableProperty().bind(
                playerOne.textProperty().isEmpty().or(
                        playerTwo.textProperty().isEmpty()));

        Scene userOptions = new Scene(startPane);
        stage.setScene(userOptions);
        stage.show();
        startButton.setOnAction(e -> {
            playerOneName = playerOne.getText();
            playerTwoName = playerTwo.getText();
            if (gameBoard.currentPlayer.equals("O")) {
                currentPlaying.setText("Currently playing: " + playerTwoName);}
            if (gameBoard.currentPlayer.equals("X")) {
                currentPlaying.setText("Currently playing: " + playerOneName);
            }
            String input = gridSizeInput.getText();
            if (input == null || input.isEmpty() || !input.matches("\\d+")) {
                gridSize = 3; // Default if input is invalid
            } else {
                gridSize = Integer.parseInt(input);
            }
            createGameGrid(mainPane);


            stage.setScene(new Scene(mainPane));
            stage.show();
        });

        //Scene mainScene = new Scene(gridPane);

        //stage.setScene(mainScene);
        stage.show();




    }


    public static void main(String[] args) {
        launch(args);
    }
}