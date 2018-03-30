package com.example.katrin.pokemoncatalog;


import retrofit2.Call;
import retrofit2.http.GET;

public interface PokemonListService {

    @GET("api/v2/pokemon")
    Call<APIResponseObject> getPokemons();
}
