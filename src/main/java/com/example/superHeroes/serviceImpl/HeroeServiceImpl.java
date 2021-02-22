package com.example.superHeroes.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.superHeroes.common.bean.MessageResponseDto;
import com.example.superHeroes.db.HeroeRepository;
import com.example.superHeroes.db.entity.HeroeEntity;
import com.example.superHeroes.service.HeroeService;


@Component
public class HeroeServiceImpl implements HeroeService {
	
	private static final String HEROE_NOT_EXIST = "Heroe No Existe";
	private static final String HEROE_UPDATE_SUCCESSFULLY = "Heroe Editado Correctamente";
	private static final String HEROE_DELETE_SUCCESSFULLY = "Heroe Eliminado Correctamente";

	
	
	@Autowired
	HeroeRepository heroeRepository;

	@Override
	public MessageResponseDto<HeroeEntity> findByIdHeroe(Long id) {
		
		Optional<HeroeEntity> optional = heroeRepository.findById(id);
		
		if(!optional.isPresent()) {
			return MessageResponseDto.fail(HEROE_NOT_EXIST);
		}
		return MessageResponseDto.success(optional.get());
	}

	@Override
	public MessageResponseDto<String> editarHeroe(Long id, HeroeEntity heroeUpdate) {
		Optional<HeroeEntity> optionalHeroe = heroeRepository.findById(id);
		
		if(!optionalHeroe.isPresent()) {
			return MessageResponseDto.fail(HEROE_NOT_EXIST);
		}
		
		HeroeEntity heroeToUpdate = optionalHeroe.get();
		
		if(heroeUpdate.getNombre() != null && !heroeUpdate.getNombre().equals("")) {
			heroeToUpdate.setNombre(heroeUpdate.getNombre());
		}
		
		if(heroeUpdate.getPoder() != null && !heroeUpdate.getPoder().equals("")) {
			heroeToUpdate.setPoder(heroeUpdate.getPoder());
		}
		
		if(heroeUpdate.getUniverso() != null && !heroeUpdate.getUniverso().equals("")) {
			heroeToUpdate.setUniverso(heroeUpdate.getUniverso());
		}
		
		heroeRepository.save(heroeToUpdate);
		return MessageResponseDto.success(HEROE_UPDATE_SUCCESSFULLY);
	}

	@Override
	public MessageResponseDto<String> deleteHeroe(Long id) {
		Optional<HeroeEntity> optionalHeroe = heroeRepository.findById(id);
		
		if(!optionalHeroe.isPresent()) {
			return MessageResponseDto.fail(HEROE_NOT_EXIST);
		}
		
		heroeRepository.deleteById(id);
		
		return  MessageResponseDto.success(HEROE_DELETE_SUCCESSFULLY);
	}

}
