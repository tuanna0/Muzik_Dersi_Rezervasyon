package com.otomasyon.controller;

import com.otomasyon.OgretmenListe;
import com.otomasyon.OgretmenNode;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class OgretmenGoruntulePanelController {

    @FXML private ListView<String> liste;
    @FXML private TextField txtAra;

    @FXML
    public void initialize() {
        listeYukle();
    }

    @FXML
    public void yenile() {
        listeYukle();
    }

    @FXML
    public void adaGoreAra() {

        liste.getItems().clear();

        String ad = txtAra.getText();

        if (ad == null || ad.trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Lütfen öğretmen adı giriniz!").show();
            return;
        }

        OgretmenNode bulunan = OgretmenListe.INSTANCE.ogretmenBulAdaGore(ad);

        if (bulunan == null) {
            liste.getItems().add("Bu isimde öğretmen bulunamadı.");
        } else {
            liste.getItems().add("🔍 ARAMA SONUCU");
            liste.getItems().add(ogretmenToString(bulunan));
        }
    }

    private void listeYukle() {

        liste.getItems().clear();
        OgretmenNode temp = OgretmenListe.head;

        while (temp != null) {
            liste.getItems().add(ogretmenToString(temp));
            temp = temp.next;
        }
    }

    private String ogretmenToString(OgretmenNode o) {
        return o.id + " - " +
               o.ad + " " + o.soyad +
               " | " + o.email +
               " | Tel: " + o.ceptel +
               " | Maaş: " + o.maas;
    }
}
