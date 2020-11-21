package com.wp.domain.searchword;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchWordRepository extends JpaRepository<SearchWord, Long>, SearchWordCustomRepository {
	
}
