package league;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Testing Comparator functionality which is used for ranking by points/alphabetical order
 */
public class TeamPointsTest {

    @Test
    public void shouldRankReverseNaturalOrder() {
        // given:
        final TeamPoints homePoints = new TeamPoints("home", 3);
        final TeamPoints awayPoints = new TeamPoints("away", 1);

        // when,then:
        assertTrue(homePoints.compareTo(awayPoints) < 0);
        assertTrue(awayPoints.compareTo(homePoints) > 0);
    }

    @Test
    public void shouldRankAlphabeticalIfEqualPoints() {
        // given:
        final TeamPoints homePoints = new TeamPoints("home", 3);
        final TeamPoints awayPoints = new TeamPoints("away", 3); //A wins so result = negative

        // when:
        final int result = homePoints.compareTo(awayPoints);

        // them:
        assertTrue(result > 0);
    }
}
