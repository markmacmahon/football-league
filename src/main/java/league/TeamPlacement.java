package league;

/**
 * Placement of team in table, most notably dealing with situation where
 * teams on the same number of points share the same placement number in the table.
 */
public class TeamPlacement extends TeamPoints {

    private final int placement;

    public TeamPlacement(int placement, TeamPoints points) {
        super(points.getTeam(), points.getPoints(),points.getGoalsFor(),points.getGoalsAgainst());
        this.placement = placement;
    }

    public int getPlacement() {
        return placement;
    }

    /**
     * Formatting string according to output file format.
     */
    @Override
    public String toString() {
        final String pts = getPoints() == 1 ? "pt" : "pts";
        return String.format("%s. %s, %s %s GD: %s", placement, getTeam(), getPoints(), pts,getGoalDifference());
    }

}
