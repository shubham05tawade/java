
 
class maxoccur
{
    
    static void removeDuplicates(int arr[])
    {
        int j = 0;
        if (arr.length==0 || arr.length==1)
            j = arr.length; 
      
        int[] temp = new int[arr.length];
         
        
        for (int i=0; i<(arr.length-1); i++)
           
            if (arr[i] != arr[i+1])
                temp[j++] = arr[i];
         

        temp[j++] = arr[arr.length-1];   
         

        for (int i=0; i<j; i++)
            arr[i] = temp[i];
      
        for (int i=0; i<j; i++)
            System.out.print(arr[i]+" ");
    }
     
    public static void main (String[] args) 
    {
        int arr[] = {1, 2, 2, 3, 4, 4, 4, 5, 5};
        int n = arr.length;
         
        removeDuplicates(arr);
  
        for (int i=0; i<n; i++)
           System.out.print(arr[i]+" ");
    }
}