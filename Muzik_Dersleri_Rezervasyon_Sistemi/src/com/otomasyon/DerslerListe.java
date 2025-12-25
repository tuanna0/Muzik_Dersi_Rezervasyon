package com.otomasyon;

import java.io.*;
import java.util.*;

public class DerslerListe {

    public static DerslerListe INSTANCE = new DerslerListe();

    public static DerslerNode head = null;
    public static DerslerNode tail = null;


    public DerslerNode dersBulAdaGore(String dersAdi) {
        DerslerNode temp = head;
        while (temp != null) {
            if (temp.dersAdi.equalsIgnoreCase(dersAdi)) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    private void listeyeEkle(DerslerNode yeni) {
        if (head == null) {
            head = yeni;
            tail = yeni;
        } else {
            yeni.next = head;
            head = yeni;
        }
    }


    public boolean dersEkleGUI(String dersAdi, int sure, double ucret, int ogretmenId) {

        if (dersAdi == null || dersAdi.isBlank())
            return false;

        if (sure <= 0 || ucret <= 0 || ogretmenId < 0)
            return false;

        if (dersBulAdaGore(dersAdi) != null)
            return false;

        DerslerNode yeni = new DerslerNode(dersAdi, sure, ucret, ogretmenId);
        listeyeEkle(yeni);
        dersleriDosyayaYaz();
        return true;
    }


    public boolean dersSilGUI(String dersAdi) {

        if (head == null)
            return false;

        if (head.dersAdi.equalsIgnoreCase(dersAdi)) {
            head = head.next;
            if (head == null) tail = null;
            dersleriDosyayaYaz();
            return true;
        }

        DerslerNode prev = head;
        DerslerNode curr = head.next;

        while (curr != null) {
            if (curr.dersAdi.equalsIgnoreCase(dersAdi)) {
                prev.next = curr.next;
                if (curr == tail) tail = prev;
                dersleriDosyayaYaz();
                return true;
            }
            prev = curr;
            curr = curr.next;
        }

        return false;
    }

    public void dersEkle() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Ders Adı: ");
        String dersAdi = sc.nextLine();

        if (dersAdi.isBlank()) {
            System.out.println("Hata: Ders adı boş olamaz!");
            return;
        }

        if (dersBulAdaGore(dersAdi) != null) {
            System.out.println("Bu ders zaten mevcut!");
            return;
        }

        System.out.print("Süre (dk): ");
        int sure = sc.nextInt();

        if (sure <= 0) {
            System.out.println("Süre 0'dan büyük olmalı!");
            return;
        }

        System.out.print("Ücret: ");
        double ucret = sc.nextDouble();

        if (ucret <= 0) {
            System.out.println("Ücret 0'dan büyük olmalı!");
            return;
        }

        System.out.print("Öğretmen ID (yoksa 0): ");
        int ogretmenId = sc.nextInt();

        DerslerNode yeni = new DerslerNode(dersAdi, sure, ucret, ogretmenId);
        listeyeEkle(yeni);
        dersleriDosyayaYaz();

        System.out.println("Ders başarıyla eklendi.");
    }

    public void dersSil() {

        if (head == null) {
            System.out.println("Sistemde kayıtlı ders yok!");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Silinecek ders adı: ");
        String dersAdi = sc.nextLine();

        boolean silindi = dersSilGUI(dersAdi);

        if (silindi)
            System.out.println("Ders silindi.");
        else
            System.out.println("Bu isimde ders bulunamadı.");
    }

    public void dersGoruntule() {

        if (head == null) {
            System.out.println("Kayıtlı ders yok!");
            return;
        }

        DerslerNode temp = head;
        while (temp != null) {
            System.out.println("Ders: " + temp.dersAdi +
                    " | Süre: " + temp.sure +
                    " | Ücret: " + temp.ucret +
                    " | Öğretmen ID: " + temp.ogretmenId);
            temp = temp.next;
        }
    }

    private void dersleriDosyayaYaz() {

        try (FileWriter fw = new FileWriter("dersler.txt")) {

            DerslerNode temp = head;
            while (temp != null) {
                fw.write(
                        temp.dersAdi + ";" +
                        temp.sure + ";" +
                        temp.ucret + ";" +
                        temp.ogretmenId + "\n"
                );
                temp = temp.next;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean dersSilAdaGoreGUI(String dersAdi) {

        if (head == null) return false;

        if (head.dersAdi.equalsIgnoreCase(dersAdi)) {
            head = head.next;
            if (head == null) tail = null;
            dersleriDosyayaYaz(); // KALICI SİLME
            return true;
        }

        DerslerNode prev = head;
        DerslerNode curr = head.next;

        while (curr != null) {
            if (curr.dersAdi.equalsIgnoreCase(dersAdi)) {
                prev.next = curr.next;
                if (curr == tail) tail = prev;
                dersleriDosyayaYaz();
                return true;
            }
            prev = curr;
            curr = curr.next;
        }

        return false;
    }


    public void dersleriDosyadanYukle() {

        try (BufferedReader br = new BufferedReader(new FileReader("dersler.txt"))) {

            String satir;
            while ((satir = br.readLine()) != null) {

                String[] p = satir.split(";");
                String dersAdi = p[0];
                int sure = Integer.parseInt(p[1]);
                double ucret = Double.parseDouble(p[2]);
                int ogretmenId = Integer.parseInt(p[3]);

                DerslerNode yeni = new DerslerNode(dersAdi, sure, ucret, ogretmenId);
                listeyeEkle(yeni);
            }

            System.out.println("Dersler dosyadan yüklendi.");

        } catch (Exception e) {
            System.out.println("Ders dosyası yok veya boş.");
        }
    }

    public static List<DerslerNode> getTumDersler() {

        List<DerslerNode> liste = new ArrayList<>();
        DerslerNode temp = head;

        while (temp != null) {
            liste.add(temp);
            temp = temp.next;
        }
        return liste;
    }
}
