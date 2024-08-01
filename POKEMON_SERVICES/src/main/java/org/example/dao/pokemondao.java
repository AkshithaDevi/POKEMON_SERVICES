package org.example.dao;

import org.example.core.trainer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class pokemondao {

    private final SessionFactory sessionFactory;

    @Inject
    public pokemondao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<trainer> getAllTrainersByPokeIndex(int pokeIndex) {
        try (Session session = sessionFactory.openSession()) {
            // HQL query to fetch trainers who have at least one Pokémon with the specified pokeindex
            String hql = "SELECT DISTINCT t FROM trainer t JOIN t.pokemons p WHERE p.pokeindex = :pokeIndex";
            Query<trainer> query = session.createQuery(hql, trainer.class);
            query.setParameter("pokeIndex", pokeIndex);

            // Execute the query and return the result list
            return query.list();
        }
    }

    public trainer GetTrainer(int pid) {
        try (Session session = sessionFactory.openSession()){
            String hql = "SELECT t from trainer t JOIN t.pokemons p WHERE p.id = :pid";
            Query<trainer> query = session.createQuery(hql, trainer.class);
            query.setParameter("pid", pid);
            return query.uniqueResult();
        }
    }

}
