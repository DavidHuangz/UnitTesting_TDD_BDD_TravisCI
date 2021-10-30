package nz.auckland.se754.a6.controller;

import nz.auckland.se754.a6.service.CardActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

@Controller
public class CardActivityGameController {
    @Autowired
    CardActivityService service;

    @GetMapping("/card-activity/play")
    public String playView(Map<String, Object> model) {
        service.resetPlayers();
        model.put("players", service.getPlayers());
        return "card-activity-play";
    }

    @PostMapping("/card-activity/add-player")
    public String addPlayer(String new_player, Map<String, Object> model) {
        try {
            service.addPlayer(new_player);
        } catch (UnsupportedOperationException e) {
            model.put("error", "Name is already being used");
        }

        model.put("players", service.getPlayers());
        return "card-activity-play";
    }

    @PostMapping("/card-activity/play-game")
    public String playGame(Map<String, Object> model) {
        try {
            List<String[]> cards = service.initialise();
            model.put("cards", cards);
        } catch (UnsupportedOperationException e) {
            model.put("players", service.getPlayers());
            model.put("error", "Must have at least 2 players");
        }

        return "card-activity-play";
    }

    @PostMapping("/card-activity/select-card")
    public String selectCard(String player, Map<String, Object> model) {
        service.selectPlayer(player);

        model.put("selected_player", service.getSelectedPlayer());
        model.put("cards", service.getCards());
        return "card-activity-play";
    }
}
