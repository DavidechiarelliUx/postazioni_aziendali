package it.epicode.postazioni_aziendali.prenotazioni;


import it.epicode.postazioni_aziendali.postazione.Postazione;
import it.epicode.postazioni_aziendali.utenti.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    public List<Prenotazione> findByUtente(Utente utente){
        return prenotazioneRepository.findByUtente(utente);
    }

    public List<Prenotazione> findByDataPrenotazione(LocalDate dataPrenotazione){
        return prenotazioneRepository.findByDataPrenotazione(dataPrenotazione);
    }
    public List<Prenotazione> findByPostazione_IdAndDataPrenotazione(Long postazioneId, LocalDate dataPrenotazione){
        return prenotazioneRepository.findByPostazione_IdAndDataPrenotazione(postazioneId, dataPrenotazione);
    }
    public List<Prenotazione> findByUtenteAndDataPrenotazione(Utente utente, LocalDate dataPrenotazione){
        return prenotazioneRepository.findByUtenteAndDataPrenotazione(utente, dataPrenotazione);
    }
    public Prenotazione save(Prenotazione prenotazione){
        if (prenotazione.getDataPrenotazione() == null) {
            throw new IllegalArgumentException("La data di prenotazione non può essere null");
        }
        if (prenotazione.getUtente() == null) {
            throw new IllegalArgumentException("L'utente di prenotazione non può essere null");
        }
        if (prenotazione.getPostazione() == null) {
            throw new IllegalArgumentException("La postazione di prenotazione non può essere null");
        }
        return prenotazioneRepository.save(prenotazione);
    }


    public Prenotazione creaPrenotazione(Utente utente, Postazione postazione, LocalDate dataPrenotazione) {
        // postazione è occupata
        if (prenotazioneRepository.existsByPostazione_IdAndDataPrenotazione(postazione.getId(), dataPrenotazione)) {
            throw new IllegalArgumentException("La postazione è già prenotata per questa data");
        }

        // l'utente ha già prenotato per quel giorno
        if (!prenotazioneRepository.findByUtenteAndDataPrenotazione(utente, dataPrenotazione).isEmpty()) {
            throw new IllegalArgumentException("L'utente ha già una prenotazione per questa data");
        }

        Prenotazione nuova = new Prenotazione();
        nuova.setUtente(utente);
        nuova.setPostazione(postazione);
        nuova.setDataPrenotazione(dataPrenotazione);
        nuova.setPrenotato(true);

        System.out.println("prenotazione " + nuova + " prenotata");

        return prenotazioneRepository.save(nuova);
    }

    


}
