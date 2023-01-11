package com.codigodata.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codigodata.model.Pizzas;
import com.codigodata.repository.PizzasRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class PizzasController {

  @Autowired
  PizzasRepository pizzasRepository;

  @GetMapping("/pizzas")
  public ResponseEntity<List<Pizzas>> getAllPizzass(@RequestParam(required = false) String sabor) {
    try {
      List<Pizzas> pizzass = pizzasRepository.findAll();

      return new ResponseEntity<>(pizzass, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


  @PostMapping("/pizzas")
  public ResponseEntity<Pizzas> createPizzas(@RequestBody Pizzas pizzas) {
    try {
      Pizzas _Pizzas = PizzasRepository
          .save(new Pizzas(Pizzas.getTitle(), Pizzas.getDescription(), false));
      return new ResponseEntity<>(_Pizzas, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/Pizzass/{id}")
  public ResponseEntity<Pizzas> updatePizzas(@PathVariable("id") long id, @RequestBody Pizzas Pizzas) {
    Optional<Pizzas> PizzasData = PizzasRepository.findById(id);

    if (PizzasData.isPresent()) {
      Pizzas _Pizzas = PizzasData.get();
      _Pizzas.setTitle(Pizzas.getTitle());
      _Pizzas.setDescription(Pizzas.getDescription());
      _Pizzas.setPublished(Pizzas.isPublished());
      return new ResponseEntity<>(PizzasRepository.save(_Pizzas), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/Pizzass/{id}")
  public ResponseEntity<HttpStatus> deletePizzas(@PathVariable("id") long id) {
    try {
      PizzasRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


}