package com.example.contestbackend;

import org.junit.jupiter.api.Test;


import java.nio.charset.Charset;


class ContestBackendApplicationTests {

	// Get the default character encoding
	public static void main(String[] args) {
		// Get the default character encoding
		System.out.println(System.getProperty("file.encoding"));
	}

	@Test
	public void cos() {
		String headers = "Numer RSPO;REGON;NIP;Typ;Nazwa;Kod terytorialny województwo;Kod terytorialny powiat;Kod terytorialny gmina;Kod terytorialny miejscowość;Kod terytorialny ulica;Województwo;Powiat;Gmina;Miejscowość;Rodzaj miejscowości;Ulica;Numer budynku;Numer lokalu;Kod pocztowy;Poczta;Telefon;Faks;E-mail;Strona www;Publiczność status;Kategoria uczniów;Specyfika placówki;Imię i nazwisko dyrektora;Data założenia;Data rozpoczęcia działalności;Data likwidacji;Typ organu prowadzącego;Nazwa organu prowadzącego;REGON organu prowadzącego;NIP organu prowadzącego;Województwo organu prowadzącego;Powiat organu prowadzącego;Gmina organu prowadzącego;Miejsce w strukturze;RSPO podmiotu nadrzędnego;Typ podmiotu nadrzędnego;Nazwa podmiotu nadrzędnego;Liczba uczniów;Tereny sportowe;Języki nauczane;Czy zatrudnia logopedę;Czy zatrudnia psychologa;Czy zatrudnia pedagoga;Oddziały podstawowe wg specyfiki;Oddziały dodatkowe";
		var headersArray = headers.split(";");
		for (int i = 0; i < headersArray.length; i++) {
			if(headersArray[i].toLowerCase().contains("miejscowość")) System.out.println(i+1);
		}

	}

}
