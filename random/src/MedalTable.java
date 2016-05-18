import java.util.*;

public class MedalTable {


    public String[] generate(String[] results){
        
        Map<String, CM> r = new HashMap<String, CM>();

        for (String result: results) {

            // parts for 3 country names
            String[] ps = result.split(" ");

            // set the default
            if ( !r.containsKey(ps[0])) {
                r.put(ps[0], new CM(ps[0], 0, 0, 0));
            }

            if ( !r.containsKey(ps[1])) {
                r.put(ps[1], new CM(ps[1], 0, 0, 0));
            }

            if ( !r.containsKey(ps[2])) {
                r.put(ps[2], new CM(ps[2], 0, 0, 0));
            }

            // start increment the counters
            CM cm = r.get(ps[0]);
            cm.gPlus();

            cm = r.get(ps[1]);
            cm.sPlus();

            cm = r.get(ps[2]);
            cm.bPlus();
        }

        // Sort the results
        List<CM> list = new ArrayList<CM>();

        for (CM cm: r.values()) {
            list.add(cm);
        }

        
        String[] ret = new String[list.size()];

        Collections.sort(list);

        for (int i = 0; i < list.size() ; i++) {
            CM cm = list.get(i);
            ret[i] = cm.n + " " + cm.g + " " + cm.s + " " + cm.b;
        }

        return ret;

    }


    // Medals per country
    private class CM implements Comparable {
        public String n;
        public int g;
        public int s;
        public int b;


        public CM(String n, int g, int s, int b) {
            this.n = n;
            this.g = g;
            this.s = s;
            this.b = b;
        }

        public void gPlus(){
            this.g++;
        }

        public void sPlus(){
            this.s++;
        }

        public void bPlus(){
            this.b++;
        }


        public int compareTo(Object o) {
            CM oCM = (CM) o;
            if (this.g != oCM.g ) {
                return this.g > oCM.g ? -1 : 1;
            }
            if (this.s != oCM.s) {
                return this.s > oCM.s ? -1 : 1;
            }
            if (this.b != oCM.b) {
                return this.b > oCM.b ? -1 : 1;
            }
              
            return this.n.compareTo(oCM.n);
        }

        // public boolean equals(Object o) {

        // }
    }



}

