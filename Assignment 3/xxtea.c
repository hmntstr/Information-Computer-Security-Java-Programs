 #include <stdint.h>
 #include<stdio.h>

  #define DELTA 0x9e3779b9
  #define MX (((z>>5^y<<2) + (y>>3^z<<4)) ^ ((sum^y) + (key[(p&3)^e] ^ z)))
  
  void btea(uint32_t *v, int n, uint32_t const key[4]) {
    uint32_t y, z, sum;
    unsigned p, rounds, e;
    if (n > 1) {          /* Coding Part */
      rounds = 6 + 52/n;
      sum = 0;
      z = v[n-1];
      do {
        sum += DELTA;
        e = (sum >> 2) & 3;
        for (p=0; p<n-1; p++) {
          y = v[p+1]; 
          z = v[p] += MX;
        }
        y = v[0];
        z = v[n-1] += MX;
      } while (--rounds);
    } else if (n < -1) {  /* Decoding Part */
      n = -n;
      rounds = 6 + 52/n;
      sum = rounds*DELTA;
      y = v[0];
      do {
        e = (sum >> 2) & 3;
        for (p=n-1; p>0; p--) {
          z = v[p-1];
          y = v[p] -= MX;
        }
        z = v[n-1];
        y = v[0] -= MX;
        sum -= DELTA;
      } while (--rounds);
    }
  }
  
  int main(void)
{
uint32_t v[2]={0x58465234,0x74525452};
uint32_t key[4]={0x84587548,0x95865458,0x65235485,0x96584525};
/* The casts are ugly, but required */
printf("\nPlaintext:%08x %08x",v[0],v[1]);

btea(v,32,key);


printf("\nEncrypted Text:%08x %08x",v[0],v[1]);

btea(v,-32,key);
printf("\nDecrypted Text:%08x %08x",v[0],v[1]);


return 0;
}
