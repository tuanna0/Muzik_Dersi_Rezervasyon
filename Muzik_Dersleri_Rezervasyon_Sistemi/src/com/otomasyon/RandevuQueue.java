package com.otomasyon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Scanner;

public class RandevuQueue {

    public static RandevuQueue INSTANCE = new RandevuQueue();

    public static RandevuNode front = null;
    public static RandevuNode rear = null;

    public String sonHata = "";
    String ogrenciAdSoyad;
    String ogretmenAdSoyad;
    String dersAdi;
    String tarih;
    String saat;

    Scanner scanner = new Scanner(System.in);

    public void randevuEkle() {
        System.out.println("Randevu Oluşturmaya Hoşgeldiniz");

        while (true) {
            System.out.println("Öğrenci Ad Soyadı:");
            ogrenciAdSoyad = scanner.nextLine();
            if (!ogrenciAdSoyad.isEmpty()) break;
            System.out.println("Hata:Boş geçilemez!");
        }

        while (true) {
            System.out.println("Öğretmen Ad Soyadı:");
            ogretmenAdSoyad = scanner.nextLine();
            if (!ogretmenAdSoyad.isEmpty()) break;
            System.out.println("Hata:Boş geçilemez!");
        }

        while (true) {
            System.out.println("Ders Adı:");
            dersAdi = scanner.nextLine();
            if (!dersAdi.isEmpty()) break;
            System.out.println("Hata:Boş geçilemez!");
        }

        while (true) {
            System.out.println("Tarih (GG/AA/YYYY):");
            tarih = scanner.nextLine();
            if (!tarih.isEmpty()) break;
            System.out.println("Hata:Boş geçilemez!");
        }

        while (true) {
            System.out.println("Saat (HH:MM):");
            saat = scanner.nextLine();
            if (!saat.isEmpty()) break;
            System.out.println("Hata:Boş geçilemez!");
        }

        boolean ok = randevuEkleGUI(ogrenciAdSoyad, ogretmenAdSoyad, dersAdi, tarih, saat);

        if (ok) System.out.println("Randevu başarıyla oluşturuldu.");
        else System.out.println("Hata: " + sonHata);
    }

    public void randevuSil() {
        if (front == null) {
            System.out.println("Silinecek randevu bulunamadı.");
            return;
        }

        System.out.println("\nSilinen Randevu:");
        System.out.println("Öğrenci: " + front.ogrenciAdSoyad);
        System.out.println("Öğretmen: " + front.ogretmenAdSoyad);
        System.out.println("Ders: " + front.dersAdi);
        System.out.println("Tarih: " + front.tarih);
        System.out.println("Saat: " + front.saat);

        front = front.next;
        if (front == null) rear = null;

        tumunuDosyayaYaz();
    }

    public void randevuGoruntule() {
        if (front == null) {
            System.out.println("Kayıtlı randevu bulunamadı.");
            return;
        }

        System.out.println("\nRandevu Listesi");
        RandevuNode temp = front;

        while (temp != null) {
            System.out.println("Öğrenci : " + temp.ogrenciAdSoyad);
            System.out.println("Öğretmen: " + temp.ogretmenAdSoyad);
            System.out.println("Ders    : " + temp.dersAdi);
            System.out.println("Tarih   : " + temp.tarih);
            System.out.println("Saat    : " + temp.saat);
            temp = temp.next;
        }
    }
    
    public boolean randevuEkleGUI(String ogr, String ogretmen, String ders, String tarih, String saat) {

        ogr = (ogr == null) ? "" : ogr.trim();
        ogretmen = (ogretmen == null) ? "" : ogretmen.trim();
        ders = (ders == null) ? "" : ders.trim();
        tarih = (tarih == null) ? "" : tarih.trim();
        saat = (saat == null) ? "" : saat.trim();

        if (ogr.isEmpty() || ogretmen.isEmpty() || ders.isEmpty() || tarih.isEmpty() || saat.isEmpty()) {
            sonHata = "Alanlar boş bırakılamaz!";
            return false;
        }

        if (!isimGecerliMi(ogr)) {
            sonHata = "Öğrenci ad-soyad hatalı!";
            return false;
        }

        if (!isimGecerliMi(ogretmen)) {
            sonHata = "Öğretmen ad-soyad hatalı!";
            return false;
        }

        if (!dersAdiGecerliMi(ders)) {
            sonHata = "Ders adı hatalı!";
            return false;
        }

        if (!tarihGecerliMi(tarih)) {
            sonHata = "Tarih hatalı (GG/AA/YYYY) veya geçmiş tarih!";
            return false;
        }

        if (!saatGecerliMi(saat)) {
            sonHata = "Saat hatalı (00:00 - 23:59)!";
            return false;
        }

        if (randevuCakisiyorMu(ogretmen, tarih, saat)) {
            sonHata = "Çakışma var! Bu öğretmen aynı tarih/saatte dolu.";
            return false;
        }

        RandevuNode yeni = new RandevuNode(ogr, ogretmen, ders, tarih, saat);

        if (front == null) {
            front = yeni;
            rear = yeni;
        } else {
            rear.next = yeni;
            rear = yeni;
        }

        randevuyuDosyayaEkle(ogr, ogretmen, ders, tarih, saat);
        sonHata = "";
        return true;
    }

