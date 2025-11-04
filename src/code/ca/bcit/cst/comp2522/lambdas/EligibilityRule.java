package ca.bcit.cst.comp2522.lambdas;

/**
 * EligibilityRule is a functional interface that determines if a hockey player
 * meets specified eligibility criteria based on age and goal requirements.
 *
 * @author Jacob Lebl, Samuel Pita
 * @version 1.0
 */
@FunctionalInterface
public interface EligibilityRule
{
    /**
     * Tests whether a player meets the eligibility requirements.
     *
     * @param player      the hockey player to test
     * @param minAge      the minimum age requirement
     * @param minGoals    the minimum number of goals required
     * @param currentYear the current year to calculate age from year of birth
     * @return true if the player meets both the age and goal requirements, false otherwise
     */
    boolean test(final HockeyPlayer player, final int minAge, final int minGoals, final int currentYear);
}
