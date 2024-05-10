package com.example.appdev_finalproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class IntroSlideFragment extends Fragment {

    private static final String ARG_IMAGE_RESOURCE = "arg_image_resource";

    public static IntroSlideFragment newInstance(int imageResource, String title) {
        IntroSlideFragment fragment = new IntroSlideFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_IMAGE_RESOURCE, imageResource);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_intro_slide, container, false);
        ImageView imageView = view.findViewById(R.id.imageView);

        Bundle args = getArguments();
        if (args != null) {
            imageView.setImageResource(args.getInt(ARG_IMAGE_RESOURCE));
        }

        return view;
    }
}
