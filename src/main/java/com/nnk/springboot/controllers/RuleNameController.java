package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.dto.RuleNameDto;
import com.nnk.springboot.exception.IdNotFoundException;
import com.nnk.springboot.service.RuleNameService;
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
public class RuleNameController {
private final RuleNameService service;

    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
        List<RuleNameDto> ruleNameDtoList = service.findAll();
        log.info(String.valueOf(ruleNameDtoList.size()));
        model.addAllAttributes(ruleNameDtoList);
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleNameDto ruleNameDto, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", result.getAllErrors().get(0).getDefaultMessage());
            log.error(result.getAllErrors().toString());
        }
        try {
            service.save(ruleNameDto);
            log.info(ruleNameDto.toString());
            model.addAllAttributes(Collections.singleton(ruleNameDto));
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            log.error(e.getMessage());
        }
        return "ruleName/add";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute(service.findById(id));
        } catch (IdNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            log.error("Bid with id: {} not found.", id);
        }
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleNameDto ruleNameDto,
                             BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", result.getAllErrors().get(0).getDefaultMessage());
            log.error(result.getAllErrors().toString());
        }
        try {
            final var response = new ResponseEntity<>(service.update(id, ruleNameDto), HttpStatus.CREATED);
            log.info(response.toString());
        } catch ( Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            log.error(e.getMessage());
        }
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        service.delete(id);
        log.info("Bid with id: {} deleted.", id);
        return "redirect:/ruleName/list";
    }
}
