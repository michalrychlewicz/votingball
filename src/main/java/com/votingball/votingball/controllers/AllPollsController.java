package com.votingball.votingball.controllers;

import com.votingball.votingball.dao.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AllPollsController {

    @Autowired
    private PollRepository repository;

    @GetMapping("/allpolls")
    public String getAllPolls(Model model)
    {
        model.addAttribute("allPolls",repository.findAll());
        return "all-polls";
    }
}
