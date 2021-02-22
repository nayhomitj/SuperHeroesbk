package com.example.superHeroes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.superHeroes.controllers.HeroeController;
import com.example.superHeroes.db.HeroeRepository;
import com.example.superHeroes.db.entity.HeroeEntity;

@RunWith(SpringRunner.class)
@DataJpaTest
public class HeroeRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private HeroeRepository heroeRepository;
	
	@Test
	public void saveHeroe() {
		
		HeroeEntity heroe = getHeroe();
		HeroeEntity savedHeroe = entityManager.persist(heroe);
		Optional<HeroeEntity> result = heroeRepository.findById(savedHeroe.getId());
		HeroeEntity heroeResult  = result.get();
		assertThat(heroeResult).isEqualTo(savedHeroe);
		
	}
	
	private HeroeEntity getHeroe() {
		HeroeEntity heroe = new HeroeEntity();
		heroe.setNombre("Doctor Stranger");
		heroe.setPoder("Magia");
		heroe.setUniverso("Marvel");
		heroe.setCreateAt(new Date());
		return heroe;
	}
	@Test
	public void deleteHeroe() {
		HeroeEntity result = entityManager.persistAndFlush(getHeroe());
		
		heroeRepository.deleteById(result.getId());
		assertThat(heroeRepository.findById(result.getId()).isEmpty());
	}
	

}
