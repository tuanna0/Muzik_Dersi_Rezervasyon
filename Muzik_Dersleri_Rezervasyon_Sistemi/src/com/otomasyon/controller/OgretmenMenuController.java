package com.otomasyon.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import util.NavigationManager;

public class OgretmenMenuController {

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
    private void ogretmenEklePanelAc() {
        load("OgretmenEklePanel.fxml");
    }

    @FXML
    private void ogretmenSilPanelAc() {
        load("OgretmenSilPanel.fxml");
    }

    @FXML
    private void ogretmenGorPanelAc() {
        load("OgretmenGoruntulePanel.fxml");
    }
}
