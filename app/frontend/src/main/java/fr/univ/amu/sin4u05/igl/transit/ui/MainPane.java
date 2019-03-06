package fr.univ.amu.sin4u05.igl.transit.ui;

import fr.univ.amu.sin4u05.igl.transit.ui.util.FXUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.VBox;

public class MainPane extends VBox {

    public SplitPane splitPane;
    public Label helloBox;
    public Label worldBox;

    MainPane() {
        FXUtil.loadFXML("/fr/univ/amu/sin4u05/igl/transit/ui/MainPane.fxml", this, this);
    }

    @FXML
    private void initialize() {
        splitPane.setDividerPositions(0.5);

        helloBox.setText("Hello");
        worldBox.setText("world!");
    }
}
