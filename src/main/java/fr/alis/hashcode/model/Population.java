package fr.alis.hashcode.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Population {
    private List<PossibleSolution> possibleSolutions = new ArrayList<>();
    private int size;
    private EvenMorePizzaInput input;
    public Population(int size, EvenMorePizzaInput input) {
        this.size = size;
        this.input = input;
    }

    public void initialize() {
        for (int i = 0; i < size; i++) {
            possibleSolutions.add(new PossibleSolution(input.copy()));
        }
    }

    public PossibleSolution getFittestSolution(){
        return possibleSolutions.stream()
                .max(Comparator.comparing(PossibleSolution::getScore))
                .orElseThrow(IllegalArgumentException::new);
    }

    public int size() {
        return size;
    }

    public void saveSolution(int i, PossibleSolution newIndividual) {
        if (possibleSolutions.size() > i) {
            possibleSolutions.remove(i);
        }
        possibleSolutions.add(i, newIndividual);
    }

    public PossibleSolution getSolution(int i) {
        return possibleSolutions.get(i);
    }

    public double getAverageScore() {
        long sum = possibleSolutions.stream().mapToLong(PossibleSolution::getScore).sum();
        return (double) sum / possibleSolutions.size();
    }

    public EvenMorePizzaInput getInput() {
        return this.input;
    }
}