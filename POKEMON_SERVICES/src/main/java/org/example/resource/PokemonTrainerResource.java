package org.example.resource;

import org.example.client.MainFunctionality;
import org.example.core.pokemon;
import org.example.core.trainer;
import org.example.DTO.pokemondto;
import org.example.DTO.trainerdto;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Path("/trainers")
public class PokemonTrainerResource {

    private final MainFunctionality mainFunctionality;

    public PokemonTrainerResource() {
        this.mainFunctionality = new MainFunctionality();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTrainers() {
        List<trainerdto> trainers = mainFunctionality.getAllTrainers();
        return Response.ok(trainers).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrainer(trainer newTrainer) {

        mainFunctionality.addTrainer(newTrainer);
        return Response.status(Response.Status.CREATED).entity(newTrainer).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTrainer(@PathParam("id") int id) {
        Optional<trainerdto> trainer = mainFunctionality.getTrainerById(id);
        if (trainer.isPresent()) {
            mainFunctionality.deleteTrainer(id);
            return Response.ok().entity("Trainer deleted: " + trainer).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Trainer not found with ID: " + id)
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTrainer(@PathParam("id") int id, trainer updatedTrainer) {

        mainFunctionality.updateTrainer(updatedTrainer);
        return Response.ok(updatedTrainer).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTrainerById(@PathParam("id") int id) {
        Optional<trainerdto> trainer = mainFunctionality.getTrainerById(id);
        if (trainer != null) {
            return Response.ok(trainer).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Trainer not found with ID: " + id)
                    .build();
        }
    }

    @GET
    @Path("/{trainerId}/pokemons")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPokemons(@PathParam("trainerId") int trainerId) {
        List<pokemondto> pokemons = mainFunctionality.getAllPokemons(trainerId);
        return Response.ok(pokemons).build();
    }

    @GET
    @Path("/{trainerId}/pokemons/{pokemonId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPokemonByTrainerIdAndPokemonId(@PathParam("trainerId") int trainerId,
                                                      @PathParam("pokemonId") int pokemonId) {
        Optional<pokemondto> pokemon = mainFunctionality.getPokemonByTrainerIdAndPokemonId(trainerId, pokemonId);
        if (pokemon != null) {
            return Response.ok(pokemon).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Pokemon not found with Trainer ID: " + trainerId + " and Pokemon ID: " + pokemonId)
                    .build();
        }
    }

    @POST
    @Path("/{trainerId}/pokemons")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPokemonToTrainer(@PathParam("trainerId") int trainerId, pokemon newPokemon) {
        mainFunctionality.addPokemonToTrainer(trainerId, newPokemon);
        return Response.status(Response.Status.CREATED).entity("Pokemon added to Trainer ID: " + trainerId).build();
    }

    @DELETE
    @Path("/{trainerId}/pokemons/{pokemonId}")
    public Response deletePokemonFromTrainer(@PathParam("trainerId") int trainerId,
                                             @PathParam("pokemonId") int pokemonId) {
        Optional<pokemondto> pokemon = mainFunctionality.getPokemonByTrainerIdAndPokemonId(trainerId, pokemonId);
        if (pokemon.isPresent()) {
            int id = pokemon.get().getId();
            mainFunctionality.deletePokemonFromTrainer(trainerId, id);
            return Response.ok().entity("Pokemon removed from Trainer ID: " + trainerId).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Pokemon not found with Trainer ID: " + trainerId + " and Pokemon ID: " + pokemonId)
                    .build();
        }
    }

    @GET
    @Path("/pokemons/{pokeIndex}/trainers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTrainersByPokeIndex(@PathParam("pokeIndex") int pokeIndex) {
        List<trainerdto> trainers = mainFunctionality.getAllTrainersByPokeIndex(pokeIndex);
        return Response.ok(trainers).build();
    }

    @GET
    @Path("/pokemons/{pokemonId}/trainer")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTrainerByPokemonId(@PathParam("pokemonId") int pokemonId) {
        Optional<trainerdto> trainer = Optional.ofNullable(mainFunctionality.getTrainerByPokemonId(pokemonId));
        if (trainer != null) {
            return Response.ok(trainer).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Trainer not found for Pokemon ID: " + pokemonId)
                    .build();
        }
    }
}