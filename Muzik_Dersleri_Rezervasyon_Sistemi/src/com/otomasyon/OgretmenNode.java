package com.otomasyon;

public class OgretmenNode {
	public int id;
	public String ad;
	public String soyad;
	public String email;
	public String tckn;
	public String ceptel;
	public double maas;
	public OgretmenNode next;
	
	public OgretmenNode(int id, String ad, String soyad, String email, String tckn, String ceptel, double maas) {	 
		this.id = id;
		this.ad = ad;
		this.soyad = soyad;
		this.email = email;
		this.tckn = tckn;
		this.ceptel = ceptel;
		this.maas = maas;
		next = null;
	}
}