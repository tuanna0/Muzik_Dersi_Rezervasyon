package com.otomasyon;

public class OgrenciNode {
	public int id;
	public String ad;
	public String soyad;
	public String email;
	public String tckn;
	public String ceptel;
	public String seviye;
	public OgrenciNode next;
	
	public OgrenciNode(int id, String ad, String soyad, String email, String tckn, String ceptel, String seviye) {
		this.id = id;
		this.ad = ad;
		this.soyad = soyad;
		this.email = email;
		this.tckn = tckn;
		this.ceptel = ceptel;
		this.seviye = seviye;
		next = null;
	}
}
