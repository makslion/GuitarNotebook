package com.maksym.guitarnotebook;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/*
unimplemented feature. Tuner should be put here. There is link to code, that could be here
in Final Report at Appendix, Project extensions 1 (link to GitHub with open source
 */

public class Tuner extends Fragment
{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_tuner, container, false);
    }
}
