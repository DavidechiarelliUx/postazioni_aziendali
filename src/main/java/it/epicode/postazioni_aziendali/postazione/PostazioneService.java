package it.epicode.postazioni_aziendali.postazione;

import it.epicode.postazioni_aziendali.edifici.EdificioRepository;
import it.epicode.postazioni_aziendali.prenotazioni.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostazioneService {

    @Autowired
    private PostazioneRepository postazioneRepository;

    @Autowired
    private EdificioRepository edificioRepository;

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    public List<Postazione> findByTipo(Tipo tipo) {
        return postazioneRepository.findByTipo(tipo);
    }

    public List<Postazione> findByEdificio_Citta(String citta) {
        return postazioneRepository.findByEdificio_Citta(citta);
    }

    public List<Postazione> findByTipoAndEdificio_Citta(Tipo tipo, String citta) {
        return postazioneRepository.findByTipoAndEdificio_Citta(tipo, citta);
    }

    public Postazione findByDescrizione(String descrizione) {
        return postazioneRepository.findByDescrizione(descrizione);
    }

    public Postazione save(Postazione postazione) {
        if (postazione.getDescrizione() == null) {
            throw new IllegalArgumentException("La descrizione di una postazione non può essere null");
        }
        return postazioneRepository.save(postazione);
    }


}