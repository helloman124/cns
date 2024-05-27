#include<stdio.h>
#include<string.h>
#include<stdlib.h>

int main() {

    char pt[] = "HelloWorld";
    

    for(int i=0; i<strlen(pt); i++) {
        pt[i] = pt[i] & 127;
        printf("%c ", pt[i]);
    }
    printf("\n");

    for(int i=0; i<strlen(pt); i++) {
        pt[i] = pt[i] | 127;
        printf("%c ", pt[i]);
    }
    printf("\n");

    for(int i=0; i<strlen(pt); i++) {
        pt[i] = pt[i] ^ 127;
        printf("%c ", pt[i]);
    }
    printf("\n");
}
