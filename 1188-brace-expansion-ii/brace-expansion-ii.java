class Solution {
    private String expr;
    private int pos;

    public List<String> braceExpansionII(String expression) {
        this.expr = expression;
        this.pos = 0;
        Set<String> result = parseExpr();

        List<String> sorted = new ArrayList<>(result);
        Collections.sort(sorted);
        return sorted;
    }
    private Set<String> parseExpr() {
        Set<String> result = new TreeSet<>();
        result.addAll(parseTerm());

        while (pos < expr.length() && expr.charAt(pos) == ',') {
            pos++;
            result.addAll(parseTerm());
        }

        return result;
    }
    private Set<String> parseTerm() {
        Set<String> result = new HashSet<>();
        result.add(""); 

        while (pos < expr.length() && expr.charAt(pos) != ',' && expr.charAt(pos) != '}') {
            Set<String> factor = parseFactor();
            Set<String> newResult = new HashSet<>();

            for (String prefix : result) {
                for (String suffix : factor) {
                    newResult.add(prefix + suffix);
                }
            }
            result = newResult;
        }

        return result;
    }

    
    private Set<String> parseFactor() {
        if (expr.charAt(pos) == '{') {
            pos++; 
            Set<String> result = parseExpr();
            pos++; 
            return result;
        } else {
           
            Set<String> result = new HashSet<>();
            result.add(String.valueOf(expr.charAt(pos)));
            pos++;
            return result;
        }
    }
}