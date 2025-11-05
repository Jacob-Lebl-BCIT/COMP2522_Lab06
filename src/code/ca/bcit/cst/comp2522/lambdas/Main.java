package ca.bcit.cst.comp2522.lambdas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * Main class demonstrates the use of various functional interfaces and lambda expressions
 * with a hockey team roster.
 *
 * @author Jacob Lebl, Samuel Pita
 * @version 1.0
 */
public class Main
{
    private static final int CURRENT_YEAR              = 2025;
    private static final int MIN_AGE_FOR_ELIGIBILITY   = 20;
    private static final int MIN_GOALS_FOR_ELIGIBILITY = 15;
    private static final int MIN_GOALS_FOR_HIGH_SCORER = 20;

    /**
     * Creates and returns a sample hockey team with a preset roster of players.
     *
     * @return a HockeyTeam with sample players
     */
    private static HockeyTeam sampleTeam()
    {
        final List<HockeyPlayer> players;

        players = new ArrayList<>();
        players.add(new HockeyPlayer("Alex Morgan", "F", 2002, 21));
        players.add(new HockeyPlayer("Ben Carter", "D", 1999, 6));
        players.add(new HockeyPlayer("Casey Young", "F", 2004, 28));
        players.add(new HockeyPlayer("Drew Singh", "G", 2000, 0));
        players.add(new HockeyPlayer("Eva Chen", "D", 2001, 5));
        players.add(new HockeyPlayer("Fiona Lee", "F", 2003, 17));

        return new HockeyTeam("BCIT Blizzards", players);
    }

    /**
     * Main method demonstrating lambda expressions with various functional interfaces.
     *
     * @param args command line arguments (not used)
     */
    public static void main(final String[] args)
    {
        final HockeyTeam team;
        final List<HockeyPlayer> roster;

        team   = sampleTeam();
        roster = team.getRoster();

        System.out.println("=== BCIT Blizzards Hockey Team ===\n");

        // Task 1: Supplier - create a call-up player and add to roster
        task1Supplier(roster);

        // Task 2: Predicate - filter forwards with 20+ goals
        task2Predicate(roster);

        // Task 3: Function - map player to label string
        task3Function(roster);

        // Task 4: Consumer - print names
        task4Consumer(roster);

        // Task 5: UnaryOperator - uppercase names
        task5UnaryOperator(roster);

        // Task 6: Comparator - sort by goals descending
        task6Comparator(roster);

        // Task 7: Aggregation - calculate total goals
        task7Aggregation(roster);

        // Task 8: Custom Functional Interface - eligibility rule
        task8EligibilityRule(roster);
    }

    /**
     * Task 1: Demonstrates Supplier to create a new call-up player.
     *
     * @param roster the team roster to add the player to
     */
    private static void task1Supplier(final List<HockeyPlayer> roster)
    {
        final Supplier<HockeyPlayer> callUp;

        System.out.println("--- Task 1: Supplier<HockeyPlayer> ---");

        callUp = () -> new HockeyPlayer("Jordan Smith", "F", 2005, 12);
        roster.add(callUp.get());

        System.out.println("Call-up player added: " + callUp.get().getName());
        System.out.println("Roster size: " + roster.size() + "\n");
    }

    /**
     * Task 2: Demonstrates Predicate to filter forwards with 20+ goals.
     *
     * @param roster the team roster to filter
     */
    private static void task2Predicate(final List<HockeyPlayer> roster)
    {
        final Predicate<HockeyPlayer> isForward;
        final Predicate<HockeyPlayer> has20PlusGoals;

        System.out.println("--- Task 2: Predicate<HockeyPlayer> ---");

        isForward      = player -> player.getPosition().equals(HockeyPlayer.FORWARD);
        has20PlusGoals = player -> player.getGoals() >= MIN_GOALS_FOR_HIGH_SCORER;

        System.out.println("Forwards with 20+ goals:");
        for (final HockeyPlayer player : roster)
        {
            if (isForward.test(player) && has20PlusGoals.test(player))
            {
                System.out.println("  " + player.getName() + " - " + player.getGoals() + " goals");
            }
        }
        System.out.println();
    }

