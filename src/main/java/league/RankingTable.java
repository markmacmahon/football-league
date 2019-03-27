package league;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Generates Ranking table based on a list of results.
 */
public class RankingTable {

    private final List<TeamPlacement> content;

    RankingTable(List<TeamPlacement> content) {
        this.content = new ArrayList<>(content);
        Collections.sort(this.content);
    }

    static List<TeamPoints> groupAndCountPoints(Collection<Result> results) {
        return results.stream()
                .map(Result::determinePoints)
                .flatMap(List::stream)
                .collect(Collectors
                        .groupingBy(TeamPoints::getTeam,
                                Collectors.reducing((o, o2) -> TeamPoints.add(o,o2))))
                .entrySet().stream()
                .filter(entry -> entry.getValue().isPresent())
                .map(entry -> entry.getValue().get())
                .sorted(TeamPoints::compareTo)
                .collect(Collectors.toList());
    }


    static List<TeamPlacement> calculatePlacements(Collection<TeamPoints> teamPoints) {
        final List<TeamPlacement> placements = new ArrayList<>();
        int index = 1;
        int rankedPosition = 1;
        TeamPoints previous = null;
        for (TeamPoints current : teamPoints) {
            // Teams on the same number of points share the same placed position.
            // Different points to previous so revert to index position.
            if ((previous != null) && (pointsAndGoalsComparator.compare(current,previous) != 0)) {
                rankedPosition = index;
            }
            //otherwise no change in placed position
            placements.add(new TeamPlacement(rankedPosition, current));
            previous = current;
            index++;
        }
        return placements;

    }

    public List<TeamPlacement> getContent() {
        return content;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (TeamPlacement placement : getContent()) {
            builder.append(placement.toString());
            builder.append("\n");
        }
        return builder.toString();
    }

    static class Builder {

        public RankingTable build(Stream<String> results) {
            final List<TeamPoints> teamPoints = groupAndCountPoints(results.map(Result::fromString).collect(Collectors.toList()));
            return new RankingTable(calculatePlacements(teamPoints));
        }

        public RankingTable build(Path path) throws IOException {
            try (final Stream<String> stream = Files.lines(path)) {
                return build(stream);
            }
        }

    }

    static Comparator<TeamPoints> pointsAndGoalsComparator = (first, other) -> {
        final int byPoints = Integer.compare(first.getPoints(), other.getPoints()) * -1; //reverse natural order
        if (byPoints == 0) {
            return Integer.compare(first.getGoalDifference(), other.getGoalDifference()) * -1;

        }
        return byPoints;
    };
}
