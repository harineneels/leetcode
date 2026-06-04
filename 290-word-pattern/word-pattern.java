import java.util.*;

class Solution {
    public boolean wordPattern(String p, String s) {
        String[] w = s.split(" ");
        if (p.length() != w.length) return false;

        Map m1 = new HashMap(), m2 = new HashMap();

        for (int i = 0; i < p.length(); i++)
            if (!Objects.equals(m1.put(p.charAt(i), i),
                                m2.put(w[i], i)))
                return false;

        return true;
    }
}
 