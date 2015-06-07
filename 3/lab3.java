import java.util.*;

class Lab3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        HashMap<String, Integer> score = new HashMap<String, Integer>();

        String domain;
        float threshold = 0;
        int total = 0;

        threshold = in.nextFloat();
        in.nextLine();
        while (in.hasNextLine()) {
            domain = in.nextLine().trim();
            if (score.containsKey(domain)) {
                score.put(domain, score.get(domain) + 1);
            }
            else {
                score.put(domain, 1);
            }
            ++total;
        }

        Iterator<String> iterator = score.keySet().iterator();
        float currentScore;
        System.out.println("Showing sites with a-popularity >= " + threshold);
        while (iterator.hasNext()) {
            String key = iterator.next();
            currentScore = (float)score.get(key) / total;
            if (currentScore >= threshold) {
                System.out.println(key + " is " + currentScore + "-popular");
            }
        }
    }
}
