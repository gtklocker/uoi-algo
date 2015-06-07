// AM       NAME
// 2454     Kostis Karantias
// 2492     Marios Balamatsias
import java.util.*;

/*
 * Input format:
 * N
 * x1 y1
 * x2 y2
 * ...
 * xN yN
 */

class Coordinate {
    public int x;
    public int y;
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public double dist(Coordinate v) {
        return Math.sqrt(
            (v.x - this.x) * (v.x - this.x) +
            (v.y - this.y) * (v.y - this.y)
        );
    }
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}

interface Selector {
    public int select(Coordinate c);
}

class SelectX implements Selector {
    public int select(Coordinate c) {
        return c.x;
    }
}

class SelectY implements Selector {
    public int select(Coordinate c) {
        return c.y;
    }
}

class MergeSort {
    public static ArrayList<Coordinate> sort(Selector selector, ArrayList<Coordinate> array) {
        if (array.size() <= 1) {
            return array;
        }
        int mid = array.size() / 2;
        ArrayList<Coordinate> leftUnsorted = new ArrayList<Coordinate>(array.subList(0, mid));
        ArrayList<Coordinate> rightUnsorted = new ArrayList<Coordinate>(array.subList(mid, array.size()));
        ArrayList<Coordinate> left = sort(selector, leftUnsorted);
        ArrayList<Coordinate> right = sort(selector, rightUnsorted);
        return merge(selector, left, right);
    }
    private static ArrayList<Coordinate> merge(Selector selector, ArrayList<Coordinate> left, ArrayList<Coordinate> right) {
        ArrayList<Coordinate> result = new ArrayList<Coordinate>();
        int i = 0;
        int j = 0, k = 0;
        while (i < left.size() + right.size()) {
            if (k >= right.size()) {
                result.add(left.get(j));
                ++j;
                ++i;
                continue;
            }
            if (j >= left.size()) {
                result.add(right.get(k));
                ++k;
                ++i;
                continue;
            }
            if (selector.select(left.get(j)) > selector.select(right.get(k))) {
                result.add(right.get(k));
                ++k;
            }
            else {
                result.add(left.get(j));
                ++j;
            }
            ++i;
        }

        return result;
    }
}

public class Lab4 {
    private static double MS(ArrayList<Coordinate> A) {
        //System.out.println("Called MS with A:");
        for (Coordinate c : A) {
            //System.out.println(c.x + " " + c.y);
        }
        if (A.size() == 2) {
            //System.out.println("Calculating distance between two points");
            //System.out.println("(" + A.get(0).x + ", " + A.get(0).y + ")" + " (" + A.get(1).x + ", " + A.get(1).y + ")");
            return A.get(0).dist(A.get(1));
        }
        if (A.size() < 2) {
            return 99999;
        }
        int mid = A.size() / 2;
        double d, minD;
        ArrayList<Coordinate> betweenLeft = new ArrayList<Coordinate>();
        ArrayList<Coordinate> betweenRight = new ArrayList<Coordinate>();
        ArrayList<Coordinate> left = new ArrayList<Coordinate>(A.subList(0, mid));
        ArrayList<Coordinate> right = new ArrayList<Coordinate>(A.subList(mid, A.size()));
        //System.out.println("Calculating left MS");
        //System.out.println("Calculating right MS");
        d = Math.min(MS(left), MS(right));

        for (Coordinate c : left) {
            if (c.x >= mid - d) {
                betweenLeft.add(c);
            }
        }

        for (Coordinate c : right) {
            if (c.x <= mid + d) {
                betweenRight.add(c);
            }
        }

        //System.out.println("Calculating in between distances");
        minD = Integer.MAX_VALUE;
        ArrayList<Coordinate> betweenLeftY = MergeSort.sort(new SelectY(), betweenLeft);
        ArrayList<Coordinate> betweenRightY = MergeSort.sort(new SelectY(), betweenRight);
        for (Coordinate c : betweenLeftY) {
            for (Coordinate v : betweenRightY) {
                if (c.y - v.y < -d) {
                    continue;
                }
                if (c.y - v.y > d) {
                    break;
                }
                //System.out.println("Calculating distance of " + c + " and " + v + " = " + c.dist(v));

                minD = Math.min(minD, c.dist(v));
            }
        }
        return Math.min(minD, d);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Scanner line;
        ArrayList<Coordinate> boats = new ArrayList<Coordinate>();
        int N = 0;

        if (in.hasNextInt()) {
            N = in.nextInt();
        }
        else {
            System.exit(1);
        }
        for (int i = 0; i < N; ++i) {
            boats.add(new Coordinate(in.nextInt(), in.nextInt()));
        }

        ArrayList<Coordinate> boatsX = MergeSort.sort(new SelectX(), boats);
        double minMS = MS(boatsX);
        System.out.println("minimum distance = " + minMS);
        if (minMS < 2) {
            System.out.println("minimum distance < 2, SOS");
        }
    }
}
