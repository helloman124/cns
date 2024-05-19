#include <stdio.h>
#include <string.h>

void main() {
    unsigned int enc[3][3] = {{6, 24, 1}, {13, 16, 10}, {20, 17, 15}};
    unsigned int dec[3][3] = {{8, 5, 10}, {21, 8, 21}, {21, 12, 8}};

    unsigned int pt[3];
    unsigned int ct[3];
    unsigned int res[3];

    char plaintext[20];
    gets(plaintext);

    for(int i=0; i<3; i++) {
        pt[i] = plaintext[i] - 'A';
    }

    // encryption: 
    for(int i=0; i<3; i++) {
        int sum = 0;
        for(int j=0; j<3; j++) {
            sum+= pt[j] * enc[i][j];
        }
        ct[i] = sum % 26;
    }

    for(int i=0; i<3; i++) {
        printf("%c ", ct[i] + 'A');
    }

    // decryption:
    for(int i=0; i<3; i++) {
        int sum = 0;
        for(int j=0; j<3; j++) {
            sum+=  ct[j] * dec[i][j];
        }
        res[i] = sum % 26;
    }

    for(int i=0; i<3; i++) {
        printf("%c ", res[i] + 'A');
    }
}