package epicode.it.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "eventi")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name="titolo", nullable = false, length = 50)
    private String titolo;

    @Column(name="data_evento", nullable = false)
    private Date dataEvento;

    @Column(name="descrizione", nullable = false)
    private String descrizione;

    @Column(name="tipe_evento", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoEvento tipoEvento;

    @Column(name="num_max_partecipanti", nullable = false)
    private Integer numeroMaxPartecipanti;



    @Override
    public String toString() {
        return "Evento{" +
                "id=" + id +
                ", titolo='" + titolo + '\'' +
                ", dataEvento=" + dataEvento +
                ", descrizione='" + descrizione + '\'' +
                ", tipoEvento=" + tipoEvento +
                ", numeroMaxPartecipanti=" + numeroMaxPartecipanti +
                '}';
    }
}
