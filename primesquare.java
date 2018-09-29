import java.util.*;
class primesquare{
    public static void main(String args[]){
        int N;
        Scanner get = new Scanner(System.in);
        N = get.nextInt();
        if(N%2==0){
            int count = 0,prime = 0;
            for(int i=2; i<=1000; i++){
                int isprime = 1;
                for(int j=2; j<i; j++){
                    if(i%j==0){
                        isprime=0;
                        break;
                    }
                }
                if(isprime==1){
                    count++;
                }
                if(count==(N/2)){
                    prime = i;
                    System.out.println(prime);
                    break;
                }
            }
        }
        else{
            int count = 0,prime = 0;
            for(int i=2; i<=1000; i++){
                int isprime = 1;
                for(int j=2; j<i; j++){
                    if(i%j==0){
                        isprime=0;
                        break;
                    }
                }
                if(isprime==1){
                    count++;
                }
                if(count==((N/2)+1)){
                    prime = i;
                    System.out.println(prime*prime);
                    break;
                }
            }
        }
    }
}