package com.votingball.votingball.controllers;

import com.votingball.votingball.PollForTestPreparer;
import com.votingball.votingball.dao.PollsRepository;
import com.votingball.votingball.entity.Poll;
import com.votingball.votingball.entity.Position;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Michal Rychlewicz
 */
@RunWith(SpringRunner.class)
@WebMvcTest(PollsController.class)
public class PollsControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PollsRepository pollsRepository;

    @Test
    public void shouldReturnAllPolls_whenSuchRequested() throws Exception {
        //given
        Poll firstPoll = PollForTestPreparer.preparePoll("title1");
        Position positionOfFirstPoll_1 = new Position(1,"First position",3);
        Position positionOfFirstPoll_2 = new Position(2,"Second position",1);
        firstPoll.setPositions(new LinkedHashSet<>(Arrays.asList(positionOfFirstPoll_1,positionOfFirstPoll_2)));

        Poll secondPoll = PollForTestPreparer.preparePoll("title2");
        Position positionOfSecondPoll_1 = new Position(1,"Answer #1",0);
        Position positionOfSecondPoll_2 = new Position(2,"Answer #2",0);
        Position positionOfSecondPoll_3 = new Position(3,"Answer #3",2);
        secondPoll.setPositions(new LinkedHashSet<>(Arrays.asList(positionOfSecondPoll_1,positionOfSecondPoll_2,positionOfSecondPoll_3)));

        Poll thirdPoll = new Poll("title without positions");

        Poll pollWithoutAnything = new Poll();

        List<Poll> polls = new ArrayList<>(Arrays.asList(firstPoll,secondPoll,thirdPoll,pollWithoutAnything));
        when(pollsRepository.findAll()).thenReturn(polls);

        //when then
        mvc.perform(get("/api/allpolls").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect()
    }

}