package epicode.it.dao;

import epicode.it.entity.Evento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class EventoDAO {
    private EntityManager em;

    public EventoDAO(EntityManager em) {
        this.em = em;
    }

    public void saveEvento(Evento evento) {
        this.em.getTransaction().begin();
        this.em.persist(evento);
        this.em.getTransaction().commit();
    }

    public void saveEventi(List<Evento> eventi) {
        this.em.getTransaction().begin();
        eventi.forEach(e -> {
            this.em.persist(e);
        });
        this.em.getTransaction().commit();
    }

    public Evento getEventoById(Long id) {
        return this.em.find(Evento.class, id);
    }

    public void updateAutore(Evento evento) {
        this.em.getTransaction().begin();
        this.em.merge(evento);
        this.em.getTransaction().commit();
    }

    public void updateAutore(Long id, Evento evento) {
        Evento found = this.em.find(Evento.class, id);
        found.setTitolo(evento.getTitolo());
        found.setDataEvento(evento.getDataEvento());
        found.setDescrizione(evento.getDescrizione());
        found.setTipoEvento(evento.getTipoEvento());
        found.setNumeroMaxPartecipanti(evento.getNumeroMaxPartecipanti());
        this.em.getTransaction().begin();
        this.em.merge(found);
        this.em.getTransaction().commit();

    }

    public Evento deleteEvento(Long id) {
        Evento found = getEventoById(id);
        this.em.getTransaction().begin();
        this.em.remove(found);
        System.out.println("Evento removed: " + found);
        this.em.getTransaction().commit();
        return found;
    }

    public void deleteEvento(Evento evento) {
        this.em.getTransaction().begin();
        this.em.remove(evento);
        this.em.getTransaction().commit();
    }

}
