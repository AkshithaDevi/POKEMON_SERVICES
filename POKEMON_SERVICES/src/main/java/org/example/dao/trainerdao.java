package org.example.dao;

import java.time.LocalDateTime;
import org.example.core.pokemon;
import org.example.core.trainer;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.inject.Inject;
import java.util.List;

public class trainerdao {
    private final SessionFactory sessionFactory;

    @Inject
    public trainerdao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void AddTrainer(trainer trainer) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            trainer.setDateAdded(LocalDateTime.now());
            session.save(trainer);
            tx.commit();
        }
    }
    public void DeleteTrainer(trainer id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
                String hql = "SELECT t FROM trainer t WHERE t.id = :trainerId";
                Query<trainer> query = session.createQuery(hql, trainer.class);
                query.setParameter("trainerId", id);
                trainer trainer = query.uniqueResult();
            session.delete(trainer);
            tx.commit();
        }
    }
    public void UpdateTrainer(trainer trainer) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(trainer);
            tx.commit();
        }
    }

    public List<trainer> getAllTrainers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from trainer", trainer.class).list();
        }
    }


    public trainer GetTrainerByTrainerid(int trainerId) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT t FROM trainer t WHERE t.id = :trainerId";
            Query<trainer> query = session.createQuery(hql, trainer.class);
            query.setParameter("trainerId", trainerId);
            return query.uniqueResult();
        }
    }

    //get all pokemons by trainerid
    public List<pokemon> GetAllPokemons(int trainerId) {
        try (Session session = sessionFactory.openSession()) {
            // HQL query to fetch Pokémon by trainer ID
            String hql = "SELECT p FROM trainer t JOIN t.pokemons p WHERE t.id = :trainerId";
            Query<pokemon> query = session.createQuery(hql, pokemon.class);
            query.setParameter("trainerId", trainerId);

            List<pokemon> pokemons = query.list();

            // Eagerly load the collections if needed
            for (pokemon pokemon : pokemons) {
                Hibernate.initialize(pokemon.getPtype());
                Hibernate.initialize(pokemon.getGender());
            }

            return pokemons;
        }
    }

    public pokemon GetPokemonByTraineridPokemonid(int trainerId, int pokemonId) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT p FROM trainer t JOIN t.pokemons p WHERE t.id = :trainerId AND p.id = :pokemonId";
            Query<pokemon> query = session.createQuery(hql, pokemon.class);
            query.setParameter("trainerId", trainerId);
            query.setParameter("pokemonId", pokemonId);

            // Fetch the single result (if found) or null if not found
            return query.uniqueResult();
        }
    }

    public void AddPokemonToTrainer(int trainerId, pokemon pokemon) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            trainer trainer = session.get(trainer.class, trainerId);
            if (trainer != null) {
                trainer.getPokemons().add(pokemon);
                pokemon.setTrainer(trainer); // Assuming a bi-directional relationship
                session.saveOrUpdate(pokemon);
                session.update(trainer);
            }
            tx.commit();
        }

    }

    public void DeletePokemonFromTrainer(int trainerId, pokemon pokemon) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            trainer trainer = session.get(trainer.class, trainerId);
            if (trainer != null && trainer.getPokemons().contains(pokemon)) {
                trainer.getPokemons().remove(pokemon);
                session.saveOrUpdate(trainer);
                session.update(trainer);
            }
            tx.commit();
        }
    }

}
