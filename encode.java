import java.util.*;
class encode{
    public static void main(String args[]){
        String s;
        int count = 1;
        Scanner get = new Scanner(System.in);
        s = get.nextLine();
        for(int i=0; i<s.length(); i++){
            if((i+1) < s.length()){
                if(s.charAt(i) == s.charAt(i+1)){
                    count++;
                }
                else{
                    System.out.print(String.valueOf(s.charAt(i))+count);
                    count = 1;
                }
            }
            else{
                System.out.print(String.valueOf(s.charAt(i))+count);
            }
        }
    }
}