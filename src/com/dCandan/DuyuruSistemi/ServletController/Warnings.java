package com.dCandan.DuyuruSistemi.ServletController;

public class Warnings {
	String [] Warnings = {
			"Kullanici Adi veya Parolanizi Hatali Girdiniz.",
			"Kullanici Adi veya Parolanizi Eksik Girdiniz.",
			"Gerekli Bilgileri Eksiksiz Girmeniz Gerekmektedir.",
			"Isleminiz Basarili Bir Sekilde Sonlandirilmistir.",
			"Isleminiz Basarisiz Sekilde Sonlandirildi.",
			"Giris Yaptiginiz Bilgiler Kayitli Olanlarla Uyusmuyor.",
			"Tekrar Deneyiniz.",
			"Duyuru Aciklamasi En Fazla 500 Karakter Olabilir.",
			"Fakulte Secimi Yapmadiniz.",
			"Reklam Secimi Yapmadiniz.",
			"Reklam Aciklamasi En Fazla 400 Karakter Olabilir.",
			"Genel Yonetici Onayini Bekleyiniz.",
			"Giris Yaptiginiz Bilgiler Birbirleriyle Uyusmuyor.",
			"Bu Islemi Yapabilmek Icin Giris Yapmalisiniz.",
			"Kendinizi Sildiniz."
	};

	public String getWarnings(int indexException){
		return Warnings[indexException];
	}
}

/*	
 * 	0)	Kullanýcý Adý Parola Yanlýþ Girildi
 * 	1)	Kullanýcý Adý Parola Eksik Girildi
 * 	2)	Malformed Data
 * 	3)	Basarili Islem
 * 	4)	Basarisiz Islem
 * 	5)	Kayitlilarla Uyumsuz Veri
 * 	6)	Tekrar Deneyiniz
 * 	7)	Duyuru Boyutu Asimi
 * 	8)	Fakulte Secimi Yapilmadi
 * 	9)	Reklam Secimi Yapilmadi
 * 	10)	Reklam Boyutu Asimi
 * 	11)	Genel Yonetici Onayi
 * 	12)	Birbirleriyle Uyumsuz Veri
 * 	13)	Hack Engeli
 * 	14) Genel Yonetici Kendini Sildi.	
 */