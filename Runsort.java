import edu.princeton.cs.algs4.*;
import java.util.Arrays;

public class Runsort {

	public Runsort() {
	}

	public static void main(String[] args){
		String[] myArray = StdIn.readAllStrings();
		// Stopwatch timer = new Stopwatch();
		sort(myArray);
		// double elapsedTime = timer.elapsedTime();
		show(myArray);
		// StdOut.printf("Elapsed time, sorting: %f\n", elapsedTime);
		// elapsedTime = timer.elapsedTime();
		// StdOut.printf("Elapsed time, printing: %f\n", elapsedTime);
	}

	//Inspired by http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/7-b147/java/util/TimSort.java#TimSort.minRunLength%28int%29
	private static int findRunLength(Comparable[] a, int lo, int hi) {
		//Find the length of the run starting at position lo and running no further than position hi in an array of comparables
			int runHi = lo+1;
			if (runHi==hi) return 1;

		//Find end of run
			while (runHi < hi && less(a[runHi-1], a[runHi])) {
				runHi++;
			}

		//Return length of run
			return runHi - lo;
	}

	//Inspired by http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/7-b147/java/util/TimSort.java#TimSort.minRunLength%28int%29
	public static void sort(Comparable[] a){
	int N = a.length;

	//Create a queue to keep track of the runs
	Queue<int[]> q = new Queue<int[]>();

	//First off, find all the runs in the unsorted array
	int runCount = 0;
	int j = 0;
	while (j<N) {
			int[] tempArray = new int[2];
			tempArray[0] = j;												//Start position
			tempArray[1] = findRunLength(a, j, N);	//Length of run
			q.enqueue(tempArray);
			j=j+tempArray[1];												//Start next round from startPosition + runLength
			runCount++;
		}

	//Merge neighbouring runs for each pass through
	Comparable[] aux = new Comparable[N];
	while (runCount>1) {
		int k = 0;
		while (k<runCount/2) {
			int[] runOne = q.dequeue();
			int[] runTwo = q.dequeue();
			int lo = runOne[0];
			int mid = runTwo[0] - 1;
			int hi = mid + runTwo[1];
			merge(a,aux, lo, mid, hi);
			runOne[1] = runOne[1] + runTwo[1];			//Update runLength
			q.enqueue(runOne);
			k++;
		}
		if (runCount%2 == 1) {										//Handles uneven number of runs
			q.enqueue(q.dequeue());
		}
		runCount-= k;
	}
}

	//merge() from algs4 MergeBU
  private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
			// copy to aux[]
			for (int k = lo; k <= hi; k++) {
          aux[k] = a[k];
      }

			// merge back to a[]
      int i = lo, j = mid+1;
      for (int k = lo; k <= hi; k++) {
          if      (i > mid)              a[k] = aux[j++];
          else if (j > hi)               a[k] = aux[i++];
          else if (less(aux[j], aux[i])) a[k] = aux[j++];
          else                           a[k] = aux[i++];
      }
  }

	//less() from algs4 MergeBU
	private static boolean less(Comparable v, Comparable w) {
      return v.compareTo(w) < 0;
  }

	//show() from algs4 MergeBU
  public static void show(Comparable[] sortedArray){
  	for (int i = 0; i < sortedArray.length; i++){
		System.out.println(sortedArray[i]);
		}
	}

}
