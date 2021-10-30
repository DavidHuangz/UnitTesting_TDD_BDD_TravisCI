package nz.auckland.se754.a6.backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

public class CardActivityTest {
    private final List<String> VALID_CARD_NUMBERS = Arrays.asList("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K");
    private final List<String> VALID_CARD_SUITS = Arrays.asList("Spades", "Hearts", "Clubs", "Diamonds");

    private CardActivity card_activity;
    private Database db;

    @BeforeEach
    public void setupEach() {
        db = Mockito.mock(Database.class);
        card_activity = new CardActivity(db);
    }

    @Test
    @Tag("UnitTest")
    public void testCanGenerateValidCardNumber() {
        String card_number = card_activity.getRandomCardNumber();
        assertTrue(VALID_CARD_NUMBERS.contains(card_number));
    }

    @Test
    @Tag("UnitTest")
    public void testCanGenerateMultipleCardNumbers() {
        String[] card_numbers = card_activity.getRandomCardNumbers(2);
        assertTrue(VALID_CARD_NUMBERS.contains(card_numbers[0]));
        assertTrue(VALID_CARD_NUMBERS.contains(card_numbers[1]));
    }

    @Test
    @Tag("UnitTest")
    public void testCanGenerateValidCardSuit() {
        String card_suit = card_activity.getRandomCardSuit();
        assertTrue(VALID_CARD_SUITS.contains(card_suit));
    }

    @Test
    @Tag("UnitTest")
    public void testCanGenerateMultipleCardSuits() {
        String[] card_suits = card_activity.getRandomCardSuits(2);
        assertTrue(VALID_CARD_SUITS.contains(card_suits[0]));
        assertTrue(VALID_CARD_SUITS.contains(card_suits[1]));
    }

    @Test
    @Tag("UnitTest")
    public void testCanUseCustomCardNumbers() {
        List<String> custom_numbers = new ArrayList<>(List.of("A", "B", "C"));

        card_activity.setCustomNumbers(custom_numbers);
        String random_number = card_activity.getRandomCardNumber();
        assertTrue(custom_numbers.contains(random_number));
    }

    @Test
    @Tag("UnitTest")
    public void testCanUseCustomCardSuits() {
        List<String> custom_suits = new ArrayList<>(List.of("dogs", "cats", "birds"));

        card_activity.setCustomSuits(custom_suits);
        String random_suit = card_activity.getRandomCardSuit();
        assertTrue(custom_suits.contains(random_suit));
    }

    @Test
    @Tag("UnitTest")
    public void testCanGenerateDefaultCard() {
        String card = card_activity.generateRandomCard();

        String[] card_components = card.split(" ");
        assertTrue(VALID_CARD_NUMBERS.contains(card_components[0]));
        assertTrue(VALID_CARD_SUITS.contains(card_components[1]));
    }

    @Test
    @Tag("UnitTest")
    public void testCanInitialiseCardSetForUsers() {
        User user1 = Mockito.mock(User.class);
        User user2 = Mockito.mock(User.class);
        User[] users = new User[]{user1, user2};

        Map<User, String> cards = card_activity.initialise(users);
        String card = cards.get(user1);

        String[] card_components = card.split(" ");
        assertTrue(VALID_CARD_NUMBERS.contains(card_components[0]));
        assertTrue(VALID_CARD_SUITS.contains(card_components[1]));
    }

    @Test
    @Tag("UnitTest")
    public void testErrorWhenInitialiseWithoutEnoughUsers() {
        try {
            User user = Mockito.mock(User.class);
            User[] users = new User[]{user};

            card_activity.initialise(users);
            fail();
        } catch (UnsupportedOperationException e) {
            assertEquals("Card activities must have at least 2 users", e.getMessage());
        }
    }

    @Test
    @Tag("UnitTest")
    public void testUserCanGetTheirCard() {
        User user1 = Mockito.mock(User.class);
        User user2 = Mockito.mock(User.class);
        User[] users = new User[]{user1, user2};

        Map<User, String> cards = card_activity.initialise(users);

        String first_user_card = card_activity.getUserCard(user1);
        assertEquals(cards.get(user1), first_user_card);
    }

    @Test
    @Tag("UnitTest")
    public void testErrorForUninitialisedCardAccess() {
        try {
            card_activity.getUserCard(null);

            fail();
        } catch (UnsupportedOperationException e) {
            assertEquals("Activity has not been initialised", e.getMessage());
        }
    }

    @Test
    @Tag("UnitTest")
    public void testUsersCanSwapOrder() {
        User user1 = Mockito.mock(User.class);
        User user2 = Mockito.mock(User.class);
        User[] users = new User[]{user1, user2};

        card_activity.initialise(users);
        card_activity.swapUsers(user1, user2);

        List<User> swapped_users = card_activity.getOrder();

        assertEquals(swapped_users.get(0), user2);
        assertEquals(swapped_users.get(1), user1);
    }

    @Test
    @Tag("UnitTest")
    public void testErrorForSwappingInvalidFirstUser() {
        try {
            User user1 = Mockito.mock(User.class);
            User user2 = Mockito.mock(User.class);
            User user3 = Mockito.mock(User.class);
            Mockito.when(user3.getName()).thenReturn("user3");

            User[] users = new User[]{user1, user2};
            card_activity.initialise(users);
            card_activity.swapUsers(user3, user1);

            fail();
        } catch (UnsupportedOperationException e) {
            assertEquals("user3 is not registered for this activity", e.getMessage());
        }
    }

    @Test
    @Tag("UnitTest")
    public void testErrorForSwappingInvalidSecondUser() {
        try {
            User user1 = Mockito.mock(User.class);
            User user2 = Mockito.mock(User.class);
            User user3 = Mockito.mock(User.class);
            Mockito.when(user3.getName()).thenReturn("user3");

            User[] users = new User[]{user1, user2};
            card_activity.initialise(users);
            card_activity.swapUsers(user1, user3);

            fail();
        } catch (UnsupportedOperationException e) {
            assertEquals("user3 is not registered for this activity", e.getMessage());
        }
    }

    @Test
    @Tag("UnitTest")
    public void testCanSaveActivitySettings() {
        card_activity.save();
        Mockito.verify(db, times(1)).saveActivity(VALID_CARD_NUMBERS, VALID_CARD_SUITS);
    }

    @Test
    @Tag("UnitTest")
    public void testActivityResult() {
        User user1 = Mockito.mock(User.class);
        User user2 = Mockito.mock(User.class);

        Mockito.when(user1.getName()).thenReturn("user1");
        Mockito.when(user2.getName()).thenReturn("user2");

        User[] users = new User[]{user1, user2};
        card_activity.initialise(users);

        String user1_card = card_activity.getUserCard(user1);
        String user2_card = card_activity.getUserCard(user2);
        List<String[]> result = card_activity.getResult();

        assertEquals(result.get(0)[0], "user1");
        assertEquals(result.get(0)[1], user1_card);
        assertEquals(result.get(1)[0], "user2");
        assertEquals(result.get(1)[1], user2_card);
    }
}