package com.btsistemas.cursomc.repositories;

import com.btsistemas.cursomc.domain.ItemRequestSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRequestSaleRepository extends JpaRepository<ItemRequestSale, Integer> {

}
