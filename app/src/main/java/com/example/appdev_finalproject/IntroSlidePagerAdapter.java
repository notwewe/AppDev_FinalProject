package com.example.appdev_finalproject;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import java.util.List;

public class IntroSlidePagerAdapter extends FragmentStatePagerAdapter {

    private List<IntroSlidePage> pages;

    public IntroSlidePagerAdapter(FragmentManager fragmentManager, List<IntroSlidePage> pages) {
        super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.pages = pages;
    }

    @Override
    public Fragment getItem(int position) {
        IntroSlidePage page = pages.get(position);
        return IntroSlideFragment.newInstance(page.getImageResource(), page.getTitle());
    }

    @Override
    public int getCount() {
        return pages.size();
    }
}
