import java.util.*;
class chessboard{
    public static void main(String args[]){
        /* Since all chess boards available in the market are 8 X 8 boards, Alex decides to paint a customised N X N board. Given the painted chess board, can you tell if it is painted correctly or not ? A chess board is considered valid if every adjacent 2 cells are painted with different color. Two cells are considered adjacent if they share a boundary */
        Scanner get = new Scanner(System.in);
        int T = get.nextInt();
        int arr[][] = new int[100][100];
        for(int i=0; i<T; i++){
            for(int j=0; j<T; j++){
                arr[i][j] = get.nextInt();
            }
        }

        int k=0;
        for(int i=0; i<T; i++){
            for(int j=0; j<T; j++){
                if(i!=k){
                    if(arr[k][0] == arr[i][0]){
                        System.out.println("No");
                        System.exit(0);
                    }
                    k++;
                }
                if((j+1)<T){
                    if(arr[i][j]==arr[i][j+1]){
                        System.out.println("No");
                        System.exit(0);
                    }
                }
            }
        }

        System.out.println("Yes");

        
    }
}