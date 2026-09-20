class Solution {
    public String simplifyPath(String path) {
        Deque<String> stack = new ArrayDeque<>();
        
        String[] parts = path.split("/");

        for (String part : parts) {
            if (part.isEmpty() || part.equals(".")) {
                continue; 
            } else if (part.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop(); 
                }
            } else {
                stack.push(part); 
            }
        }

        
        StringBuilder sb = new StringBuilder();

        List<String> components = new ArrayList<>(stack);
        Collections.reverse(components);

        for (String component : components) {
            sb.append('/').append(component);
        }

        return sb.length() == 0 ? "/" : sb.toString();
        
    }
}