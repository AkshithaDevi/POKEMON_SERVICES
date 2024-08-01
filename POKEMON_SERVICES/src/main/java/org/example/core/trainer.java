package org.example.core;

import javax.persistence.*;
import java.util.List;

import java.time.LocalDateTime;

@Entity
@Table(name = "trainer")
public class trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int trainerID;

    @Column(name = "name", nullable = false, length = 50)
    private String trainerName;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "date_added")
    private LocalDateTime dateAdded;

    @OneToMany(mappedBy = "Trainer")
    private List<pokemon> pokemons;

    public trainer(int trainerID, String trainerName, int age, List<pokemon> pokemons) {
        this.trainerID = trainerID;
        this.trainerName = trainerName;
        this.age = age;
        this.pokemons = pokemons;
    }

    public trainer() {
    }

    public int getTrainerID() {
        return trainerID;
    }

    public void setTrainerID(int trainerID) {
        this.trainerID = trainerID;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    @Override
    public String toString() {
        return "trainer{" +
                "trainerID=" + trainerID +
                ", trainerName='" + trainerName + '\'' +
                ", age=" + age +
                '}';
    }

    public void setName(String name) {
        this.trainerName = name;
    }

    public void setDateAdded(LocalDateTime now) {
        this.dateAdded = now;
    }
}
