package com.nnk.springboot.controllers;

import com.nnk.springboot.dto.CurvePointDto;
import com.nnk.springboot.exception.IdNotFoundException;
import com.nnk.springboot.service.CurvePointService;
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
public class CurveController {

    private final CurvePointService service;

    @RequestMapping("/curvePoint/list")
    public String home(Model model, Authentication authentication) {
        List<CurvePointDto> curvePointDtoList = service.findAll();
        log.info(String.valueOf(curvePointDtoList.size()));
        model.addAttribute("curvePoints",curvePointDtoList);
        model.addAttribute("username",authentication.getName());
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addCurveForm(CurvePointDto curvePointDto,Model model,Authentication authentication) {
        model.addAttribute("username",authentication.getName());
        model.addAttribute("curvePointDto",curvePointDto);
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePointDto curvePointDto, BindingResult result, Model model,Authentication authentication) {

        model.addAttribute("username",authentication.getName());

        if (result.hasErrors()) {
            log.error(result.getAllErrors().toString());
            return "curvePoint/add";
        }
        service.save(curvePointDto);
        log.info(curvePointDto.toString());
        model.addAttribute("curvePointDto",curvePointDto);
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes,Authentication authentication) {
        model.addAttribute("username",authentication.getName());
        try {
            model.addAttribute("curvePointDto",service.findById(id));
        } catch (IdNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            log.error("CurvePoint with id: {} not found.", id);
        }
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePointDto curvePointDto,
                             BindingResult result, Model model ,Authentication authentication) {
        model.addAttribute("username",authentication.getName());
        if (result.hasErrors()) {
            log.error(result.getAllErrors().toString());
            return "curvePoint/update";
        }
        try {
            service.update(id,curvePointDto);
        } catch (IdNotFoundException e) {
            log.error(e.getMessage());
        }
        model.addAttribute("curvePointDto",curvePointDto);
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model,Authentication authentication) {
        model.addAttribute("username",authentication.getName());
        service.delete(id);
        log.info("CurvePoint with id: {} deleted.", id);
        return "redirect:/curvePoint/list";
    }
}
