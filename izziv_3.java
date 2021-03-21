import java.util.Scanner;
public class izziv_3 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] a = new int[n];
        int[] b = new int[m];
        int[] c = new int[n+m];

        for(int i = 0; i<n; i++){
            a[i]=sc.nextInt();
        }

        for(int i = 0; i<m; i++){
            b[i]=sc.nextInt();
        }
        sc.close();

        int first=0;
        int second=0;

        for(int i = 0; i<n+m;i++){

            if(first==a.length){
                for(int j = i; i<n+m;i++){
                    c[i]=b[second];
                    second++;
                    System.out.print("b");
                }
                break;
            }

            if(second==b.length){
                for(int j = i; i<n+m;i++){
                    c[i]=a[first];
                    first++;
                    System.out.print("a");
                }
                break;
            }
            
            if(a[first]<=b[second]){
                c[i]=a[first];
                first++;
                System.out.print("a");
            }else{
                c[i]=b[second];
                second++;
                System.out.print("b");
            }
        }

        System.out.println();

        for(int i = 0; i<n+m; i++){
            System.out.printf("%d ",c[i]);
        }
     }
}
