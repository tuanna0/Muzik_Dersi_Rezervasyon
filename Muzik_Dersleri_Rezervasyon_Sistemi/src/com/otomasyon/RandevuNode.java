package com.otomasyon;

public class RandevuNode {

    public String ogrenciAdSoyad;
    public String ogretmenAdSoyad;
    public String dersAdi;
    public String tarih;
    public String saat;

    public RandevuNode next;

    public RandevuNode(String ogrenciAdSoyad,
                       String ogretmenAdSoyad,
                       String dersAdi,
                       String tarih,
                       String saat) {

        this.ogrenciAdSoyad = ogrenciAdSoyad;
        this.ogretmenAdSoyad = ogretmenAdSoyad;
        this.dersAdi = dersAdi;
        this.tarih = tarih;
        this.saat = saat;
        this.next = null;
    }

    @Override
    public String toString() {
        return "Öğrenci : " + ogrenciAdSoyad + "\n" +
               "Öğretmen: " + ogretmenAdSoyad + "\n" +
               "Ders    : " + dersAdi + "\n" +
               "Tarih   : " + tarih + "\n" +
               "Saat    : " + saat + "\n" ;
    }
}
