package com.server.pos.repositories;

import com.server.pos.models.Pos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PosRepository extends JpaRepository<Pos,Integer> {


}
