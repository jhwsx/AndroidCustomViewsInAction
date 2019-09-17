package com.example.chaper06;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @author wangzhichao
 * @date 2019/09/17
 */
public class PageFragment extends Fragment {
    private static final String ARGS_LAYOUT_RES = "args_layout_res";

    public static PageFragment newInstance(int layoutRes) {

        Bundle args = new Bundle();
        args.putInt(ARGS_LAYOUT_RES, layoutRes);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }
    private int layoutRes;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            layoutRes = getArguments().getInt(ARGS_LAYOUT_RES);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        ViewStub viewStub = view.findViewById(R.id.viewStub);
        viewStub.setLayoutResource(layoutRes);
        viewStub.inflate();
        return view;
    }
}
