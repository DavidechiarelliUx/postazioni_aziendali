package it.epicode.postazioni_aziendali.edifici;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EdificioService {

    @Autowired
    private EdificioRepository edificioRepository;

    public Edificio findByCitta(String citta) {
        return edificioRepository.findByCitta(citta);
    }

    public Edificio findByNome(String nome) {
        return edificioRepository.findByNome(nome);
    }

    public Edificio save(Edificio edificio) {
        if (edificio.getNome() == null) {
            throw new IllegalArgumentException("Il nome di un edificio non può essere null");
        }
        if (edificio.getCitta() == null) {
            throw new IllegalArgumentException("La città di un edificio non può essere null");
        }
        if (edificio.getIndirizzo() == null) {
            throw new IllegalArgumentException("L'indirizzo di un edificio non può essere null");
        }
        return edificioRepository.save(edificio);
    }
}
