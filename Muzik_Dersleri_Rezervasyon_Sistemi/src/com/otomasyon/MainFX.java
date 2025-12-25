package com.otomasyon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFX extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        OgrenciListe.INSTANCE.ogrencileriDosyadanYukle();
        OgretmenListe.INSTANCE.ogretmenleriDosyadanYukle();
        DerslerListe.INSTANCE.dersleriDosyadanYukle();
        RandevuQueue.INSTANCE.randevulariDosyadanYukle();
        Parent root = FXMLLoader.load(
            getClass().getResource("/com/otomasyon/ui/AdminPanel.fxml")
        );

        stage.setScene(new Scene(root));
        stage.setTitle("Müzik Kursu Otomasyonu");
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
