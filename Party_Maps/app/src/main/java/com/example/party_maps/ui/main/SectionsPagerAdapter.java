package com.example.party_maps.ui.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.preference.PreferenceManager;

import com.example.party_maps.R;
import com.example.party_maps.SettingsActivity;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3};
    private static final int[] TAB_TITLES_HOR = new int[]{R.string.tab_text_hor_1, R.string.tab_text_hor_2, R.string.tab_text_hor_3};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(position + 1, mContext);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(mContext);
        boolean isChecked = settings.getBoolean("hoster_mode", false);
        if (mContext.getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE && isChecked) {
            return mContext.getResources().getString(TAB_TITLES_HOR[position]);
        }
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }
}