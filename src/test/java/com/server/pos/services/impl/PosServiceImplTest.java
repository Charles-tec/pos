package com.server.pos.services.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.server.pos.dtos.PosDto;
import com.server.pos.enums.StatusEnum;
import com.server.pos.exceptions.InvalidParameterException;
import com.server.pos.models.Pos;
import com.server.pos.repositories.PosRepository;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PosServiceImpl.class})
@ExtendWith(SpringExtension.class)
class PosServiceImplTest {
    @MockBean
    private PosRepository posRepository;

    @Autowired
    private PosServiceImpl posServiceImpl;

    /**
     * Method under test: {@link PosServiceImpl#createPos(PosDto)}
     */
    @Test
    void testCreatePos() {
        Pos pos = new Pos();
        pos.setId(1);
        pos.setReference("Reference");
        pos.setStatus(StatusEnum.FAILED);
        when(posRepository.save((Pos) any())).thenReturn(pos);

        PosDto posDto = new PosDto();
        posDto.setId(1);
        posDto.setReference("Reference");
        posDto.setStatus(StatusEnum.FAILED);
        assertSame(pos, posServiceImpl.createPos(posDto));
        verify(posRepository).save((Pos) any());
    }

    /**
     * Method under test: {@link PosServiceImpl#createPos(PosDto)}
     */
    @Test
    void testCreatePos2() {
        when(posRepository.save((Pos) any())).thenThrow(new InvalidParameterException("Exception"));

        PosDto posDto = new PosDto();
        posDto.setId(1);
        posDto.setReference("Reference");
        posDto.setStatus(StatusEnum.FAILED);
        assertThrows(InvalidParameterException.class, () -> posServiceImpl.createPos(posDto));
        verify(posRepository).save((Pos) any());
    }

    /**
     * Method under test: {@link PosServiceImpl#updatePos(Integer, PosDto)}
     */
    @Test
    void testUpdatePos() {
        Pos pos = new Pos();
        pos.setId(1);
        pos.setReference("Reference");
        pos.setStatus(StatusEnum.FAILED);
        when(posRepository.save((Pos) any())).thenReturn(pos);
        when(posRepository.existsById((Integer) any())).thenReturn(true);

        PosDto posDto = new PosDto();
        posDto.setId(1);
        posDto.setReference("Reference");
        posDto.setStatus(StatusEnum.FAILED);
        assertSame(pos, posServiceImpl.updatePos(1, posDto));
        verify(posRepository).existsById((Integer) any());
        verify(posRepository).save((Pos) any());
    }

    /**
     * Method under test: {@link PosServiceImpl#updatePos(Integer, PosDto)}
     */
    @Test
    void testUpdatePos2() {
        when(posRepository.save((Pos) any())).thenThrow(new InvalidParameterException("Exception"));
        when(posRepository.existsById((Integer) any())).thenReturn(true);

        PosDto posDto = new PosDto();
        posDto.setId(1);
        posDto.setReference("Reference");
        posDto.setStatus(StatusEnum.FAILED);
        assertThrows(InvalidParameterException.class, () -> posServiceImpl.updatePos(1, posDto));
        verify(posRepository).existsById((Integer) any());
        verify(posRepository).save((Pos) any());
    }

    /**
     * Method under test: {@link PosServiceImpl#updatePos(Integer, PosDto)}
     */
    @Test
    void testUpdatePos3() {
        Pos pos = new Pos();
        pos.setId(1);
        pos.setReference("Reference");
        pos.setStatus(StatusEnum.FAILED);
        when(posRepository.save((Pos) any())).thenReturn(pos);
        when(posRepository.existsById((Integer) any())).thenReturn(false);

        PosDto posDto = new PosDto();
        posDto.setId(1);
        posDto.setReference("Reference");
        posDto.setStatus(StatusEnum.FAILED);
        assertThrows(EntityNotFoundException.class, () -> posServiceImpl.updatePos(1, posDto));
        verify(posRepository).existsById((Integer) any());
    }

    /**
     * Method under test: {@link PosServiceImpl#updatePos(Integer, PosDto)}
     */
    @Test
    void testUpdatePos4() {
        Pos pos = new Pos();
        pos.setId(1);
        pos.setReference("Reference");
        pos.setStatus(StatusEnum.FAILED);
        when(posRepository.save((Pos) any())).thenReturn(pos);
        when(posRepository.existsById((Integer) any())).thenReturn(true);

        PosDto posDto = new PosDto();
        posDto.setId(1);
        posDto.setReference("Reference");
        posDto.setStatus(StatusEnum.FAILED);
        assertThrows(InvalidParameterException.class, () -> posServiceImpl.updatePos(123, posDto));
    }

    /**
     * Method under test: {@link PosServiceImpl#getOnePos(Integer)}
     */
    @Test
    void testGetOnePos() {
        Pos pos = new Pos();
        pos.setId(1);
        pos.setReference("Reference");
        pos.setStatus(StatusEnum.FAILED);
        Optional<Pos> ofResult = Optional.of(pos);
        when(posRepository.findById((Integer) any())).thenReturn(ofResult);
        assertSame(pos, posServiceImpl.getOnePos(1));
        verify(posRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link PosServiceImpl#getOnePos(Integer)}
     */
    @Test
    void testGetOnePos2() {
        when(posRepository.findById((Integer) any())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> posServiceImpl.getOnePos(1));
        verify(posRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link PosServiceImpl#getOnePos(Integer)}
     */
    @Test
    void testGetOnePos3() {
        when(posRepository.findById((Integer) any())).thenThrow(new InvalidParameterException("transaction not found"));
        assertThrows(InvalidParameterException.class, () -> posServiceImpl.getOnePos(1));
        verify(posRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link PosServiceImpl#deletePos(Integer)}
     */
    @Test
    void testDeletePos() {
        doNothing().when(posRepository).deleteById((Integer) any());
        assertTrue(posServiceImpl.deletePos(1));
        verify(posRepository).deleteById((Integer) any());
    }

    /**
     * Method under test: {@link PosServiceImpl#deletePos(Integer)}
     */
    @Test
    void testDeletePos2() {
        doThrow(new InvalidParameterException("Exception")).when(posRepository).deleteById((Integer) any());
        assertThrows(InvalidParameterException.class, () -> posServiceImpl.deletePos(1));
        verify(posRepository).deleteById((Integer) any());
    }

    /**
     * Method under test: {@link PosServiceImpl#getPos()}
     */
    @Test
    void testGetPos() {
        ArrayList<Pos> posList = new ArrayList<>();
        when(posRepository.findAll()).thenReturn(posList);
        List<Pos> actualPos = posServiceImpl.getPos();
        assertSame(posList, actualPos);
        assertTrue(actualPos.isEmpty());
        verify(posRepository).findAll();
    }

    /**
     * Method under test: {@link PosServiceImpl#getPos()}
     */
    @Test
    void testGetPos2() {
        when(posRepository.findAll()).thenThrow(new InvalidParameterException("Exception"));
        assertThrows(InvalidParameterException.class, () -> posServiceImpl.getPos());
        verify(posRepository).findAll();
    }
}

