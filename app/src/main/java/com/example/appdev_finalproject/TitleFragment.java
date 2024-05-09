package com.example.appdev_finalproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TitleFragment extends Fragment {

    private static final String ARG_TITLE = "arg_title";

    public static TitleFragment newInstance(String title) {
        TitleFragment fragment = new TitleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_title, container, false);
        TextView titleTextView = view.findViewById(R.id.titleTextView);

        Bundle args = getArguments();
        if (args != null) {
            String title = args.getString(ARG_TITLE);
            titleTextView.setText(title);
        }

        return view;
    }
}
