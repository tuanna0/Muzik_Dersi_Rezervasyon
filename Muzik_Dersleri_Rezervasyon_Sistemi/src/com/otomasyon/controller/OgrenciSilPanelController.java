package com.otomasyon.controller;

import com.otomasyon.OgrenciListe;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class OgrenciSilPanelController {

    @FXML private TextField txtSilId;
    @FXML private Label lblSonuc;

    private final OgrenciListe ogrenciListe = OgrenciListe.INSTANCE;

    @FXML
    public void ogrenciSil() {

        String idStr = txtSilId.getText();

        if (idStr == null || idStr.trim().isEmpty()) {
            lblSonuc.setText("ID boş bırakılamaz!");
            lblSonuc.setStyle("-fx-text-fill: red;");
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            boolean silindi = ogrenciListe.ogrenciSilGUI(id);

            if (silindi) {
                lblSonuc.setText("Öğrenci başarıyla silindi!");
                lblSonuc.setStyle("-fx-text-fill: green;");
                txtSilId.clear();
            } else {
                lblSonuc.setText("Belirtilen ID bulunamadı!");
                lblSonuc.setStyle("-fx-text-fill: red;");
            }

        } catch (NumberFormatException e) {
            lblSonuc.setText("Geçerli bir ID giriniz!");
            lblSonuc.setStyle("-fx-text-fill: red;");
        }
    }
}
