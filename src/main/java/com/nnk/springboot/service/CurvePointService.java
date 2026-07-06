package com.nnk.springboot.service;

import com.nnk.springboot.dto.CurvePointDto;

import java.util.List;

public interface CurvePointService {

    List<CurvePointDto> findAll();

    CurvePointDto save(CurvePointDto curvePointDto);

    void delete(Integer id);

    CurvePointDto update(Integer id, CurvePointDto curvePointDto);

    CurvePointDto findById(Integer id);
}
