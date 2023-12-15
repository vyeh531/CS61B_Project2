package ngordnet.main;
import edu.princeton.cs.algs4.In;

import java.util.*;

public class WordNet {
    private Graph graph;
    private Map<String, Set<Integer>> wordToNodeIds;


    public WordNet(String synsetsfile, String hyponymsfile){
        graph = new Graph();
        In synset = new In(synsetsfile);
        In hyponyms = new In(hyponymsfile);
        while (synset.hasNextLine()) {
            String[] synline = synset.readLine().split(",");
            Integer syindex = Integer.parseInt(synline[0]);
            String[] synWords = synline[1].split(" ");
            graph.createNode(syindex, synWords);
        }
        while(hyponyms.hasNextLine()){
            String[] hyponline = hyponyms.readLine().split(",");
            Integer hyindex = Integer.parseInt(hyponline[0]);
            LinkedHashSet<Integer> allhyid = new LinkedHashSet<>();
            allhyid.add(hyindex);
            for(int i =1; i< hyponline.length; i++){
                allhyid.add(Integer.parseInt(hyponline[i]));
            }
            for (Integer id : allhyid) {

                graph.addEdge(hyindex,id);
            }
        }
    }


    public void hyponyms_helper(Integer id,Set<String> hyponms_words){

        List node_words = graph.getNode(id).getWords();
        hyponms_words.addAll(node_words);
    }

    public List<Integer> hyponyms_id(Integer id) {
        List<Integer> children = new LinkedList<Integer>();
        List<Integer> neighbors = graph.getNeighbors(id);
        children.add(id);
        neighbors.remove(id);
        if (!neighbors.isEmpty()) {
            for (int neigh : neighbors) {
                children.add(neigh);
                children.addAll(hyponyms_id(neigh));
            }
        }
        return children;
    }
//        List<Integer> allID = new LinkedList<Integer>();
//        Queue<Integer> queue = new LinkedList<Integer>();
//        Set<Integer> visited = new HashSet<Integer>();
//
//        queue.add(id);
//
//        while (!queue.isEmpty()) {
//            Integer currentId = queue.poll();
//
//            if (!visited.contains(currentId)) {
//                visited.add(currentId);
//                List<Integer> neighbors = graph.getNeighbors(currentId);
//
//                for (Integer neighborId : neighbors) {
//                    allID.add(neighborId);
//                    queue.add(neighborId);
//                }
//            }
//        }
//        return allID;
//    }




    public Set<String> hyponyms(String word){

        Set<Integer> nodeID = graph.getNode(word);
        Set<String> hyponms_words = new TreeSet<>();
        List<Integer> get_all= new LinkedList<Integer>();
        for(Integer sub_id: nodeID){
            get_all=hyponyms_id(sub_id);
            for(Integer sub_each: get_all){
                hyponyms_helper(sub_each,hyponms_words);
            }
        }
        return hyponms_words;
}

}



