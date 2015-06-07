// AM       NAME
// 2454     Kostis Karantias
// 2492     Marios Balamatsias
// Initial input is hardcoded in the intervals ArrayList
// additional input is given in variable i on line 84
import java.util.*;

class Interval {
  public int low;
  public int high;

  public Interval(int low, int high) {
    this.low = low;
    this.high = high;
  }
}

public class lab2 {
  public static void main(String[] args) {
    ArrayList<Interval> intervals = new ArrayList<Interval>();

    intervals.add(new Interval(4, 15));
    intervals.add(new Interval(3, 8));
    intervals.add(new Interval(0, 12));
    intervals.add(new Interval(5, 20));
    intervals.add(new Interval(1, 13));
    intervals.add(new Interval(11, 16));
    intervals.add(new Interval(2, 14));

    ArrayList<Integer> A = new ArrayList<Integer>();
    ArrayList<Integer> B = new ArrayList<Integer>();

    // Inserting elements in a sorted array
    boolean added = false;
    for (Interval i : intervals) {
      added = false;
      for (int j = 0; j < A.size(); ++j) {
        if (i.low < A.get(j)) {
          A.add(j, i.low);
          added = !added;
          break;
        }
      }
      if (!added) {
        A.add(i.low);
      }
    }

    for (Interval i : intervals) {
      added = false;
      for (int j = 0; j < B.size(); ++j) {
        if (i.high < B.get(j)) {
          B.add(j, i.high);
          added = !added;
          break;
        }
      }
      if (!added) {
        B.add(i.high);
      }
    }

    // Calculating score, O(n)
    int max_score = 0, min_pos = 0, max_pos = 0;
    for (int i = A.size() - 1; i >= 0; --i) {
      if (A.get(i) < B.get(0)) {
        max_score = i + 1;
        min_pos = A.get(i);
        break;
      }
    }
    for (int i = 0; i < B.size(); ++i) {
      if (B.get(i) > min_pos) {
        max_pos = B.get(i);
        break;
      }
    }
    System.out.println("F_max = " + max_score);
    System.out.println("C_min = " + min_pos);
    System.out.println("C_max = " + max_pos);

    System.out.println("Adding interval [6,9].");
    // Adding new interval (insertion sort), O(n)
    Interval i = new Interval(6, 9);
    added = false;
    for (int j = 0; j < A.size(); ++j) {
      if (i.low < A.get(j)) {
        A.add(j, i.low);
        added = !added;
        break;
      }
    }
    if (!added) {
      A.add(i.low);
    }

    added = false;
    for (int j = 0; j < B.size(); ++j) {
      if (i.high < B.get(j)) {
        B.add(j, i.high);
        added = !added;
        break;
      }
    }
    if (!added) {
      B.add(i.high);
    }

    // Calculating score, O(n)
    max_score = 0;
    min_pos = 0;
    max_pos = 0;
    for (int j = A.size() - 1; j >= 0; --j) {
      if (A.get(j) < B.get(0)) {
        max_score = j + 1;
        min_pos = A.get(j);
        break;
      }
    }
    for (int j = 0; j < B.size(); ++j) {
      if (B.get(j) > min_pos) {
        max_pos = B.get(j);
        break;
      }
    }
    System.out.println("F_max = " + max_score);
    System.out.println("C_min = " + min_pos);
    System.out.println("C_max = " + max_pos);
  }
}
