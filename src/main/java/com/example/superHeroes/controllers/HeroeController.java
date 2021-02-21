package com.example.superHeroes.controllers;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.superHeroes.db.HeroeRepository;
import com.example.superHeroes.db.entity.HeroeEntity;

import io.swagger.annotations.ApiOperation;


@CrossOrigin(origins= "*", maxAge= 3600)
@RestController
@RequestMapping("/heroes")
public class HeroeController {
	
	@Autowired
	private HeroeRepository heroeRepository;
	
	@ApiOperation(value="Obtener listado de heroes", notes= "Obtener listado de heroes")
	@GetMapping("/")
	public List<HeroeEntity> getHeroesList() {
		return heroeRepository.findAll();
		
	}
	
	@ApiOperation(value="Obtener heroe por id", notes= "Obtener heroe por id")
	@GetMapping("/{id}")
	public Optional<HeroeEntity> getHeroesById(@PathVariable("id") Long id) {
		return heroeRepository.findById(id);	
	}
	
	
	@ApiOperation(value="Crear  Heroe", notes= "Crear  Heroe")
	@PostMapping("/save")
	public HeroeEntity save(@RequestBody HeroeEntity heroeRequest){
		return heroeRepository.save(heroeRequest);
	}
	
	
	@ApiOperation(value="Edicion de Heroe", notes= "Edicion de Heroe")
	@PatchMapping("{id}/editar")
	public String addHeroe(@PathVariable("id") Long id,@RequestBody HeroeEntity heroeRequest) throws URISyntaxException {
		
		Optional<HeroeEntity> optionalHeroe = heroeRepository.findById(id);
		
		if(!optionalHeroe.isPresent()) {
			return "Heore no Existe";
		}
		
		HeroeEntity heroeToUpdate = optionalHeroe.get();
		
		if(heroeRequest.getNombre() != null && !heroeRequest.getNombre().equals("")) {
			heroeToUpdate.setNombre(heroeRequest.getNombre());
		}
		
		if(heroeRequest.getPoder() != null && !heroeRequest.getPoder().equals("")) {
			heroeToUpdate.setPoder(heroeRequest.getPoder());
		}
		
		if(heroeRequest.getUniverso() != null && !heroeRequest.getUniverso().equals("")) {
			heroeToUpdate.setUniverso(heroeRequest.getUniverso());
		}
		
		heroeRepository.save(heroeToUpdate);
		
		return "El Heroe se edito correctamente";
		
	}
	
	@ApiOperation(value="Eliminar  Heroe", notes= "Eliminar Heroe")
	@DeleteMapping("{id}/eliminar")
	public String deleteHeroe(@PathVariable("id") Long id) {
		
		Optional<HeroeEntity> optionalHeroe = heroeRepository.findById(id);
		
		if(!optionalHeroe.isPresent()) {
			return "Heore no Existe";
		}
		
		heroeRepository.deleteById(id);
		
		return  "Heroe eliminado correctamente";

	}
	
	@ApiOperation(value="Buscar heroe que contenga un valor que coincida en el nombre", notes= "Buscar heroe que contenga un valor que coincida en el nombre")
	@DeleteMapping("{nombre}")
	public List<HeroeEntity> findByValor(@PathVariable("nombre") String nombre) {
		
		List<HeroeEntity> heroeList = heroeRepository.findByValor(nombre);
		
		return  heroeList;

	}
	
}
