package it.epicode.postazioni_aziendali.postazione;


import it.epicode.postazioni_aziendali.edifici.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostazioneRepository extends JpaRepository<Postazione, Long> {

    Postazione findByDescrizione(String descrizione);

    List<Postazione> findByTipo(Tipo tipo);

    List<Postazione> findByEdificio_Citta(String citta);

    List<Postazione> findByTipoAndEdificio_Citta(Tipo tipo, String citta);
}
