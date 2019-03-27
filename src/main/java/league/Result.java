package league;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Structured representation of result with logic for determining points.
 */
public class Result {
    private String homeTeam;
    private int homeGoals;
    private String awayTeam;
    private int awayGoals;

    public static Result fromString(String s) {
        final String[] values = s.split(",");
        final String homeTeamAndGoals = values[0].trim();
        final String awayTeamAndGoals = values[1].trim();
        int splitPosition = homeTeamAndGoals.lastIndexOf(" ");
        final Result result = new Result();
        result.homeTeam = homeTeamAndGoals.substring(0, splitPosition);
        result.homeGoals = Integer.parseInt(homeTeamAndGoals.substring(splitPosition + 1));
        splitPosition = awayTeamAndGoals.lastIndexOf(" ");
        result.awayTeam = awayTeamAndGoals.substring(0, splitPosition);
        result.awayGoals = Integer.parseInt(awayTeamAndGoals.substring(splitPosition + 1));
        return result;
    }

    public List<TeamPoints> determinePoints() {
        final int home;
        final int away;
        if (this.homeGoals == this.awayGoals) {
            home = 1;
            away = 1;
        } else if (this.homeGoals > this.awayGoals) {
            home = 3;
            away = 0;
        } else {
            home = 0;
            away = 3;
        }
        return Arrays.asList(new TeamPoints(this.homeTeam, home,homeGoals,awayGoals), new TeamPoints(this.awayTeam, away,awayGoals,homeGoals));
    }

    public String getHomeTeam() {
        return homeTeam;
    }


    public int getHomeGoals() {
        return homeGoals;
    }


    public String getAwayTeam() {
        return awayTeam;
    }

    public int getAwayGoals() {
        return awayGoals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Result)) return false;
        final Result result = (Result) o;
        return homeGoals == result.homeGoals &&
                awayGoals == result.awayGoals &&
                Objects.equals(homeTeam, result.homeTeam) &&
                Objects.equals(awayTeam, result.awayTeam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeTeam, homeGoals, awayTeam, awayGoals);
    }

    /**
     * Formatting string according to input file format.
     */
    @Override
    public String toString() {
        return String.format("%s %s, %s %s", homeTeam, homeGoals, awayTeam, awayGoals);
    }


}
