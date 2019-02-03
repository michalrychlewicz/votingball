package com.votingball.votingball.controllers;

import com.votingball.votingball.dao.PollsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PollsController {

    @Autowired
    private PollsDao pollsDao;

    @GetMapping("/allpolls")
    public String getAllPolls(Model model)
    {
        model.addAttribute("allPolls",pollsDao.findAll());
        return "all-polls";
    }


}
