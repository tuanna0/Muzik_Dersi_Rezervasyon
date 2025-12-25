package com.otomasyon.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import util.NavigationManager;

public class AdminPanelController {

    @FXML
    private StackPane contentArea;

    @FXML
    private Button btnGeri;

    @FXML
    public void initialize() {
        contentArea.setVisible(false);
        contentArea.setManaged(false);

        btnGeri.setVisible(false);

        NavigationManager.init(contentArea);
    }

    private void loadPanel(String fxml) {
        try {
            Node node = FXMLLoader.load(
                getClass().getResource("/com/otomasyon/ui/" + fxml)
            );

            contentArea.setVisible(true);
            contentArea.setManaged(true);
            btnGeri.setVisible(true);

            NavigationManager.navigate(node);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void geriDon() {
        NavigationManager.goBack();

        if (contentArea.getChildren().isEmpty()) {
            contentArea.setVisible(false);
            contentArea.setManaged(false);
            btnGeri.setVisible(false);
        }
    }

    @FXML
    public void ogrenciPanelAc() {
        loadPanel("OgrenciMenu.fxml");
    }

    @FXML
    public void ogretmenPanelAc() {
        loadPanel("OgretmenMenu.fxml");
    }

    @FXML
    public void dersMenuAc() {
        loadPanel("DersMenu.fxml");
    }

    @FXML
    public void randevuPanelAc() {
        loadPanel("RandevuMenu.fxml");
    }
}
