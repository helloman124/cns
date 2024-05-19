#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void encrypt(int key, char *pt, char* ct) {
    int line, i, length = strlen(pt), j = 0, k = 0, skip;

    for(line =0; line<key-1; line++) {
        skip = 2 * (key-line-1);
        k = 0;

        for(i=line; i<length;) {
            ct[j] = pt[i];

            if((line == 0) || (k % 2 == 0)) i+=skip;
            else i+= 2*(key-1)-skip;
            j++; 
            k++;
        }
    }
    for(i=line; i<length; i+= 2*(key-1)) ct[j++] = pt[i];
}

void decrypt(int key, char *ct, char *pt) {
    int line, i, length = strlen(ct), j, k = 0, skip;

    for(line =0; line<key-1; line++) {
        skip = 2 * (key-line-1);
        j = 0;

        for(i=line; i<length;) {
            pt[i] = ct[k++];

            if((line == 0) || (j % 2 == 0)) i+=skip;
            else i+= 2*(key-1)-skip;
            j++;
        }
    }
    for(i=line; i<length; i+= 2*(key-1)) pt[i] = ct[k++];
}

void main() {

    char *pt = "helloworld";
    char *ct = malloc(strlen(pt) + 1);
    char *res = malloc(strlen(pt) + 1);
    int key = 3;

    encrypt(key, pt, ct);
    decrypt(key, ct, res);
    printf("%s \t %s \t %s \t", pt, ct, res);
}