import javafx.scene.Parent;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;

public class Son extends Parent {
    private Instru instru;
    private Slider slider;

    public Son(Instru ins) {
        instru = ins;
        
        VBox vbox = new VBox(8);
        vbox.setPadding(new Insets(10));
        vbox.setAlignment(Pos.CENTER);

        Text label = new Text("🔊 Volume");
        label.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        label.setFill(Color.web("#1976D2"));
        
        slider = new Slider(0, 127, instru.getVolume());
        slider.setShowTickLabels(false);
        slider.setShowTickMarks(false);
        slider.setPrefWidth(200);
        slider.setStyle("-fx-control-inner-background: #E3F2FD;");
        
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
                instru.setVolume(new_val.intValue());
            }
        });

        vbox.getChildren().addAll(label, slider);
        this.getChildren().add(vbox);
    }
}
