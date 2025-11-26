package InferenceEngine;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import GeneticAlgorithm.Utils.Pair;


public class MamdaniEngine extends IEngine {
//    protected Map<String , Double> newMap = new java.util.HashMap<>();
   public Map<String, Set<Pair>> cnt = new java.util.HashMap<>();
   public Map<String, Set<Pair>> newMap = new java.util.HashMap<>();

   @Override/// <"wash time is short", value>
   public void setConsequenceValue(String conseq ,  Double value, Map<String, Double> variables) {
       
       String[] parts = conseq.split(" is ");
 
        if (parts.length == 2) {
            String X = parts[0].trim();  
            String Y = parts[1].trim();  


            updateMap(newMap, X, Y, value);
            updateMap(cnt, X, Y, 1);
            
        }

        if (conseq.startsWith("(") && conseq.endsWith(")")) {
            conseq = conseq.substring(1, conseq.length() - 1);
        }
 
       
        int operatorIndex = findOperatorIndex(conseq);
        if (operatorIndex == -1)
            return; 
 
        // Split the rule into left and right parts
        String leftrule = conseq.substring(0, operatorIndex).trim();
        String rightrule = conseq.substring(operatorIndex + 1).trim();
        // Recursively evaluate both sides
        setConsequenceValue(leftrule, value, variables);
        setConsequenceValue(rightrule, value, variables);
   }


    @Override
    public Map<String, Set<Pair>> runConsequences() {
        Map<String, Set<Pair>> res = new HashMap<>();

        /// for(auto [str, st]: newMap){
        ///     for(auto &[first, second]: st){
        ///         second /= *(cnt[str].find(fist)).second;
        ///     }
        /// }
        for (Map.Entry<String, Set<Pair>> entry : newMap.entrySet()) {
            String str = entry.getKey();
            Set<Pair> valueSet = entry.getValue();

            Set<Pair> cntSet = cnt.getOrDefault(str, new HashSet<>());

            for (Pair pair : valueSet) {
                String first = pair.getFirst();
                double second = pair.getSecond();

                // Find the count for this 'first' in cntSet
                int count = 1; // default to 1 if not found
                for (Pair c : cntSet) {
                    if (c.getFirst().equals(first)) {
                        count = (int) c.getSecond();
                        break;
                    }
                }

                // Divide the value by the count
                 pair.setSecond(second / count);
              
            }
            res.put(str, valueSet);
        }


        System.out.println("\n//////////////////////////////////////");
        for (Map.Entry<String, Set<Pair>> entry : res.entrySet()) {
            String key = entry.getKey();
            Set<Pair> valueSet = entry.getValue();
            System.out.print(key + ": ");
            for (Pair pair : valueSet) {
                System.out.print("[" + pair.getFirst() + ", " + pair.getSecond() + "] ");
            }
            System.out.println();
        }
        System.out.println("//////////////////////////////////////");
        
        return res;
    }

    private void updateMap(Map<String, Set<Pair>> map, String X, String Y, double value) {
        map.putIfAbsent(X, new HashSet<>());
        Set<Pair> set = map.get(X);

        boolean found = false;
        for (Pair p : set) {
            if (p.getFirst().equals(Y)) {
                p.setSecond(p.getSecond() + value); // increment existing value
                found = true;
                break;
            }
        }

        if (!found) 
            set.add(new Pair(Y, value)); // add new pair if not found
    }


    // Function to find the operator (AND or OR) in the rule
    public int findOperatorIndex(String rule) {
        int depth = 0;
        for (int i = 0; i < rule.length(); i++) {
            char c = rule.charAt(i);
            if (c == '(') depth++;
            if (c == ')') depth--;
            // Find the operator at the outermost depth level
            if (depth == 0 && (c == '&' || c == '|'))
                return i;
        }
        return -1;  // If no operator is found
    }


}
