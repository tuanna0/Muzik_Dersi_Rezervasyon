package com.otomasyon.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import util.NavigationManager;

public class DersMenuContreller {

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
    private void dersEklePanelAc() {
        load("DersEklePanel.fxml");
    }

    @FXML
    private void dersSilPanelAc() {
        load("DersSilPanel.fxml");
    }

    @FXML
    private void dersGoruntulePanelAc() {
        load("DersGoruntulePanel.fxml");
    }
}
