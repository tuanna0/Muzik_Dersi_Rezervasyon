package com.otomasyon;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;


public class OgretmenListe {
	public static OgretmenNode head = null;
	public static OgretmenNode tail = null;
	public static OgretmenNode temp = null;
	public static OgretmenNode temp2 = null;
	public static int idSayac = 1;
	
	public static OgretmenListe INSTANCE = new OgretmenListe();

	
	int id;
	String ad;
	String soyad;
	String email;
	String tckn;
	String ceptel;
	double maas;
	
	Scanner scanner = new Scanner(System.in);
	
	void ogretmenEkle() {
		System.out.println("Öğretmen bilgilerini giriniz:");
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
			System.out.println("Maaş: "); maas = scanner.nextDouble();
			if (maas == 0) {
	            System.out.println("Hata: Seviye boş bırakılamaz!");
	            continue;
	        }
	        break;
		}
		int id = idSayac++;
		
		OgretmenNode eleman = new OgretmenNode(id, ad, soyad, email, tckn, ceptel, maas);
		
		if (head == null) {
			head = eleman;
			tail = eleman;
			System.out.println("İlk öğretmen başarıyla kayıt olundu. ID: " + id);
		}
		else {
			eleman.next = head;
			head = eleman;
			System.out.println("Öğretmen kayıt oldu. ID: " + id);
		}
	}
	
	void ogretmenSil() {
		if (head == null) {
			System.out.println("Kayıtlı öğretmen yok. Silme işlemi başarısız.");
		}
		else {
			System.out.print("Silmek istediğiniz öğretmenin ID'sini giriniz: ");
			int silId = scanner.nextInt();
			scanner.nextLine();
			
			if (head.next == null && head.id == silId) {
				head = null;
				tail = null;
				System.out.println(head.id + " numaralı ID'ye sahip öğretmen silindi.");
			}
			else if (head.next != null && head.id == silId) {
				head = head.next;
				System.out.println(head.id + " numaralı ID'ye sahip öğretmen silindi.");
			}
			else {
				temp = head;
				temp2 = head;
				while (temp.next!=null) {
					if (silId == temp.id) {
						System.out.println(id + " numaralı ID'ye sahip öğretmen silindi.");
					}
					temp2 = temp;
					temp = temp.next;
					if (silId == temp.id) {
						temp2.next = null;
						tail = temp2;
						System.out.println(id + " numaralı ID'ye sahip öğretmen silindi.");
					}
				}
			}
		}
	}
	
	void ogretmenYazdir() {
		if (head == null) {
			System.out.println("Sistemde kayıtlı öğretmen bulunamadı.");
		}
		temp = head;
		while(temp != null) {
			System.out.println(temp.id + " ID'li öğretmenin bilgileri:");
			System.out.println("Ad               : " + temp.ad);
			System.out.println("Soyad            : " + temp.soyad);
			System.out.println("Email            : " + temp.email);
			System.out.println("TC               : " + temp.tckn);
			System.out.println("Telefon Numarası : " + temp.ceptel);
			System.out.println("Maaş             : " + temp.maas);

			temp = temp.next;
		}
	}
	
	public boolean ogretmenEkleGUI(String ad, String soyad, String email,
            String tckn, String ceptel, double maas) {

		if (ad.equals("") || soyad.equals("") || email.equals("") ||
				tckn.equals("") || ceptel.equals("")) {
			return false;
		}

		if (ad.length() > 20 || soyad.length() > 20)
			return false;

		if (!ad.matches("[a-zA-ZçÇğĞıİöÖşŞüÜ ]+") ||
				!soyad.matches("[a-zA-ZçÇğĞıİöÖşŞüÜ ]+"))
			return false;

		if (!tckn.matches("\\d{11}"))
			return false;

		if (!ceptel.matches("5\\d{9}"))
			return false;

		if (email.length() > 30)
            return false;

        if (!email.matches("^[a-zA-Z0-9._%+-]+@(gmail|hotmail|outlook)\\.com$"))
            return false;

		if (maas <= 0)
			return false;
		
		if (maas > 1000000)
            return false;
		
		int id = idSayac++;
		OgretmenNode eleman = new OgretmenNode(id, ad, soyad, email, tckn, ceptel, maas);

		if (head == null) {
			head = eleman;
			tail = eleman;
		} else {
			eleman.next = head;
			head = eleman;
		}
		ogretmeniDosyayaEkle(id, ad, soyad, email, tckn, ceptel, maas);


	return true;
	}
	
	public boolean ogretmenSilGUI(int silId) {

	    if (head == null)
	        return false;

	    if (head.next == null && head.id == silId) {
	        head = null;
	        tail = null;
	        ogretmenleriDosyayaYenidenYaz();
	        return true;
	    }

	    if (head.id == silId) {
	        head = head.next;
	        ogretmenleriDosyayaYenidenYaz();
	        return true;
	    }

	    OgretmenNode temp = head;
	    OgretmenNode prev = null;

	    while (temp != null) {
	        if (temp.id == silId) {
	            prev.next = temp.next;

	            if (temp == tail)
	                tail = prev;

	            ogretmenleriDosyayaYenidenYaz();
	            return true;
	        }

	        prev = temp;
	        temp = temp.next;
	    }

	    return false;
	}

	
	private void ogretmeniDosyayaEkle(int id, String ad, String soyad, String email, String tckn, String tel, double maas) {
		try (FileWriter fw = new FileWriter("ogretmenler.txt", true)) {
			fw.write(id + ";" + ad + ";" + soyad + ";" + email + ";" +
					tckn + ";" + tel + ";" + maas + "\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void ogretmenleriDosyadanYukle() {
	    try (BufferedReader br = new BufferedReader(new FileReader("ogretmenler.txt"))) {
	        String satir;

	        while ((satir = br.readLine()) != null) {
	            String[] p = satir.split(";");

	            int id = Integer.parseInt(p[0]);
	            String ad = p[1];
	            String soyad = p[2];
	            String email = p[3];
	            String tckn = p[4];
	            String tel = p[5];
	            double maas = Double.parseDouble(p[6]);

	            OgretmenNode yeni = new OgretmenNode(id, ad, soyad, email, tckn, tel, maas);

	            if (head == null) {
	                head = yeni;
	                tail = yeni;
	            } else {
	                yeni.next = head;
	                head = yeni;
	            }

	            idSayac = Math.max(idSayac, id + 1);
	        }

	        System.out.println("Öğretmenler dosyadan yüklendi.");

	    } catch (Exception e) {
	        System.out.println("Öğretmen dosyası yok veya boş.");
	    }
	}
	
	private void ogretmenleriDosyayaYenidenYaz() {
	    try (FileWriter fw = new FileWriter("ogretmenler.txt", false)) {

	        OgretmenNode temp = head;

	        while (temp != null) {
	            fw.write(
	                temp.id + ";" +
	                temp.ad + ";" +
	                temp.soyad + ";" +
	                temp.email + ";" +
	                temp.tckn + ";" +
	                temp.ceptel + ";" +
	                temp.maas + "\n"
	            );
	            temp = temp.next;
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public OgretmenNode ogretmenBulAdaGore(String ad) {

	    if (head == null)
	        return null;

	    OgretmenNode temp = head;

	    while (temp != null) {
	        if (temp.ad.equalsIgnoreCase(ad.trim())) {
	            return temp;
	        }
	        temp = temp.next;
	    }

	    return null;
	}
}