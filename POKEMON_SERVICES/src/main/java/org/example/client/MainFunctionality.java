package org.example.client;

import org.example.core.pokemon;
import org.example.core.trainer;
import org.example.dao.pokemondao;
import org.example.dao.trainerdao;
import org.example.services.pokemonservices;
import org.example.services.trainerservices;
import org.example.DTO.pokemondto;
import org.example.DTO.trainerdto;

import org.example.HibernateUtil;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MainFunctionality {
    private static pokemonservices pokemonServices;
    private static trainerservices trainerServices;

    public MainFunctionality() {
        initialiseSessionFactory();
    }

    private void initialiseSessionFactory() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        pokemondao pokemonDAO = new pokemondao(sessionFactory);
        trainerdao trainerDAO = new trainerdao(sessionFactory);

        pokemonServices = new pokemonservices(pokemonDAO);
        trainerServices = new trainerservices(trainerDAO);
    }

    public List<trainerdto> getAllTrainers() {
        List<trainer> trainers = trainerServices.GetallTrainers();
        List<trainerdto> trainerDtos = (List<trainerdto>) trainers.stream();
        trainers.forEach(System.out::println);
        return trainerDtos;
    }

    public void addTrainer(trainer newTrainer) {
        trainerServices.AddTrainer(newTrainer);
        System.out.println("Trainer added: " + newTrainer);
    }

    public void deleteTrainer(int id) {
        trainer trainer = trainerServices.GetTrainerByTrainerid(id);
        if (trainer != null) {
            trainerServices.DeleteTrainer(trainer);
            System.out.println("Trainer deleted: " + trainer);
        } else {
            System.out.println("Trainer not found with ID: " + id);
        }
    }

    public void updateTrainer(trainer trainer) {
        if (trainer != null) {
            trainerServices.UpdateTrainer(trainer);
            System.out.println("Trainer updated: " + trainer);
        } else {
            System.out.println("Trainer not found with ID: ");
        }
    }

    public Optional<trainerdto> getTrainerById(int id) {
        Optional<trainer> trainer = Optional.ofNullable(trainerServices.GetTrainerByTrainerid(id));
        return Optional.ofNullable(trainer.map(t -> {
            trainerdto trainerDTO = new trainerdto(t);
            System.out.println(trainerDTO);
            return trainerDTO;
        }).orElseGet(() -> {
            System.out.println("Trainer not found with ID: " + id);
            return null;
        }));
    }

    public List<pokemondto> getAllPokemons(int trainerId) {
        List<pokemon> pokemons = trainerServices.Getallpokemons(trainerId);
        List<pokemondto> pokemondtos = (List<pokemondto>) pokemons.stream();
        pokemons.forEach(System.out::println);
        return pokemondtos;
    }

    public Optional<pokemondto> getPokemonByTrainerIdAndPokemonId(int trainerId, int pokemonId) {
        Optional<pokemon> pokemon = Optional.ofNullable(trainerServices.GetPokemonByTraineridPokemonid(trainerId, pokemonId));
        return Optional.ofNullable(pokemon.map(p -> {
            pokemondto pokemonDTO = new pokemondto(p);
            System.out.println(pokemonDTO);
            return pokemonDTO;
        }).orElseGet(() -> {
            System.out.println("Pokémon not found for Trainer ID: " + trainerId + " and Pokémon ID: " + pokemonId);
            return null;
        }));
    }

    public void addPokemonToTrainer(int trainerId, pokemon newPokemon) {
        trainerServices.AddPokemonToTrainer(trainerId, newPokemon);
        System.out.println("Pokemon added to Trainer ID: " + trainerId);
    }

    public void deletePokemonFromTrainer(int trainerId, int pokemonId) {
        pokemon pokemon = trainerServices.GetPokemonByTraineridPokemonid(trainerId, pokemonId);
        if (pokemon != null) {
            trainerServices.DeletePokemonFromTrainer(trainerId, pokemon);
            System.out.println("Pokemon removed from Trainer ID: " + trainerId);
        } else {
            System.out.println("Pokemon not found with Trainer ID: " + trainerId + " and Pokemon ID: " + pokemonId);
        }
    }

    public List<trainerdto> getAllTrainersByPokeIndex(int pokeIndex) {
        List<trainer> trainers = pokemonServices.GetAllTrainersByPokeIndex(pokeIndex);
        List<trainerdto> trainerDtos = (List<trainerdto>) trainers.stream();
        trainers.forEach(System.out::println);
        return trainerDtos;
    }

    public trainerdto getTrainerByPokemonId(int pokemonId) {
        trainer trainer = pokemonServices.GetTrainer(pokemonId);
        trainerdto trainerDTO = new trainerdto(trainer);
        if (trainerDTO.getTrainerid() != 0) {
            System.out.println(trainer);
        } else {
            System.out.println("Trainer not found for Pokemon ID: " + pokemonId);
        }
        return trainerDTO;
    }

    public static void main(String[] args) {
        MainFunctionality mainFunctionality = new MainFunctionality();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Get all trainers");
            System.out.println("2. Add a new trainer");
            System.out.println("3. Delete a trainer");
            System.out.println("4. Update a trainer");
            System.out.println("5. Get trainer by ID");
            System.out.println("6. Get all pokemons of a trainer");
            System.out.println("7. Get pokemon by trainer ID and pokemon ID");
            System.out.println("8. Add pokemon to trainer");
            System.out.println("9. Delete pokemon from trainer");
            System.out.println("10. Get all trainers by pokeindex");
            System.out.println("11. Get trainer by pokemon ID");
            System.out.println("12. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    mainFunctionality.getAllTrainers();
                    break;
                case 2:
                    System.out.print("Enter trainer name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter trainer age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    String genderInput = scanner.nextLine();
                    trainer newTrainer = new trainer();
                    newTrainer.setTrainerName(name);
                    newTrainer.setAge(age);

                    mainFunctionality.addTrainer(newTrainer);
                    break;

                case 3:
                    System.out.print("Enter trainer ID to delete: ");
                    int deleteId = scanner.nextInt();
                    mainFunctionality.deleteTrainer(deleteId);
                    break;
                case 4:
                    System.out.print("Enter trainer ID to update: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.print("Enter new trainer name: ");
                    String newname = scanner.nextLine();
                    System.out.print("Enter new trainer age: ");
                    int newage = scanner.nextInt();

                    trainer trainer = trainerServices.GetTrainerByTrainerid(id);
                    if (trainer != null) {
                        trainer.setName(newname);
                        trainer.setAge(newage);
                    } else {
                        System.out.println("Trainer not found with ID: " + id);
                    }
                    mainFunctionality.updateTrainer(trainer);
                    break;
                case 5:
                    System.out.print("Enter trainer ID: ");
                    int trainerId = scanner.nextInt();
                    mainFunctionality.getTrainerById(trainerId);
                    break;
                case 6:
                    System.out.print("Enter trainer ID to get all pokemons: ");
                    int trainerIdForPokemons = scanner.nextInt();
                    mainFunctionality.getAllPokemons(trainerIdForPokemons);
                    break;
                case 7:
                    System.out.print("Enter trainer ID: ");
                    int tid = scanner.nextInt();
                    System.out.print("Enter pokemon ID: ");
                    int pid = scanner.nextInt();
                    mainFunctionality.getPokemonByTrainerIdAndPokemonId(tid, pid);
                    break;
                case 8:
                    System.out.print("Enter trainer ID: ");
                    int trainerID = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    pokemon newPokemon = new pokemon();
                    System.out.print("Enter pokemon name: ");
                    newPokemon.setName(scanner.nextLine());
                    System.out.print("Enter pokemon level: ");
                    newPokemon.setLevel(scanner.nextInt());
                    mainFunctionality.addPokemonToTrainer(trainerID, newPokemon);
                    break;
                case 9:
                    System.out.print("Enter trainer ID: ");
                    int delTrainerId = scanner.nextInt();
                    System.out.print("Enter pokemon ID to delete: ");
                    int delPokemonId = scanner.nextInt();
                    mainFunctionality.deletePokemonFromTrainer(delTrainerId, delPokemonId);
                    break;
                case 10:
                    System.out.print("Enter pokeindex: ");
                    int pokeIndex = scanner.nextInt();
                    mainFunctionality.getAllTrainersByPokeIndex(pokeIndex);
                    break;
                case 11:
                    System.out.print("Enter pokemon ID: ");
                    int pokemonID = scanner.nextInt();
                    mainFunctionality.getTrainerByPokemonId(pokemonID);
                    break;
                case 12:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

