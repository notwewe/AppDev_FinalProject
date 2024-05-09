package com.example.appdev_finalproject;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class IntroSlideTitlePagerAdapter extends FragmentStatePagerAdapter {
    private List<IntroSlidePage> pages;

    public IntroSlideTitlePagerAdapter(FragmentManager fm, List<IntroSlidePage> pages) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.pages = pages;
    }

    @Override
    public Fragment getItem(int position) {
        return TitleFragment.newInstance(pages.get(position).getTitle());
    }

    @Override
    public int getCount() {
        return pages.size();
    }
}
