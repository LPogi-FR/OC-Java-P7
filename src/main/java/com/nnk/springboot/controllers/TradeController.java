package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.dto.TradeDto;
import com.nnk.springboot.exception.IdNotFoundException;
import com.nnk.springboot.service.TradeService;
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
public class TradeController {
private final TradeService service;

    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        List<TradeDto> tradeDtoList = service.findAll();
        log.info(String.valueOf(tradeDtoList.size()));
        model.addAllAttributes(tradeDtoList);
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addTrade(Trade trade) {
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid TradeDto tradeDto, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", result.getAllErrors().get(0).getDefaultMessage());
            log.error(result.getAllErrors().toString());
        }
        try {
            service.save(tradeDto);
            log.info(tradeDto.toString());
            model.addAllAttributes(Collections.singleton(tradeDto));
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            log.error(e.getMessage());
        }
        return "trade/add";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model,RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute(service.findById(id));
        } catch (IdNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            log.error("Bid with id: {} not found.", id);
        }
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid TradeDto tradeDto,
                             BindingResult result, Model model,RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", result.getAllErrors().get(0).getDefaultMessage());
            log.error(result.getAllErrors().toString());
        }
        try {
            final var response = new ResponseEntity<>(service.update(id, tradeDto), HttpStatus.CREATED);
            log.info(response.toString());
        } catch ( Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            log.error(e.getMessage());
        }
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        service.delete(id);
        log.info("Bid with id: {} deleted.", id);
        return "redirect:/trade/list";
    }
}
