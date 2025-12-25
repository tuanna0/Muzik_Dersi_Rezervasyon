package com.otomasyon.controller;

import com.otomasyon.RandevuQueue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class RandevuSilPanelController {

    RandevuQueue queue = new RandevuQueue();
    
    @FXML
    private void randevuSil() {
        boolean ok = queue.randevuSilGUI();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(ok ? "İlk randevu silindi!" : "Silinecek randevu bulunamadı!");
        alert.show();
    }
}
