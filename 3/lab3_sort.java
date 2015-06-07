import java.util.*;

class Lab3 {
    public static int[] mergeSort(int[] array) {
        if (array.length <= 1) {
            return array;
        }
        int mid = array.length / 2;
        int[] leftUnsorted = Arrays.copyOfRange(array, 0, mid);
        int[] rightUnsorted = Arrays.copyOfRange(array, mid, array.length);
        int[] left = mergeSort(leftUnsorted);
        int[] right = mergeSort(rightUnsorted);
        return merge(left, right);
    }
    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0;
        int j = 0, k = 0;
        while (i < left.length + right.length) {
            if (k >= right.length) {
                result[i] = left[j];
                ++j;
                ++i;
                continue;
            }
            if (j >= left.length) {
                result[i] = right[k];
                ++k;
                ++i;
                continue;
            }
            if (left[j] > right[k]) {
                result[i] = right[k];
                ++k;
            }
            else {
                result[i] = left[j];
                ++j;
            }
            ++i;
        }

        return result;
    }
    public static void main(String[] args) {
        int[] A = { 1, 2, 1, 2 };
        double threshold = 0.5;
        int[] B = mergeSort(A);
        int current_site = -1, current_votes = 0;
        for (int i = 0; i <= B.length; ++i) {
            if (i == B.length && (float)current_votes / B.length >= threshold) {
                System.out.println(B[i - 1] + " has " + (double)current_votes / B.length + "-rating.");
            }
            if (i == B.length) {
                break;
            }
            if (B[i] != current_site) {
                if (i > 0 && (float)current_votes / B.length >= threshold) {
                    System.out.println(B[i - 1] + " has " + (double)current_votes / B.length + "-rating.");
                }
                current_site = B[i];
                current_votes = 1;
                continue;
            }
            ++current_votes;
        }
    }
}
