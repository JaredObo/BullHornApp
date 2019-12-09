package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    MessageRepository msgrep;

    @RequestMapping("/")
    public String twitterPost(Model model){
        model.addAttribute("post", msgrep.findAll());
            return "post";
    }
    @GetMapping("/add")
    public String newPost(Model model){
        model.addAttribute("post", new Message());
            return "postform";
    }
    @PostMapping("/process")
    public String processPost(@Valid Message twPost, BindingResult result){
        if (result.hasErrors()) {
            return "postform";
        }

        msgrep.save(twPost);
        return "redirect:/";
    }
}
