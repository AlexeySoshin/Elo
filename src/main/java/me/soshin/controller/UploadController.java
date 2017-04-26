package me.soshin.controller;


import me.soshin.dao.PlayerDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Allows uploading new versions of files
 * Both files must be uploaded together
 */
@RestController
public class UploadController {

    @Resource
    PlayerDAO playerDAO;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity upload(@RequestParam("players") final MultipartFile players,
                                 @RequestParam("games") final MultipartFile games) throws IOException {

        this.playerDAO.load(players.getInputStream(), games.getInputStream());

        return new ResponseEntity(HttpStatus.OK);
    }
}
