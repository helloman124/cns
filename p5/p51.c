#include <stdio.h>

int ip[] = {2,6,3,1,4,8,5,7};
int ipinv[] = {4,1,3,5,7,2,8,6};

int s0[4][4] = {
    {1,0,3,2},
    {3,2,1,0},
    {0,2,1,3},
    {3,1,3,2}
};

int s1[4][4] = {
    {0,1,2,3},
    {2,0,1,3},
    {3,0,1,0},
    {2,1,0,3}
};

int initperm (int pt) {
    int res = 0;
    for(int i=0; i<8; i++) {
        res |= ((pt>>(8-ip[i])) & 1) << (7-i);
    }
    return res;
}

int invinitperm (int ct) {
    int res = 0;
    for(int i=0; i<8; i++) {
        res |= ((ct>>(8-ipinv[i])) & 1) << (7-i);
    }
    return res;
}

int sub(int val, int sbox[4][4]) {
    int r = ((val & 0b1000) >> 2) | (val & 0b0001);
    int c = (val & 0b0110) >> 1;
    return sbox[r][c];
}

int main() {

    int pt = 0b11010110;
    printf("PT: %x\n", pt);

    int ct = initperm(pt);
    printf("CT: %x\n", ct);

    int sboxval = 0b1101;
    int sboxres = sub(sboxval, s0);
    printf("SBox res: %x\n", sboxres);

    int dectext = invinitperm(ct);
    printf("DT: %x\n", dectext);

    return 0;
}