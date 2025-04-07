//LEGGERE LE ISTRUZIONI NEL FILE README.md

//Import di Classi Java necessarie al funzionamento del programma
import java.util.Scanner;
import java.util.Random;

// Classe principale, con metodo main
class Esercizio {

    private static Random random = new Random();
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int n, i, x, p, d;

        System.out.println("Inserire il numero di elementi da generare casualmente");
        n = Integer.parseInt( input.nextLine() );
        int[] dispari = new int[n];
        int[] pari = new int[n];

        // Caricamento dei due vettori. Incremento p e d man mano che accodo gli elementi nel vettori Pari o Dispari.
        p = 0;
        d = 0;
        for (i = 0; i <= n - 1; i++) {
            x = 1 + random.nextInt(n);
            if (x % 2 == 0) {
                pari[p] = x;
                p = p + 1;
            } else {
                dispari[d] = x;
                d = d + 1;
            }
        }

        // p e d qui sono le dimensioni di Pari e Dispari.
        System.out.println("Vettore Pari:");
        visualizzaVettore(pari, p);
        System.out.println("Vettore Dispari:");
        visualizzaVettore(dispari, d);

        // Ordinamento dei vettori
        bubbleSort(pari, p);
        bubbleSort(dispari, d);
        System.out.println("Vettore Pari Ordinato:");
        visualizzaVettore(pari, p);
        System.out.println("Vettore Dispari Ordinato:");
        visualizzaVettore(dispari, d);

        // Fondo i vettori usando il merge
        System.out.println("🏁🏁🏁🏁🏁 Merge 🏁🏁🏁🏁🏁");
        int[] v = new int[n];

        n = merge(pari, p, dispari, d, v);
        System.out.println("Vettore Finale Ordinato:");
        visualizzaVettore(v, n);

        // Chiedo il valore da cercare, visualizzo l'indice del vettore.
        int elemento;

        do {
            System.out.println("Inserire l'elemento da cercare: ");
            elemento = Integer.parseInt( input.nextLine() );
            i = ricercaBinaria(v, n, elemento);
            if (i == -1) {
                System.out.println("L'elemento non esiste");
            }
        } while (i == -1);
        System.out.println("Elemento " + elemento + " trovato all'indice " + i);
    }
    
    public static void bubbleSort(int[] v, int n) {
        int t;
        boolean scambio;
        int i, j;

        scambio = true;
        i = 0;
        while (i <= n - 1 && scambio) {
            scambio = false;
            j = 0;
            while (j <= n - 2 - i) {
                if (v[j] > v[j + 1]) {
                    scambio = true;
                    t = v[j];
                    v[j] = v[j + 1];
                    v[j + 1] = t;
                }
                j = j + 1;
            }
            i = i + 1;
        }
    }
    
    public static int merge(int[] v1, int d1, int[] v2, int d2, int[] v) {
        int d;
        int i1, i2;

        d = 0;
        i1 = 0;
        i2 = 0;

        // Accodo gli elementi in V sapendo che V1 e V2 sono ordinati...
        while (i1 < d1 && i2 < d2) {
            if (v1[i1] <= v2[i2]) {
                v[d] = v1[i1];
                i1 = i1 + 1;
            } else {
                v[d] = v2[i2];
                i2 = i2 + 1;
            }
            d = d + 1;
        }

        // Se ci sono ancora elementi di V1 da processare (e quindi non ci sono altri elementi di V2), li accodo in V.
        while (i1 < d1) {
            v[d] = v1[i1];
            d = d + 1;
            i1 = i1 + 1;
        }

        // Se ci sono ancora elementi di V2 da processare (e quindi non ci sono altri elementi di V1), li accodo in V.
        while (i2 < d2) {
            v[d] = v2[i2];
            d = d + 1;
            i2 = i2 + 1;
        }
        
        return d;
    }
    
    public static int ricercaBinaria(int[] v, int n, int valore) {
        int inizio, fine, medio, indice;

        indice = -1;
        inizio = 0;
        fine = n - 1;
        while (inizio <= fine && indice == -1) {
            medio = inizio + (fine - inizio) / 2;
            if (valore == v[medio]) {
                indice = medio;
            } else {
                if (valore > v[medio]) {
                    inizio = medio + 1;
                } else {
                    fine = medio - 1;
                }
            }
        }
        
        return indice;
    }
    
    public static void visualizzaVettore(int[] v, int n) {
        int i;

        for (i = 0; i <= n - 1; i++) {
            System.out.println(Integer.toString(i) + ": " + v[i]);
        }
    }
}
