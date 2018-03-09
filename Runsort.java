import edu.princeton.cs.algs4.*;
import java.util.Arrays;

public class Runsort {

private static 	Comparable[] aux;
private static String[] myArray;

	public Runsort() {

	}

	public static void main(String[] args){
		String[] tempArray = StdIn.readAllLines();
		// int l = tempArray.length;
		// for (int i=0; i<l; i++) {
		// 	StdOut.println(tempArray[i]);
		// }
		// StdOut.println("------");
		myArray = tempArray; //Why not just use myArray?
		sort(myArray);
		showSortedArray(myArray);
	}

	//Inspired by http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/7-b147/java/util/TimSort.java#TimSort.minRunLength%28int%29

	private static int findRunLength(comparable[] a, int lo, int hi) {
		//Find the length of the run starting at position lo and running no further than position hi in an array of comparables
		int runHi = lo+1;
		if (runHi==hi) return 1;

		//Find end of run
		while (runHi < hi && less(a[runHi], a[runHi-1])) {
			runHi++;

		//Return length of run
		return runHi - lo;
		}
	}

	//Inspired by http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/7-b147/java/util/TimSort.java#TimSort.minRunLength%28int%29
	private static void pushRun(Stack stack, int runBase, int runLen) {
		int[] a = {runBase, runLen};
		stack.push()
	}

	private static boolean isSorted(comparable[] a) {
		int N = a.length;
		int runLength = findRunLength(a, 0, N-1);
		return (runlength == 1);
	}

	public static void sort(Comparable[] a){
	int N = a.length;
	//Create a queue to keep track of the runs
	Stack runStack = new Stack();
	//Find the ascending runs and push to stack


	//While array is not sorted, find and merge runs
	while (!isSorted()) {

	}


	aux = new Comparable[N];
	for (int sz = 1; sz < N; sz = sz+sz)
    	for (int lo = 0; lo < N-sz; lo += sz+sz)
    		merge(a, aux, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
	}

	// Merges the array - MergeBU from section 2.2
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              a[k] = aux[j++];
            else if (j > hi)               a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else                           a[k] = aux[i++];
        }

    }
    // Comparing elements - MergeBU from section 2.2
    private static boolean less(Comparable a, Comparable b) {
        String h = a.toString();
        String i = b.toString();
        return h.compareTo(i) < 0;
    }

    public static void showSortedArray(Comparable[] sorted){
    	for (int i = 0; i < myArray.length; i++){
			System.out.println(myArray[i]);
		}
    }
}
