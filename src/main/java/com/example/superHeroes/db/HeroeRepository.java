package com.example.superHeroes.db;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.superHeroes.db.entity.HeroeEntity;

/**
 * 
 * The interface HeroeRepository.
 *
 */
public interface HeroeRepository extends CrudRepository<HeroeEntity, Long> {

	/**
	 * Find All.
	 * 
	 * @return the list
	 */

	public List<HeroeEntity> findAll();

	/**
	 * Find by id.
	 * 
	 * @param id the id
	 * @return the optional
	 */

	@Query("SELECT h FROM HeroeEntity h WHERE h.id = :id")
	Optional<HeroeEntity> findById(@Param("id") Long id);
	
	
	/**
	 * Save Heroe
	 * 
	 * @param requestDto the HeroeDto
	 * @return
	 */
	public HeroeEntity save(HeroeEntity requestDto);
	
	/**
	 * Delete Heroe
	 */

	public void deleteById(Long id);
	
	/**
	 * 
	 * Find Hero from 
	 * @param nombre
	 * @return
	 */
	@Query("SELECT h from HeroeEntity h WHERE h.nombre like  %:nombre%")
	List<HeroeEntity> findByValor(@Param("nombre") String nombre);


}
