package com.otomasyon;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int secim = -1;
        int secim2 = -1;
        Scanner scanner = new Scanner(System.in);

        OgrenciListe ogrenciler = new OgrenciListe();
        OgretmenListe ogretmenler = new OgretmenListe();
        DerslerListe dersler = new DerslerListe();
        RandevuQueue randevular = new RandevuQueue();

        while (secim != 0) {
            System.out.println();
            System.out.println("MÜZİK DERSLERİ REZERVASYON SİSTEMİ ");
            System.out.println("1- Öğrenci İşlemleri");
            System.out.println("2- Öğretmen İşlemleri");
            System.out.println("3- Ders İşlemleri");
            System.out.println("4- Randevu İşlemleri");
            System.out.println("0- Çıkış");
            System.out.print("Seçiminiz: ");
            secim = scanner.nextInt();

            switch (secim) {

            case 1: {
                secim2 = -1;
                while (secim2 != 0) {
                    System.out.println();
                    System.out.println("ÖĞRENCİ İŞLEMLERİ");
                    System.out.println("1- Öğrenci Ekle");
                    System.out.println("2- Öğrenci Sil");
                    System.out.println("3- Öğrenciyi Görüntüle");
                    System.out.println("0- Geri Dön");
                    System.out.print("Seçiminiz: ");
                    secim2 = scanner.nextInt();

                    switch (secim2) {
                    case 1: ogrenciler.ogrenciEkle(); break;
                    case 2: ogrenciler.ogrenciSil(); break;
                    case 3: ogrenciler.ogrenciYazdir(); break;
                    case 0: break;
                    default: System.out.println("Geçersiz seçim!");
                    }
                }
                break;
            }

            case 2: {
                secim2 = -1;
                while (secim2 != 0) {
                    System.out.println();
                    System.out.println("ÖĞRETMEN İŞLEMLERİ");
                    System.out.println("1- Öğretmen Ekle");
                    System.out.println("2- Öğretmen Sil");
                    System.out.println("3- Öğretmen Görüntüle");
                    System.out.println("0- Geri Dön");
                    System.out.print("Seçiminiz: ");
                    secim2 = scanner.nextInt();

                    switch (secim2) {
                    case 1: ogretmenler.ogretmenEkle(); break;
                    case 2: ogretmenler.ogretmenSil(); break;
                    case 3: ogretmenler.ogretmenYazdir(); break;
                    case 0: break;
                    default: System.out.println("Geçersiz seçim!");
                    }
                }
                break;
            }

            case 3: {
                secim2 = -1;
                while (secim2 != 0) {
                    System.out.println();
                    System.out.println("DERS İŞLEMLERİ");
                    System.out.println("1- Ders Ekle");
                    System.out.println("2- Ders Sil");
                    System.out.println("3- Dersleri Görüntüle");
                    System.out.println("0- Geri Dön");
                    System.out.print("Seçiminiz: ");
                    secim2 = scanner.nextInt();

                    switch (secim2) {
                    case 1: dersler.dersEkle(); break;
                    case 2: dersler.dersSil(); break;
                    case 3: dersler.dersGoruntule(); break;
                    case 0: break;
                    default: System.out.println("Geçersiz seçim!");
                    }
                }
                break;
            }
            
            case 4:{
            	secim2 = -1;
                while (secim2 != 0) {
                    System.out.println();
                    System.out.println("RANDEVU İŞLEMLERİ");
                    System.out.println("1- Randevu Ekle");
                    System.out.println("2- Randevu İptal Et");
                    System.out.println("3- Randevu Görüntüle");
                    System.out.println("0- Geri Dön");
                    System.out.print("Seçiminiz: ");
                    secim2 = scanner.nextInt();

                    switch (secim2) {
                    case 1: randevular.randevuEkle(); break;
                    case 2: randevular.randevuSil(); break;
                    case 3: randevular.randevuGoruntule(); break;
                    case 0: break;
                    default: System.out.println("Geçersiz seçim!");
                    }
                }
                break;
            }

            case 0:
                System.out.println("Çıkış yapıldı.");
                break;

            default:
                System.out.println("Geçersiz seçim!");
            }
        }
    }
}
