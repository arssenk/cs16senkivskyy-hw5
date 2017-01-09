package ua.edu.ucu.tries;

public final class Tuple {
    private final String term;


    private final int weight;

    public Tuple(String term, int weight) {
        this.term = term;
        this.weight = weight;
    }

    String getTerm() {
        return term;
    }

    int getWeight() {
        return weight;
    }


}