package org.example.services;

import org.example.core.pokemon;
import org.example.core.trainer;
import org.example.dao.pokemondao;

import javax.inject.Inject;
import java.util.List;

public class pokemonservices {

    private final pokemondao pokemonDAO;

    @Inject
    public pokemonservices(pokemondao pokemonDAO) {
        this.pokemonDAO = pokemonDAO;
    }
    //find all trainers which have the pokemon with the pokeindex
    public List<trainer> GetAllTrainersByPokeIndex(int pokeid) {
        return pokemonDAO.getAllTrainersByPokeIndex(pokeid);
    }
    public trainer GetTrainer(int pid) {
        return pokemonDAO.GetTrainer(pid);
    }
    public void GetAllPokemonByPokeIndex(int pokeid) {
        return;
    }

    public List<pokemon> GetAllPokemons(int trainerId) {
        return (List<pokemon>) pokemonDAO.GetTrainer(trainerId);
    }
}
