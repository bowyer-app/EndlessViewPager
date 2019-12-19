package com.bowyer.app.endlessviewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bowyer.app.endlessviewpager.databinding.FragmentContentsBinding

class ContentFragment : Fragment() {

  companion object {
    private const val KEY_TITLE = "key_title"

    fun newInstance(
      tabName: String
    ): ContentFragment {
      val args = bundleOf(
        KEY_TITLE to tabName
      )
      return ContentFragment().apply {
        arguments = args
      }
    }
  }

  private lateinit var binding: FragmentContentsBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = FragmentContentsBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    arguments?.getString(KEY_TITLE).let {
      binding.text.text = it
    }
  }
}
