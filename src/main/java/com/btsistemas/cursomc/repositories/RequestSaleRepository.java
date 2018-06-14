package com.btsistemas.cursomc.repositories;

import com.btsistemas.cursomc.domain.RequestSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestSaleRepository extends JpaRepository<RequestSale, Integer> {

}
