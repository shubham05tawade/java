import java.util.*;
class hello{
    public static void main(String args[]){
        Scanner get = new Scanner(System.in);
        int a,n,s1,s2; 
        a = get.nextInt();
        s1 = get.nextInt();
        s2 = get.nextInt();
        n =get.nextInt();
        if(n%2==0){
            int temp = 1;
            int ratio = s1/a;
            for(int i=2; i<=(n/2); i++){
                temp = temp * ratio; 
            }            
            System.out.println(a*temp);
        }
        else{
            int temp = 1;
            int ratio = s2/a;
            for(int i=2; i<=((n/2)+1); i++){
                temp = temp * ratio; 
            }            
            System.out.println(a*temp);
        }
    }
}

/*odd prime 
even prime square*/