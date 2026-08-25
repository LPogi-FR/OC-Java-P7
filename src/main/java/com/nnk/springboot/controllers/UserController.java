package com.nnk.springboot.controllers;

import com.nnk.springboot.dto.UserDto;
import com.nnk.springboot.exception.IdNotFoundException;
import com.nnk.springboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
public class UserController {
    @Autowired
    private UserService service;

    @RequestMapping("/user/list")
    public String home(Model model)
    {
        model.addAttribute("users", service.findAll());
        return "user/list";
    }

    @GetMapping("/user/add")
    public String addUser(UserDto userDto,Model model) {

        model.addAttribute("userDto",userDto);

        return "user/add";
    }

    @PostMapping("/user/validate")
    public String validate(@Valid UserDto userDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            log.error(result.getAllErrors().toString());
            //model.addAttribute("userDto",userDto);
            return "user/add";

        }
        service.save((userDto));
        model.addAttribute("userDto", userDto);
        return "redirect:/user/list";
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model,RedirectAttributes redirectAttributes) {

        //model.addAttribute("userDto", service.findById(id));
        try{
            model.addAttribute("userDto",service.findById(id));
        }catch (IdNotFoundException e){
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            log.error("User with id: {} not found.", id);
        }
        return "user/update";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid UserDto userDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/update";
        }

        service.update(id,userDto);
        model.addAttribute("userDto", service.findAll());
        return "redirect:/user/list";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        service.delete(id);
        log.info("User with id: {} deleted.", id);
        return "redirect:/user/list";
    }
}
