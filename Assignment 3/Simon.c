//gcc 5.4.0

#include  <stdio.h>
#include <stdint.h>

#define word_size 16
#define shift_one(x_word) (((x_word) << 1) | ((x_word) >> (word_size - 1)))
#define shift_eight(x_word) (((x_word) << 8) | ((x_word) >> (word_size - 8)))
#define shift_two(x_word) (((x_word) << 2) | ((x_word) >> (word_size - 2)))

#define rshift_three(x) (((x) >> 3) | (((x) & 0x7) << (word_size - 3)))
#define rshift_one(x)   (((x) >> 1) | (((x) & 0x1) << (word_size - 1)))

uint64_t z[5] = {0b0001100111000011010100100010111110110011100001101010010001011111,
                        0b0001011010000110010011111011100010101101000011001001111101110001,
                        0b0011001101101001111110001000010100011001001011000000111011110101,
                        0b0011110000101100111001010001001000000111101001100011010111011011,
                        0b0011110111001001010011000011101000000100011011010110011110001011};

int main(void)
{
    uint16_t Input[]={0x3322,0x1100,0xddcc,0xbbaa},output[10],doutput[10];
    int m=4,n=16,T=32;
    int i,j=0,l;
    uint16_t tmp,use,x,y;
    uint16_t k[32]={0x0099,0x8877,0x6655,0x4433};
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
    for(i=m;i<T;i++){

        tmp=rshift_three(k[i-1]);
        if(m==4)
            tmp^=k[i-3];

        tmp^=rshift_one(tmp);
        k[i]=tmp^(uint16_t)k[i-m]^tmp^(uint16_t)((z[j] >> (i % 62)) & 1)^(uint16_t)3;
    }

	//Encryption
	for(i=0;i<4;i+=2){
        x=Input[i];
        y=Input[i+1];

        for(l=0;l<T;l++){
            tmp=x;
            x=y^(shift_one(x)&shift_eight(x))^shift_two(x)^k[l];
            y=tmp;
        }

        output[i]=x;
        output[i+1]=y;
    }

    printf("\nEncrypted Text:");
    for(i=0;i<4;i+=2){
        printf("%04x%04x",output[i],output[i+1]);
    }

    //Decryption
    for(i=0;i<4;i+=2){
        x=output[i];
        y=output[i+1];

        for(l=T-1;l>=0;l--){
            tmp=y;
            y=x^(shift_one(y)&shift_eight(y))^shift_two(y)^k[l];
            x=tmp;
        }

        doutput[i]=x;
        doutput[i+1]=y;
    }

    printf("\nDecrypted Text:");
    for(i=0;i<4;i+=2){
        printf("%04x%04x",doutput[i],doutput[i+1]);
    }
    printf("\n");
    return 0;
}
