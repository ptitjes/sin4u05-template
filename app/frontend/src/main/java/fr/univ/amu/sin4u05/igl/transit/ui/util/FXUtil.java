package fr.univ.amu.sin4u05.igl.transit.ui.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class FXUtil {

    public static void loadFXML(String name, Object component, Object controller) {

        FXMLLoader loader = new FXMLLoader(FXUtil.class.getResource(name));
        loader.setRoot(component);
        loader.setController(controller);

        try {
            loader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
