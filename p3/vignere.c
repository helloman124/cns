#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<ctype.h>

void encrypt(char *pt, char *key, char *ct) {

    for(int i=0,j=0; i<strlen(pt); i++,j++) {

        if(j>=strlen(key)) j = 0;

        int shift = toupper(key[j]) - 'A';
        char res = (toupper(pt[i] - 'A' + shift) % 26) + 'A';
        ct[i] = res;
    }
    
}

void decrypt(char* ct, char* key, char* res) {
    for(int i=0,j=0; i<strlen(ct); i++,j++) {

        if(j>=strlen(key)) j = 0;

        int shift = toupper(key[j]) - 'A';
        char ans = (toupper(ct[i] - 'A' + 26 - shift) % 26) + 'A';
        res[i] = ans;
    }
}

int main() {
    char pt[128];
    char ct[128];
    char key[16];
    char res[128];

    printf("Enter pt in upper case: ");
    gets(pt);

    printf("Enter key in upper case: ");
    gets(key);

    encrypt(pt,key,ct);
    printf("%s", ct);

    decrypt(ct,key,res);
    printf("\nResult: %s", res);
}