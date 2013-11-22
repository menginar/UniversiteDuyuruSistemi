package com.dCandan.DuyuruSistemi.ServletController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.dCandan.DuyuruSistemi.DatabaseController.database_genelYoneticiController;
import com.dCandan.DuyuruSistemi.Model.tduyuru_bilgi;
import com.dCandan.DuyuruSistemi.Model.tduyuru_icerik;
import com.dCandan.DuyuruSistemi.Model.treklam;

public class VeriTemizligiYap {
	database_genelYoneticiController controller = new database_genelYoneticiController();

	public VeriTemizligiYap() {
		DuyuruKontrol();
		ReklamKontrol();

		controller = null;
	}

	public void DuyuruKontrol(){
		ArrayList<tduyuru_icerik> Duyurular = controller.DuyuruIcerikListele();
		ArrayList<tduyuru_icerik> SilinecekDuyurular = new ArrayList<>();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Date BugununTarihi = null;
		try {
			BugununTarihi = format.parse(format.format(new Date()));
		} catch (ParseException e1) {
			System.out.println("Parse Exception");
		}
		Date KayitliTarih;

		long BugununTarihi_milisecond = BugununTarihi.getTime();
		long KayitliTarih_milisecond;

		for (tduyuru_icerik DuyuruIcerik : Duyurular){
			String DuyuruBitis = DuyuruIcerik.getDuyuruBitisTarih();
			try {
				KayitliTarih = format.parse(DuyuruBitis);
				KayitliTarih_milisecond = KayitliTarih.getTime();

				long zamanFarki = BugununTarihi_milisecond - KayitliTarih_milisecond;
				if (zamanFarki > 0)
					SilinecekDuyurular.add(DuyuruIcerik);
			} catch (ParseException e) {
				System.out.println("Parse Exception 2");
			}
		}
		tduyuru_bilgi DuyuruBilgi;
		for (tduyuru_icerik Duyuru : SilinecekDuyurular)
		{
			DuyuruBilgi = new tduyuru_bilgi(Duyuru.getDuyuruID(), 0, 0, 0);

			if (controller.DuyuruSil(DuyuruBilgi, "tduyuru_bilgi", null))
				controller.DuyuruSil(null, "tduyuru_icerik", Duyuru);
		}
		DuyuruBilgi = null;
	}

	public void ReklamKontrol(){
		ArrayList<treklam> Reklamlar = controller.ReklamListele();
		ArrayList<treklam> SilinecekReklamlar = new ArrayList<>();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Date BugununTarihi = null;
		try {
			BugununTarihi = format.parse(format.format(new Date()));
		} catch (ParseException e1) {
			System.out.println("Parse Exception");
		}
		Date KayitliTarih;

		long BugununTarihi_milisecond = BugununTarihi.getTime();
		long KayitliTarih_milisecond;

		for (treklam Reklam : Reklamlar){
			String ReklamBitis = Reklam.getReklamBitisTarih();
			try {
				KayitliTarih = format.parse(ReklamBitis);
				KayitliTarih_milisecond = KayitliTarih.getTime();

				long zamanFarki = BugununTarihi_milisecond - KayitliTarih_milisecond;
				if (zamanFarki > 0)
					SilinecekReklamlar.add(Reklam);
			} catch (ParseException e) {
				System.out.println("Parse Exception 2");
			}
		}
		for (treklam Reklam : SilinecekReklamlar)
			controller.ReklamSil(Reklam);
	}
}