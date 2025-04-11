package it.epicode.postazioni_aziendali.utenti;


import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepository extends JpaRepository<Utente, Long> {
    Utente findByUsername(String username);
    Utente findByEmail(String email);
}
