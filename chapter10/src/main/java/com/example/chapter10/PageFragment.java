package com.example.chapter10;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

/**
 * @author wangzhichao
 * @date 2019/09/19
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
