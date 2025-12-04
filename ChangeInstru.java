import javafx.scene.Parent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Toggle;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;

public class ChangeInstru extends Parent {
    private RadioButton rb_piano;
    private RadioButton rb_guitare;
    private RadioButton rb_orgue;
    private Instru instru;

    public ChangeInstru(Instru ins) {
        instru = ins;
        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(15));
        gridpane.setHgap(30);
        gridpane.setVgap(10);
        gridpane.setAlignment(Pos.CENTER);
        
        // Fond coloré
        gridpane.setBackground(new Background(new BackgroundFill(
            Color.web("#F5F5F5"), 
            new CornerRadii(10), 
            Insets.EMPTY
        )));

        // Création des images des 3 instruments
        // Piano
        ImageView piano = new ImageView(new Image(ChangeInstru.class.getResourceAsStream("images/piano.png")));
        piano.setFitHeight(60);
        piano.setPreserveRatio(true);

        // Guitare
        ImageView guitare = new ImageView(new Image(ChangeInstru.class.getResourceAsStream("images/guitare.png")));
        guitare.setFitHeight(60);
        guitare.setPreserveRatio(true);

        // Orgue
        ImageView orgue = new ImageView(new Image(ChangeInstru.class.getResourceAsStream("images/orgue.png")));
        orgue.setFitHeight(60);
        orgue.setPreserveRatio(true);

        // Création des boutons radio avec style
        rb_piano = new RadioButton("Piano");
        rb_piano.setStyle("-fx-font-size: 14px; -fx-text-fill: #1976D2; -fx-font-weight: bold;");
        
        rb_guitare = new RadioButton("Guitare");
        rb_guitare.setStyle("-fx-font-size: 14px; -fx-text-fill: #1976D2; -fx-font-weight: bold;");
        
        rb_orgue = new RadioButton("Orgue");
        rb_orgue.setStyle("-fx-font-size: 14px; -fx-text-fill: #1976D2; -fx-font-weight: bold;");

        ToggleGroup groupe = new ToggleGroup();
        rb_piano.setToggleGroup(groupe);
        rb_guitare.setToggleGroup(groupe);
        rb_orgue.setToggleGroup(groupe);

        rb_piano.setSelected(true); // Default

        // Ajout d'un ChangeListener au groupe de boutons radio
        groupe.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (groupe.getSelectedToggle() != null) {
                    if (groupe.getSelectedToggle() == rb_piano) {
                        instru.set_instrument(0);
                    } else if (groupe.getSelectedToggle() == rb_guitare) {
                        instru.set_instrument(26);
                    } else if (groupe.getSelectedToggle() == rb_orgue) {
                        instru.set_instrument(16);
                    }
                }
            }
        });

        // On ajoute nos images à notre layout (centrées)
        VBox pianoBox = new VBox(5, piano, rb_piano);
        pianoBox.setAlignment(Pos.CENTER);
        
        VBox guitareBox = new VBox(5, guitare, rb_guitare);
        guitareBox.setAlignment(Pos.CENTER);
        
        VBox orgueBox = new VBox(5, orgue, rb_orgue);
        orgueBox.setAlignment(Pos.CENTER);
        
        gridpane.add(pianoBox, 0, 0);
        gridpane.add(guitareBox, 1, 0);
        gridpane.add(orgueBox, 2, 0);

        this.getChildren().add(gridpane);
    }
}
