# NLP-ONBOARDING-EXERCISE


Requirements:

Application should use Java 8, dropwizard, REST API, hibernate.

Problem statement:


Implement a service which represents a pokemon world which is full of pokemon trainers and pokemons. We require the following functionalities:

Get all trainers
Add a new trainer
For a trainer using trainer id get the trainer details
For a trainer using trainer id get all their pokemons details
For a trainer using trainer id get a specific pokemon details using pokemon id
A trainer’s pokemon might level up or the trainer may change their nickname, so an functionality to update the trainer’s pokemon details
A trainer may catch a pokemon so add that to the trainer’s collection
A trainer may release a pokemon in the wild so the functionality to release the pokemon from trainer’s collection
Given a pokemon’s pokedex id fetch all trainers which have that pokemon
Given a pokemon’s id fetch its trainer and it’s details




Trainer class should have the following parameters (you can add extra fields as you like or require, also feel free to rename the fields) 
1. Trainer name
2. Trainer age
3. Trainer id (can be auto incremented upto you)
4. Date added





Pokemon class should have the following parameters (you can add extra fields as you like or require, also feel free to rename the fields) 


Pokemon name
Pokemon id (can be auto incremented upto you)
Level
Pokemon nickname (defaults to pokemon name)
PokemonTypes (array of enums) Ex: dragon, electric
Gender (should be enum, MALE/FEMALE)
Date Added


You can also add extra tables as per your requirement

Hint: you would require foreign key for this.
