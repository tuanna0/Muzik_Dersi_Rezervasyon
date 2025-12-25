package com.otomasyon;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class OgrenciListe {

    public static OgrenciNode head = null;
    public static OgrenciNode tail = null;
    public static OgrenciNode temp = null;
    public static OgrenciNode temp2 = null;
    public static int idSayac = 1;

    public static OgrenciListe INSTANCE = new OgrenciListe();

    int id;
    String ad;
    String soyad;
    String email;
    String tckn;
    String ceptel;
    String seviye;

    Scanner scanner = new Scanner(System.in);

    void ogrenciEkle() {
        System.out.println("Öğrenci bilgilerini giriniz:");
        while (true) {
            System.out.print("İsim: "); ad = scanner.nextLine();
            if (ad.equals("")) {
                System.out.println("Hata: Ad boş bırakılamaz!");
                continue;
            }
            break;
        }
        while (true) {
            System.out.print("Soyisim: "); soyad = scanner.nextLine();
            if (soyad.equals("")) {
                System.out.println("Hata: Soyad boş bırakılamaz!");
                continue;
            }
            break;
        }
        while (true) {
            System.out.print("Email: "); email = scanner.nextLine();
            if (email.equals("")) {
                System.out.println("Hata: Email boş bırakılamaz!");
                continue;
            }
            break;
        }
        while (true) {
            System.out.print("TCKN: "); tckn = scanner.nextLine();
            if (tckn.equals("")) {
                System.out.println("Hata: TCKN boş bırakılamaz!");
                continue;
            }
            if (tckn.length() != 11) {
                System.out.println("Hata: TCKN 11 haneli olmalı!");
                continue;
            }
            boolean rakamMi = true;
            for (int i = 0; i < tckn.length(); i++) {
                if (!Character.isDigit(tckn.charAt(i))) {
                    rakamMi = false;
                    break;
                }
            }
            if (!rakamMi) {
                System.out.println("Hata: TCKN yalnızca rakam olabilir!");
                continue;
            }
            break;
        }
        while (true) {
            System.out.print("Telefon numaranız (5 ile başlayınız): "); ceptel = scanner.nextLine();
            if (ceptel.equals("")) {
                System.out.println("Hata: Telefon boş bırakılamaz!");
                continue;
            }
            if (ceptel.length() != 10) {
                System.out.println("Hata: Telefon 10 haneli olmalı!");
                continue;
            }
            boolean rakamMi = true;
            for (int i = 0; i < ceptel.length(); i++) {
                if (!Character.isDigit(ceptel.charAt(i))) {
                    rakamMi = false;
                    break;
                }
            }
            if (!rakamMi) {
                System.out.println("Hata: Telefon sadece rakamlardan oluşmalı!");
                continue;
            }
            break;
        }
        while (true) {
            System.out.println("Seviyeniz: "); seviye = scanner.nextLine();
            if (seviye.equals("")) {
                System.out.println("Hata: Seviye boş bırakılamaz!");
                continue;
            }
            break;
        }

        int id = idSayac++;
        OgrenciNode eleman = new OgrenciNode(id, ad, soyad, email, tckn, ceptel, seviye);

        if (head == null) {
            head = eleman;
            tail = eleman;
            System.out.println("İlk öğrenci başarıyla kayıt olundu. ID: " + id);
        } else {
            eleman.next = head;
            head = eleman;
            System.out.println("Öğrenci kayıt oldu. ID: " + id);
        }

        ogrenciyiDosyayaEkle(id, ad, soyad, email, tckn, ceptel, seviye);
    }

    void ogrenciSil() {
        if (head == null) {
            System.out.println("Kayıtlı öğrenci yok. Silme işlemi başarısız.");
        } else {
            System.out.print("Silmek istediğiniz öğrencinin ID'sini giriniz: ");
            int silId = scanner.nextInt();
            scanner.nextLine();

            boolean sonuc = ogrenciSilGUI(silId);
            if (sonuc) {
                System.out.println(silId + " numaralı ID'ye sahip öğrenci silindi.");
            } else {
                System.out.println("Bu ID ile öğrenci bulunamadı.");
            }
        }
    }

    void ogrenciYazdir() {
        if (head == null) {
            System.out.println("Sistemde kayıtlı öğrenci bulunamadı.");
            return;
        }
        temp = head;
        while (temp != null) {
            System.out.println(temp.id + " ID'li öğrencinin bilgileri:");
            System.out.println("Ad               : " + temp.ad);
            System.out.println("Soyad            : " + temp.soyad);
            System.out.println("Email            : " + temp.email);
            System.out.println("TC               : " + temp.tckn);
            System.out.println("Telefon Numarası : " + temp.ceptel);
            System.out.println("Seviyesi         : " + temp.seviye);
            temp = temp.next;
        }
    }

    public boolean ogrenciEkleGUI(String ad, String soyad, String email,
                                 String tckn, String ceptel, String seviye) {

        if (ad == null || soyad == null || email == null || tckn == null || ceptel == null || seviye == null)
            return false;

        ad = ad.trim();
        soyad = soyad.trim();
        email = email.trim();
        tckn = tckn.trim();
        ceptel = ceptel.trim();
        seviye = seviye.trim();

        if (ad.equals("") || soyad.equals("") || email.equals("") || tckn.equals("") || ceptel.equals("") || seviye.equals(""))
            return false;

        if (ad.length() > 20 || soyad.length() > 20)
            return false;

        if (!ad.matches("[a-zA-ZçÇğĞıİöÖşŞüÜ ]+") || !soyad.matches("[a-zA-ZçÇğĞıİöÖşŞüÜ ]+"))
            return false;

        if (!tckn.matches("\\d{11}"))
            return false;

        if (!ceptel.matches("5\\d{9}"))
            return false;

        if (email.length() > 30)
            return false;

        if (!email.matches("^[a-zA-Z0-9._%+-]+@(gmail|hotmail|outlook)\\.com$"))
            return false;

        int id = idSayac++;
        OgrenciNode yeni = new OgrenciNode(id, ad, soyad, email, tckn, ceptel, seviye);

        if (head == null) {
            head = yeni;
            tail = yeni;
        } else {
            yeni.next = head;
            head = yeni;
        }

        ogrenciyiDosyayaEkle(id, ad, soyad, email, tckn, ceptel, seviye);
        return true;
    }

    public boolean ogrenciSilGUI(int silId) {

        if (head == null)
            return false;

        if (head.id == silId) {
            head = head.next;
            if (head == null) {
                tail = null;
            }
            ogrencileriDosyayaYenidenYaz();
            return true;
        }

        OgrenciNode prev = head;
        OgrenciNode curr = head.next;

        while (curr != null) {
            if (curr.id == silId) {
                prev.next = curr.next;

                if (curr == tail) {
                    tail = prev;
                }

                ogrencileriDosyayaYenidenYaz();
                return true;
            }
            prev = curr;
            curr = curr.next;
        }

        return false;
    }
    
    public OgrenciNode ogrenciBulIdIle(int id) {
        OgrenciNode temp = head;
        while (temp != null) {
            if (temp.id == id)
                return temp;
            temp = temp.next;
        }
        return null;
    }


    public boolean ogrenciGuncelleGUI(int id, String ad, String soyad,
            String email, String tel, String seviye) {

    	OgrenciNode ogr = ogrenciBulIdIle(id);
    	if (ogr == null)
    		return false;

    	if (ad == null || soyad == null || email == null || tel == null || seviye == null)
    		return false;

    	ad = ad.trim();
    	soyad = soyad.trim();
    	email = email.trim();
    	tel = tel.trim();
    	seviye = seviye.trim();

    	if (ad.isEmpty() || soyad.isEmpty() || email.isEmpty() || tel.isEmpty() || seviye.isEmpty())
    		return false;

    	if (ad.length() > 20 || soyad.length() > 20)
    		return false;

    	if (!ad.matches("[a-zA-ZçÇğĞıİöÖşŞüÜ ]+") ||
    			!soyad.matches("[a-zA-ZçÇğĞıİöÖşŞüÜ ]+"))
    		return false;

    	if (!tel.matches("5\\d{9}"))
    		return false;

    	if (email.length() > 30)
    		return false;

    	if (!email.matches("^[a-zA-Z0-9._%+-]+@(gmail|hotmail|outlook)\\.com$"))
    		return false;

    	ogr.ad = ad;
    	ogr.soyad = soyad;
    	ogr.email = email;
    	ogr.ceptel = tel;
    	ogr.seviye = seviye;

    	return true;
    }



    public OgrenciNode ogrenciBulAdaGore(String ad) {
        if (ad == null) return null;
        String aranan = ad.trim();

        OgrenciNode temp = head;
        while (temp != null) {
            if (temp.ad != null && temp.ad.equalsIgnoreCase(aranan)) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    public void ogrencileriDosyadanYukle() {
        try (BufferedReader br = new BufferedReader(new FileReader("ogrenciler.txt"))) {
            String satir;

            while ((satir = br.readLine()) != null) {
                String[] p = satir.split(";");

                int id = Integer.parseInt(p[0]);
                String ad = p[1];
                String soyad = p[2];
                String email = p[3];
                String tckn = p[4];
                String tel = p[5];
                String seviye = p[6];

                OgrenciNode yeni = new OgrenciNode(id, ad, soyad, email, tckn, tel, seviye);

                if (head == null) {
                    head = yeni;
                    tail = yeni;
                } else {
                    yeni.next = head;
                    head = yeni;
                }

                idSayac = Math.max(idSayac, id + 1);
            }

            System.out.println("Öğrenciler dosyadan yüklendi.");

        } catch (Exception e) {
            System.out.println("Öğrenci dosyası bulunamadı veya boş.");
        }
    }

    private void ogrenciyiDosyayaEkle(int id, String ad, String soyad, String email,
                                     String tckn, String tel, String seviye) {
        try (FileWriter fw = new FileWriter("ogrenciler.txt", true)) {
            fw.write(id + ";" + ad + ";" + soyad + ";" + email + ";" +
                    tckn + ";" + tel + ";" + seviye + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ogrencileriDosyayaYenidenYaz() {
        try (FileWriter fw = new FileWriter("ogrenciler.txt", false)) {
            OgrenciNode temp = head;

            while (temp != null) {
                fw.write(
                        temp.id + ";" +
                        temp.ad + ";" +
                        temp.soyad + ";" +
                        temp.email + ";" +
                        temp.tckn + ";" +
                        temp.ceptel + ";" +
                        temp.seviye + "\n"
                );
                temp = temp.next;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
