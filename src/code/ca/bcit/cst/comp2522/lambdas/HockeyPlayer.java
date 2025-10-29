package ca.bcit.cst.comp2522.lambdas;

public class HockeyPlayer
{
    private final String name;
    private       String position;
    private final int    yearOfBirth;
    private       int    goals;


    public static final String FORWARD = "F";
    public static final String DEFENCE = "D";
    public static final String GOALIE  = "G";

    public HockeyPlayer(final String name,
                        final String position,
                        final int yearOfBirth,
                        final int goals)
    {

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
            return position;
        }

        throw new IllegalArgumentException("position is not one of constants FORWARD," +
                                           "DEFENCE, or GOALIE");

    }

    ;
}
