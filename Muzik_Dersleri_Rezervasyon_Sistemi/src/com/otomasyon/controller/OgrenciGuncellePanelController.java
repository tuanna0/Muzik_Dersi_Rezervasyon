package com.otomasyon.controller;

import com.otomasyon.OgrenciListe;
import com.otomasyon.OgrenciNode;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class OgrenciGuncellePanelController {

    @FXML private TextField txtId;

    @FXML private TextField txtAd;
    @FXML private TextField txtSoyad;
    @FXML private TextField txtEmail;
    @FXML private TextField txtTel;
    @FXML private ComboBox<String> cmbSeviye;

    private final OgrenciListe ogrenciListe = OgrenciListe.INSTANCE;
    private OgrenciNode secilenOgrenci;

    @FXML
    public void initialize() {
        sadeceRakam(txtId, 6);
        sadeceHarf(txtAd, 20);
        sadeceHarf(txtSoyad, 20);
        telefonKontrol(txtTel);
        mailKontrol(txtEmail);

        cmbSeviye.getItems().addAll("Başlangıç", "Orta", "İleri");
    }

    private void sadeceHarf(TextField tf, int max) {
        tf.setTextFormatter(new TextFormatter<>(c -> {
            String t = c.getControlNewText();
            if (t.length() > max) return null;
            if (!t.matches("[a-zA-ZçÇğĞıİöÖşŞüÜ ]*")) return null;
            return c;
        }));
    }

    private void sadeceRakam(TextField tf, int max) {
        tf.setTextFormatter(new TextFormatter<>(c -> {
            String t = c.getControlNewText();
            if (t.length() > max) return null;
            if (!t.matches("\\d*")) return null;
            return c;
        }));
    }

    private void telefonKontrol(TextField tf) {
        tf.setTextFormatter(new TextFormatter<>(c -> {
            String t = c.getControlNewText();
            if (!t.matches("\\d*")) return null;
            if (t.length() > 10) return null;
            if (t.length() == 1 && !t.startsWith("5")) return null;
            return c;
        }));
    }

    private void mailKontrol(TextField tf) {
        tf.setTextFormatter(new TextFormatter<>(c -> {
            String t = c.getControlNewText();

            if (t.length() > 30) return null;
            if (!t.matches("[a-zA-Z0-9._%+@-]*")) return null;

            if (t.contains("@")) {
                String[] p = t.split("@", -1);
                if (p.length > 2) return null;

                String domain = p[1];
                if (!domain.isEmpty() &&
                        !("gmail.com".startsWith(domain)
                        || "hotmail.com".startsWith(domain)
                        || "outlook.com".startsWith(domain))) {
                    return null;
                }
            }
            return c;
        }));
    }

    @FXML
    public void bilgileriGetir() {

        if (txtId.getText().isBlank()) {
            new Alert(Alert.AlertType.WARNING, "Lütfen ID giriniz!").show();
            return;
        }

        int id = Integer.parseInt(txtId.getText());
        secilenOgrenci = ogrenciListe.ogrenciBulIdIle(id);

        if (secilenOgrenci == null) {
            new Alert(Alert.AlertType.ERROR,
                    "Bu ID ile öğrenci bulunamadı!").show();
            return;
        }

        txtAd.setText(secilenOgrenci.ad);
        txtSoyad.setText(secilenOgrenci.soyad);
        txtEmail.setText(secilenOgrenci.email);
        txtTel.setText(secilenOgrenci.ceptel);
        cmbSeviye.setValue(secilenOgrenci.seviye);
    }

    @FXML
    public void guncelle() {

        if (secilenOgrenci == null) {
            new Alert(Alert.AlertType.WARNING,
                    "Önce ID ile öğrenci getiriniz!").show();
            return;
        }

        String seviye = cmbSeviye.getValue();

        if (seviye == null) {
            new Alert(Alert.AlertType.WARNING,
                    "Lütfen seviye seçiniz!").show();
            return;
        }

        boolean sonuc = ogrenciListe.ogrenciGuncelleGUI(
                secilenOgrenci.id,
                txtAd.getText(),
                txtSoyad.getText(),
                txtEmail.getText(),
                txtTel.getText(),
                seviye
        );

        if (sonuc) {
            new Alert(Alert.AlertType.INFORMATION,
                    "Öğrenci başarıyla güncellendi!").show();
        } else {
            new Alert(Alert.AlertType.ERROR,
                    "Güncelleme başarısız! Bilgileri kontrol edin.").show();
        }
    }
}
