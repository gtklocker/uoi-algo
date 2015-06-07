public class Lab1std {
  private static float score(int[] A, int[] B) {
    int intersection, united;
    intersection = united = 0;
    for (int i = 0; i < A.length; ++i) {
      //System.out.println("At " + i);
      //System.out.println("A[" + i + "] = " + A[i]);
      //System.out.println("B[" + i + "] = " + B[i]);
      if (A[i] == B[i] && A[i] == 1) {
        //System.out.println("Both 1, ++both");
        ++intersection;
        ++united;
        continue;
      }
      if (A[i] == 1 || B[i] == 1) {
        //System.out.println("One 1, ++union");
        ++united;
      }
    }
    return (float)intersection / (float)united;
  }

  public static void main(String[] args) {
    // TODO code application logic here

    // O pinakas me tis 48 oikogeneis
    int F[][] = {
      {1,0,0,1,1,0,0,1,1,1},
      {0,0,0,1,1,0,0,0,0,0},
      {0,1,0,1,0,1,1,1,1,1},
      {1,0,0,0,1,0,0,1,0,0},
      {1,1,0,0,1,0,0,1,1,1},
      {0,0,0,0,0,0,1,1,1,0},
      {1,1,1,0,0,1,0,0,0,1},
      {1,1,1,1,1,0,1,0,0,0},
      {1,1,1,0,1,0,0,0,1,0},
      {0,1,1,0,0,1,1,1,0,0},
      {0,1,0,1,1,1,1,0,0,0},
      {0,1,1,1,1,1,1,0,1,1},
      {0,1,1,0,0,0,1,0,0,0},
      {0,1,1,1,1,0,0,0,1,1},
      {0,0,1,1,1,0,0,0,0,0},
      {1,0,0,0,1,1,1,1,1,1},
      {1,0,1,1,0,0,0,1,1,1},
      {0,0,0,1,0,1,0,1,0,1},
      {1,1,0,1,0,0,0,1,1,0},
      {1,1,0,1,1,0,0,1,1,1},
      {1,0,0,1,0,1,1,1,1,0},
      {0,0,1,1,1,0,1,0,1,1},
      {0,1,0,1,0,0,1,1,1,0},
      {0,0,0,1,1,0,0,0,0,0},
      {0,1,1,1,0,0,0,1,0,1},
      {1,0,0,0,1,1,1,0,1,1},
      {0,1,1,1,0,0,1,0,1,0},
      {0,0,0,0,0,0,0,0,1,1},
      {1,1,0,1,1,0,1,1,0,1},
      {0,1,0,0,1,0,1,0,1,1},
      {1,0,0,0,1,1,1,0,1,0},
      {1,1,1,0,0,1,0,1,1,1},
      {0,0,0,1,1,1,0,0,0,0},
      {0,1,1,1,0,1,0,0,1,1},
      {1,1,0,1,1,1,1,1,1,1},
      {1,1,1,1,0,1,1,0,1,1},
      {1,0,1,0,1,0,0,1,0,1},
      {0,0,0,0,1,1,1,0,1,1},
      {1,0,1,0,0,1,0,1,0,0},
      {1,0,1,1,0,1,1,0,0,1},
      {1,0,1,1,1,1,0,0,0,0},
      {0,1,0,0,1,1,1,0,0,0},
      {1,1,0,1,0,0,1,0,1,0},
      {0,0,1,1,1,1,0,0,1,1},
      {1,0,1,0,1,0,0,1,0,1},
      {1,0,1,1,0,0,0,0,0,0},
      {1,0,1,0,0,0,0,1,0,1},
      {1,1,1,1,0,0,1,1,1,0},
    };
    int T1[]= {1,1,1,0,0,1,0,0,0,1};
    int T2[]= {0,1,1,0,0,1,0,0,0,0};

    // Start here your code
    float cur_match = 0, max_match = 0;
    int pos_match = 0;
    for (int i = 0; i < F.length; ++i) {
      cur_match = score(T1, F[i]);
      if (max_match < cur_match) {
        max_match = cur_match;
        pos_match = i;
      }
    }
    System.out.println("T1 matches to family " + pos_match);

    for (int i = 0; i < F.length; ++i) {
      cur_match = score(T2, F[i]);
      if (cur_match >= .8) {
        System.out.println("T2 is malware. It is similar to family " + cur_match);
        break;
      }
    }

    if (cur_match < .8) {
      System.out.println("T2 is benign.");
    }

  }//end main
}//end class
