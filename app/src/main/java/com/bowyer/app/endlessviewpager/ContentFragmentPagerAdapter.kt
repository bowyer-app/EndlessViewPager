package com.bowyer.app.endlessviewpager

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ContentFragmentPagerAdapter(private val context: Context, fm: FragmentManager) :
  FragmentPagerAdapter(fm) {

  companion object {
    private const val NUMBER_OF_LOOPS = 10000
  }

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
    return tabNames.size * NUMBER_OF_LOOPS
  }

  override fun getPageTitle(position: Int): CharSequence? {
    return tabNames[getRealPosition(position)]
  }

  fun getRealCount() = tabNames.size

  private fun getRealPosition(position: Int) = position % tabNames.size

  fun getCenterPosition(position: Int): Int {
    return tabNames.size * NUMBER_OF_LOOPS / 2 + position
  }
}
