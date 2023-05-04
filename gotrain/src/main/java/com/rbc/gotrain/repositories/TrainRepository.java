package com.rbc.gotrain.repositories;

import com.rbc.gotrain.models.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train,Integer> {
}
