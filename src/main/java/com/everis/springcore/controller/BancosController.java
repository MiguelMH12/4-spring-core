package com.everis.springcore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.everis.springcore.model.Banco;
import com.everis.springcore.service.BancoService;

@RestController
@RequestMapping("bancos")
public class BancosController {
	
	@Autowired
	private BancoService bancoService;
	
	@GetMapping("/")
	public List<Banco> listar(){
		return bancoService.listar();
	}
	
	@PostMapping("/")
	public boolean insertar(@RequestBody Banco banco) {
		return bancoService.insertar(banco);
	}
	
	@PutMapping("/")
	public boolean actualizar(@RequestBody Banco banco) {
		return bancoService.actualizar(banco);
	}
	
	@DeleteMapping("/{idBanco}")
	public boolean eliminar(@PathVariable int idBanco) {
		return bancoService.eliminar(idBanco);
	}
}
