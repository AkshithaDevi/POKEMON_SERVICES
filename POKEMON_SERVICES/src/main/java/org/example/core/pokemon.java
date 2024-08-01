package org.example.core;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pokemon")
public class pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "level", nullable = false)
    private int level;

    @Column(name = "nickname", nullable = false, length = 50)
    private String nickname;

    @Column(name = "pokemontype", nullable = false)
    @Enumerated(EnumType.STRING)
    private pokemontype ptype;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private gender Gender;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "pokeindex", nullable = false)
    private int pokeindex;

    @ManyToOne
    @JoinColumn(name = "trainerID")
    private trainer Trainer;

    public pokemon(int id, String name, int level, String nickname, pokemontype ptype, gender Gender, Date date) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.nickname = nickname;
        this.ptype = ptype;
        this.Gender = Gender;
        this.date = date;
        this.pokeindex = ptype.getPokeindex();
    }

    public pokemon() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public pokemontype getPtype() {
        return ptype;
    }

    public void setPtype(pokemontype ptype) {
        this.ptype = ptype;
    }

    public gender getGender() {
        return Gender;
    }

    public void setGender(gender Gender) {
        this.Gender = Gender;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPokeindex() {
        return pokeindex;
    }

    public void setPokeindex(int pokeindex) {
        this.pokeindex = pokeindex;
    }

    public trainer getTrainer() {
        return Trainer;
    }

    public void setTrainer(trainer Trainer) {
        this.Trainer = Trainer;
    }

    @Override
    public String toString() {
        return "pokemon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", nickname='" + nickname + '\'' +
                ", ptype=" + ptype +
                ", Gender=" + Gender +
                ", date=" + date +
                ", pokeindex=" + pokeindex +
                '}';
    }


}
