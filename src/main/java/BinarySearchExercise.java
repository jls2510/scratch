package main.java;

public class BinarySearchExercise {
	
	private static int counter = 0;

	public static void main(String[] args) {

		int[] searchableArray = new int[100000];
		for (int index = 0; index < searchableArray.length; index++) {
			searchableArray[index] = index;
		}

		int valueToSearchFor = 33110;
		System.out.println("index of " + valueToSearchFor + " = " + searchRecursivelyFor(valueToSearchFor, searchableArray));
		System.out.println("counter = " + counter);

	}

	private static int searchFor(int target, int[] arrayToSearch) {

		int lowerBound = 0;
		int upperBound = arrayToSearch.length;
		int medianIndex = -1;
		int medianValue = -1;

		int loopCounter = 0;

		while (lowerBound < upperBound && loopCounter <= 500) {
			++loopCounter;
			medianIndex = lowerBound + ((upperBound - lowerBound) / 2);
			medianValue = arrayToSearch[medianIndex];

			System.out.println("looping: loopCounter = " + loopCounter + "; lowerBound = " + lowerBound
					+ "; upperBound = " + upperBound);
			System.out.println("medianIndex = " + medianIndex + "; medianValue = " + medianValue);

			if (medianValue == target) {
				return medianIndex;
			} else if (medianValue < target) {
				lowerBound = medianIndex;
			} else {
				upperBound = medianIndex;
			}
		}

		return -1;
	}

	// searchRecursivelyFor
	private static int searchRecursivelyFor(int target, int[] arrayToSearch) {

		int lowerBound = 0;
		int upperBound = arrayToSearch.length;
		return doRecursiveSearch(target, arrayToSearch, lowerBound, upperBound);
	}

	// searchRecursivelyFor
	private static int doRecursiveSearch(int target, int[] arrayToSearch, int lowerBound, int upperBound) {
		
		++counter;

		int medianIndex = lowerBound + ((upperBound - lowerBound) / 2);
		int medianValue = arrayToSearch[medianIndex];

		if (lowerBound < upperBound && counter < 500) {
			if (medianValue == target) {
				return medianIndex;
			} else if (medianValue < target) {
				lowerBound = medianIndex;
			} else {
				upperBound = medianIndex;
			}
		} else {
			return -1;
		}

		return doRecursiveSearch(target, arrayToSearch, lowerBound, upperBound);
	}

	// System.out.println("looping: loopCounter = " + loopCounter + "; lowerBound =
	// " + lowerBound + "; upperBound = " + upperBound);
	// System.out.println("medianIndex = " + medianIndex + "; medianValue = " +
	// medianValue);

}
