package org.example.DTO;
import org.example.core.trainer;
import org.example.core.pokemon;
import org.example.core.pokemontype;
import org.example.core.gender;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class trainerdto {
    private int trainerid;
    private String trainername;
    private  int age;
    private LocalDateTime dateadded;
    private List<pokemon> pokemonlist;

    public trainerdto() {}
    public trainerdto(trainer trainer) {
        this.trainerid = trainer.getTrainerID();
        this.trainername = trainer.getTrainerName();
        this.age = trainer.getAge();
        this.dateadded = LocalDateTime.now();
        this.pokemonlist = new ArrayList<>();
    }
    public int getTrainerid() {
        return trainerid;
    }
    public void setTrainerid(int trainerid) {
        this.trainerid = trainerid;
    }
    public String getTrainername() {
        return trainername;
    }
    public void setTrainername(String trainername) {
        this.trainername = trainername;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public LocalDateTime getDateadded() {
        return dateadded;
    }
    public void setDateadded(LocalDateTime dateadded) {
        this.dateadded = dateadded;
    }
    public List<pokemon> getPokemonlist() {
        return pokemonlist;
    }
    public void setPokemonlist(List<pokemon> pokemonlist) {
        this.pokemonlist = pokemonlist;
    }

    @Override
    public String toString() {
        return "trainer{" +
                "trainerID=" + trainerid +
                ", trainerName='" + trainername + '\'' +
                ", age=" + age +
                '}';
    }
}
