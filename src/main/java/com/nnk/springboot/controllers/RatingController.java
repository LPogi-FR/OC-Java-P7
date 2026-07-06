package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.dto.RatingDto;
import com.nnk.springboot.exception.IdNotFoundException;
import com.nnk.springboot.service.RatingService;
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
public class RatingController {
private final RatingService service;

    @RequestMapping("/rating/list")
    public String home(Model model)
    {
        List<RatingDto> ratingDtoList = service.findAll();
        log.info(String.valueOf(ratingDtoList.size()));
        model.addAllAttributes(ratingDtoList);
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid RatingDto ratingDto, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", result.getAllErrors().get(0).getDefaultMessage());
            log.error(result.getAllErrors().toString());
        }
        try {
            service.save(ratingDto);
            log.info(ratingDto.toString());
            model.addAllAttributes(Collections.singleton(ratingDto));
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            log.error(e.getMessage());
        }
        return "rating/add";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute(service.findById(id));
        } catch (IdNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            log.error("Bid with id: {} not found.", id);
        }
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid RatingDto ratingDto,
                             BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", result.getAllErrors().get(0).getDefaultMessage());
            log.error(result.getAllErrors().toString());
        }
        try {
            final var response = new ResponseEntity<>(service.update(id, ratingDto), HttpStatus.CREATED);
            log.info(response.toString());
        } catch ( Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            log.error(e.getMessage());
        }
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        service.delete(id);
        log.info("Bid with id: {} deleted.", id);
        return "redirect:/rating/list";
    }
}
