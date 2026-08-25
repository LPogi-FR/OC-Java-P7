package com.nnk.springboot.controllers;

import com.nnk.springboot.dto.TradeDto;
import com.nnk.springboot.exception.IdNotFoundException;
import com.nnk.springboot.service.TradeService;
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
public class TradeController {
private final TradeService service;

    @RequestMapping("/trade/list")
    public String home(Model model, Authentication authentication) {
        List<TradeDto> tradeDtoList = service.findAll();
        log.info(String.valueOf(tradeDtoList.size()));
        model.addAttribute("trades",tradeDtoList);
        model.addAttribute("username",authentication.getName());
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addTrade(TradeDto tradeDto,Model model,Authentication authentication) {

        model.addAttribute("username",authentication.getName());
        model.addAttribute("tradeDto",tradeDto);

        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid TradeDto tradeDto, BindingResult result, Model model,Authentication authentication) {

        model.addAttribute("username",authentication.getName());

        if (result.hasErrors()) {
            log.error(result.getAllErrors().toString());
            return "trade/add";
        }
        service.save(tradeDto);
        log.info(tradeDto.toString());
        model.addAttribute("tradeDto",tradeDto);
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model,RedirectAttributes redirectAttributes,Authentication authentication) {
        model.addAttribute("username",authentication.getName());

        try {
            model.addAttribute("tradeDto",service.findById(id));
        } catch (IdNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            log.error("Trade with id: {} not found.", id);
        }
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid TradeDto tradeDto, BindingResult result, Model model,Authentication authentication) {
        model.addAttribute("username",authentication.getName());
        if (result.hasErrors()) {
            log.error(result.getAllErrors().toString());
            return "trade/update";
        }
        try {
            service.update(id,tradeDto);
        } catch ( Exception e) {
            log.error(e.getMessage());
        }
        model.addAttribute("tradeDto",tradeDto);
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model,Authentication authentication) {
        model.addAttribute("username",authentication.getName());
        service.delete(id);
        log.info("Trade with id: {} deleted.", id);
        return "redirect:/trade/list";
    }
}
