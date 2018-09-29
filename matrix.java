import java.util.*;
class matrix{
    int count1=0,count2=0;
    public static void main(String args[]){
        Scanner get =new Scanner(System.in);
        int n;
        n = get.nextInt();
        int[][] matrix1 = new int[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                matrix1[i][j] = get.nextInt();
            }
        }
        TypeOfMatrix(matrix1[][]);
    }
    void  TypeOfMatrix(int[][] matrix1){
        for(int i=0; i<matrix1.length; i++){
            for(int j=0; j<matrix1.length; j++){
                if(i<j){
                    if(matrix1[i][j]==0){
                        count1++;
                    }
                }
                else if(i>j){
                    if(matrix1[i][j]==0){
                        count2++;
                    }
                }
            }
        }
        int sum = 0;
        int temp = 1;
        while(temp<4){
            sum = sum + temp;
            temp++;
        }
        System.out.print(count1+" "+sum);
        if(sum==count1){
            System.out.println("Lower Triangular Matrix");
        }
        else if(sum==count2){
            System.out.println("Upper Triangular Matrix");
        }


    }
}