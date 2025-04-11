package it.epicode.postazioni_aziendali.edifici;


import org.springframework.data.jpa.repository.JpaRepository;

public interface EdificioRepository extends JpaRepository<Edificio, Long> {

    Edificio findByNome(String nome);
    Edificio findByCitta(String citta);
}
