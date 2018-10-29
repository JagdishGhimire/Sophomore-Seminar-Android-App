package com.example.android.miwok;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class CategoryAdapter extends FragmentPagerAdapter {
    private Context mContext;
    
    public CategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0: {
                fragment = new NumbersFragment();
                break;
            }
            case 1: {
                fragment = new FamilyFragment();
                break;
            }
            case 2: {
                fragment = new ColorsFragment();
                break;
            }
            case 3: {
                fragment = new PhrasesFragment();
                break;
            }
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        switch (position) {
            case 0: {
                title = mContext.getString(R.string.category_numbers);
                break;
            }
            case 1: {
                title = mContext.getString(R.string.category_family);
                break;
            }
            case 2: {
                title = mContext.getString(R.string.category_colors);
                break;
            }
            case 3: {
                title = mContext.getString(R.string.category_phrases);
                break;
            }
        }

        return title;
    }
}
