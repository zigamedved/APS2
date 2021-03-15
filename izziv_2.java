import java.util.Scanner;

public class izziv_2{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n =  sc.nextInt();
        int[] tab = new int[n];

        for(int i = 0; i<n; i++){
            tab[i]=sc.nextInt();
        }
        sc.close();
        
        //Pomoc iz predavanj, prosojnice heapsort
        for(int i = n/2; i>=0; i--){
            pogrezni(tab,i,n);
        }
        //System.out.println("Po gradnji kopice: ");
        printKopica(tab,n);

        //Pomoc iz predavanj, prosojnice heapsort
        int r = n;
        r--;

        int p = 1;
        while(r>0){
            int x = tab[0];
            tab[0] = tab[r];
            tab[r] = x;
            pogrezni(tab, 0, r);
            //System.out.printf("Po %d pogrezanju:\n",p);
            printKopica(tab,r);
            r--;
            p++;
        }
        //print(tab);
        
    }

    

    public static void pogrezni(int[] a,int i, int dolzKopice){
        int trenuten=i;
        int s=(2*i)+1;
        
        if(s<dolzKopice){
            if(a[s]>a[trenuten]){
                trenuten = s;
            }
        }

        if(s+1<dolzKopice){
            if(a[s+1]>a[trenuten]){
                trenuten=s+1;
            } 
        }

        if(a[trenuten]>a[i]){
            int zamenjaj = a[i];
            a[i] = a[trenuten];
            a[trenuten] = zamenjaj;
            pogrezni(a,trenuten,dolzKopice);
        }
    }
    
    public static void printKopica(int[] tab,int dolzina){
        int stevec = 0;
        int meja = 1;


        for(int i = 0; i<dolzina; i++){
            System.out.printf("%d",tab[i]);
            stevec++;
            if(stevec==meja && i!=(dolzina-1)){
                stevec=0;
                meja*=2;
                System.out.printf(" | ");
            }else{
                System.out.printf(" ");
            }
        }
        System.out.println();
    }
    /*
    public static void print(int[] tab){
        for(int i = tab.length-1; i>=0; i--){
            System.out.printf("%d, ",tab[i]);
        }
    }
    */
}