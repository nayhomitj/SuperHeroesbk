package com.example.superHeroes.controllers;

import static org.springframework.http.ResponseEntity.ok;

import java.util.List;

import org.springframework.http.ResponseEntity;

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

import com.example.superHeroes.common.bean.MessageResponseDto;
import com.example.superHeroes.db.HeroeRepository;
import com.example.superHeroes.db.entity.HeroeEntity;
import com.example.superHeroes.service.HeroeService;

import io.swagger.annotations.ApiOperation;


@CrossOrigin(origins= "*", maxAge= 3600)
@RestController
@RequestMapping("/heroes")
public class HeroeController {
	
	/* the repository */
	@Autowired
	private HeroeRepository heroeRepository;
	
	/* the heroes service*/
	@Autowired
	private HeroeService heroeService;
	
	
	
	
	@ApiOperation(value="Obtener listado de heroes", notes= "Obtener listado de heroes")
	@GetMapping("/")
	public MessageResponseDto<List<HeroeEntity>> getHeroesList() {
		 return MessageResponseDto.success(heroeRepository.findAll());
		
	}
	
	@ApiOperation(value="Obtener heroe por id", notes= "Obtener heroe por id")
	@GetMapping("/{id}")
	public MessageResponseDto<HeroeEntity> getHeroesById(@PathVariable("id") Long id) {
		return heroeService.findByIdHeroe(id);	
	}
	
	
	@ApiOperation(value="Crear  Heroe", notes= "Crear  Heroe")
	@PostMapping("/save")
	public MessageResponseDto<HeroeEntity> save(@RequestBody HeroeEntity heroeRequest){
		return MessageResponseDto.success(heroeRepository.save(heroeRequest));
	}
	
	
	@ApiOperation(value="Edicion de Heroe", notes= "Edicion de Heroe")
	@PatchMapping("{id}/editar")
	public ResponseEntity<MessageResponseDto<String>> addHeroe(@PathVariable("id") Long id,@RequestBody HeroeEntity heroeRequest) {
		
		return ok(heroeService.editarHeroe(id, heroeRequest));
	}
	
	@ApiOperation(value="Eliminar  Heroe", notes= "Eliminar Heroe")
	@DeleteMapping("{id}/eliminar")
	public ResponseEntity<MessageResponseDto<String>> deleteHeroe(@PathVariable("id") Long id) {
		
		return ok(heroeService.deleteHeroe(id));

	}
	
	@ApiOperation(value="Buscar heroe que contenga un valor que coincida en el nombre", notes= "Buscar heroe que contenga un valor que coincida en el nombre")
	@DeleteMapping("{nombre}")
	public  MessageResponseDto<List<HeroeEntity>> findByValor(@PathVariable("nombre") String nombre) {
		
		  return MessageResponseDto.success(heroeRepository.findByValor(nombre));

	}
	
}
