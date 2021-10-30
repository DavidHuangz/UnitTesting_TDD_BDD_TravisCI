package nz.auckland.se754.a6.controller;

import nz.auckland.se754.a6.service.CardActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

@Controller
public class CardActivityConfigController {
    @Autowired
    CardActivityService service;

    @GetMapping("/card-activity")
    public String configView(Map<String, Object> model) {
        service.reset();

        model.put("values", service.getValues());
        model.put("suits", service.getSuits());
        return "card-activity-config";
    }

    @PostMapping("/remove-value")
    public String removeValue(String card_value, Map<String, Object> model) {
        try {
            service.removeValue(card_value);
        } catch (UnsupportedOperationException e) {
            model.put("error", "Must have at least one value");
        }

        model.put("values", service.getValues());
        model.put("suits", service.getSuits());
        return "card-activity-config";
    }

    @PostMapping("/remove-suit")
    public String removeSuit(String card_suit, Map<String, Object> model) {
        try {
            service.removeSuit(card_suit);
        } catch (UnsupportedOperationException e) {
            model.put("error", "Must have at least one suit");
        }

        model.put("values", service.getValues());
        model.put("suits", service.getSuits());
        return "card-activity-config";
    }

    @PostMapping("/add-value")
    public String addValue(String new_value, Map<String, Object> model) {
        service.addValue(new_value);

        model.put("values", service.getValues());
        model.put("suits", service.getSuits());
        return "card-activity-config";
    }

    @PostMapping("/add-suit")
    public String addSuit(String new_suit, Map<String, Object> model) {
        service.addSuit(new_suit);

        model.put("values", service.getValues());
        model.put("suits", service.getSuits());
        return "card-activity-config";
    }
}
