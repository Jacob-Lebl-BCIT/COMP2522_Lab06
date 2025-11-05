package ca.bcit.cst.comp2522.lambdas;

import java.util.List;

/**
 * HockeyTeam represents a hockey team with a name and a roster of players.
 *
 * @author Jacob Lebl, Samuel Pita
 * @version 1.0
 */
public class HockeyTeam
{
    private final String name;
    private final List<HockeyPlayer> roster;

    /**
     * Constructs a new HockeyTeam with the specified name and roster.
     *
     * @param name   the team's name
     * @param roster the list of players on the team
     */
    public HockeyTeam(final String name, final List<HockeyPlayer> roster)
    {
        this.name = validateName(name);
        this.roster = validateRoster(roster);
    }

    /**
     * Validates the team's name.
     *
     * @param name the name to validate
     * @return the validated name
     * @throws IllegalArgumentException if the name is null or blank
     */
    public static String validateName(final String name)
    {
        if (name == null || name.trim().isBlank())
        {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        return name;
    }

    /**
     * Validates the team's roster.
     *
     * @param roster the roster to validate
     * @return the validated roster
     * @throws IllegalArgumentException if the roster is null
     */
    public static List<HockeyPlayer> validateRoster(final List<HockeyPlayer> roster)
    {
        if (roster == null)
        {
            throw new IllegalArgumentException("Roster cannot be null");
        }
        return roster;
    }

    /**
     * Gets the team's name.
     *
     * @return the team's name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Gets the team's roster of players.
     *
     * @return the list of players on the team
     */
    public List<HockeyPlayer> getRoster()
    {
        return roster;
    }
}
