package it.epicode.postazioni_aziendali.utenti;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    public Utente findByUsername(String username) {
        return utenteRepository.findByUsername(username);
    }
    public Utente findByEmail(String email) {
        return utenteRepository.findByEmail(email);
    }
    public Utente save(Utente utente) {
        if (utente.getUsername() == null) {
            throw new IllegalArgumentException("L'username dell'utente non può essere null");
        }
        if (utente.getEmail() == null) {
            throw new IllegalArgumentException("L'email dell'utente non può essere null");
        }
        if (utente.getNomeCompleto() == null) {
            throw new IllegalArgumentException("Il nome dell'utente non può essere null");
        }
        return utenteRepository.save(utente);
    }
}
