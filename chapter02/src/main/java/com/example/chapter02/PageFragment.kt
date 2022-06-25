package com.example.chapter02

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.chapter02.part5_frame_animation.FrameAnimXmlViewGroup
import com.example.chapter02.part5_frame_animation.FrameAnimCodeViewGroup
import com.example.chapter02.part2_view_animation_code.ViewAnimationCodeControlListenerViewGroup

/**
 * @author wangzhichao
 * @date 2019/09/17
 */
class PageFragment : Fragment() {
    private var layoutRes = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            layoutRes = arguments!!.getInt(ARGS_LAYOUT_RES)
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