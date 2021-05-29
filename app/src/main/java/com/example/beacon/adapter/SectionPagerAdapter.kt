package com.example.beacon.adapter

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.beacon.R
import com.example.beacon.ui.home.EligibilityCheckFragment
import com.example.beacon.ui.home.SocialAssistanceCheckFragment

class SectionPagerAdapter(private val mContext: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.cek_kelayakan_tab_title, R.string.cek_bansos_tab_title)
    }

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> EligibilityCheckFragment()
            1 -> SocialAssistanceCheckFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence? =
        mContext.resources.getString(TAB_TITLES[position])

    override fun getCount(): Int = 2

}