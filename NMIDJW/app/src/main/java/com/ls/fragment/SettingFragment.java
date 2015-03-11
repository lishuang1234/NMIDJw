package com.ls.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ls.nmidjw.R;

/**
 * Created by ls on 15-3-5.
 */
public class SettingFragment extends Fragment {

    private Toolbar mToolbar;
    private ActionBarActivity activity;

    @Override
    public void onAttach(Activity activity) {
        this.activity= (ActionBarActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting,container,false);
        mToolbar  = (Toolbar) view.findViewById(R.id.fragment_setting_toolbar);
        activity.setSupportActionBar(mToolbar);
        return view;
    }
}