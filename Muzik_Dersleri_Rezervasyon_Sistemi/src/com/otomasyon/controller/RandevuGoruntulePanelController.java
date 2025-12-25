package com.otomasyon.controller;

import com.otomasyon.RandevuQueue;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class RandevuGoruntulePanelController {

    @FXML private TextArea txtListe;

    RandevuQueue queue = RandevuQueue.INSTANCE;

    @FXML
    private void randevuGoruntule() {
        txtListe.setText(queue.randevuListeGUI());
    }
}
