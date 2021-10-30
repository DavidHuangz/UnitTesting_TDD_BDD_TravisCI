package nz.auckland.se754.a6.service;

import nz.auckland.se754.a6.backend.CardActivity;
import nz.auckland.se754.a6.backend.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardActivityService {
    private static CardActivity activity = new CardActivity();
    private static List<User> players = new ArrayList<>();

    private User selected_user;

    public List<String> getValues() {
        return activity.getNumbers();
    }

    public List<String> getSuits() {
        return activity.getSuits();
    }

    public void removeValue(String value) {
        List<String> numbers = activity.getNumbers();
        if (numbers.size() > 1) {
            numbers.remove(value);
            activity.setCustomNumbers(numbers);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public void removeSuit(String suit) {
        List<String> suits = activity.getSuits();
        if (suits.size() > 1) {
            suits.remove(suit);
            activity.setCustomSuits(suits);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public void reset() {
        activity = new CardActivity();
    }

    public void addValue(String new_value) {
        List<String> numbers = activity.getNumbers();
        numbers.add(new_value);
        activity.setCustomNumbers(numbers);
    }

    public void addSuit(String new_suit) {
        List<String> suits = activity.getSuits();
        suits.add(new_suit);
        activity.setCustomSuits(suits);
    }

    public List<String> getPlayers() {
        List<String> player_names = new ArrayList<>();

        for (User u : players) {
            player_names.add(u.getName());
        }
        return player_names;
    }

    public void addPlayer(String new_player) {
        for (User user : players) {
            if (user.getName().equals(new_player)) {
                throw new UnsupportedOperationException();
            }
        }

        User u = new User("", "");
        u.setName(new_player);
        players.add(u);
    }

    public void resetPlayers() {
        players = new ArrayList<>();
    }

    public List<String[]> initialise() {
        User[] user_array = new User[players.size()];
        user_array = players.toArray(user_array);

        activity.initialise(user_array);

        return activity.getResult();
    }

    public Object getCards() {
        return activity.getResult();
    }

    public void selectPlayer(String player) {
        User new_select = null;

        for (User u : players) {
            if (u.getName().equals(player)) {
                new_select = u;
            }
        }

        if (selected_user == null) {
            selected_user = new_select;
        } else {
            activity.swapUsers(selected_user, new_select);

            selected_user = null;
        }
    }

    public Object getSelectedPlayer() {
        String player_name = "";

        if (selected_user != null) {
            player_name = selected_user.getName();
        }
        return player_name;
    }
}
