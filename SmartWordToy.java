// Define the right node (word, level) data structure is the key
// Define the 4x4 table for forbidden states is also helpful

import java.util.*;

public class SmartWordToy {
    
    private class Node {

        public String n;
        public int level;

        public Node(String n, int level) {
            this.n = n;
            this.level = level;
        }

    }

    public static boolean[][][][] ftable = new boolean[26][26][26][26];

    public void initFTable(String[] forbid) {

        Set<String> term = new HashSet<String>();

        for (int i = 0; i < forbid.length; i++) { //["le fe sed ab", ]
            String fi = forbid[i]; // "le fe sed ab"
            if (term.contains(fi)) {
                break;
            } else {
                term.add(fi);
            }

            String[] parts = fi.split(" "); // [le, fe, sed, ab]
            int hit = 0;
            char cha = 'a';
            for (int a = 0; a < parts[0].length(); a++) {
                for (int b = 0; b < parts[1].length(); b++) {
                    for (int c = 0; c < parts[2].length(); c++) {
                        for (int d = 0; d < parts[3].length(); d++) {
                            ftable[parts[0].charAt(a)- cha][parts[1].charAt(b)- cha][parts[2].charAt(c)- cha][parts[3].charAt(d)- cha] = true;
                        }
                    }
                }
            }
        }

    }

    public int minPresses(String start, String finish, String[] forbid) {

        // Initialize the forbid table
        this.initFTable(forbid);

        if (this.isForbidden(finish)){
            return -1;
        }
        
        if (forbid.length == 0){
            char a = 'a';
            char z = 'z';
            int distance = 0;
            for (int i=0; i< 4; i++) {
                int d = Math.abs(finish.charAt(i) - start.charAt(i)) > 13 ? (26 - Math.abs(finish.charAt(i) - start.charAt(i))) : Math.abs(finish.charAt(i) - start.charAt(i));
                distance += d;
            };
            return distance;
        }

        // Start to expand until meet finish
        LinkedList<Node> bfsqueue = new LinkedList<Node>();
        LinkedList<Node> nodes = new LinkedList<Node>();
        Set<String> founds = new HashSet<String>();

        bfsqueue.add(new Node(start, 0));
        founds.add(start);

        while (bfsqueue.size() > 0) {
            Node node = bfsqueue.get(0);
            nodes = this.nextCandidates(node, founds);
            Iterator it = nodes.iterator();
            while (it.hasNext()) {
                
                Node n = (Node)it.next();
                if (n.n.equals(finish)) {
          
                    return n.level;
                } else {
                    // not match
                    bfsqueue.add(n);
                    founds.add(n.n);
                }
            }

            bfsqueue.remove(0);

        }

        return -1;

    };

    public boolean isForbidden(String start) {
        char cha = 'a';
        return ftable[start.charAt(0) - cha][start.charAt(1) - cha][start.charAt(2) - cha][start.charAt(3) - cha];
    }

    // find possible next candidates
    public LinkedList<Node> nextCandidates(Node node, Set<String> founds) {

        LinkedList<Node> ss = new LinkedList<Node>();
        String word = node.n;

        for (int a = 0; a< 4; a++) {
            char[] w1 = word.toCharArray();
            char[] w2 = word.toCharArray();

            char c1 = word.charAt(a);
            char lastC = ( c1 == 'a' )? 'z' : (char)(c1-1);
            char nextC = ( c1 == 'z' )? 'a' : (char)(c1+1);


            w1[a] = lastC;
            w2[a] = nextC;

            String temps = new String(w1);
            String temps1 = new String(w2);

            if (!founds.contains(temps) && !this.isForbidden(temps)) {
                ss.add(new Node(temps, node.level + 1));
            }
            if (!founds.contains(temps1) && !this.isForbidden(temps1)) {
                ss.add(new Node(temps1,node.level + 1));
            }

        }

        return ss;
    };
    
}
