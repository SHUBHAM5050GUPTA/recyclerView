package com.example.shubhamgupta.recyclerview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity  {

    RecyclerView mRecyclerView;
    RecyclerAdapter mRecyclerAdapter;
    ArrayList<Notes> mNotesArraylist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                int size=mNotesArraylist.size();
                Notes mNotes=new Notes("Title"+size,"Description"+size);
                mNotesArraylist.add(mNotes);
                mRecyclerAdapter.notifyItemInserted(size);
                mRecyclerView.smoothScrollToPosition(size);
            }
        });

        mRecyclerView=(RecyclerView) findViewById(R.id.recycler_view);
        mNotesArraylist=Notes.getRandomNotes(5);
        mRecyclerAdapter=new RecyclerAdapter(this, mNotesArraylist, new RecyclerAdapter.NotesClickListner() {
            @Override
            public void OnItemClick(View view, int postion) {
                Notes mNote=mNotesArraylist.get(postion);
                Snackbar.make(mRecyclerView,mNote.getTitle(),Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void OnRemoveClick(int position) {
                mNotesArraylist.remove(position);
                mRecyclerAdapter.notifyDataSetChanged();
            }
        });


        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP | ItemTouchHelper.DOWN ,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int from=viewHolder.getAdapterPosition();
                int to=target.getAdapterPosition();
                Collections.swap(mNotesArraylist,from,to);
                mRecyclerAdapter.notifyItemMoved(from,to);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int postion =viewHolder.getAdapterPosition();
                mNotesArraylist.remove(postion);
                mRecyclerAdapter.notifyDataSetChanged();
            }
        });
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id==R.id.reset_button)
        {
            mNotesArraylist.clear();
            mRecyclerAdapter.notifyDataSetChanged();
        }

        return super.onOptionsItemSelected(item);
    }
}
