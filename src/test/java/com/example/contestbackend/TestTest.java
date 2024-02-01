package com.example.contestbackend;

import com.example.contestbackend.service.RSPOService;
import org.junit.jupiter.api.Test;

public class TestTest {

    @Test
    public void cos() {
        String headers = "Numer RSPO;REGON;NIP;Typ;Nazwa;Kod terytorialny województwo;Kod terytorialny powiat;Kod terytorialny gmina;Kod terytorialny miejscowość;Kod terytorialny ulica;Województwo;Powiat;Gmina;Miejscowość;Rodzaj miejscowości;Ulica;Numer budynku;Numer lokalu;Kod pocztowy;Poczta;Telefon;Faks;E-mail;Strona www;Publiczność status;Kategoria uczniów;Specyfika placówki;Imię i nazwisko dyrektora;Data założenia;Data rozpoczęcia działalności;Data likwidacji;Typ organu prowadzącego;Nazwa organu prowadzącego;REGON organu prowadzącego;NIP organu prowadzącego;Województwo organu prowadzącego;Powiat organu prowadzącego;Gmina organu prowadzącego;Miejsce w strukturze;RSPO podmiotu nadrzędnego;Typ podmiotu nadrzędnego;Nazwa podmiotu nadrzędnego;Liczba uczniów;Tereny sportowe;Języki nauczane;Czy zatrudnia logopedę;Czy zatrudnia psychologa;Czy zatrudnia pedagoga;Oddziały podstawowe wg specyfiki;Oddziały dodatkowe";
        var headersArray = headers.split(";");
        for (int i = 0; i < headersArray.length; i++) {
            if(headersArray[i].toLowerCase().contains("dyrektora")) System.out.println("TUTAJ: "+i+1);
        }

    }

}

