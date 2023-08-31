package com.example.hotelmanagementsystem.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.hotelmanagementsystem.R;

public class AdminLoginFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.admin_login_fragment, container, false);

        return v;
    }

}
