package com.otomasyon.controller;

import com.otomasyon.OgrenciListe;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ComboBox;


public class OgrenciEklePanelController {

    @FXML private TextField txtAd;
    @FXML private TextField txtSoyad;
    @FXML private TextField txtEmail;
    @FXML private TextField txtTckn;
    @FXML private TextField txtTel;
    @FXML private ComboBox<String> cmbSeviye;

    @FXML private ListView<String> listeOgrenciler;

    private final OgrenciListe ogrenciListe = OgrenciListe.INSTANCE;

    @FXML
    public void initialize() {
        sadeceHarf(txtAd, 20);
        sadeceHarf(txtSoyad, 20);
        sadeceRakam(txtTckn, 11);
        telefonKontrol(txtTel);
        mailKontrol(txtEmail);
        cmbSeviye.getItems().addAll("Başlangıç", "Orta", "İleri");
    }

    private void sadeceHarf(TextField tf, int max) {
        tf.setTextFormatter(new TextFormatter<>(change -> {
            String yeni = change.getControlNewText();
            if (yeni.length() > max) return null;
            if (!yeni.matches("[a-zA-ZçÇğĞıİöÖşŞüÜ ]*")) return null;
            return change;
        }));
    }

    private void sadeceRakam(TextField tf, int max) {
        tf.setTextFormatter(new TextFormatter<>(change -> {
            String yeni = change.getControlNewText();
            if (yeni.length() > max) return null;
            if (!yeni.matches("\\d*")) return null;
            return change;
        }));
    }

    private void telefonKontrol(TextField tf) {
        tf.setTextFormatter(new TextFormatter<>(change -> {
            String yeni = change.getControlNewText();

            if (!yeni.matches("\\d*")) return null;
            if (yeni.length() > 10) return null;

            if (yeni.length() == 1 && !yeni.startsWith("5"))
                return null;

            return change;
        }));
    }

    private void mailKontrol(TextField tf) {
        tf.setTextFormatter(new TextFormatter<>(change -> {
            String yeni = change.getControlNewText();

            if (yeni.length() > 30)
                return null;

            if (!yeni.matches("[a-zA-Z0-9._%+@-]*"))
                return null;

            if (yeni.contains("@")) {
                String[] parts = yeni.split("@", -1);

                if (parts.length > 2)
                    return null;

                String domain = parts[1];

                if (!domain.isEmpty() &&
                    !("gmail.com".startsWith(domain)
                    || "hotmail.com".startsWith(domain)
                    || "outlook.com".startsWith(domain))) {
                    return null;
                }
            }

            return change;
        }));
    }


    @FXML
    public void ogrenciEkle() {

        String seviye = cmbSeviye.getValue();

        boolean eklendi = ogrenciListe.ogrenciEkleGUI(
                txtAd.getText(),
                txtSoyad.getText(),
                txtEmail.getText(),
                txtTckn.getText(),
                txtTel.getText(),
                seviye
        );

        if (eklendi) {
            txtAd.clear();
            txtSoyad.clear();
            txtEmail.clear();
            txtTckn.clear();
            txtTel.clear();
            cmbSeviye.getSelectionModel().clearSelection();

            new Alert(Alert.AlertType.INFORMATION,
                    "Öğrenci başarıyla eklendi.").show();

        } else {
            new Alert(Alert.AlertType.ERROR,
                    "Bilgiler hatalı!\n\n" +
                    "- Ad/Soyad max 20 karakter\n" +
                    "- TC 11 hane rakam\n" +
                    "- Telefon 5 ile başlar (10 hane)\n" +
                    "- Email max 30 karakter\n" +
                    "- Email gmail / hotmail / outlook\n" +
                    "- Seviye seçilmelidir"
            ).show();
        }
    }

}
