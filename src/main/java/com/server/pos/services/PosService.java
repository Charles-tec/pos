package com.server.pos.services;

import com.server.pos.dtos.PosDto;
import com.server.pos.models.Pos;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PosService {

    Pos createPos(PosDto posDto);

    Pos updatePos(Integer id, PosDto posDto);

    Pos getOnePos(Integer id);

    Boolean deletePos(Integer id);

    List<Pos> getPos();

}
