import edu.princeton.cs.algs4.StdOut;
import org.junit.Before;
import org.junit.Test;

/**
 * @author jacka
 * @version 1.0 on 6/20/2016.
 */
public class BaseballEliminationTestTeam7 {

    @Before
    public void setUp() {
        BaseballElimination division = new BaseballElimination("test/teams7.txt");
        for (String team : division.teams()) {
            if (division.isEliminated(team)) {
                StdOut.print(team + " is eliminated by the subset R = { ");
                for (String t : division.certificateOfElimination(team)) {
                    StdOut.print(t + " ");
                }
                StdOut.println("}");
            }
            else {
                StdOut.println(team + " is not eliminated");
            }
        }
    }

    @Test
    public void testIsEliminated() {
        
    }
}