package com.everis.springcore.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.springcore.dao.ClienteDAO;
import com.everis.springcore.model.Cliente;

/**
 * Regla de negocio relacionada a clientes. El cliente sabe como opera su negocio
 * @author Dell5458
 *
 */
@Service  //Servicio de negocio, agrega un objeto al contexto de Spring
public class ClienteService {
	@Autowired
	private ClienteDAO clienteDAO;
	
	public List<Cliente> listar(){
		return clienteDAO.listar();
	}
	
	public boolean insertar(Cliente cliente) {
		//Validar el RFC que no permita duplicarlo
		String rfc = cliente.getRfc();
		List<Cliente> clientes = listar();
		for(Cliente cliente1 : clientes) {
			if(cliente1.getRfc().equals(rfc)) {
				return false;
			} 
		} 
		clienteDAO.insertar(cliente);
		return true;
	}
	
	public boolean exportarXLSX(String rutaArchivo) {  //Me pasan la ruta de donde escribirlo
		
		XSSFWorkbook libro= new XSSFWorkbook();
		XSSFSheet hoja1 = libro.createSheet("Mis Clientes");
		//cabecera de la hoja de excel
		String [] header= new String[]{"Id CLiente", "Nombre","A Paterno","A Materno", "RFC"};
		
		XSSFRow row=hoja1.createRow(0);//Fila de encabezado
		for(int i = 0; i < header.length; i++) {  //Le agregamos las celdas 
			XSSFCell cell= row.createCell(i);
			cell.setCellValue(header[i]);
		}
		
		List<Cliente> clientes = listar();
		for(int i = 1; i <= clientes.size(); i++) {
			XSSFRow fila = hoja1.createRow(i);
			Cliente cliente = clientes.get(i - 1);
			
			XSSFCell celdaIdCliente = fila.createCell(0);
			celdaIdCliente.setCellValue(cliente.getIdCliente());

			XSSFCell celdaNombre = fila.createCell(1);
			celdaNombre.setCellValue(cliente.getNombre());
			
			XSSFCell celdaApaterno = fila.createCell(2);
			celdaApaterno.setCellValue(cliente.getApaterno());
			
			XSSFCell celdaAmaterno = fila.createCell(3);
			celdaAmaterno.setCellValue(cliente.getAmaterno());
			
			XSSFCell celdaRfc = fila.createCell(4);
			celdaRfc.setCellValue(cliente.getRfc());
		}
		try {
			File archivo = new File(rutaArchivo);
			FileOutputStream fileOut = new FileOutputStream(archivo);
			if(archivo.exists()) {
				archivo.delete();
			} else {
				libro.write(fileOut);
				fileOut.flush();
				fileOut.close();
			}
			return true;
			
		}catch(IOException e) {
			System.err.println(e.getMessage());
			return false;
		}
		
		
	}
	
	public Cliente buscar(int idCliente) {
		return clienteDAO.buscar(idCliente);
	}
	
	public boolean actualizar(Cliente cliente) {
		return clienteDAO.actualizar(cliente);
	}
	
	public boolean eliminar(int idCliente) {
		return clienteDAO.eliminar(idCliente);
	}
	
	
}
