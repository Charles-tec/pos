package com.server.pos.services.impl;

import com.server.pos.dtos.PosDto;
import com.server.pos.exceptions.InvalidParameterException;
import com.server.pos.models.Pos;
import com.server.pos.repositories.PosRepository;
import com.server.pos.services.PosService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class PosServiceImpl implements PosService {

    private final PosRepository posRepository;
    @Override
    public Pos createPos(PosDto posDto) {
        Pos pos=new Pos();
        pos.setReference(posDto.getReference());
        pos.setStatus(posDto.getStatus());

        return posRepository.save(pos);
    }

    @Override
    public Pos updatePos(Integer id, PosDto posDto) {
      if(!Objects.equals(id,posDto.getId())){
          throw new InvalidParameterException("invalid details");
      }
      if(!posRepository.existsById(id)){
          throw new EntityNotFoundException("transaction with id not found");
      }
      Pos pos = new Pos();
      pos.setId(pos.getId());
      pos.setReference(pos.getReference());
      pos.setStatus(pos.getStatus());

      return posRepository.save(pos);

    }

    @Override
    public Pos getOnePos(Integer id) {
        return posRepository.findById(id).orElseThrow(()->new EntityNotFoundException("transaction not found"));
    }

    @Override
    public Boolean deletePos(Integer id) {
        posRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Pos> getPos() {
        return posRepository.findAll();
    }
}
;