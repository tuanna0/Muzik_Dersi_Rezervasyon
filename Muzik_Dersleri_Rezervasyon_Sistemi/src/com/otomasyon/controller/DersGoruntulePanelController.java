package com.otomasyon.controller;

import com.otomasyon.DerslerListe;
import com.otomasyon.DerslerNode;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DersGoruntulePanelController {

    @FXML private ListView<String> liste;
    @FXML private ComboBox<String> cmbSirala;

    @FXML
    public void initialize() {

        cmbSirala.setItems(FXCollections.observableArrayList(
                "Ders Adına Göre",
                "Öğretmen ID'ye Göre",
                "Ücrete Göre"
        ));

        // İlk açılış
        yukle(DerslerListe.getTumDersler());
    }

    @FXML
    public void yenile() {
        cmbSirala.getSelectionModel().clearSelection();
        yukle(DerslerListe.getTumDersler());
    }

    @FXML
    public void sirala() {

        String secim = cmbSirala.getValue();
        if (secim == null) return;

        List<DerslerNode> dersler =
                new ArrayList<>(DerslerListe.getTumDersler());

        switch (secim) {

            case "Ders Adına Göre":
                dersler.sort(
                        Comparator.comparing(
                                d -> d.dersAdi.toLowerCase()
                        )
                );
                break;

            case "Öğretmen ID'ye Göre":
                dersler.sort(
                        Comparator.comparingInt(d -> d.ogretmenId)
                );
                break;

            case "Ücrete Göre":
                dersler.sort(
                        Comparator.comparingDouble(d -> d.ucret)
                );
                break;
        }

        yukle(dersler);
    }

    private void yukle(List<DerslerNode> dersler) {

        liste.getItems().clear();

        if (dersler == null || dersler.isEmpty()) {
            liste.getItems().add("Henüz kayıtlı ders yok.");
            return;
        }

        for (DerslerNode d : dersler) {
            liste.getItems().add(
                    d.dersAdi +
                    " | Süre: " + d.sure +
                    " dk | Ücret: " + d.ucret +
                    " | Öğretmen ID: " + d.ogretmenId
            );
        }
    }
}
