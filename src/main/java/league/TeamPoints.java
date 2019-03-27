package league;

import java.util.Objects;

/**
 * Tuple that associates a team with points.
 */
class TeamPoints implements Comparable<TeamPoints> {

    private final String team;
    private final int points;
    private final int goalsFor;
    private final int goalsAgainst;


    public TeamPoints(String team, int points, int goalsFor, int goalsAgainst) {
        this.team = team;
        this.points = points;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
    }

    public TeamPoints(String team, int points) {
        this(team,points,0,0);
    }


    public String getTeam() {
        return team;
    }

    public int getPoints() {
        return points;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public static TeamPoints add(TeamPoints t1, TeamPoints t2){
        return new TeamPoints(t1.getTeam(),t1.getPoints()+t2.getPoints(),
                t1.getGoalsFor()+t2.getGoalsFor(),
                t1.getGoalsAgainst()+t2.getGoalsAgainst());
    }


    public int getGoalDifference(){
        return  goalsFor - goalsAgainst;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeamPoints)) return false;
        final TeamPoints that = (TeamPoints) o;
        return points == that.points &&
                goalsFor == that.goalsFor &&
                goalsAgainst == that.goalsAgainst &&
                Objects.equals(team, that.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, points, goalsFor, goalsAgainst);
    }

    @Override
    public int compareTo(TeamPoints other) {
        int byPoints = Integer.compare(getPoints(), other.getPoints()) * -1; //reverse natural order
        if (byPoints == 0) {
            byPoints = Integer.compare(getGoalDifference(), other.getGoalDifference()) * -1;
        }
        if (byPoints == 0) {
            //fallback to alphabetical order
            return getTeam().compareToIgnoreCase(other.getTeam());
        }
        return byPoints;
    }




}
