package epicode.it;


import com.github.javafaker.Faker;
import epicode.it.dao.EventoDAO;
import epicode.it.entity.Evento;
import epicode.it.entity.TipoEvento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Application {

    public static void main(String[] args) {

        Faker faker = new Faker(new Locale("it"));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit-jpa");
        EntityManager em = emf.createEntityManager();
        EventoDAO managerEvento = new EventoDAO(em);

        List<Evento> eventi = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Evento newEvento = new Evento();
            newEvento.setTitolo(faker.lorem().fixedString(10));
            newEvento.setDataEvento(faker.date().future(30, TimeUnit.DAYS));
            newEvento.setDescrizione(faker.lorem().sentence());
            newEvento.setTipoEvento(faker.random().nextInt(1,2) == 1 ? TipoEvento.PUBBLICO : TipoEvento.PRIVATO);
            newEvento.setNumeroMaxPartecipanti(faker.random().nextInt(1,100));
            eventi.add(newEvento);
        }

        managerEvento.saveEventi(eventi);

        while(true) {
            Evento found =managerEvento.getEventoById(faker.random().nextInt(1,100).longValue());
            if(found != null) {
                System.out.println(found);
                break;
            }
        }

        while(true) {
            Evento found = managerEvento.deleteEvento(faker.random().nextInt(1,100).longValue());
            if(found != null) {
                break;
            }
        }

    }
}