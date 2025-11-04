package ca.bcit.cst.comp2522.lambdas;

import java.util.List;


/**
 * HockeyTeam represents a hockey team with a name and a roster of players.
 *
 * @author Jacob Lebl, Samuel Pita
 * @version 2025
 */
public class HockeyTeam
{
    private final String             name;
    private final List<HockeyPlayer> roster;

    /**
     * HockeyTeam constructs a new HockeyTeam with the specified name and roster.
     *
     * @param name   the team's name
     * @param roster the team's roster of players
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public HockeyTeam(final String name, final List<HockeyPlayer> roster)
    {
        this.name = HockeyPlayer.validateName(name);
        this.roster = validateRoster(roster);
    }

    /**
     * validateRoster ensures the roster is not null or empty.
     * @param roster
     * @return roster if valid
     */
    private List<HockeyPlayer> validateRoster(final List<HockeyPlayer> roster)
    {
        if (roster == null || roster.isEmpty())
        {
            throw new IllegalArgumentException("Roster cannot be null or empty.");
        }
        return roster;
    }


}
