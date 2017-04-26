package me.soshin.controller;

import me.soshin.controller.exception.PlayerNotFoundException;
import me.soshin.model.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SuggestionControllerTest {


    @Resource
    SuggestionController controller;

    @Test(expected = PlayerNotFoundException.class)
    public void suggestedPlayersNoPlayer() throws Exception {

        controller.suggestedPlayers(99);
    }

    @Test
    public void suggestedPlayers() throws Exception {

        List<Player> suggested = controller.suggestedPlayers(31);

        assertEquals(3, suggested.size());

        assertEquals("Tai", suggested.get(0).getName());
    }


    @Test
    public void suggestedPlayersFirst() throws Exception {

        List<Player> suggested = controller.suggestedPlayers(36);

        assertEquals(3, suggested.size());

        assertEquals("Hunter", suggested.get(0).getName());
        assertEquals("Fernanda", suggested.get(1).getName());
    }

        @Test
    public void suggestedPlayersLast() throws Exception {

        List<Player> suggested = controller.suggestedPlayers(7);

        assertEquals(3, suggested.size());

        assertEquals("Johanne", suggested.get(0).getName());
        assertEquals("Bunny", suggested.get(1).getName());
        assertEquals("Tracey", suggested.get(2).getName());
    }

}