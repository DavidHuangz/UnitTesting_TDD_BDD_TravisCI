package nz.auckland.se754.a6.backend;

import java.util.*;

public class CardActivity {
    public static final List<String> DEFAULT_CARD_NUMBERS = new ArrayList<>(List.of("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"));
    public static final List<String> DEFAULT_CARD_SUITS = new ArrayList<>(List.of("Spades", "Hearts", "Clubs", "Diamonds"));

    private Database _db;

    private List<String> usable_numbers;
    private List<String> usable_suits;

    private Map<User, String> user_cards;
    private List<User> user_order;

    public CardActivity() {
        usable_numbers = new ArrayList<>(List.copyOf(DEFAULT_CARD_NUMBERS));
        usable_suits = new ArrayList<>(List.copyOf(DEFAULT_CARD_SUITS));
    }

    public CardActivity(Database db) {
        _db = db;
        usable_numbers = new ArrayList<>(List.copyOf(DEFAULT_CARD_NUMBERS));
        usable_suits = new ArrayList<>(List.copyOf(DEFAULT_CARD_SUITS));
    }

    public void setCustomNumbers(List<String> custom_numbers) {
        usable_numbers = custom_numbers;
    }

    public void setCustomSuits(List<String> custom_suits) {
        usable_suits = custom_suits;
    }

    public String getRandomCardNumber() {
        int random_card_index = (int)(Math.random() * usable_numbers.size());
        return usable_numbers.get(random_card_index);
    }

    public String[] getRandomCardNumbers(int n) {
        String[] card_numbers = new String[n];

        for (int i = 0; i < n; i++) {
            card_numbers[i] = getRandomCardNumber();
        }

        return card_numbers;
    }

    public String getRandomCardSuit() {
        int random_suit_index = (int)(Math.random() * usable_suits.size());
        return usable_suits.get(random_suit_index);
    }

    public String[] getRandomCardSuits(int n) {
        String[] card_suits = new String[n];

        for (int i = 0; i < n; i++) {
            card_suits[i] = getRandomCardSuit();
        }

        return card_suits;
    }

    public String generateRandomCard() {
        return getRandomCardNumber() + " " + getRandomCardSuit();
    }

    public Map<User, String> initialise(User[] users) {
        if (users.length < 2) {
            throw new UnsupportedOperationException("Card activities must have at least 2 users");
        }

        user_cards = new HashMap<>();
        user_order = Arrays.asList(users);

        for (User u : users) {
            String new_card = generateRandomCard();
            user_cards.put(u, new_card);
        }

        return user_cards;
    }

    public String getUserCard(User user) {
        if (user_cards == null) {
            throw new UnsupportedOperationException("Activity has not been initialised");
        }
        return user_cards.get(user);
    }

    public void swapUsers(User u1, User u2) {
        int index1 = user_order.indexOf(u1);
        int index2 = user_order.indexOf(u2);

        if (index1 == -1) {
            throw new UnsupportedOperationException(u1.getName() + " is not registered for this activity");
        } else if (index2 == -1) {
            throw new UnsupportedOperationException(u2.getName() + " is not registered for this activity");
        }

        user_order.set(index1, u2);
        user_order.set(index2, u1);
    }

    public List<User> getOrder() {
        return user_order;
    }

    public void save() {
        _db.saveActivity(usable_numbers, usable_suits);
    }

    public List<String[]> getResult() {
        List<String[]> result = new ArrayList<>();

        for (User user : user_order) {
            String user_name = user.getName();
            String user_card = getUserCard(user);

            result.add(new String[]{user_name, user_card});
        }

        return result;
    }

    public List<String> getNumbers() {
        return usable_numbers;
    }

    public List<String> getSuits() {
        return usable_suits;
    }
}
