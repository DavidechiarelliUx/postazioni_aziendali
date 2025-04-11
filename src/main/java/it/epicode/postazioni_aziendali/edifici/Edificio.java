package it.epicode.postazioni_aziendali.edifici;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "edifici")

public class Edificio {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "nome",length = 100,nullable = false)
    private String nome;
    @Column(name = "indirizzo", length = 100, nullable = false)
    private String indirizzo;
    @Column(name = "citta", length = 50, nullable = false)
    private String citta;


}
