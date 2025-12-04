import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.effect.Reflection;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class Clavier extends Parent {
    private Touche[] touches;
    private Instru instru;

    public Clavier(Instru ins) {
        instru = ins;
        
        Rectangle fond_clavier = new Rectangle();
        fond_clavier.setWidth(680); 
        fond_clavier.setHeight(200);
        fond_clavier.setArcWidth(30);
        fond_clavier.setArcHeight(30);
        fond_clavier.setFill(
            new LinearGradient(0f, 0f, 0f, 1f, true, CycleMethod.NO_CYCLE,
                new Stop[] {
                    new Stop(0, Color.web("#333333")),
                    new Stop(1, Color.web("#000000"))
                }
            )
        );

        Reflection r = new Reflection();
        r.setFraction(0.25);
        r.setBottomOpacity(0);
        r.setTopOpacity(0.5);
        fond_clavier.setEffect(r);

        this.getChildren().add(fond_clavier);

        touches = new Touche[8];
        // MIDI Notes: Do=60, Re=62, Mi=64, Fa=65, Sol=67, La=69, Si=71, Do=72
        int[] notes = {60, 62, 64, 65, 67, 69, 71, 72};
        String[] lettres = {"U", "I", "O", "P", "J", "K", "L", "M"};
        
        for (int i = 0; i < 8; i++) {
            touches[i] = new Touche(lettres[i], 25 + i * 80, 50, notes[i], instru);
            this.getChildren().add(touches[i]);
        }

        this.setFocusTraversable(true); 
        
        this.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                String key = event.getText().toUpperCase();
                for (Touche t : touches) {
                    if (t.lettre.equals(key)) {
                        t.appuyer();
                    }
                }
            }
        });

        this.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                String key = event.getText().toUpperCase();
                for (Touche t : touches) {
                    if (t.lettre.equals(key)) {
                        t.relacher();
                    }
                }
            }
        });
    }
    
    // Public methods for programmatic note control (auto-play)
    public void playNote(String letter) {
        for (Touche t : touches) {
            if (t.lettre.equals(letter.toUpperCase())) {
                t.appuyer();
                break;
            }
        }
    }
    
    public void stopNote(String letter) {
        for (Touche t : touches) {
            if (t.lettre.equals(letter.toUpperCase())) {
                t.relacher();
                break;
            }
        }
    }
}
