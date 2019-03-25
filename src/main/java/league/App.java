package league;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Run with single command line argument for input file name. Assuming well formed input files.
 */
public class App {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Missing input file");
        }
        final RankingTable rankingTable = new RankingTable.Builder().build(Paths.get(args[0]));
        System.out.println(rankingTable.toString());
    }
}
