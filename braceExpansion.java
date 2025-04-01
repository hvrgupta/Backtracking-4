// Time Complexity : O(m^k) --k groups m characters in it
// Space Complexity : O(m^k) --k groups m characters in it
// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :

class Solution {
    List<String> result;

    public String[] expand(String s) {
        this.result = new ArrayList<>();
        List<List<Character>> li = new ArrayList<>();

        int i = 0;
        boolean isBracket = false;
        List<Character> temp = new ArrayList<>();
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c == '{') {
                isBracket = true;
                temp = new ArrayList<>();
            } else if (c == '}') {
                isBracket = false;
                Collections.sort(temp);
                li.add(new ArrayList<>(temp));
                temp.clear();
            } else if (Character.isAlphabetic(c) && isBracket) {
                temp.add(c);
            } else if (Character.isAlphabetic(c) && !isBracket) {
                temp.add(c);
                li.add(new ArrayList<>(temp));
                temp.clear();
            }
            i++;
        }
        backtrack(li, 0, new StringBuilder());
        String[] res = new String[result.size()];
        for (int j = 0; j < res.length; j++) {
            res[j] = result.get(j);
        }
        return res;
    }

    private void backtrack(List<List<Character>> li, int idx, StringBuilder sb) {
        if (idx == li.size()) {
            result.add(sb.toString());
            return;
        }

        List<Character> seq = li.get(idx);

        for (int i = 0; i < seq.size(); i++) {
            sb.append(seq.get(i));
            backtrack(li, idx + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}