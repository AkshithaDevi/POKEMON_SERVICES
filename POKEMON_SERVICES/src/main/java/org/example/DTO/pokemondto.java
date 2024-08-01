package org.example.DTO;
import org.example.core.pokemontype;
import org.example.core.gender;
import org.example.core.trainer;
import org.example.core.pokemon;

import java.time.LocalDateTime;

public class pokemondto {

    private int id;
    private String name;
    private int level;
    private String nickname;
    private pokemontype ptype;
    private gender gender;
    private LocalDateTime date;
    private int pokeindex;
    private trainer Trainer;

   public pokemondto(pokemon pokemon) {
       this.id = pokemon.getId();
       this.name = pokemon.getName();
       this.level = pokemon.getLevel();
       this.nickname = pokemon.getNickname();
       this.ptype = pokemon.getPtype();
       this.gender = pokemon.getGender();
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
       return gender;
   }
   public void setGender(gender gender) {
       this.gender = gender;
   }
   public LocalDateTime getDate() {
       return date;
   }
   public void setDate(LocalDateTime date) {
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
   public void setTrainer(trainer trainer) {
       Trainer = trainer;
   }

}
