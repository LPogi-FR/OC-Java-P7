package com.nnk.springboot.controllers;

import com.nnk.springboot.dto.CurvePointDto;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.exception.CurvePointIdIsNullException;
import com.nnk.springboot.exception.IdNotFoundException;
import com.nnk.springboot.service.CurvePointService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Collections;
import java.util.List;

@Slf4j
@Controller
@AllArgsConstructor
public class CurveController {
    private final CurvePointService service;

    @RequestMapping("/curvePoint/list")
    public String home(Model model) {
        List<CurvePointDto> curvePointDtoList = service.findAll();
        log.info(String.valueOf(curvePointDtoList.size()));
        model.addAllAttributes(curvePointDtoList);
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addCurveForm(CurvePoint curvePoint) {
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePointDto curvePointDto, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", result.getAllErrors().get(0).getDefaultMessage());
            log.error(result.getAllErrors().toString());
        }
        try {
            service.save(curvePointDto);
            log.info(curvePointDto.toString());
            model.addAllAttributes(Collections.singleton(curvePointDto));
        } catch (CurvePointIdIsNullException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            log.error(e.getMessage());
        }
        return "curvePoint/add";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute(service.findById(id));
        } catch (IdNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            log.error("Bid with id: {} not found.", id);
        }
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePointDto curvePointDto,
                             BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", result.getAllErrors().get(0).getDefaultMessage());
            log.error(result.getAllErrors().toString());
        }
        try {
            final var response = new ResponseEntity<>(service.update(id, curvePointDto), HttpStatus.CREATED);
            log.info(response.toString());
        } catch (CurvePointIdIsNullException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            log.error(e.getMessage());
        }
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        service.delete(id);
        log.info("Bid with id: {} deleted.", id);
        return "redirect:/curvePoint/list";
    }
}
