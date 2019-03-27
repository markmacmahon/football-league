package league;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
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

        // then:
        assertTrue(result > 0);
    }

    @Test
    public void shouldAddPoints(){
        // given:
        final TeamPoints points1 = new TeamPoints("points1", 3,4,2);
        final TeamPoints points2 = new TeamPoints("points2", 2,5,1);

        // when:
        final TeamPoints result = TeamPoints.add(points1,points2);

        // then:
        assertEquals(5,result.getPoints());
        assertEquals(5,result.getPoints());
        assertEquals(9,result.getGoalsFor());
        assertEquals(3,result.getGoalsAgainst());
        assertEquals(6,result.getGoalDifference());


    }
}
