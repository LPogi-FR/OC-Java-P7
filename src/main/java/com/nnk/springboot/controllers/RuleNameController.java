package com.nnk.springboot.controllers;

import com.nnk.springboot.dto.RuleNameDto;
import com.nnk.springboot.exception.IdNotFoundException;
import com.nnk.springboot.service.RuleNameService;
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
public class RuleNameController {
private final RuleNameService service;

    @RequestMapping("/ruleName/list")
    public String home(Model model, Authentication authentication)
    {
        List<RuleNameDto> ruleNameDtoList = service.findAll();
        log.info(String.valueOf(ruleNameDtoList.size()));
        model.addAttribute("ruleNames",ruleNameDtoList);
        model.addAttribute("username",authentication.getName());

        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleNameDto ruleNameDto,Model model ,Authentication authentication) {

       model.addAttribute("username",authentication.getName());
       model.addAttribute("ruleNameDto",ruleNameDto);

       return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleNameDto ruleNameDto, BindingResult result, Model model, RedirectAttributes redirectAttributes,Authentication authentication) {

        model.addAttribute("username",authentication.getName());

        if (result.hasErrors()) {
            log.error(result.getAllErrors().toString());
            return  "ruleName/add";
        }
        service.save(ruleNameDto);
        log.info(ruleNameDto.toString());
        model.addAttribute("ruleNameDto",ruleNameDto);
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes,Authentication authentication) {
        model.addAttribute("username",authentication.getName());
        try {
            model.addAttribute("ruleNameDto",service.findById(id));
        } catch (IdNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            log.error("RuleName with id: {} not found.", id);
        }
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleNameDto ruleNameDto, BindingResult result, Model model,Authentication authentication) {
        model.addAttribute("username",authentication.getName());
        if (result.hasErrors()) {
            log.error(result.getAllErrors().toString());
            return "ruleName/update";
        }
        try {
            service.update(id,ruleNameDto);
        } catch ( Exception e) {
            log.error(e.getMessage());
        }
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model,Authentication authentication) {
        service.delete(id);
        model.addAttribute("username",authentication.getName());
        log.info("RuleName with id: {} deleted.", id);
        return "redirect:/ruleName/list";
    }
}
