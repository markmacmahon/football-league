package league;

import org.junit.Test;

import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class RankingTableTest {

    private static Path fromClasspath(String filename) throws URISyntaxException {
        return Paths.get(ClassLoader.getSystemResource(filename).toURI());
    }

    @Test
    public void shouldGroupAndCountPoints() {

        // given: 2 teams in 2 matches
        final List<Result> results = Arrays.asList(Result.fromString("Home 3, Away 0"), Result.fromString("Home 3, Away 1"));

        // when:
        final List<TeamPoints> ranking = RankingTable.groupAndCountPoints(results);

        // then:
        assertEquals(2, ranking.size()); // 2 teams
        final TeamPoints first = ranking.get(0);
        final TeamPoints second = ranking.get(1);

        assertEquals("Home", first.getTeam());
        assertEquals("Away", second.getTeam());
        assertEquals(6, first.getPoints());
        assertEquals(0, second.getPoints());
    }

    @Test
    public void shouldCalculatePlacements() {

        // given: expected ordering
        final TeamPoints first = new TeamPoints("first", 6);
        final TeamPoints second = new TeamPoints("second", 5);
        final TeamPoints third = new TeamPoints("third", 4);
        final TeamPoints jointThird = new TeamPoints("jointThird", 4);

        // when:
        final List<TeamPlacement> placements = RankingTable.calculatePlacements(Arrays.asList(first, second, jointThird, third));

        // then:
        assertEquals(4, placements.size());
        assertEquals("first", placements.get(0).getTeam());
        assertEquals(1, placements.get(0).getPlacement());
        assertEquals("second", placements.get(1).getTeam());
        assertEquals(2, placements.get(1).getPlacement());
        assertEquals(3, placements.get(2).getPlacement());
        assertEquals(3, placements.get(3).getPlacement());
    }


    @Test
    public void shouldRenderPlacementsAsString() throws Exception {
        // given:
        final String expectedContent = new String(Files.readAllBytes(fromClasspath("output-expected.txt")));

        // when:
        final String content = new RankingTable.Builder().build(fromClasspath("input-sample.txt")).toString();

        // then:
        assertEquals(expectedContent, content);
    }

    @Test
    public void shouldSortPlacementsInRankingTable(){
        // given:
        final TeamPlacement team1 = new TeamPlacement(1, new TeamPoints("team1", 1));
        final TeamPlacement team2 = new TeamPlacement(1, new TeamPoints("team2", 3));

        // when:  - testing that constructor sorts list
        final RankingTable rankingTable = new RankingTable(Arrays.asList(team1,team2));

        // then:
        assertEquals(2, rankingTable.getContent().size());
        assertEquals(team2, rankingTable.getContent().get(0));
        assertEquals(team1, rankingTable.getContent().get(1));
    }

    @Test
    public void shouldFormatTable(){
        // given:
        final TeamPlacement team1 = new TeamPlacement(1, new TeamPoints("team1", 3));
        final TeamPlacement team2 = new TeamPlacement(1, new TeamPoints("team2", 2));

        // when:
        final RankingTable rankingTable = new RankingTable(Arrays.asList(team1,team2));

        // then:
        assertEquals(2, rankingTable.getContent().size());
        assertTrue(rankingTable.toString().startsWith("1. team1, 3 pts"));
        assertTrue(rankingTable.toString().contains("1. team2, 2 pts"));
    }


}
