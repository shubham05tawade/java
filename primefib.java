import java.util.*;
class hello{
    public static void main(String args[]){
        Scanner get = new Scanner(System.in);
        /* Consider a sequence 1,1,3,1,5,2,7,3,9,...
           which consist of fibonacci and prime number series such that prime series are placed on odd position and fibonacci series are placed on even position.  
        */
        int T = get.nextInt();
        int a=0,b=1,fib=0;
        int isprime = 1;
        int prime = 0;

        if(T%2==0){
            T = T/2;
            if(T<=2){
                System.out.println(a+b); 
            }
            else{
                while(T>0){
                    fib = a + b;
                    a = b;
                    b = fib;
                    T--;
                }
                System.out.println(fib);
            }
        }

        else{
            T = (T/2) + 1;
            for(int i=2; T>0; i++){
                for(int j=2; j<i; j++){
                    if(i%j == 0){
                        isprime = 0;
                        break;
                    }
                }
                if(isprime==1){
                    prime = i;
                    T--;
                }
            }
            System.out.println(prime);
        }
        
    }
}