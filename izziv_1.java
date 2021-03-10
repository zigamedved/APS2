import java.util.*;
import java.util.Scanner;
import java.util.Random;

public class izziv_1{

    public static void main(String[] args){
        long casLinearno=0;
        long casBinarno=0;
        System.out.printf("n\t|\tlinearno\t|\tdvojisko\t|\n");
        System.out.printf("--------+-----------------------+------------------------\n");
       
        for(int i = 1000; i<100000; i=i+1000){
            //int i = 3000;
            casLinearno = timeLinear(i);
            casBinarno = timeBinary(i);
            System.out.printf("%d\t|\t%d\t\t|\t%d\n",i,casLinearno,casBinarno);
        }
    }
    
    public static long timeLinear(int n){
        Random rand = new Random();
        int[] tabela = generateTable(n);
        long startTime = System.nanoTime();

        int stevilo;
        int najdi;
        for(int i = 0; i<1000; i++){
            stevilo = rand.nextInt(n)+1;
            najdi = findLinear(tabela, stevilo);
        }

        long executionTime = System.nanoTime() - startTime;
        long povprecje = executionTime/1000;
        return povprecje;
    }

    public static long timeBinary(int n){
        Random rand = new Random();
        int[] tabela = generateTable(n);
        long startTime = System.nanoTime();
        
        int stevilo;
        int najdi;
        for(int i = 0; i<1000; i++){
            stevilo = rand.nextInt(n)+1;
            najdi = findBinary(tabela,0,tabela.length,stevilo);
        }

        long executionTime = System.nanoTime() - startTime;
        long povprecje = executionTime/1000;
        return povprecje;
    }

    //isci linearno
    public static int findLinear(int[] a, int v){
        for(int i = 0; i<a.length; i++){
            if(a[i]==v)return i;
        }
        return -1;
    }

    //isci binarno
    public static int findBinary(int[] a, int l, int r, int v){

        if(l>r)return -1;
        int sredina = l+(r-l)/2;
        
        if(a[sredina]==v)return sredina;

        if(a[sredina]<v){
            return findBinary(a,sredina+1,r,v);
        }else{
            return findBinary(a, l, sredina-1, v);
        }
    }

    //generiraj tabelo
    public static int[] generateTable(int n){
        int[] tabela = new int[n];
        for(int i = 0; i<n; i++){
            tabela[i]=i+1;
        }
        return tabela;
    }
}