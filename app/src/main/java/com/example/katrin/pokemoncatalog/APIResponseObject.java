package com.example.katrin.pokemoncatalog;

import java.util.List;

class APIResponseObject {

    private int count;
    private String previous;
    private String next;
    private List<Pokemon> results;

    List<Pokemon> getResults() {
        return results;
    }
}
