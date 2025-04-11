package it.epicode.postazioni_aziendali.prenotazioni;


import it.epicode.postazioni_aziendali.utenti.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {

    List<Prenotazione> findByUtente(Utente utente);
    List<Prenotazione> findByDataPrenotazione(LocalDate dataPrenotazione);
    List<Prenotazione> findByPostazione_IdAndDataPrenotazione(Long postazioneId, LocalDate dataPrenotazione);
    List<Prenotazione> findByUtenteAndDataPrenotazione(Utente utente, LocalDate dataPrenotazione);
    boolean existsByPostazione_IdAndDataPrenotazione(Long postazioneId, LocalDate dataPrenotazione);
}
