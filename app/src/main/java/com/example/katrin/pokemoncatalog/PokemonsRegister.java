package com.example.katrin.pokemoncatalog;

import android.support.annotation.NonNull;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class PokemonsRegister extends Observable {

    private static List<Pokemon> pokemons;
    private static PokemonsRegister instance;

    private PokemonsRegister() {
    }

    static PokemonsRegister getInstance() {
        if (instance == null) {
            instance = new PokemonsRegister();
        }
        return instance;
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);

        if (pokemons != null) {
            o.update(this, pokemons);
        } else {
            requestPokemonFromApi();
        }
    }

    private void requestPokemonFromApi() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pokeapi.co")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PokemonListService pokemonListService = retrofit.create(PokemonListService.class);

        pokemonListService.getPokemons().enqueue(new Callback<APIResponseObject>() {
            @Override
            public void onResponse(@NonNull Call<APIResponseObject> call, @NonNull Response<APIResponseObject> response) {

                APIResponseObject apiObject = response.body();
                if (apiObject != null) {
                    pokemons = apiObject.getResults();
                }
                setChanged();
                notifyObservers(pokemons);


            }

            @Override
            public void onFailure(@NonNull Call<APIResponseObject> call, @NonNull Throwable t) {

                int a = 6;
            }
        });

    }


}
