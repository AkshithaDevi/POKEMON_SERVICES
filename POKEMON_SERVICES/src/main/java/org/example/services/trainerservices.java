package org.example.services;

import org.example.core.pokemon;
import org.example.core.trainer;
import org.example.dao.trainerdao;

import javax.inject.Inject;
import java.util.List;


public class trainerservices {

    private final trainerdao trainerDAO;

    @Inject
    public trainerservices(trainerdao trainerDAO) {
        this.trainerDAO = trainerDAO;
    }
    public List<trainer> GetallTrainers(){
        return trainerDAO.getAllTrainers();
    }
    public void AddTrainer(trainer trainer){
        trainerDAO.AddTrainer(trainer);
    }
    public void DeleteTrainer(trainer trainer){
        trainerDAO.DeleteTrainer(trainer);
    }
    public void UpdateTrainer(trainer trainer){
        trainerDAO.UpdateTrainer(trainer);
    }
    public trainer GetTrainerByTrainerid(int id){
        return trainerDAO.GetTrainerByTrainerid(id);
    }
    public  List<pokemon> Getallpokemons(int id){
        return trainerDAO.GetAllPokemons(id);
    }
    public pokemon GetPokemonByTraineridPokemonid(int tid,int pid){
        return trainerDAO.GetPokemonByTraineridPokemonid(tid,pid);
    }

    public void AddPokemonToTrainer(int trainerID, pokemon pokemon){
        trainerDAO.AddPokemonToTrainer(trainerID,pokemon);
    }
    public void DeletePokemonFromTrainer(int trainerID, pokemon pokemonid){
        trainerDAO.DeletePokemonFromTrainer(trainerID,pokemonid);
    }


    public trainer getTrainerById(int id) {
      return trainerDAO.GetTrainerByTrainerid(id);
    }
}
