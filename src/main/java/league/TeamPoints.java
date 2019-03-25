package league;

import java.util.Objects;

/**
 * Tuple that associates a team with points.
 */
class TeamPoints implements Comparable<TeamPoints> {

    private final String team;
    private final int points;

    public TeamPoints(String team, int points) {
        this.team = team;
        this.points = points;
    }

    public String getTeam() {
        return team;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeamPoints)) return false;
        final TeamPoints that = (TeamPoints) o;
        return points == that.points &&
                Objects.equals(team, that.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, points);
    }

    @Override
    public int compareTo(TeamPoints other) {
        final int byPoints = Integer.compare(getPoints(), other.getPoints()) * -1; //reverse natural order
        if (byPoints == 0) {
            //fallback to alphabetical order
            return getTeam().compareToIgnoreCase(other.getTeam());
        }
        return byPoints;
    }

}
