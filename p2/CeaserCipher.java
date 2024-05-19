package lab.p2;

public class CeaserCipher {

    private static final String letter = "abcdefghijklmnopqrstuvwxyz";
    
    public String encode(String pt) {

        StringBuilder ct = new StringBuilder();
        for (int i = 0; i < pt.length(); i++) {
            char ch = pt.charAt(i);
            int idx = letter.indexOf(ch);

            if (idx!=-1) {
                int newIdx = (idx+3)%26;
                if (newIdx < 0) {
                    newIdx += 26;
                }
                ct.append(letter.charAt(newIdx));
            }
        }
        return ct.toString();
    }

    public String decode(String ct) {

        StringBuilder pt = new StringBuilder();

        for (int i = 0; i < ct.length(); i++) {
            char ch = ct.charAt(i);
            int idx = letter.indexOf(ch);

            if (idx!=-1) {
                int newIdx = (idx-3)%26;
                if (newIdx < 0) {
                    newIdx += 26;
                }
                pt.append(letter.charAt(newIdx));
            }
        }

        return pt.toString();
    }
    public static void main(String[] args) {
     
        CeaserCipher c = new CeaserCipher();
        String ans = c.encode("dipankar");
        System.out.println(ans);

        System.out.println(c.decode(ans));
    }
}
