package it.epicode.postazioni_aziendali.common;

import it.epicode.postazioni_aziendali.edifici.Edificio;
import it.epicode.postazioni_aziendali.edifici.EdificioRepository;
import it.epicode.postazioni_aziendali.edifici.EdificioService;
import it.epicode.postazioni_aziendali.postazione.Postazione;
import it.epicode.postazioni_aziendali.postazione.PostazioneRepository;
import it.epicode.postazioni_aziendali.postazione.PostazioneService;
import it.epicode.postazioni_aziendali.postazione.Tipo;
import it.epicode.postazioni_aziendali.prenotazioni.Prenotazione;
import it.epicode.postazioni_aziendali.prenotazioni.PrenotazioneRepository;
import it.epicode.postazioni_aziendali.prenotazioni.PrenotazioneService;
import it.epicode.postazioni_aziendali.utenti.Utente;
import it.epicode.postazioni_aziendali.utenti.UtenteRepository;
import it.epicode.postazioni_aziendali.utenti.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class CommonRunner implements CommandLineRunner {

    @Autowired
    private EdificioRepository edificioRepository;

    @Autowired
    private PostazioneRepository postazioneRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private PrenotazioneService prenotazioneService;

    @Autowired
    private EdificioService edificioService;

    @Autowired
    private PostazioneService postazioneService;

    @Override
    public void run(String... args) throws Exception {

        //creazione utenti
        Utente utente = new Utente();
        utente.setUsername("davide99");
        utente.setEmail("davide90@email.com");
        utente.setNomeCompleto("Davide chiarelli");

        Utente utente2 = new Utente();
        utente2.setUsername("marco01");
        utente2.setEmail("marco01@email.com");
        utente2.setNomeCompleto("Marco Rossi");

        Utente utente3 = new Utente();
        utente3.setUsername("luca02");
        utente3.setEmail("luca02@email.com");
        utente3.setNomeCompleto("Luca Verdi");

        //creazione edificio
        Edificio edificio = new Edificio();
        edificio.setNome("Palazzo");
        edificio.setCitta("Roma");
        edificio.setIndirizzo("Via Roma 1");

        Edificio edificio1 = new Edificio();
        edificio1.setNome("casa");
        edificio1.setCitta("Milano");
        edificio1.setIndirizzo("Via Milano 1");

        Edificio edificio2 = new Edificio();
        edificio2.setNome("Palazzo");
        edificio2.setCitta("Napoli");
        edificio2.setIndirizzo("Via Napoli 1");

        //creazione postazioni
        Postazione postazione = new Postazione();
        postazione.setDescrizione("Postazione 1");
        postazione.setTipo(Tipo.PRIVATO);
        postazione.setNumeroMaxPosti(5);
        postazione.setEdificio(edificio);


        postazione.setEdificio(edificioRepository.findByCitta("Roma"));

        Postazione postazione1 = new Postazione();
        postazione1.setDescrizione("Postazione 2");
        postazione1.setTipo(Tipo.OPENSPACE);
        postazione1.setNumeroMaxPosti(10);
        postazione1.setEdificio(edificio1);


        Postazione postazione2 = new Postazione();
        postazione2.setDescrizione("Postazione 3");
        postazione2.setTipo(Tipo.SALA_RIUNIONI);
        postazione2.setNumeroMaxPosti(15);
        postazione2.setEdificio(edificio2);

        //creazione prenotazione
        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setDataPrenotazione(LocalDate.of(2024, 12, 1));
        prenotazione.setPrenotato(true);
        prenotazione.setUtente(utente);
        prenotazione.setPostazione(postazione);

        Prenotazione prenotazione1= new Prenotazione();
        prenotazione1.setDataPrenotazione(LocalDate.of(2024, 12, 1));
        prenotazione1.setPrenotato(true);
        prenotazione1.setUtente(utente2);
        prenotazione1.setPostazione(postazione1);

        Prenotazione prenotazione2= new Prenotazione();
        prenotazione2.setDataPrenotazione(LocalDate.of(2024, 12, 1));
        prenotazione2.setPrenotato(true);
        prenotazione2.setUtente(utente3);
        prenotazione2.setPostazione(postazione2);

        edificioRepository.saveAll(List.of(edificio, edificio1, edificio2));
        postazioneRepository.saveAll(List.of(postazione, postazione1, postazione2));
        utenteRepository.saveAll(List.of(utente, utente2, utente3));
        prenotazioneRepository.saveAll(List.of(prenotazione, prenotazione1, prenotazione2));

        System.out.println("Database popolato con successo");

        prenotazioneService.creaPrenotazione(utente, postazione, LocalDate.of(2024, 01, 1));


        List<Postazione> postazioniRoma = postazioneService.findByTipoAndEdificio_Citta(Tipo.OPENSPACE, "Roma");
        System.out.println("Postazioni disponibili: " + postazioniRoma);

        if (postazioniRoma.isEmpty()) {
            System.out.println("Nessuna postazione trovata per il tipo OPENSPACE a Roma");
        } else {
            Postazione cercaPrimaPostazione = postazioniRoma.get(0);
            prenotazioneService.creaPrenotazione(utente, cercaPrimaPostazione, LocalDate.of(2024, 12, 2));
        }


        List<Postazione> postazioniNapoli = postazioneService.findByTipoAndEdificio_Citta(Tipo.SALA_RIUNIONI, "Napoli");
        System.out.println("Postazioni disponibili: " + postazioniNapoli);

        if (postazioniNapoli.isEmpty()) {
            System.out.println("Nessuna postazione trovata per il tipo OPENSPACE a Napoli");
        } else {
            System.out.println("prenotazione avvenuta con successo : ");
            Postazione cercaPrimaPostazione = postazioniNapoli.get(0);
            prenotazioneService.creaPrenotazione(utente, cercaPrimaPostazione, LocalDate.of(2024, 12, 2));
        }
    }
}