    public boolean randevuSilGUI() {
        if (front == null) return false;

        front = front.next;
        if (front == null) rear = null;

        tumunuDosyayaYaz();
        return true;
    }

    private boolean isimGecerliMi(String ad) {
        ad = ad.trim();
        return !ad.isEmpty()
                && ad.length() <= 40
                && ad.matches("[a-zA-ZçÇğĞıİöÖşŞüÜ ]+");
    }

    private boolean dersAdiGecerliMi(String ders) {
        ders = ders.trim();
        return !ders.isEmpty()
                && ders.length() <= 30
                && ders.matches("[a-zA-ZçÇğĞıİöÖşŞüÜ ]+");
    }

    private boolean saatGecerliMi(String saat) {
        saat = saat.trim();
        return saat.matches("([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]");
    }

    private boolean tarihGecerliMi(String tarih) {
        tarih = tarih.trim();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/uuuu")
                    .withResolverStyle(ResolverStyle.STRICT);

            LocalDate girilen = LocalDate.parse(tarih, formatter);
            LocalDate bugun = LocalDate.now();

            return !girilen.isBefore(bugun);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private boolean randevuCakisiyorMu(String ogretmen, String tarih, String saat) {
        String og = ogretmen.trim().toLowerCase();
        String tr = tarih.trim();
        String st = saat.trim();

        RandevuNode t = front;
        while (t != null) {
            if (t.ogretmenAdSoyad.trim().toLowerCase().equals(og)
                    && t.tarih.trim().equals(tr)
                    && t.saat.trim().equals(st)) {
                return true;
            }
            t = t.next;
        }
        return false;
    }

    // ---------- FILE ----------
    private void randevuyuDosyayaEkle(String ogr, String ogretmen, String ders, String tarih, String saat) {
        try (FileWriter fw = new FileWriter("randevular.txt", true)) {
            fw.write(ogr + ";" + ogretmen + ";" + ders + ";" + tarih + ";" + saat + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tumunuDosyayaYaz() {
        try (FileWriter fw = new FileWriter("randevular.txt")) {
            RandevuNode t = front;
            while (t != null) {
                fw.write(t.ogrenciAdSoyad + ";" + t.ogretmenAdSoyad + ";" + t.dersAdi + ";" + t.tarih + ";" + t.saat + "\n");
                t = t.next;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void randevulariDosyadanYukle() {
        try (BufferedReader br = new BufferedReader(new FileReader("randevular.txt"))) {
            String satir;
            while ((satir = br.readLine()) != null) {
                String[] p = satir.split(";");
                if (p.length < 5) continue;

                RandevuNode yeni = new RandevuNode(p[0], p[1], p[2], p[3], p[4]);

                if (front == null) {
                    front = yeni;
                    rear = yeni;
                } else {
                    rear.next = yeni;
                    rear = yeni;
                }
            }
            System.out.println("Randevular dosyadan yüklendi.");
        } catch (Exception e) {
            System.out.println("Randevu dosyası yok veya boş.");
        }
    }
    
    public String randevuListeGUI() {

        if (front == null) {
            return "Kayıtlı randevu bulunamadı.";
        }

        StringBuilder sb = new StringBuilder();
        RandevuNode t = front;

        while (t != null) {
            sb.append("Öğrenci : ").append(t.ogrenciAdSoyad).append("\n")
              .append("Öğretmen: ").append(t.ogretmenAdSoyad).append("\n")
              .append("Ders    : ").append(t.dersAdi).append("\n")
              .append("Tarih   : ").append(t.tarih).append("\n")
              .append("Saat    : ").append(t.saat).append("\n")
              .append("---------------------------\n");

            t = t.next;
        }

        return sb.toString();
    }

}
