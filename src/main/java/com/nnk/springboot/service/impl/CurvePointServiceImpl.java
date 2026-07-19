package com.nnk.springboot.service.impl;

import com.nnk.springboot.dto.CurvePointDto;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.exception.IdNotFoundException;
import com.nnk.springboot.mapper.CurvePointMapper;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.service.CurvePointService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CurvePointServiceImpl implements CurvePointService {

    private  final CurvePointRepository repository;
    private final CurvePointMapper mapper;

    @Override
    public List<CurvePointDto> findAll() {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    public CurvePointDto save(CurvePointDto curvePointDto) {
        repository.save(mapper.toEntity(curvePointDto));
        return curvePointDto;
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public CurvePointDto update(Integer id, CurvePointDto curvePointDto) {
        CurvePoint curvePointToUpdate = mapper.toEntity(curvePointDto);
        curvePointToUpdate.setId(id);
        repository.save(curvePointToUpdate);
        return curvePointDto;
    }

    @Override
    public CurvePointDto findById(Integer id) {
        Optional<CurvePoint> optionnalEntity = repository.findById(id);
        if (optionnalEntity.isPresent()) {
            return mapper.toDto(optionnalEntity.get());
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }
}
