package com.otomasyon.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import util.NavigationManager;

public class RandevuMenuController {

    private void load(String fxml) {
        try {
            Node node = FXMLLoader.load(
                getClass().getResource("/com/otomasyon/ui/" + fxml)
            );
            NavigationManager.navigate(node);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void randevuOlustur() {
        load("RandevuOlustur.fxml");
    }

    @FXML
    private void randevuGoruntule() {
        load("RandevuGoruntule.fxml");
    }

    @FXML
    private void randevuSil() {
        load("RandevuSil.fxml");
    }
}
