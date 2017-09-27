package com.example.shubhamgupta.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Shubham Gupta on 12-07-2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.NoteViewHolder> {
    private Context mContext;
     private ArrayList<Notes> mNotesArrayList;
     private NotesClickListner mListner;

   public interface NotesClickListner
    {
        void OnItemClick(View view ,int postion);
        void OnRemoveClick(int position);

    }

    public RecyclerAdapter(Context mContext, ArrayList<Notes> mNotesArrayList,NotesClickListner mListner) {
        this.mContext = mContext;
        this.mNotesArrayList = mNotesArrayList;
        this.mListner=mListner;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.layout_notes,parent,false);
        return  new NoteViewHolder(view,mListner);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {

        Notes mNote=mNotesArrayList.get(position);
        holder.titleTextView.setText(mNote.getTitle());
        holder.descTextView.setText(mNote.getDescription());

    }

    @Override
    public int getItemCount() {
        return mNotesArrayList.size();
    }


    public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView titleTextView;
        TextView descTextView;
        Button button;
        NotesClickListner mNotesClickListner;


        public NoteViewHolder(View itemView,NotesClickListner mListner) {
            super(itemView);
            mNotesClickListner=mListner;
            itemView.setOnClickListener(this);
            titleTextView=itemView.findViewById(R.id.text_View1);
            descTextView=itemView.findViewById(R.id.text_View2);
            button=itemView.findViewById(R.id.remove_button);
            button.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            int id=view.getId();
            int position=getAdapterPosition();
            if(position!= RecyclerView.NO_POSITION) {
                if (id == R.id.remove_button) {
                    mNotesClickListner.OnRemoveClick(position);

                } else if (id == R.id.notes_view) {
                    mNotesClickListner.OnItemClick(view, position);

                }
            }

        }
    }
}
