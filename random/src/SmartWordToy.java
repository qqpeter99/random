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
    
    public void found(String word) { //share the same big table is helpful
        char cha = 'a';
        ftable[word.charAt(0)- cha][word.charAt(1)- cha][word.charAt(2)- cha][word.charAt(3)- cha] = true;
    }

    public int minPresses(String start, String finish, String[] forbid) {

        // Initialize the forbid table
        this.initFTable(forbid);

        if (this.isForbidden(finish)){
            return -1;
        }
        
        if (forbid.length == 0){
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

        bfsqueue.add(new Node(start, 0));
        found(start);

        while (bfsqueue.size() > 0) {
            Node node = bfsqueue.get(0);
            nodes = this.nextCandidates(node);
            Iterator it = nodes.iterator();
            while (it.hasNext()) {
                
                Node n = (Node)it.next();
                if (n.n.equals(finish)) {
          
                    return n.level;
                } else {
                    // not match
                    bfsqueue.add(n);
                    found(n.n);
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
    public LinkedList<Node> nextCandidates(Node node) {

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

            if (!this.isForbidden(temps)) {
                ss.add(new Node(temps, node.level + 1));
            }
            if (!this.isForbidden(temps1)) {
                ss.add(new Node(temps1,node.level + 1));
            }

        }

        return ss;
    };

    public static void main(String[] arg){
        // aaaa, zzzz, []
//        "zzzz"
//        "aaaa"
//        {"abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk"}
//        String[] forbid = new String[]{"abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk"};
        
        String start = "aaaa";
        String finish = "mmll";
//        String[] forbid = new String[]{"jac jbc jabd jbcd", "jab j jcd jab", "jbd jabd jbc j", "jacd jb jac jb", "jabc ja jb jc", "jabd jbd jabd jac", "jb jc jab jbd", "jc jb jacd j", "jc jb ja jd", "jd jacd jcd jc", "jbc ja jc jbc", "j jabcd ja jabc", "jc ja jabcd jab", "jc jac jbcd jac", "jbd jac jbc ja", "jcd jabc jc jab", "jbc jab jad jacd", "jbc j jac jd", "jbc jcd jc jacd", "jd jbcd jb jcd", "jabc jcd jabc jbc", "jacd jacd jd jb", "jbcd jbd jd jc", "jabd jb jabc j", "jbc jc jc jabcd", "jacd jc jad jbc", "jacd jabd jabcd j", "jabc jcd jabd jad", "jab jabcd j jac", "jbd jabd jabc jab", "jbc jbd jd jbcd", "j jbcd jcd jbcd", "jc jabd jbc jabc", "jcd jabcd jb jb", "ja jc jc j", "jd jac jbd jab", "jbc jbc ja jab", "jbcd jabc jad jbd", "jacd jcd jd jbc", "jabc jabcd jc j", "jabd ja jb jac", "ja jbc jabc jab", "jabd jbd jc jabd", "jd jab jbc jabd", "jbc jab jbc jc", "ja jbd jbcd jbcd", "jac jbcd jabcd jabd", "jb jad ja jbd", "jabcd j ja jb", "jab jc jb jab"};
//        String[] forbid1 = new String[]{"cdefghijklmnopqrstuvwxyz a a a", "a cdefghijklmnopqrstuvwxyz a a", "a a cdefghijklmnopqrstuvwxyz a", "a a a cdefghijklmnopqrstuvwxyz"};
        String[] forbid2 = new String[]{"c d e f"};
        long startTime = System.nanoTime();
        int i = new SmartWordToy().minPresses(start, finish, forbid2);
        long endTime = System.nanoTime();

        System.out.println("result is " + i + " with " + (endTime-startTime)/1000000 + " ms");

    };
    
}
