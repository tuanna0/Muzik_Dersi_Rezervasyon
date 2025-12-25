package com.otomasyon.controller;

import com.otomasyon.OgretmenListe;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class OgretmenSilPanelController {

    @FXML
    private TextField txtId;

    // ✅ DOĞRU INSTANCE KULLANIMI
    private OgretmenListe liste = OgretmenListe.INSTANCE;

    @FXML
    private void ogretmenSil() {
        try {
            int id = Integer.parseInt(txtId.getText().trim());

            boolean sonuc = liste.ogretmenSilGUI(id);

            if (sonuc) {
                new Alert(Alert.AlertType.INFORMATION, "Öğretmen silindi!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Bu ID ile öğretmen bulunamadı!").show();
            }

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "ID sayısal olmalı!").show();
        }
    }
}
