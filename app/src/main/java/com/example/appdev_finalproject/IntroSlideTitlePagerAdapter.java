package com.example.appdev_finalproject;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;

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
        String title = pages.get(position).getTitle();
        CharSequence formattedTitle = getFormattedTitle(title);
        return TitleFragment.newInstance(formattedTitle);
    }

    @Override
    public int getCount() {
        return pages.size();
    }

    private CharSequence getFormattedTitle(String title) {
        String[] words = title.split("\\s+");
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        for (String word : words) {
            if (word.equals("Wild") || word.equals("Canteen") || word.equals("Hassle-Free") || word.equals("Advance")) {
                SpannableString spannableWord = new SpannableString(word);
                spannableWord.setSpan(new ForegroundColorSpan(Color.parseColor("#800000")), 0, word.length(), 0);
                spannableStringBuilder.append(spannableWord).append(" ");
            } else {
                spannableStringBuilder.append(word).append(" ");
            }
        }
        return spannableStringBuilder;
    }
}
