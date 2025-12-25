package com.otomasyon;

public class DerslerNode {

    public String dersAdi;
    public int sure;
    public double ucret;
    public int ogretmenId;
    public DerslerNode next;

    public DerslerNode(String dersAdi, int sure, double ucret, int ogretmenId) {
        this.dersAdi = dersAdi;
        this.sure = sure;
        this.ucret = ucret;
        this.ogretmenId = ogretmenId;
        this.next = null;
    }
}
