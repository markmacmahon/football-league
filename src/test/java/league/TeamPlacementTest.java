package league;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Testing Formatting of String representation
 */
public class TeamPlacementTest {

    @Test
    public void shouldFormatPointsAccordingToNumber() {
        assertEquals("1. foo, 0 pts GD: 0", new TeamPlacement(1, new TeamPoints("foo", 0)).toString());
        assertEquals("1. foo, 1 pt GD: 0", new TeamPlacement(1, new TeamPoints("foo", 1)).toString());
        assertEquals("1. foo, 2 pts GD: 0", new TeamPlacement(1, new TeamPoints("foo", 2)).toString());
    }

}
