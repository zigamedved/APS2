import java.util.Scanner;
import java.util.ArrayList;

public class izziv_5{
    
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<Integer>();

        while(sc.hasNextInt()){
            numbers.add(sc.nextInt());
        }

        sc.close();

        /*
        for(int i = 0; i<numbers.size(); i++){
            System.out.printf("%d, ",numbers.get(i));
        }

        System.out.println("Uspesno");
        */

        int[] tabela = new int[numbers.size()];

        for(int i = 0; i<numbers.size(); i++){
            tabela[i]=(int)numbers.get(i);
            //System.out.printf("%d, ",tabela[i]);
        }

        int isci = razdeli(tabela,0,tabela.length-1);
    }

    public static int razdeli(int[] tabela, int left, int right){
        
        if(left==right){
            System.out.printf("[%d]: %d\n",tabela[left], tabela[left]);
            return tabela[left];
        }else{
            int mid=(right+left)/2;
      
            int levaPodT = razdeli(tabela, left, mid);
            int desnaPodT = razdeli(tabela, mid+1, right);

            System.out.printf("[");
            for(int i = left; i< right; i++){
                System.out.printf("%d, ",tabela[i]);
            }
            System.out.printf("%d",tabela[right]);
            System.out.printf("]: ");
           
            //max podzaporedja
            int podzaporedje = poisciMax(tabela,mid,left,right);
  
            //izpis maxa
            int max = Math.max(Math.max(levaPodT, desnaPodT),podzaporedje);
            System.out.println(max);
            
            return max;
        }
    }


    public static int poisciMax(int[] tabela,int mid, int left, int right){
        int vsotaLevo = -2147483648;
        int vsotaDesno = -2147483648;
        int trenutnoLevo=0;
        int trenutnoDesno=0;
        
        for(int i = mid; i>=left; i--){
            trenutnoLevo+=tabela[i];
            if(trenutnoLevo>vsotaLevo){
                vsotaLevo=trenutnoLevo;
            }
        }
        for(int i = mid+1; i<=right; i++){
            trenutnoDesno+=tabela[i];
            if(trenutnoDesno>vsotaDesno){
                vsotaDesno=trenutnoDesno;
            }
        }
        return vsotaDesno+vsotaLevo;
    } 
}


    