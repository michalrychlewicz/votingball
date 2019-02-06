package com.votingball.votingball.controllers;

import com.votingball.votingball.dao.PollsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PollsController {

    @Autowired
    private PollsRepository pollsRepository;

    @GetMapping("/allpolls")
    public String getAllPolls(Model model)
    {
        model.addAttribute("allPolls", pollsRepository.findAll());
        return "all-polls";
    }


}
