package com.example.chapter04

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import androidx.fragment.app.Fragment

/**
 * @author wangzhichao
 * @date 2019/09/17
 */
class PageFragment : Fragment() {
    private var layoutRes = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            layoutRes = requireArguments().getInt(ARGS_LAYOUT_RES)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_page, container, false)
        val viewStub = view.findViewById<ViewStub>(R.id.viewStub)
        viewStub.layoutResource = layoutRes
        viewStub.inflate()
        return view
    }

    companion object {
        private const val ARGS_LAYOUT_RES = "args_layout_res"
        fun newInstance(layoutRes: Int): PageFragment {
            val args = Bundle()
            args.putInt(ARGS_LAYOUT_RES, layoutRes)
            val fragment = PageFragment()
            fragment.arguments = args
            return fragment
        }
    }
}