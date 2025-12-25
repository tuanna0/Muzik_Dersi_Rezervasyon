package com.otomasyon.controller;

import com.otomasyon.OgretmenListe;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class OgretmenEklePanelController {

    @FXML private TextField txtAd;
    @FXML private TextField txtSoyad;
    @FXML private TextField txtEmail;
    @FXML private TextField txtTckn;
    @FXML private TextField txtTel;
    @FXML private TextField txtMaas;

    private final OgretmenListe liste = OgretmenListe.INSTANCE;

    @FXML
    public void initialize() {
        sadeceHarf(txtAd, 20);
        sadeceHarf(txtSoyad, 20);
        sadeceRakam(txtTckn, 11);
        telefonKontrol(txtTel);
        mailKontrol(txtEmail);
        maasKontrol(txtMaas);
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

    private void maasKontrol(TextField tf) {
        tf.setTextFormatter(new TextFormatter<>(c -> {
            String t = c.getControlNewText();
            if (!t.matches("\\d*(\\.\\d*)?")) return null;
            if (t.length() > 7) return null; // max 100000
            return c;
        }));
    }

    @FXML
    private void ogretmenEkle() {

        if (txtMaas.getText().isBlank()) {
            new Alert(Alert.AlertType.ERROR, "Maaş boş bırakılamaz!").show();
            return;
        }

        double maas = Double.parseDouble(txtMaas.getText());

        boolean eklendi = liste.ogretmenEkleGUI(
                txtAd.getText(),
                txtSoyad.getText(),
                txtEmail.getText(),
                txtTckn.getText(),
                txtTel.getText(),
                maas
        );

        if (eklendi) {
            txtAd.clear();
            txtSoyad.clear();
            txtEmail.clear();
            txtTckn.clear();
            txtTel.clear();
            txtMaas.clear();

            new Alert(Alert.AlertType.INFORMATION,
                    "Öğretmen başarıyla eklendi!").show();
        } else {
            new Alert(Alert.AlertType.ERROR,
                    "Bilgiler hatalı!\n\n" +
                    "- Ad/Soyad max 20 karakter\n" +
                    "- TC 11 hane\n" +
                    "- Telefon 5 ile başlar\n" +
                    "- Email gmail / hotmail / outlook\n" +
                    "- Maaş 0'dan büyük olmalı").show();
        }
    }
}
