package league;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Assuming well formed input so testing happy path only.
 */
public class ResultTest {

    @Test
    public void shouldParseStringResult() {
        // given:
        final String resultString = "Home 3, Away 2";

        // when:
        final Result result = Result.fromString(resultString);

        // then:
        assertEquals("Home", result.getHomeTeam());
        assertEquals(3, result.getHomeGoals());
        assertEquals(2, result.getAwayGoals());
        assertEquals("Away", result.getAwayTeam());
    }


    @Test
    public void shouldDetermineDraw() {
        // given:
        final Result result = Result.fromString("home 1, away 1");

        // when:
        final List<TeamPoints> teamPoints = result.determinePoints();
        final TeamPoints home = teamPoints.stream().filter(points -> points.getTeam().equals("home")).findFirst().get();
        final TeamPoints away = teamPoints.stream().filter(points -> points.getTeam().equals("away")).findFirst().get();

        // then:
        assertEquals(1, home.getPoints());
        assertEquals(1, away.getPoints());
    }


    @Test
    public void shouldDetermineHomeWin() {
        // given:
        final Result result = Result.fromString("home 1, away 0");

        // when:
        final List<TeamPoints> teamPoints = result.determinePoints();
        final TeamPoints home = teamPoints.stream().filter(points -> points.getTeam().equals("home")).findFirst().get();
        final TeamPoints away = teamPoints.stream().filter(points -> points.getTeam().equals("away")).findFirst().get();

        // then:
        assertEquals(3, home.getPoints());
        assertEquals(0, away.getPoints());
    }

    @Test
    public void shouldDetermineAwayWin() {
        // given:
        final Result result = Result.fromString("home 0, away 1");

        // when:
        final List<TeamPoints> teamPoints = result.determinePoints();
        final TeamPoints home = teamPoints.stream().filter(points -> points.getTeam().equals("home")).findFirst().get();
        final TeamPoints away = teamPoints.stream().filter(points -> points.getTeam().equals("away")).findFirst().get();

        // then:
        assertEquals(0, home.getPoints());
        assertEquals(3, away.getPoints());
    }

}
