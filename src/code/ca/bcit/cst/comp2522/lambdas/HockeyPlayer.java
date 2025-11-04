package ca.bcit.cst.comp2522.lambdas;

/**
 * HockeyPlayer Represents a hockey player with basic attributes like
 * name, position, birth year, and goals scored.
 *
 * @author Jacob Lebl, Samuel Pita
 * @version 2025
 */
public class HockeyPlayer
{
    private final String name;
    private       String position;
    private final int    yearOfBirth;
    private       int    goals;


    public static final int    GOAL_INCREMENT = 1;
    public static final String FORWARD        = "F";
    public static final String DEFENCE        = "D";
    public static final String GOALIE         = "G";

    public static final int MINIMUM_NON_NEGATIVE_VALUE = 0;

    /**
     * Constructs a new HockeyPlayer with the specified attributes.
     *
     * @param name        the player's name
     * @param position    the player's position (F, D, or G)
     * @param yearOfBirth the player's birth year
     * @param goals       number of goals scored by the player
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public HockeyPlayer(final String name,
                        final String position,
                        final int yearOfBirth,
                        final int goals)
    {
        this.name        = validateName(name);
        this.position    = validatePosition(position);
        this.yearOfBirth = validateYearOfBirth(yearOfBirth);
        this.goals       = validateGoals(goals);
    }

    /**
     * Sets the player's position after validating it with
     * {@link #validatePosition(String) validatePosition}.
     *
     * @param position
     */
    public void setPosition(final String position)
    {
        this.position = validatePosition(position);
    }

    /**
     * Gets the player's position.
     *
     * @return {@link #position position}
     */
    public String getPosition()
    {
        return position;
    }

    /**
     * Gets the player's number of goals.
     *
     * @return goals
     */
    public int getGoals()
    {
        return goals;
    }

    /**
     * Sets the player's number of goals after validating it with
     * {@link #validateGoals(int) validateGoals}.
     *
     * @param goals
     */
    public void setGoals(final int goals)
    {
        this.goals = validateGoals(goals);
    }

    /**
     * Increments the player's goals by {@value GOAL_INCREMENT}.
     */
    public void addGoal()
    {
        goals += GOAL_INCREMENT;
    }

    /**
     * validateGoals ensures that the given goals are not negative.
     *
     * @param goals
     * @return goals if valid
     * @throws IllegalArgumentException if goals are less than {@value MINIMUM_NON_NEGATIVE_VALUE}
     */
    private int validateGoals(final int goals)
    {
        if (goals >= MINIMUM_NON_NEGATIVE_VALUE)
        {
            return goals;
        }
        throw new IllegalArgumentException("goals cannot be less than " +
                                           MINIMUM_NON_NEGATIVE_VALUE);
    }

    /**
     * validateYearOfBirth ensures that the given year is not negative.
     *
     * @param yearOfBirth
     * @return yearOfBirth if valid
     * @throws IllegalArgumentException when year of birth is < {@value MINIMUM_NON_NEGATIVE_VALUE}
     */
    private int validateYearOfBirth(final int yearOfBirth)
    {
        if (yearOfBirth >= MINIMUM_NON_NEGATIVE_VALUE)
        {
            return yearOfBirth;
        }

        throw new IllegalArgumentException("yearOfBirth cannot be less than " +
                                           MINIMUM_NON_NEGATIVE_VALUE);
    }


    /**
     * validateName ensures the passed string is not null and not empty.
     *
     * @param name a name to validate.
     * @return the same name if it's valid.
     * @throws IllegalArgumentException if the name is null or empty
     */
    public static String validateName(final String name)
    {
        if (name == null || name.isBlank())
        {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        return name;
    }

    /**
     * validatePosition ensures the passed string is not null and is one of (case agnostic):
     * <ul>
     *  <li>{@value FORWARD}</li>
     *  <li>{@value DEFENCE}</li>
     *  <li>{@value GOALIE}</li>
     * </ul>
     *
     * @param position
     * @return trimmed and capitalized version of the input position.
     */
    public static String validatePosition(final String position)
    {
        if (position == null)
        {
            throw new IllegalArgumentException("Position cannot be null");
        }

        String formattedPosition;

        formattedPosition = position.trim().toUpperCase();

        if (formattedPosition.equals(FORWARD) ||
            formattedPosition.equals(DEFENCE) ||
            formattedPosition.equals(GOALIE))
        {
            return formattedPosition;
        }

        throw new IllegalArgumentException("position is not one of constants FORWARD," +
                                           "DEFENCE, or GOALIE");

    }


}
