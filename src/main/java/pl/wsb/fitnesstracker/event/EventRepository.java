package pl.wsb.fitnesstracker.event;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class EventRepository {

    @PersistenceContext private EntityManager em;

    public Event findEventById(int id) {
       return em.createQuery("select e from Event e where e.id = :id", Event.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public Event findEventByName(String name) {
        return em.createQuery("select e from Event e where e.name = :name", Event.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
