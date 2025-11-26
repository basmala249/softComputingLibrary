package RecursiveRule;

import java.util.Map;
import java.util.Set;

import GeneticAlgorithm.Utils.Pair;

public class interpretRule {

    private Map<String, Set<Pair>> newMap;
    public interpretRule(Map<String, Set<Pair>> newMap) {
        this.newMap = newMap;
    }
    public Double evaluteRule(String rule) {

        if (rule.startsWith("(") && rule.endsWith(")")) {
            rule = rule.substring(1, rule.length() - 1);
        }

        String[] parts = rule.split(" is ");
 
        if (parts.length == 2) {
            String X = parts[0].trim();  
            String Y = parts[1].trim();  
            if (Y.startsWith("not ")) {
                Y = Y.substring(4).trim();
                Set<Pair> set = newMap.get(X);
                if (set != null) {
                    for (Pair pair : set) {
                        if (pair.getFirst().equals(Y)) {
                            return 1.0 - pair.getSecond();
                        }
                    }
                }
            } 
            else {
                Set<Pair> set = newMap.get(X);
                
                if (set != null) {
                    for (Pair pair : set) {
                        if (pair.getFirst().equals(Y)) {
                            return pair.getSecond();  
                        }
                    }
                }
            }
        }

       
        int operatorIndex = findOperatorIndex(rule);
        if (operatorIndex == -1) {
            return -1.0; 
        }
 
        // Split the rule into left and right parts
        String leftrule = rule.substring(0, operatorIndex).trim();
        String rightrule = rule.substring(operatorIndex + 1).trim();
        // Recursively evaluate both sides
        Double leftResult = evaluteRule(leftrule);
        Double rightResult = evaluteRule(rightrule);
 
        // Apply the operator (AND or OR)
        char operator = rule.charAt(operatorIndex);
        if (operator == '&') {  // AND operation
            return Math.min(leftResult, rightResult);
        } else if (operator == '|') {  // OR operation
            return Math.max(leftResult, rightResult);
        }
 
        return -1.0; 
    }
 
    // Function to find the operator (AND or OR) in the rule
    public int findOperatorIndex(String rule) {
        int depth = 0;
        for (int i = 0; i < rule.length(); i++) {
            char c = rule.charAt(i);
            if (c == '(') depth++;
            if (c == ')') depth--;
            // Find the operator at the outermost depth level
            if (depth == 0 && (c == '&' || c == '|')) {
                return i;
            }
        }
        return -1;  // If no operator is found
    }

    public void printMap() {
        for (Map.Entry<String, Set<Pair>> entry : newMap.entrySet()) {
            String key = entry.getKey();
            Set<Pair> valueSet = entry.getValue();
            System.out.print(key + ": ");
            for (Pair pair : valueSet) {
                System.out.print("[" + pair.getFirst() + ", " + pair.getSecond() + "] ");
            }
            System.out.println();
        }
    }
    public void setMap( Map<String, Set<Pair>> newMap) {
        for (Map.Entry<String, Set<Pair>> entry : newMap.entrySet()) {
            String key = entry.getKey();
            Set<Pair> valueSet = entry.getValue();
            System.out.print(key + ": ");
            for (Pair pair : valueSet) {
                System.out.print("[" + pair.getFirst() + ", " + pair.getSecond() + "] ");
            }
            System.out.println();
        }
    }
    public Map<String, Set<Pair>> getMap( Map<String, Set<Pair>> newMap) {
        return newMap;
    }
}
