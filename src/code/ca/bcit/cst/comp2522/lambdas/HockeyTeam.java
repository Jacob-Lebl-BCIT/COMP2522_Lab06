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
     * @throws IllegalArgumentException if name is null or blank, or if roster is null
     */
    public HockeyTeam(final String name, final List<HockeyPlayer> roster)
    {
        if (name == null || name.isBlank())
        {
            throw new IllegalArgumentException("Team name cannot be null or blank");
        }
        if (roster == null)
        {
            throw new IllegalArgumentException("Roster cannot be null");
        }
        this.name = name;
        this.roster = roster;
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
