package homework2;
import java.util.*; // for comparing arrays in main() tests only

class AssertExp1 {

	/*
	 * minValue returns the minimum value in an array of doubles. You can assume
	 * the array is nonempty and has no duplicates. Your solution must go
	 * through the array exactly once. Your solution must not call any other
	 * functions. Here are some examples (using "==" informally):
	 * 
	 * -7 == minValue(new double[] { -7 }) -7 == minValue(new double[] { 1, -4,
	 * -7, 7, 8, 11 }) -13 == minValue(new double[] { -13, -4, -7, 7, 8, 11 })
	 * -9 == minValue(new double[] { 1, -4, -7, 7, 8, 11, -9 })
	 */
	public static double minValue(double[] list) {
		assert(list.length>0);
		double min = list[0];
		for (int i = 0; i < list.length; ++i) {
			if (list[i] < min) {
				min = list[i];
				assert(min<list[i-1]);
			}
		}
		return min;
	}

	/*
	 * minPosition returns the position of the minimum value in an array of
	 * doubles. The first position in an array is 0 and the last is the
	 * array.length-1. You can assume the array is nonempty and has no
	 * duplicates. Your solution must go through the array exactly once. Your
	 * solution must not call any other functions. Here are some examples (using
	 * "==" informally):
	 * 
	 * 0 == minPosition(new double[] { -7 }) 2 == minPosition(new double[] { 1,
	 * -4, -7, 7, 8, 11 }) 0 == minPosition(new double[] { -13, -4, -7, 7, 8, 11
	 * }) 6 == minPosition(new double[] { 1, -4, -7, 7, 8, 11, -9 })
	 */
	public static int minPosition(double[] list) {
		 // TODO
		assert(list.length>0);
		int minPos=0;
		double min = list[0];
		for (int i = 0; i < list.length; ++i) {
			if (list[i] < min) {
				min = list[i];
				minPos = i;
				assert(list[minPos] < list[minPos-1]);
			}
		}
		if(minPos>0 && minPos != list.length-1) {
			assert(list[minPos]< list[minPos-1]);
			assert(list[minPos]< list[minPos+1]);
		}
		return minPos;
	}


	/*
	 * numUnique returns the number of unique values in an array of doubles.
	 * Unlike the previous questions, the array may be empty and it may contain
	 * duplicate values. Also unlike the previous questions, you can assume the
	 * array is sorted.
	 * 
	 * Your solution must go through the array exactly once. Your solution must
	 * not call any other functions. Here are some examples (using "=="
	 * informally):
	 * 
	 * 0 == numUnique(new double[] { }) 1 == numUnique(new double[] { 11 }) 1 ==
	 * numUnique(new double[] { 11, 11, 11, 11 }) 8 == numUnique(new double[] {
	 * 11, 11, 11, 11, 22, 33, 44, 44, 44, 44, 44, 55, 55, 66, 77, 88, 88 }) 8
	 * == numUnique(new double[] { 11, 22, 33, 44, 44, 44, 44, 44, 55, 55, 66,
	 * 77, 88 })
	 */
	public static int numUnique(double[] list) {
		// TODO
		int numUnique = 1;
		if(list.length == 0) {
			return 0;
		} else {
			for(int i = 1; i<list.length; i++) {
				if(list[i-1] != list[i]) {
					numUnique++;
					assert(numUnique<list.length+1);
				}
			}
			assert(numUnique>0);
			return numUnique;
		}
	}

	/*
	 * removeDuplicates returns the number of unique values in an array of
	 * doubles. You may assume that the list is sorted, as you did for
	 * numUnique.
	 * 
	 * Your solution may call numUnique, but should not call any other
	 * functions. After the call to numUnique, you must go through the array
	 * exactly one more time. Here are some examples (using "==" informally):
	 * 
	 * new double[] { } == removeDuplicates(new double[] { }) new double[] { 11
	 * } == removeDuplicates(new double[] { 11 }) == removeDuplicates(new
	 * double[] { 11, 11, 11, 11 }) new double[] { 11, 22, 33, 44, 55, 66, 77,
	 * 88 } == removeDuplicates(new double[] { 11, 11, 11, 11, 22, 33, 44, 44,
	 * 44, 44, 44, 55, 55, 66, 77, 88, 88 }) == removeDuplicates(new double[] {
	 * 11, 22, 33, 44, 44, 44, 44, 44, 55, 55, 66, 77, 88 })
	 */
	public static double[] removeDuplicates(double[] list) {
		//TODO
		if(list.length == 0) {
			double emptyList[] = new double[0];
			return emptyList;
		}
		int index = 1;
		double newList[] = new double[numUnique(list)];
		newList[0] = list[0];
		for(int i = 1; i<list.length; i++) {
			if(list[i-1] != list[i]) {
				newList[index] = list[i];
				assert(newList[index-1] != newList[index]);
				index++;
				assert(index<newList.length+1);
			}
		}
		assert(newList.length>0);
		return newList;
	}

}
