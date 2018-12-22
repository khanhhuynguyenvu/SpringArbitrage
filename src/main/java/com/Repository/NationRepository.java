package com.Repository;

import com.Entities.NationCurrency;
import org.springframework.data.repository.CrudRepository;

public interface NationRepository extends CrudRepository<NationCurrency,Integer> {
}
