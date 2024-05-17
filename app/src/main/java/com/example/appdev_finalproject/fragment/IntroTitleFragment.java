package com.example.appdev_finalproject.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appdev_finalproject.R;

public class IntroTitleFragment extends Fragment {

    private static final String ARG_TITLE = "arg_title";

    public static IntroTitleFragment newInstance(CharSequence title) {
        IntroTitleFragment fragment = new IntroTitleFragment();
        Bundle args = new Bundle();
        args.putCharSequence(ARG_TITLE, title);
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
            CharSequence title = args.getCharSequence(ARG_TITLE);
            titleTextView.setText(title);
        }

        return view;
    }
}
