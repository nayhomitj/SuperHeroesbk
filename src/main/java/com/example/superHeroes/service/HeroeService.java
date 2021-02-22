package com.example.superHeroes.service;

import com.example.superHeroes.common.bean.MessageResponseDto;
import com.example.superHeroes.db.entity.HeroeEntity;

public interface HeroeService {
	
	/**
	 * recuperar un heroe por id
	 * @param id
	 * @return
	 */
	public MessageResponseDto<HeroeEntity> findByIdHeroe(Long id);
	
	/**
	 * modificar heroe
	 * @param id
	 * @param heroeUpdate
	 * @return
	 */
	public MessageResponseDto<String> editarHeroe(Long id, HeroeEntity heroeUpdate);
	
	/**
	 * eliminar heroe
	 * @param id
	 * @return
	 */
	public MessageResponseDto<String> deleteHeroe(Long id);

}
