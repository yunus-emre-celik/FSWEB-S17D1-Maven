package com.workintech.fswebs17d1.controller;

import com.workintech.fswebs17d1.entity.Animal;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/workintech/animal")
public class AnimalController {
    Map<Integer, Animal> animals;

    @PostConstruct
    public void loadAll(){
        System.out.println("postconstruct çalıştı");
        this.animals=new HashMap<>();
        this.animals.put(1,new Animal(1,"maymun"));
    }
    @GetMapping
    public List<Animal> getAnimal(){
        return animals.values().stream().toList();
    }
    @GetMapping("/{id}")
    public Animal getAnimalById(@PathVariable("id") Integer id){
        if(id<0){
            System.out.println("id cannot be less than zero"+id);
            return null;
        }
        return animals.get(id);

    }
    @PostMapping
    public void addAnimal(@RequestBody Animal animal){
        this.animals.put(animal.getId(),animal);
    }
    @PutMapping("/{id}")
    public Animal updateAnimal(@PathVariable Integer id,@RequestBody Animal animal){
        animals.put(id,animal);
        return animals.get(id);
    }
    @DeleteMapping("/{id}")
    public Animal deleteAnimal(@PathVariable Integer id){
        Animal animal=animals.get(id);
        animals.remove(animal.getId(),animal);
        return animal;
    }


}