package lab.p2;
class PlayFairCipher {

    private char[][] keyTable;
    private static final char APPEND = 'X';

    PlayFairCipher(String key) {
        keyTable = generateKeyTable(key);
    }

    private char[][] generateKeyTable(String key) {

        char [][]table = new char[5][5];

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                table[i][j] = ' ';
            }
        }

        int r = 0;
        int c = 0;

        boolean[] used = new boolean[26];

        // fill the key values in table
        for (int i = 0; i < key.length(); i++) {
            char ch = Character.toUpperCase(key.charAt(i));

            if (ch == 'J') {
                ch = 'I';
            }

            if(!used[ch - 'A']) {
                table[r][c] = ch;
                used[ch-'A'] = true;
                c++;

                if(c == 5) {
                    r++;
                    c=0;
                }
            }

        }

        // fill other alphabets in the table:
        for (int i = 0; i < 26; i++) {
            char ch = (char)('A'+i);

            if (ch == 'J') {
                continue;
            }

            if(!used[ch - 'A']) {
                table[r][c] = ch;
                used[ch-'A'] = true;
                c++;

                if(c == 5) {
                    r++;
                    c=0;
                }
            }
            
        }

        return table;
        
    }

    private String preprocess(String text) {

        StringBuilder sb = new StringBuilder(text.toUpperCase());

        // append if two same characters are seen
        for (int i = 1; i < sb.length(); i++) {
            if (sb.charAt(i) == sb.charAt(i-1)) {
                sb.insert(i, APPEND);
            }
        }

        if (sb.length() % 2 != 0) {
            sb.append(APPEND);
        }

        return sb.toString();
    }

    private String postprocess(String text) {
        StringBuilder sb = new StringBuilder(text.toUpperCase());

        for (int i = 1; i < sb.length(); i++) {
            if (sb.charAt(i) == APPEND) {
                sb.deleteCharAt(i);
            }
        }
        return sb.toString().replace(APPEND, ' ');
    }

    private int[] findPos(char ch) {
        int[] pos = new int[2];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (ch == keyTable[i][j]) {
                    pos[0] = i;
                    pos[1] = j;
                    return pos;
                }
            }
        }
        return null;
    }


    public String encode(String pt) {
        StringBuilder ct = new StringBuilder();

        pt = preprocess(pt);

        for (int i = 0; i < pt.length(); i+=2) {
            char ch1 = pt.charAt(i);
            char ch2 = pt.charAt(i+1);

            int[] pos1 = findPos(ch1);
            int[] pos2 = findPos(ch2);

            if (pos1[0] == pos2[0]) {
                int newc1 = (pos1[1] + 1) % 5;
                int newc2 = (pos2[1] + 1) % 5;

                ct.append(keyTable[pos1[0]][newc1]);
                ct.append(keyTable[pos2[0]][newc2]);
            }

            else if (pos1[1] == pos2[1]) {
                int newr1 = (pos1[0] + 1) % 5;
                int newr2 = (pos2[0] + 1) % 5;

                ct.append(keyTable[newr1][pos1[1]]);
                ct.append(keyTable[newr2][pos2[1]]);
            }
            else {
                ct.append(keyTable[pos1[0]][pos2[1]]);
                ct.append(keyTable[pos2[0]][pos1[1]]);
            }

        }

        return ct.toString();
    }

    public String decode(String ct) {

        StringBuilder pt = new StringBuilder();

        for (int i = 0; i < ct.length(); i+=2) {
            char ch1 = ct.charAt(i);
            char ch2 = ct.charAt(i+1);

            int[] pos1 = findPos(ch1);
            int[] pos2 = findPos(ch2);


            if (pos1[0] == pos2[0]) {
                int newc1 = (pos1[1] + 5 - 1) % 5;
                int newc2 = (pos2[1] + 5 - 1) % 5;

                pt.append(keyTable[pos1[0]][newc1]);
                pt.append(keyTable[pos2[0]][newc2]);
            }

            else if (pos1[1] == pos2[1]) {
                int newr1 = (pos1[0] + 5 - 1) % 5;
                int newr2 = (pos2[0] + 5 - 1) % 5;

                pt.append(keyTable[newr1][pos1[1]]);
                pt.append(keyTable[newr2][pos2[1]]);
            }
            else {
                pt.append(keyTable[pos1[0]][pos2[1]]);
                pt.append(keyTable[pos2[0]][pos1[1]]);
            }


        }
        return postprocess(pt.toString());
    }

}

public class PlayFair {
    public static void main(String[] args) {
       
    }
}