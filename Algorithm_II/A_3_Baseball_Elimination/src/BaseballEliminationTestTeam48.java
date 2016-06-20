import edu.princeton.cs.algs4.StdOut;
import org.junit.Before;
import org.junit.Test;

/**
 * @author jacka
 * @version 1.0 on 6/20/2016.
 */
public class BaseballEliminationTestTeam48 {

    private BaseballElimination division;
    @Before
    public void setUp() {
        division = new BaseballElimination("test/teams48.txt");
    }

    @Test
    public void testIsEliminated() {
        System.out.println(division.isEliminated("Team6"));
        System.out.println(division.certificateOfElimination("Team6"));
    }
}