    /**
     * Task 3: Demonstrates Function to map a player to a label string.
     *
     * @param roster the team roster to process
     */
    private static void task3Function(final List<HockeyPlayer> roster)
    {
        final Function<HockeyPlayer, String> playerLabel;

        System.out.println("--- Task 3: Function<HockeyPlayer, String> ---");

        playerLabel = player -> player.getName() + " - " + player.getGoals() + "G";

        System.out.println("Player labels:");
        for (final HockeyPlayer player : roster)
        {
            System.out.println("  " + playerLabel.apply(player));
        }
        System.out.println();
    }

    /**
     * Task 4: Demonstrates Consumer to print player names.
     *
     * @param roster the team roster to process
     */
    private static void task4Consumer(final List<HockeyPlayer> roster)
    {
        final Consumer<HockeyPlayer> printName;

        System.out.println("--- Task 4: Consumer<HockeyPlayer> ---");

        printName = player -> System.out.println("  " + player.getName());

        System.out.println("All player names:");
        for (final HockeyPlayer player : roster)
        {
            printName.accept(player);
        }
        System.out.println();
    }

    /**
     * Task 5: Demonstrates UnaryOperator to convert names to uppercase.
     *
     * @param roster the team roster to process
     */
    private static void task5UnaryOperator(final List<HockeyPlayer> roster)
    {
        final UnaryOperator<String> toUpperCase;

        System.out.println("--- Task 5: UnaryOperator<String> ---");

        toUpperCase = name -> name.toUpperCase();

        System.out.println("Player names in uppercase:");
        for (final HockeyPlayer player : roster)
        {
            System.out.println("  " + toUpperCase.apply(player.getName()));
        }
        System.out.println();
    }

    /**
     * Task 6: Demonstrates Comparator to sort players by goals in descending order.
     *
     * @param roster the team roster to sort
     */
    private static void task6Comparator(final List<HockeyPlayer> roster)
    {
        final Comparator<HockeyPlayer> byGoalsDescending;

        System.out.println("--- Task 6: Comparator<HockeyPlayer> ---");

        byGoalsDescending = (p1, p2) -> p2.getGoals() - p1.getGoals();

        Collections.sort(roster, byGoalsDescending);

        System.out.println("Players sorted by goals (descending):");
        for (final HockeyPlayer player : roster)
        {
            System.out.println("  " + player.getName() + " - " + player.getGoals() + " goals");
        }
        System.out.println();
    }

    /**
     * Task 7: Demonstrates loop-based aggregation to calculate total goals.
     *
     * @param roster the team roster to process
     */
    private static void task7Aggregation(final List<HockeyPlayer> roster)
    {
        int totalGoals;

        System.out.println("--- Task 7: Aggregation (loop-based) ---");

        totalGoals = 0;
        for (final HockeyPlayer player : roster)
        {
            totalGoals += player.getGoals();
        }

        System.out.println("Total team goals: " + totalGoals + "\n");
    }

    /**
     * Task 8: Demonstrates custom functional interface EligibilityRule.
     *
     * @param roster the team roster to process
     */
    private static void task8EligibilityRule(final List<HockeyPlayer> roster)
    {
        final EligibilityRule eligibilityRule;

        System.out.println("--- Task 8: Custom Functional Interface (EligibilityRule) ---");

        eligibilityRule = (player, minAge, minGoals, currentYear) ->
        {
            final int age;

            age = currentYear - player.getYearOfBirth();
            return age >= minAge && player.getGoals() >= minGoals;
        };

        System.out.println("Eligible players (age >= " + MIN_AGE_FOR_ELIGIBILITY +
                           ", goals >= " + MIN_GOALS_FOR_ELIGIBILITY + "):");
        for (final HockeyPlayer player : roster)
        {
            if (eligibilityRule.test(player, MIN_AGE_FOR_ELIGIBILITY,
                                             MIN_GOALS_FOR_ELIGIBILITY,
                                             CURRENT_YEAR))
            {
                final int age;

                age = CURRENT_YEAR - player.getYearOfBirth();
                System.out.println("  " + player.getName() + " (age " + age +
                                   ", " + player.getGoals() + " goals)");
            }
        }
        System.out.println();
    }
}

