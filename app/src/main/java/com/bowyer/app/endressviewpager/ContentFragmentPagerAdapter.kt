package com.bowyer.app.endressviewpager

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ContentFragmentPagerAdapter(private val context: Context, fm: FragmentManager) :
  FragmentPagerAdapter(fm) {

  private val tabNames = mutableListOf<String>()

  init {
    for (i in 0..2) {
      val tabName = context.getString(R.string.tab_name, i + 1)
      tabNames.add(tabName)
    }
  }

  override fun getItem(position: Int): Fragment {
    return ContentFragment.newInstance(tabNames[getRealPosition(position)])
  }

  override fun getCount(): Int {
    return tabNames.size + 2
  }

  override fun getPageTitle(position: Int): CharSequence? {
    return tabNames[getRealPosition(position)]
  }

  fun getRealCount() = tabNames.size

  private fun getRealPosition(position: Int) = when (position) {
    0 -> getRealCount() - 1
    getRealCount() + 1 -> 0
    else -> position - 1
  }
}