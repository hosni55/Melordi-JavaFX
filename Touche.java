import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.effect.DropShadow;

public class Touche extends Parent {
    public String lettre = new String("X");
    private int positionX = 0;
    private int positionY = 0;
    private int note = 0;
    private Instru instru;
    Rectangle fond_touche;
    Text lettre_touche;

    public Touche(String l, int posX, int posY, int n, Instru ins) {
        lettre = l;
        positionX = posX;
        positionY = posY;
        note = n;
        instru = ins;

        // Gradient coloré pour les touches
        LinearGradient gradient = new LinearGradient(
            0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
            new Stop(0, Color.web("#E3F2FD")),  // Bleu clair
            new Stop(1, Color.web("#90CAF9"))   // Bleu moyen
        );
        
        fond_touche = new Rectangle(75, 75);
        fond_touche.setFill(gradient);
        fond_touche.setArcHeight(15);
        fond_touche.setArcWidth(15);
        fond_touche.setStroke(Color.web("#1976D2")); // Bordure bleue
        fond_touche.setStrokeWidth(2);
        
        // Effet d'ombre
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.rgb(0, 0, 0, 0.3));
        shadow.setRadius(5);
        fond_touche.setEffect(shadow);
        
        lettre_touche = new Text(lettre);
        lettre_touche.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        lettre_touche.setFill(Color.web("#1565C0")); // Texte bleu foncé
        lettre_touche.setX(28); // Centrage amélioré
        lettre_touche.setY(48);

        this.setTranslateX(positionX);
        this.setTranslateY(positionY);
        
        this.getChildren().add(fond_touche);
        this.getChildren().add(lettre_touche);

        this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                LinearGradient hoverGradient = new LinearGradient(
                    0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                    new Stop(0, Color.web("#BBDEFB")),
                    new Stop(1, Color.web("#64B5F6"))
                );
                fond_touche.setFill(hoverGradient);
            }
        });

        this.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                LinearGradient normalGradient = new LinearGradient(
                    0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                    new Stop(0, Color.web("#E3F2FD")),
                    new Stop(1, Color.web("#90CAF9"))
                );
                fond_touche.setFill(normalGradient);
            }
        });

        this.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                appuyer();
            }
        });

        this.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                relacher();
            }
        });
    }
    
    public void appuyer() {
        LinearGradient pressedGradient = new LinearGradient(
            0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
            new Stop(0, Color.web("#42A5F5")),
            new Stop(1, Color.web("#1E88E5"))
        );
        fond_touche.setFill(pressedGradient);
        this.setTranslateY(positionY + 3); // Shift down
        instru.note_on(note);
    }
    
    public void relacher() {
        LinearGradient normalGradient = new LinearGradient(
            0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
            new Stop(0, Color.web("#E3F2FD")),
            new Stop(1, Color.web("#90CAF9"))
        );
        fond_touche.setFill(normalGradient);
        this.setTranslateY(positionY); // Shift up
        instru.note_off(note);
    }
}
