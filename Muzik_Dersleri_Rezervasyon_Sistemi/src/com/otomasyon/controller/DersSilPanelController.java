package com.otomasyon.controller;

import com.otomasyon.DerslerListe;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

public class DersSilPanelController {

    @FXML
    private ComboBox<String> cmbDersAdi;

    private final DerslerListe derslerListe = DerslerListe.INSTANCE;

    @FXML
    public void initialize() {
        cmbDersAdi.getItems().addAll(
                "Gitar",
                "Piyano",
                "Keman",
                "Bağlama",
                "Klarnet"
        );
    }

    @FXML
    private void dersSil() {

        if (cmbDersAdi.getValue() == null) {
            new Alert(Alert.AlertType.WARNING,
                    "Lütfen silinecek dersi seçiniz!").show();
            return;
        }

        String dersAdi = cmbDersAdi.getValue();

        boolean silindi = derslerListe.dersSilAdaGoreGUI(dersAdi);

        if (silindi) {
            new Alert(Alert.AlertType.INFORMATION,
                    "Ders başarıyla silindi!").show();
            cmbDersAdi.setValue(null);
        } else {
            new Alert(Alert.AlertType.ERROR,
                    "Bu ders sistemde bulunamadı!").show();
        }
    }
}
