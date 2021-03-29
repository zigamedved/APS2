import java.lang.Integer;
import java.util.Scanner;

public class izziv_4{

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] table = new int[n];
        int[] arr = new int[32];

        for(int i = 0; i<n; i++){
            table[i] = sc.nextInt();
        }

        sc.close();

        for(int i = 0; i<table.length; i++){
            arr[Integer.bitCount(table[i])]++;
        }

        for(int i = 1; i<32; i++){
            arr[i] = arr[i-1] + arr[i];
        }
        
        int[] res = new int[n+1];

        int ind,ones,num;
        for(int i = n-1; i >=0; i--){
            num = table[i];
            ones = Integer.bitCount(num);
            ind = arr[ones];
            arr[ones]--;
            res[ind]=num;

            System.out.printf("(%d,%d)\n",num,ind-1);
        }

        for(int i = 1; i<=n; i++){
            System.out.print(res[i]+" ");
        }
    }
}