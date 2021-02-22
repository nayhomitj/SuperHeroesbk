package com.example.superHeroes;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.superHeroes.controllers.HeroeController;


@RunWith(SpringRunner.class)
@SpringBootTest
class SuperHeroesApplicationTests {
	
	@Autowired
	private HeroeController heroeController;

	@Test
	public void contextLoads() {
		assertThat(heroeController).isNotNull();
	}

}
