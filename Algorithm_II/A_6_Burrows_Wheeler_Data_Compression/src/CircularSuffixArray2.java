import java.util.Arrays;

//Edward Tseng
//12/22/13

public class CircularSuffixArray {
    private int N;
    private int[] next;
    private int CUTOFF;
    private int R = 256;
    
    public CircularSuffixArray(String s) {
        // circular suffix array of s
        N = s.length();
        next = new int[N];
        for (int i = 0; i < N; i++)
            next[i] = i;
        CUTOFF = s.length() - 2;
        
        msd(s);
    }
    
    public int length() {
        // length of s
        return N;
    }
    public int index(int i) {
        // returns index of ith sorted suffix
        return next[i];
    }
    
    private static char suffixfn(String s, int shift, int d, int n) {
        if (shift + d >= n)
            return s.charAt(shift+d-n);
        else
            return s.charAt(shift+d);
    }
    
    
    private void msd(String s) {
        int N = s.length();
        int[] aux = new int[N];
        sort(s, 0, N-1, 0, aux);
    }


    // sort from a[lo] to a[hi], starting at the dth character
    private void sort(String a, int lo, int hi, int d, int[] aux) {
        // cutoff to insertion sort for small subarrays
        if (hi <= lo + CUTOFF) {
            quickSort(a, lo, hi, d);
            return;
        }
               
        //System.out.println("QUICK");
        // compute frequency counts
        int n = a.length();
        int[] count = new int[R+2];
        
        for (int i = lo; i <= hi; i++) {
            int c = suffixfn(a, next[i], d, n);
            count[c+2]++;
        }

        // transform counts to indicies
        for (int r = 0; r < R+1; r++)
            count[r+1] += count[r];
        
        // distribute
        for (int i = lo; i <= hi; i++) {
            int c = suffixfn(a, next[i], d, n);
            aux[count[c+1]++] = next[i];
        }

        // copy back
        for (int i = lo; i <= hi; i++) { 
            next[i] = aux[i - lo];
        }
        // recursively sort for each character
        for (int r = 0; r < R; r++)
            if (count [r+1] - count[r] > 1 && d < n)
                sort(a, lo + count[r], lo + count[r+1] - 1, d+1, aux);
    }


    // return dth character of s, -1 if d = length of string
    private static void insertion(String a, int lo, int hi, int d, int[] next) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(j, j-1, d, a, next); j--)
                exch(next, j, j-1);
    }

    // exchange a[i] and a[j]
    private static void exch(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // is v less than w, starting at character d
    private static boolean less(int v, int w, int d, String a, int[] next) {
        int n = a.length();
        for (int i = d; i < a.length(); i++) {
            if (suffixfn(a, next[v], i, n) < suffixfn(a, next[w], i, n))
                return true;
            else if (suffixfn(a, next[v], i, n) > suffixfn(a, next[w], i, n)) 
                return false;
        }
        return false;
    }

    // 3-way string quicksort a[lo..hi] starting at dth character
    private void quickSort(String a, int lo, int hi, int d) { 
        // cutoff to insertion sort for small subarrays
        if (hi <= lo + 5 ) {
            insertion(a, lo, hi, d, next);
            return;
        }
        
        int n = a.length();
        int lt = lo, gt = hi;
        int v = suffixfn(a, next[lo], d, n);
        int i = lo + 1;
        while (i <= gt) {
            
            int t = suffixfn(a, next[i], d, n);
            if      (t < v) exch(next, lt++, i++);
            else if (t > v) exch(next, i, gt--);
            else              i++;
        }

        quickSort(a, lo, lt-1, d);
        if (v >= 0 && d < n) quickSort(a, lt, gt, d+1);
        quickSort(a, gt+1, hi, d);
    }
    
    
    
    public static void main(String[] args){     
        // Stopwatch sw = new Stopwatch();
        // CircularSuffixArray a;
        // //for(int i = 0; i<100000; i++)
        //  a = new CircularSuffixArray("couscousadfajaabbascpojpoadsf;ljasldf");
        //  //a = new CircularSuffixArray("CADABRA!ABRA");
        
        
        // System.out.println("Resizing time: " + sw.elapsedTime() + " seconds.");
        // for (int i = 0; i < a.length(); i++)
        //  System.out.print(a.index(i));
        
    }
}