package com.codigodata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codigodata.model.Pizzas;

public interface PizzasRepository extends JpaRepository<Pizzas, Integer> {

	List<Pizzas> findBySabor(String sabor);

}