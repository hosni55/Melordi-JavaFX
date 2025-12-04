import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

public class Melordi extends Application {
    private Clavier clavier;
    private Thread melodyThread;
    private volatile boolean isPlaying = false;
    private Button playStopButton;
    
    @Override
    public void start(Stage primaryStage) {
        Instru instru = new Instru();
        
        ChangeInstru changeInstru = new ChangeInstru(instru);
        clavier = new Clavier(instru);
        Son son = new Son(instru);
        
        // Create Play/Stop button with improved styling
        playStopButton = new Button("▶ Jouer Für Elise");
        playStopButton.setStyle(
            "-fx-font-size: 14px; " +
            "-fx-font-weight: bold; " +
            "-fx-padding: 10px 20px; " +
            "-fx-background-color: linear-gradient(to bottom, #66BB6A, #43A047); " +
            "-fx-text-fill: white; " +
            "-fx-background-radius: 8; " +
            "-fx-cursor: hand;"
        );
        playStopButton.setOnAction(e -> togglePlayback());
        
        HBox buttonBox = new HBox(playStopButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));
        
        // Create bottom panel with button and volume control
        HBox bottomPanel = new HBox(20);
        bottomPanel.setAlignment(Pos.CENTER);
        bottomPanel.setPadding(new Insets(10));
        bottomPanel.getChildren().addAll(buttonBox, son);
        
        BorderPane root = new BorderPane();
        root.setTop(changeInstru);
        root.setCenter(clavier);
        root.setBottom(bottomPanel);
        
        Scene scene = new Scene(root, 800, 550);
        
        // Ensure Clavier gets focus for key events
        clavier.requestFocus();
        
        // Force focus on click
        scene.setOnMouseClicked(ev -> clavier.requestFocus());

        primaryStage.setTitle("Melordi - Für Elise");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // Request focus after show
        clavier.requestFocus();
    }
    
    private void togglePlayback() {
        if (isPlaying) {
            stopPlayback();
        } else {
            startPlayback();
        }
    }
    
    private void startPlayback() {
        isPlaying = true;
        playStopButton.setText("⏸ Arrêter");
        playStopButton.setStyle(
            "-fx-font-size: 14px; " +
            "-fx-font-weight: bold; " +
            "-fx-padding: 10px 20px; " +
            "-fx-background-color: linear-gradient(to bottom, #EF5350, #E53935); " +
            "-fx-text-fill: white; " +
            "-fx-background-radius: 8; " +
            "-fx-cursor: hand;"
        );
        
        melodyThread = new Thread(() -> {
            playMelody("fur_elise.txt");
            // When melody finishes, reset button
            Platform.runLater(() -> {
                if (isPlaying) {
                    isPlaying = false;
                    playStopButton.setText("▶ Jouer Für Elise");
                }
            });
        });
        melodyThread.start();
    }
    
    private void stopPlayback() {
        isPlaying = false;
        playStopButton.setText("▶ Jouer Für Elise");
        playStopButton.setStyle(
            "-fx-font-size: 14px; " +
            "-fx-font-weight: bold; " +
            "-fx-padding: 10px 20px; " +
            "-fx-background-color: linear-gradient(to bottom, #66BB6A, #43A047); " +
            "-fx-text-fill: white; " +
            "-fx-background-radius: 8; " +
            "-fx-cursor: hand;"
        );
        
        if (melodyThread != null && melodyThread.isAlive()) {
            melodyThread.interrupt();
        }
    }
    
    private void playMelody(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null && isPlaying) {
                line = line.trim();
                if (line.isEmpty()) continue;
                
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String letter = parts[0].trim();
                    int duration = Integer.parseInt(parts[1].trim());
                    
                    // Adjust playback speed: reduce duration by 20%
                    duration = (int)(duration * 0.8);
                    
                    // Play note on JavaFX thread
                    Platform.runLater(() -> clavier.playNote(letter));
                    
                    // Wait for note duration
                    Thread.sleep(duration);
                    
                    // Stop note on JavaFX thread
                    Platform.runLater(() -> clavier.stopNote(letter));
                    
                    // Small pause between notes
                    Thread.sleep(20);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            // Thread interrupted, stop playback gracefully
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
