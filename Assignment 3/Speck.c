//gcc 5.4.0

#include  <stdio.h>
#include <stdint.h>

#define word_size 16
#define rotate_left(x,n) (((x) >> (word_size - (n))) | ((x) << (n)))
#define rotate_right(x,n) (((x) << (word_size - (n))) | ((x) >> (n)))

int main(void)
{
	char str[300]={{'\n'}};
    uint16_t Input[]={0x3322,0x1100,0xddcc,0xbbaa},output[10],doutput[10];
    int m=4,n=16,T=22,alpha=7,beta=2;
    int i,j;
    uint16_t tmp,use,x,y;
    uint16_t k[22]={0x8877,0x6655,0x4433},l[22]={0x0099};
    
    printf("Plaintext:");
    for(i=0;i<4;i+=2){
        printf("%04x%04x",Input[i],Input[i+1]);
    }
    printf("\nKey:");
    for(i=0;i<m;i++){
        printf("%04x",k[i]);
    }
    printf("\n");

    //Key Expansion
    for(i=0;i<T-1;i++){
        l[i+m-1]=((uint16_t)(k[i]+rotate_right(l[i],alpha)))^(uint16_t)i;
        k[i+1]=rotate_left(k[i],beta)^l[i+m-1];
    }

	//Encryption
	for(i=0;i<4;i+=2){
        y=Input[i];
        x=Input[i+1];

        for(j=0;j<T;j++){
            x=(uint16_t)(rotate_right(x,alpha)+y)^k[j];
            y=rotate_left(y,beta)^x;
        }

        output[i]=y;
        output[i+1]=x;
    }

    printf("\nEncrypted Text:");
    for(i=0;i<4;i+=2){
        printf("%04x%04x",output[i],output[i+1]);
    }

    //Decryption
    for(i=0;i<4;i+=2){
        y=output[i];
        x=output[i+1];

        for(j=T-1;j>=0;j--){
            y=rotate_right((y^x),beta);
            x=rotate_left((uint16_t)((x^k[j])-y),alpha);
        }

        doutput[i]=y;
        doutput[i+1]=x;
    }

    printf("\nDecrypted Text:");
    for(i=0;i<4;i+=2){
        printf("%04x%04x",doutput[i],doutput[i+1]);
    }
    
    return 0;
}
