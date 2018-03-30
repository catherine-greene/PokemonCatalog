package com.example.katrin.pokemoncatalog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {
    private PokemonRecycler recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recycler = new PokemonRecycler(this);
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recycler);
    }

    @Override
    protected void onPause() {
        PokemonsRegister.getInstance().deleteObserver(this);
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        PokemonsRegister.getInstance().addObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {

        List<Pokemon> pokemons = (List<Pokemon>) o;
        recycler.setPokemons(pokemons);

    }
}
