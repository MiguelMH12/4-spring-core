package com.everis.springcore.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.everis.springcore.model.Producto;

@RestController  //Convierte en servlet
public class HolaMundoController {
	
	@GetMapping("/saludar")  //Metodo get y la url
	public String saludar() {
		return "Hola Spring Boot";
	}
	
	@PostMapping("/sumar/{x}/{y}")  //Invocamos con doPost y el url con parametros, hay coincidencia entre x (parametro) y x (parametro del metodo)
	public double sumar(@PathVariable double x,@PathVariable double y) {  //PathVariable toma los datos del url, Aqui probamos en el postman
		return x + y;
	}
	
	//Recibiendo un JSON
	@PutMapping("/validar")  //Desde Postman
	public String validar(@RequestBody Producto producto) { //Creamos la clase nueva en un nuevo paquete, RequestBody lo convierte a JSON
		return producto.toString();
	}
	
	@DeleteMapping("/eliminar/{idProducto}")  //Desde Postman
	public void eliminar(@PathVariable("idProducto") int id) {
		System.out.println("Eliminando producto: " + id);
	}
	 
	@GetMapping("/enviar") //Desde navegador
	public String enviar(@RequestParam String mensaje) {
		return "El mensaje fue: " + mensaje;
	}
	
	@GetMapping("/descargar")  //Regresar respuesta especifica
	public ResponseEntity<Object> descargar() throws FileNotFoundException{
		String imagen = "c:/temp/imagen.jpg";
		File archivo = new File(imagen);
		InputStreamResource resource = new InputStreamResource(new FileInputStream(archivo));  //Abrimos el archivo
		ResponseEntity<Object> respuesta = 
				ResponseEntity.ok().contentLength(archivo.length()).
				contentType(MediaType.IMAGE_JPEG).body(resource);
		return respuesta;
	}
}
