package com.nnk.springboot.controllers;

import com.nnk.springboot.dto.BidListDto;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.exception.AccountIsMandatoryException;
import com.nnk.springboot.exception.IdNotFoundException;
import com.nnk.springboot.exception.TypeIsMandatoryException;
import com.nnk.springboot.service.BidListService;
import jakarta.validation.Valid;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Collections;
import java.util.List;

@Slf4j
@Controller
@AllArgsConstructor
public class BidListController {

    private final BidListService service;

    @RequestMapping("/bidList/list")
    public String home(Model model) {
        List<BidListDto> listOfBidList = service.findAll();
        log.info(String.valueOf(listOfBidList.size()));
        model.addAllAttributes(listOfBidList);
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(
            @Valid BidListDto bidListDto,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", result.getAllErrors().get(0).getDefaultMessage());
            log.error(result.getAllErrors().toString());
        }
        try {
            service.save(bidListDto);
            log.info(bidListDto.toString());
            model.addAllAttributes(Collections.singleton(bidListDto));
        } catch (AccountIsMandatoryException | TypeIsMandatoryException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            log.error(e.getMessage());
        }
        return "bidList/add";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute(service.findById(id));
        } catch (IdNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            log.error("Bid with id: {} not found.", id);
        }
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(
            @PathVariable("id") Integer id,
            @Valid BidListDto bidListDto,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", result.getAllErrors().get(0).getDefaultMessage());
            log.error(result.getAllErrors().toString());
        }
        try {
            final var response = new ResponseEntity<>(service.update(id, bidListDto), HttpStatus.CREATED);
            log.info(response.toString());
        } catch (AccountIsMandatoryException | TypeIsMandatoryException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            log.error(e.getMessage());
        }
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        service.delete(id);
        log.info("Bid with id: {} deleted.", id);
        return "redirect:/bidList/list";
    }
}