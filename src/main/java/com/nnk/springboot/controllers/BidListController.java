package com.nnk.springboot.controllers;

import com.nnk.springboot.dto.BidListDto;
import com.nnk.springboot.exception.IdNotFoundException;
import com.nnk.springboot.service.BidListService;
import jakarta.validation.Valid;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Slf4j
@Controller
@AllArgsConstructor
public class BidListController {

    private final BidListService service;

    @RequestMapping("/bidList/list")
    public String home(Model model, Authentication authentication) {
        List<BidListDto> listOfBidList = service.findAll();
        log.info(String.valueOf(listOfBidList.size()));
        model.addAttribute("bidLists",listOfBidList);
        model.addAttribute("username",authentication.getName());
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidListDto bid,Model model , Authentication authentication) {

        model.addAttribute("bidListDto",bid);
        model.addAttribute("username",authentication.getName());

        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidListDto bidListDto,BindingResult result,Model model,Authentication authentication) {

        model.addAttribute("username",authentication.getName());

        if (result.hasErrors()) {
            log.error(result.getAllErrors().toString());
            return "bidList/add";
        }
        service.save(bidListDto);
        log.info(bidListDto.toString());
        model.addAttribute("bidListDto",bidListDto);
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes,Authentication authentication) {
        model.addAttribute("username",authentication.getName());
        try {
            model.addAttribute("bidListDto",service.findById(id));
        } catch (IdNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            log.error("Bid with id: {} not found.", id);
        }
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id,@Valid BidListDto bidListDto,BindingResult result,Model model,Authentication authentication) {
        model.addAttribute("username",authentication.getName());
        if (result.hasErrors()) {
            log.error(result.getAllErrors().toString());
            return "bidList/update";
        }
        try {
           service.update(id,bidListDto);
        } catch (IdNotFoundException e) {
            log.error(e.getMessage());
        }
        model.addAttribute("bidListDto",bidListDto);
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model,Authentication authentication) {
        model.addAttribute("username",authentication.getName());
        service.delete(id);
        log.info("Bid with id: {} deleted.", id);
        return "redirect:/bidList/list";
    }
}