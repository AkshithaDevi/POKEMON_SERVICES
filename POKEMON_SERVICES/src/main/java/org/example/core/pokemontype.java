package org.example.core;

public enum pokemontype{
    WATER(1),FIRE(2),ELECTRIC(3),POISON(4);
    private int pokeindex;

    pokemontype(int pokeindex) {
        this.pokeindex = pokeindex;
    }

    public int getPokeindex(){
        return pokeindex;
    }
}

