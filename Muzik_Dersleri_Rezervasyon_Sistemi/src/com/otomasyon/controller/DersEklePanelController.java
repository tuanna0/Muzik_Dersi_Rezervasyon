package com.otomasyon.controller;

import com.otomasyon.DerslerListe;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class DersEklePanelController {

    @FXML private ComboBox<String> cmbDersAdi;
    @FXML private TextField txtSure;
    @FXML private TextField txtUcret;
    @FXML private TextField txtOgretmenId;

    private final DerslerListe derslerListe = DerslerListe.INSTANCE;

    @FXML
    public void initialize() {

        cmbDersAdi.getItems().addAll("Gitar", "Piyano", "Keman", "Bağlama", "Klarnet");

        onlyDigitsWithMax(txtSure, 3);

        decimalWithMax(txtUcret, 7);

        onlyDigitsWithMax(txtOgretmenId, 6);
    }

    private void onlyDigitsWithMax(TextField tf, int maxLen) {
        tf.setTextFormatter(new TextFormatter<>(change -> {
            String yeni = change.getControlNewText();
            if (yeni.length() > maxLen) return null;
            if (!yeni.matches("\\d*")) return null;
            return change;
        }));
    }

    private void decimalWithMax(TextField tf, int maxLen) {
        tf.setTextFormatter(new TextFormatter<>(change -> {
            String yeni = change.getControlNewText();

            if (yeni.length() > maxLen) return null;

            if (!yeni.matches("[0-9.]*")) return null;

            long dotCount = yeni.chars().filter(ch -> ch == '.').count();
            if (dotCount > 1) return null;

            if (yeni.startsWith(".")) return null;

            return change;
        }));
    }

    @FXML
    public void dersEkle() {

        if (cmbDersAdi.getValue() == null) {
            new Alert(Alert.AlertType.WARNING, "Lütfen ders seçiniz!").show();
            return;
        }

        if (txtSure.getText().isBlank() || txtUcret.getText().isBlank()) {
            new Alert(Alert.AlertType.WARNING, "Süre ve ücret boş olamaz!").show();
            return;
        }

        int sure;
        double ucret;
        int ogretmenId;

        try {
            sure = Integer.parseInt(txtSure.getText());
            ucret = Double.parseDouble(txtUcret.getText());

            if (sure <= 0) {
                new Alert(Alert.AlertType.WARNING, "Süre 0'dan büyük olmalı!").show();
                return;
            }
            if (ucret <= 0) {
                new Alert(Alert.AlertType.WARNING, "Ücret 0'dan büyük olmalı!").show();
                return;
            }

            ogretmenId = txtOgretmenId.getText().isBlank() ? 0 : Integer.parseInt(txtOgretmenId.getText());

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Süre/Ücret/Öğretmen ID formatı hatalı!").show();
            return;
        }

        boolean sonuc = derslerListe.dersEkleGUI(
                cmbDersAdi.getValue(),
                sure,
                ucret,
                ogretmenId
        );

        if (sonuc) {
            cmbDersAdi.setValue(null);
            txtSure.clear();
            txtUcret.clear();
            txtOgretmenId.clear();
            new Alert(Alert.AlertType.INFORMATION, "Ders başarıyla eklendi!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Bu ders zaten mevcut veya bilgiler hatalı!").show();
        }
    }
}
