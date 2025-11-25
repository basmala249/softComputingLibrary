package InferenceEngine;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import GeneticAlgorithm.Utils.Pair;


public class MamdaniEngine extends IEngine {
   protected Map<String , Double> newMap = new java.util.HashMap<>();
   protected Map<String, Integer> cnt = new java.util.HashMap<>();

   @Override/// <"wash time is short", value>
   public void setConsequenceValue(String conseq ,  Double value) {

       String[] parts = conseq.split("is");
       var2BePredicted = parts[0].trim();

       conseq = parts.length > 1 ? parts[1].trim() : "" ;
       
       System.out.print(conseq + " " + value);
       System.out.println("  ADDED ********");
       
       Double curr = newMap.getOrDefault(conseq, 0.0);
       curr += value;
       newMap.put(conseq, curr);

       Integer new_cnt = cnt.getOrDefault(conseq, 0);
       new_cnt++;
       cnt.put(conseq, new_cnt);
   }


    @Override
    public Map<String, Set<Pair>> runConsequences() {
        Map<String, Set<Pair>> res = new HashMap<>();

        Set<Pair> avrSet = new HashSet<>();

        for (Map.Entry<String, Double> entry : newMap.entrySet()) {
            Double curr = entry.getValue();            
            Integer counter = cnt.getOrDefault(entry.getKey(), 1);
            avrSet.add(new Pair(entry.getKey(), curr / counter));
            
        }
        
        res.put(var2BePredicted, avrSet);

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
}
