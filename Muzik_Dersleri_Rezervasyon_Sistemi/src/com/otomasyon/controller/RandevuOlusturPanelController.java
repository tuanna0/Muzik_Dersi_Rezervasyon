package com.otomasyon.controller;

import com.otomasyon.OgrenciListe;
import com.otomasyon.OgrenciNode;
import com.otomasyon.OgretmenListe;
import com.otomasyon.OgretmenNode;
import com.otomasyon.DerslerListe;
import com.otomasyon.DerslerNode;
import com.otomasyon.RandevuQueue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;

public class RandevuOlusturPanelController {

    @FXML private ComboBox<String> cmbOgrenci;
    @FXML private ComboBox<String> cmbOgretmen;
    @FXML private ComboBox<String> cmbDers;

    @FXML private TextField txtTarih; // GG/AA/YYYY
    @FXML private TextField txtSaat;  // HH:MM

    private final RandevuQueue queue = RandevuQueue.INSTANCE;

    @FXML
    public void initialize() {

        txtTarih.setTextFormatter(tarihFormatter());
        txtSaat.setTextFormatter(saatFormatter());

        ogrencileriYukle();
        ogretmenleriYukle();
        dersleriYukle();
    }

    private void ogrencileriYukle() {
        cmbOgrenci.getItems().clear();
        OgrenciNode t = OgrenciListe.head;
        while (t != null) {
            cmbOgrenci.getItems().add(t.ad + " " + t.soyad);
            t = t.next;
        }
    }

    private void ogretmenleriYukle() {
        cmbOgretmen.getItems().clear();
        OgretmenNode t = OgretmenListe.head;
        while (t != null) {
            cmbOgretmen.getItems().add(t.ad + " " + t.soyad);
            t = t.next;
        }
    }

    private void dersleriYukle() {
        cmbDers.getItems().clear();
        DerslerNode t = DerslerListe.head;
        while (t != null) {
            cmbDers.getItems().add(t.dersAdi);
            t = t.next;
        }
    }

    @FXML
    private void randevuOlustur() {

        if (cmbOgrenci.getValue() == null || cmbOgretmen.getValue() == null || cmbDers.getValue() == null) {
            new Alert(Alert.AlertType.WARNING, "Lütfen öğrenci / öğretmen / ders seçiniz!").show();
            return;
        }

        boolean ok = queue.randevuEkleGUI(
                cmbOgrenci.getValue(),
                cmbOgretmen.getValue(),
                cmbDers.getValue(),
                txtTarih.getText(),
                txtSaat.getText()
        );

        if (ok) {
            new Alert(Alert.AlertType.INFORMATION, "Randevu başarıyla oluşturuldu!").show();
            cmbOgrenci.setValue(null);
            cmbOgretmen.setValue(null);
            cmbDers.setValue(null);
            txtTarih.clear();
            txtSaat.clear();
        } else {
            new Alert(Alert.AlertType.ERROR, queue.sonHata).show();
        }
    }

    private TextFormatter<String> tarihFormatter() {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String yeni = change.getControlNewText();
            if (yeni.length() > 10) return null;
            if (!yeni.matches("[0-9/]*")) return null;
            return change;
        };
        return new TextFormatter<>(filter);
    }

    private TextFormatter<String> saatFormatter() {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String yeni = change.getControlNewText();
            if (yeni.length() > 5) return null;
            if (!yeni.matches("[0-9:]*")) return null;
            return change;
        };
        return new TextFormatter<>(filter);
    }
}
