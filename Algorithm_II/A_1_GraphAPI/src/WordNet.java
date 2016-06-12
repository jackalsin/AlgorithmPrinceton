import edu.princeton.cs.algs4.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author jacka
 * @version 1.0 on 6/11/2016.
 */
public class WordNet {
    private final SAP sap;

    private final Map<Integer, String> idToSynset;
    private final Map<String, Set<Integer>> ancestors;

    public WordNet(String synsets, String hypernyms) {
        if (synsets == null)
            throw new NullPointerException(synsets + " should not be null");
        if (hypernyms == null)
            throw new NullPointerException(hypernyms + " should not be null");
        idToSynset = new HashMap<>();
        ancestors = new HashMap<>();
        initSynsets(synsets);
        Digraph graph = initHypernyms(hypernyms);

        if (!isRootedDAG(graph))
            throw new IllegalArgumentException("Graph created by "
                           + hypernyms + " is not a rooted DAG");

        sap = new SAP(graph);
    }

    private boolean isRootedDAG(Digraph graph) {
        if (new DirectedCycle(graph).hasCycle())
            throw new IllegalArgumentException("Graph has a cycle");
        int rootNumber = 0;
        for (int i = 0; i < graph.V(); i++) {
            // measurement: no outgoing links
            if (!graph.adj(i).iterator().hasNext()) {
                rootNumber++;
                if (rootNumber > 1) return false;
            }
        }
        return true;
    }

    private Digraph initHypernyms(String hypernyms) {
        // 164,21012,56099
        Digraph resultGraph = new Digraph(idToSynset.keySet().size());
        In file = new In(hypernyms);
        /* format: 34,47569,48084 */
        while (file.hasNextLine()) {
            String line = file.readLine();
            String[] items = line.split(",");
            Integer head = Integer.valueOf(items[0]);
            for (int i = 1; i < items.length; i++) {
                Integer item = Integer.valueOf(items[i]);
                resultGraph.addEdge(head, item);
            }
        }
        return resultGraph;

    }

    private void initSynsets(String synsets) {
        In file = new In(synsets);
        /* format: 36,AND_circuit AND_gate,a circuit in a computer that fires only when all of its inputs fire */
        // put (36, AND_circuit) into map
        while (file.hasNextLine()) {
            String line = file.readLine();
            String[] items = line.split(",");
            String[] words = items[1].split(" ");
            int id = Integer.valueOf(items[0]);
            idToSynset.put(id, items[1]);
            for (String word : words) {
                Set<Integer> ids = ancestors.getOrDefault(word, new HashSet<>());
                ids.add(id);
                ancestors.put(word, ids);
            }

        }
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return ancestors.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null) throw new NullPointerException(word + " should not be null");
        return ancestors.keySet().contains(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if ((!isNoun(nounA)) || (!isNoun(nounB)))
            throw new IllegalArgumentException("one of noun is not a noun");

        Set<Integer> idsOfNounA = ancestors.get(nounA);
        Set<Integer> idsOfNounB = ancestors.get(nounB);
        return sap.length(idsOfNounA, idsOfNounB);
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if ((!isNoun(nounA)) || (!isNoun(nounB))) {
            throw new IllegalArgumentException("Both words must be nouns!");
        }
        Set<Integer> idsOfNounA = ancestors.get(nounA);
        Set<Integer> idsOfNounB = ancestors.get(nounB);
        int ancestor = sap.ancestor(idsOfNounA, idsOfNounB);
        if (ancestor == -1)
            return null;
        else
            return idToSynset.get(ancestor);

    }

    // do unit testing of this class
    public static void main(String[] args) {

    }
}