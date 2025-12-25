package com.otomasyon.controller;

import com.otomasyon.OgrenciListe;
import com.otomasyon.OgrenciNode;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class OgrenciGoruntulePanelController {

    @FXML private ListView<String> liste;
    @FXML private TextField txtAra;

    @FXML
    public void initialize() {
        yenile();
    }

    @FXML
    public void yenile() {
        liste.getItems().clear();
        OgrenciNode temp = OgrenciListe.head;

        if (temp == null) {
            liste.getItems().add("Kayıtlı öğrenci yok.");
            return;
        }

        while (temp != null) {
            liste.getItems().add(ogrenciToString(temp));
            temp = temp.next;
        }
    }

    @FXML
    public void adaGoreAra() {
        liste.getItems().clear();

        String ad = txtAra.getText();
        if (ad == null || ad.trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Lütfen öğrenci adı giriniz!").show();
            return;
        }

        OgrenciNode bulunan = OgrenciListe.INSTANCE.ogrenciBulAdaGore(ad);

        if (bulunan == null) {
            liste.getItems().add("Bu isimde öğrenci bulunamadı.");
        } else {
            liste.getItems().add("ARAMA SONUCU");
            liste.getItems().add(ogrenciToString(bulunan));
        }
    }

    private String ogrenciToString(OgrenciNode o) {
        return
            "ID: " + o.id +
            " | Ad Soyad: " + o.ad + " " + o.soyad +
            " | Email: " + o.email +
            " | TCKN: " + o.tckn +
            " | Tel: " + o.ceptel +
            " | Seviye: " + o.seviye;
    }
}
