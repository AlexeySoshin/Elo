package me.soshin.dao;


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
public class PlayerDAOTest {


    @Resource
    PlayerDAO playerDAO;

    @Test
    public void testRanking() {
        List<Player> ranked = this.playerDAO.getPlayersRanked();

        assertEquals(36, (int) ranked.get(0).getId());

        assertEquals(7, (int) ranked.get(39).getId());
    }
}