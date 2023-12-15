package ngordnet.main;
import java.util.*;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.LinkedList;


public class Graph {
    private int size;
    private Map<Integer,LinkedList<Integer>> edges;
    private Map<Integer,Node> allNode;
    private Map<String,Set<Integer>> wordToNodes;

    class Node {
        private Integer index;
        private List<String> words;
        private LinkedList<Integer> neighbors;

        public Node(Integer index, String[] words) {
            this.index = index;
            this.words = new ArrayList<>(Arrays.asList(words));

        }

        public Integer getIndex() {
            return index;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }

        public List<String> getWords() {
            return words;
        }
        public void addWord(String word) {
            words.add(word);
        }

        public void setWords(List<String> words) {
            this.words = words;
        }

        public boolean contains(String word) {
            return words.contains(word);
        }
        public void addNeighbor(int neighborid){

            neighbors.add(neighborid);
        }
    }




    public Graph() {
        this.size = 0;
        this.edges = new HashMap<>();
        this.allNode = new HashMap<>();
        this.wordToNodes = new HashMap<>();
    }

    //    Map<Node,Set<String>> allNode;
    public void createNode(int index, String[] words) {
        if (allNode.containsKey(index)) {
            Node node = allNode.get(index);
            for (String word : words) {
                if (!node.contains(word)) {
                    node.addWord(word);
                }
                if (wordToNodes.containsKey(word)) {
                    wordToNodes.get(word).add(index);
                } else {
                    Set<Integer> nodes = new HashSet<>();
                    nodes.add(index);
                    wordToNodes.put(word, nodes);
                }
            }
        } else {
            Node node = new Node(index, words);
            allNode.put(index, node);
            size++;
            for (String word : words) {
                if (wordToNodes.containsKey(word)) {
                    wordToNodes.get(word).add(index);
                } else {
                    Set<Integer> nodes = new HashSet<>();
                    nodes.add(index);
                    wordToNodes.put(word, nodes);
                }
            }
        }
    }
    public void addEdge(int sid, int eid){
        if(!edges.containsKey(sid)){
            LinkedList<Integer> tar_id = new LinkedList<>();
            tar_id.add(eid);
            edges.put(sid,tar_id);
        }else{
            edges.get(sid).add(eid);
        }
    }

    //NEED TO FIX
    public Set<Integer> getNode(String word){
        Set<Integer> result = new HashSet<>();
        for (Map.Entry<Integer, Node> entry : allNode.entrySet()) {
            Node node = entry.getValue();
            if (node.contains(word)) {
                result.add(entry.getKey());
            }
        }
        return result;
    }
    public Graph.Node getNode(int index) {
        return allNode.get(index);
    }
    public List<Integer> getNeighbors(int index) {
        LinkedList<Integer> neighbors = edges.get(index);
        if (neighbors != null) {
            return this.edges.get(index);
        } else {
            return new ArrayList<Integer>();
        }
    }
//    public List<String> getWords(int index) {
//        Node node = allNode.get(index);
//        if (node != null) {
//            return new ArrayList<>(node.getWords());
//        } else {
//            return new ArrayList<>();
//        }
//    }
//    public boolean getNeighbor(int index) {
//        LinkedList<Integer> neighbors = edges.get(index);
//        if (neighbors != null) {
//            return true;
//        }
//        return false;
//    }



//        for (Node node : allNode.keySet()){
//            if(node.getIndex()==index){
//                for(String word :words){
//                    if(!node.getWords().contains(word)){
//                        node.getWords().add(word);
//                    }
//                }
//            }
//            else {
//                node = new Node(index,words);
//            }
//            allNode.put(node,)
//        }






    public int size(){
        return size;
    }
    //    public void addEdge(int start, int end){
//        if(!edges.containsKey(start)){
//            edges.put(start, new HashSet<>());
//        }
//        edges.get(start).add(end);
//    }

}
