package it.epicode.postazioni_aziendali.postazione;

import it.epicode.postazioni_aziendali.edifici.Edificio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "postazioni")

public class Postazione {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "descrizione",length = 100,nullable = false)
    private String descrizione;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo",nullable = false)
    private Tipo tipo;

    @Column(name = "numero_max_posti", nullable = false)
    private int numeroMaxPosti;

    @ManyToOne
    @JoinColumn(name = "edificio_id")
    private Edificio edificio;


}
