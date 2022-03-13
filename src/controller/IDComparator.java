package controller;

import model.Console;

import java.util.Comparator;

public class IDComparator implements Comparator<Console> {
    @Override
    public int compare (Console console1, Console console2){
        return Integer.compare(console1.getId(), console2.getId());
    }
}
