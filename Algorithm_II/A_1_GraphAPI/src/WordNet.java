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

    private final Map<Integer,String> idToSynset;
    private final Map<Integer,Set<Integer>> ancestors;
    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        idToSynset = new HashMap<>();
        ancestors = new HashMap<>();
        initSynsets(synsets);
        Digraph graph = initHypernyms(hypernyms);

        if(!isRootedDAG(graph))
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
            if(!graph.adj(i).iterator().hasNext()) {
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
        /* format: 36,AND_circuit AND_gate,a circuit in a computer that fires only when all of its inputs fire */
        // put (36, AND_circuit) into map
        while (file.hasNextLine()) {
            String line = file.readLine();
            String[] items = line.split(",");
            Set<Integer> subset = new HashSet<>();
            Integer head = Integer.valueOf(items[0]);
            for (int i = 1; i < items.length; i++) {
                Integer item = Integer.valueOf(items[i]);
                subset.add(item);
                resultGraph.addEdge(head, item);
            }
            ancestors.put(head, subset);
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
            idToSynset.put(Integer.valueOf(items[0]), items[1]);
        }
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return idToSynset.values();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        return idToSynset.values().contains(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB))
            throw new IllegalArgumentException("one of noun is not a noun");

        Set<Integer> idsOfNounA = ancestors.get(idToSynset.get(nounA));
        Set<Integer> idsOfNounB = ancestors.get(idToSynset.get(nounB));
        return sap.length(idsOfNounA, idsOfNounB);
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException("Both words must be nouns!");
        }
        Set<Integer> idsOfNounA = ancestors.get(idToSynset.get(nounA));
        Set<Integer> idsOfNounB = ancestors.get(idToSynset.get(nounB));
        int ancestor = sap.ancestor(idsOfNounA, idsOfNounB);
        return idToSynset.get(ancestor);

    }

    // do unit testing of this class
    public static void main(String[] args) {

    }
}