class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        StringBuilder result = new StringBuilder();
        StringBuilder key = new StringBuilder();
        boolean insideBrackets = false;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                insideBrackets = true;
                key.setLength(0);
            } else if (c == ')') {
                insideBrackets = false;
                String value = map.getOrDefault(key.toString(), "?");
                result.append(value);
            } else if (insideBrackets) {
                key.append(c);
            } else {
                result.append(c);
            }
        }

        return result.toString();
        
    }
}