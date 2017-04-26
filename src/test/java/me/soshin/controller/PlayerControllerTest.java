package me.soshin.controller;

import me.soshin.controller.exception.PlayerNotFoundException;
import me.soshin.model.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PlayerControllerTest {


    @Resource
    PlayerController controller;

    @Test
    public void getPlayer() throws Exception {

        Player p = controller.getPlayer(31);

        assertEquals("Fernanda", p.getName());
    }

    @Test(expected = PlayerNotFoundException.class)
    public void getPlayerNotFound() throws Exception {

       controller.getPlayer(311);
    }

}