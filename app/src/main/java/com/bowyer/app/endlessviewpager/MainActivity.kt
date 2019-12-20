package com.bowyer.app.endlessviewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.bowyer.app.endlessviewpager.databinding.ActivityMainBinding

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
      setCurrentItem(contentsAdapter.getCenterPosition(1), false)
      addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

        override fun onPageScrollStateChanged(state: Int) {
          // do nothing
        }

        override fun onPageScrolled(
          position: Int,
          positionOffset: Float,
          positionOffsetPixels: Int
        ) {
          // do nothing
        }

        override fun onPageSelected(position: Int) {

          val nearLeftEdge = position <= contentsAdapter.getRealCount()
          val nearRightEdge = position >= contentsAdapter.count - contentsAdapter.getRealCount()
          if (nearLeftEdge || nearRightEdge) {
            binding.viewpager.setCurrentItem(contentsAdapter.getCenterPosition(1), false)
          }
        }
      })
    }

    binding.tabs.setUpWithViewPager(binding.viewpager)
  }
}
