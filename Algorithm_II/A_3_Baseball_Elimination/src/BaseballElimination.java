import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.StdOut;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @author jacka
 * @version 1.0 on 6/20/2016.
 */
public class BaseballElimination {
    private int[] w;
    private int[] l;
    private int[] r;
    private int[][] g;
    private String[] names;
    private Map<String, Integer> nameToIndex;

    // create a baseball division from given filename in format specified below
    public BaseballElimination(String filename) {
        String line;
        nameToIndex = new HashMap<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            line = bufferedReader.readLine();
            int teamNum = Integer.parseInt(line);
            w = new int[teamNum];
            l = new int[teamNum];
            r = new int[teamNum];
            g = new int[teamNum][teamNum];
            names = new String[teamNum];
            int row = 0;
            while ((line = bufferedReader.readLine()) != null) {
                /* Atlanta       83 71  8  0 1 6 1 */
                String[] items = line.trim().split("\\s+");
                names[row] = items[0];
                nameToIndex.put(items[0], row);
                w[row] = Integer.parseInt(items[1]);
                l[row] = Integer.parseInt(items[2]);
                r[row] = Integer.parseInt(items[3]);
                for (int col = 0; col < teamNum; col++) {
                    g[row][col] = Integer.parseInt(items[col + 4]);
                }
                row++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // number of teams
    public int numberOfTeams() {
        return w.length;
    }

    // all teams
    public Iterable<String> teams() {
        return Arrays.asList(names);
    }

    // number of wins for given team
    public int wins(String team) {
        if (!nameToIndex.containsKey(team))
            throw new IllegalArgumentException();
        return w[nameToIndex.get(team)];
    }

    // number of losses for given team
    public int losses(String team) {
        if (!nameToIndex.containsKey(team))
            throw new IllegalArgumentException();
        return l[nameToIndex.get(team)];
    }

    // number of remaining games for given team
    public int remaining(String team) {
        if (!nameToIndex.containsKey(team))
            throw new IllegalArgumentException();
        return r[nameToIndex.get(team)];
    }

    // number of remaining games between team1 and team2
    public int against(String team1, String team2) {
        if (!nameToIndex.containsKey(team1) || !nameToIndex.containsKey(team2))
            throw new IllegalArgumentException();
        return g[nameToIndex.get(team1)][nameToIndex.get(team2)];
    }

    // is given team eliminated?
    public boolean isEliminated(String team) {
        if (!nameToIndex.containsKey(team))
            throw new IllegalArgumentException();
        int x = nameToIndex.get(team);
        int teamNum = numberOfTeams();

        for (int i = 0; i < teamNum; i++) {
            if (w[x] + r[x] - w[i] < 0)
                return true;
        }
        /* index starts at 0 */
        /* Structure:
        *  match node ; team node; source ; terminate */
        int matchNum = teamNum * (teamNum - 1) / 2;
        int sourceId = matchNum + teamNum;
        int terminateId = sourceId + 1;

        FlowNetwork flowNetwork = initFlowNetwork(teamNum, x);
        FordFulkerson fordFulkerson = new FordFulkerson(flowNetwork, sourceId, terminateId);

        for (FlowEdge flowEdge : flowNetwork.adj(sourceId)) {
            if (flowEdge.flow() != flowEdge.capacity())
                return true; /* 1176 --> 1083 */
        }
        return false;
    }

    // subset R of teams that eliminates given team; null if not eliminated
    public Iterable<String> certificateOfElimination(String team) {
        if (!nameToIndex.containsKey(team))
            throw new IllegalArgumentException();

        int x = nameToIndex.get(team);
        int teamNum = numberOfTeams();

        Set<String> resultSet = new HashSet<>();
        for (int i = 0; i < teamNum; i++) {
            if (w[x] + r[x] - w[i] < 0) {
                resultSet.add(names[i]);
            }
        }
        if (!resultSet.isEmpty()) return  resultSet;
        /* index starts at 0 */
        /* Structure:
        *  match node ; team node; source ; terminate */
        int matchNum = teamNum * (teamNum - 1) / 2;
        int sourceId = matchNum + teamNum;
        int terminateId = sourceId + 1;

        FlowNetwork flowNetwork = initFlowNetwork(teamNum, x);
        FordFulkerson fordFulkerson = new FordFulkerson(flowNetwork, sourceId, terminateId);

        for (FlowEdge flowEdge : flowNetwork.adj(sourceId)) {
            if (flowEdge.flow() != flowEdge.capacity()) {
                for (String name : names) {
                    if (fordFulkerson.inCut(nameToIndex.get(name) + matchNum))
                        resultSet.add(name);
                }
            }
        }
        if (resultSet.isEmpty()) return null;
        return resultSet;
    }

    private FlowNetwork initFlowNetwork(int teamNum, int x) {
        int matchNum = teamNum * (teamNum - 1) / 2;
        int sourceId = matchNum + teamNum;
        int terminateId = sourceId + 1;
        int nodeId = 0;

        int nodeNum = 2 + matchNum + teamNum;
        FlowNetwork flowNetwork= new FlowNetwork(nodeNum);
        for (int i = 0; i < teamNum; i++) {
            for (int j = i + 1; j < teamNum; j++) {

                flowNetwork.addEdge(new FlowEdge(sourceId, nodeId, g[i][j]));
                flowNetwork.addEdge(new FlowEdge(nodeId, matchNum + i, Integer.MAX_VALUE));
                flowNetwork.addEdge(new FlowEdge(nodeId, matchNum + j, Integer.MAX_VALUE));

                nodeId++;
            }
            flowNetwork.addEdge(new FlowEdge(matchNum + i, terminateId, Math.max(0, w[x] + r[x] - w[i])));
        }
        return flowNetwork;
    }


    public static void main(String[] args) {
        BaseballElimination division = new BaseballElimination("test/teams1.txt");
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


}
