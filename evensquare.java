import java.util.*;
class evensquare{
    public static void main(String args[]){
        Scanner get = new Scanner(System.in);
        int N = get.nextInt();
        if(N%2!=0){
            int val1 = even((N/2)+1);
            System.out.println(val1);
        }
        else{
            int val2 = even(N/2);
            val2 = val2/2;
            System.out.println(val2);
        }
    }
    public static int even(int n){
        int sum = 0;
        for(int i=1; i<n; i++){
            sum = sum + 2;
        }
        return sum;
    }
    
}