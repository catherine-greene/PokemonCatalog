package com.example.katrin.pokemoncatalog;

class Pokemon {

    private String name;
    private String url;

    int getId() {
        String id = url.replaceAll(".*\\/(\\d+)\\/$", "$1");
        return Integer.parseInt(id);
    }

    String getImageUrl() {
        return url.replaceAll(".*\\/(\\d+)\\/$", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$1.png");
    }

    public String getName() {
        return name;
    }
}

// https://pokeapi.co/api/v2/pokemon/8/
// \/(\d+)\/$
