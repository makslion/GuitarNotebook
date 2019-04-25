package com.maksym.guitarnotebook;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Song extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_song, container, false);

        TextView songDetailsDemo = view.findViewById(R.id.songDetailsDemo);

        Intent intent = getActivity().getIntent();

        if(intent.hasExtra(Songs.EXTRA_SONG))
            songDetailsDemo.setText(intent.getStringExtra(Songs.EXTRA_SONG));


        return view;
    }


}
