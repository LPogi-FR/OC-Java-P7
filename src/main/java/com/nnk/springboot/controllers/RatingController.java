package com.nnk.springboot.controllers;

import com.nnk.springboot.dto.RatingDto;
import com.nnk.springboot.exception.IdNotFoundException;
import com.nnk.springboot.service.RatingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Slf4j
@Controller
@AllArgsConstructor
public class RatingController {

private final RatingService service;

    @RequestMapping("/rating/list")
    public String home(Model model, Authentication authentication)
    {
        List<RatingDto> ratingDtoList = service.findAll();
        log.info(String.valueOf(ratingDtoList.size()));
        model.addAttribute("ratings",ratingDtoList);
        model.addAttribute("username",authentication.getName());
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(RatingDto ratingDto,Model model,Authentication authentication) {

        model.addAttribute("username",authentication.getName());
        model.addAttribute("ratingDto",ratingDto);

        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid RatingDto ratingDto, BindingResult result, Model model,Authentication authentication) {
        model.addAttribute("username",authentication.getName());
        if (result.hasErrors()) {
            log.error(result.getAllErrors().toString());
            return "rating/add";
        }
        service.save(ratingDto);
        log.info(ratingDto.toString());
        model.addAttribute("ratingDto",ratingDto);
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes,Authentication authentication) {
        model.addAttribute("username",authentication.getName());
        try {
            model.addAttribute("ratingDto",service.findById(id));
        } catch (IdNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            log.error("Rating with id: {} not found.", id);
        }
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid RatingDto ratingDto, BindingResult result, Model model,Authentication authentication) {
        model.addAttribute("username",authentication.getName());
        if (result.hasErrors()) {
            log.error(result.getAllErrors().toString());
            return  "rating/update";
        }
        try {
            service.update(id,ratingDto);
        } catch ( Exception e) {
            log.error(e.getMessage());
        }
        model.addAttribute("ratingDto",ratingDto);
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model,Authentication authentication) {
        model.addAttribute("username",authentication.getName());
        service.delete(id);
        log.info("Rating with id: {} deleted.", id);
        return "redirect:/rating/list";
    }
}
