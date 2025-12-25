package com.otomasyon.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import util.NavigationManager;

public class OgrenciMenuController {
	
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
    private void ogrenciEklePanel() {
        load("OgrenciEklePanel.fxml");
    }

    @FXML
    private void ogrenciSilPanel() {
        load("OgrenciSilPanel.fxml");
    }

    @FXML
    private void ogrenciGoruntulePanel() {
        load("OgrenciGoruntulePanel.fxml");
    }
    
    @FXML
    private void ogrenciGuncellePanel() {
        load("OgrenciGuncellePanel.fxml");
    }


}
