package com.codigodata.controller;

import java.util.List;

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

	@GetMapping("/pizzas/getPizzas")
	public ResponseEntity<List<Pizzas>> getAllPizzass(@RequestParam(required = false) String sabor) {
		try {
			List<Pizzas> pizzass = pizzasRepository.findAll();

			return new ResponseEntity<>(pizzass, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/pizzas/createPizza")
	public ResponseEntity<Pizzas> createPizzas(@RequestBody Pizzas inputJson) {
		try {
			Pizzas pizza = new Pizzas();
			pizza.setId(inputJson.getId());
			pizza.setNombre(inputJson.getNombre());
			pizza.setSabor(inputJson.getSabor());
			pizza.setMasa(inputJson.getMasa());
			pizza.setCantidad(inputJson.getCantidad());
			pizzasRepository.save(pizza);
			return new ResponseEntity<>(null, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/pizzas/updatePizza/{id}")
	public ResponseEntity<Pizzas> updatePizzas(@PathVariable("id") int id, @RequestBody Pizzas inputJson) {
		try {
			Pizzas pizza = pizzasRepository.findById(id).orElse(null);
			pizza.setNombre(inputJson.getNombre());
			pizza.setSabor(inputJson.getSabor());
			pizza.setMasa(inputJson.getMasa());
			pizza.setCantidad(inputJson.getCantidad());
			pizzasRepository.save(pizza);
			return new ResponseEntity<>(null, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/pizzas/deletePizzaById/{id}")
	public ResponseEntity<HttpStatus> deletePizzas(@PathVariable("id") int id) {
		try {
			pizzasRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}