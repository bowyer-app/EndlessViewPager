package com.bowyer.app.endlessviewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.bowyer.app.endlessviewpager.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

  private val binding: ActivityMainBinding by lazy {
    DataBindingUtil.setContentView<ActivityMainBinding>(
      this, R.layout.activity_main
    )
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val contentsAdapter = ContentFragmentPagerAdapter(this, supportFragmentManager)
    binding.viewpager.apply {
      adapter = contentsAdapter
      setCurrentItem(1, false)
      addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        private var realPosition = -1

        override fun onPageScrollStateChanged(state: Int) {
          if (state == ViewPager.SCROLL_STATE_IDLE && realPosition >= 0) {
            binding.viewpager.setCurrentItem(realPosition, false)
            realPosition = -1
          }
        }

        override fun onPageScrolled(
          position: Int,
          positionOffset: Float,
          positionOffsetPixels: Int
        ) {
          // do nothing
        }

        override fun onPageSelected(position: Int) {
          when (position) {
            0 -> realPosition = contentsAdapter.getRealCount()
            contentsAdapter.getRealCount() + 1 -> realPosition = 1
            else -> {
            }
          }
        }
      })
    }

    binding.tabs.apply {
      tabMode = TabLayout.MODE_SCROLLABLE
      tabGravity = TabLayout.GRAVITY_CENTER
      setupWithViewPager(binding.viewpager)
    }
  }
}
