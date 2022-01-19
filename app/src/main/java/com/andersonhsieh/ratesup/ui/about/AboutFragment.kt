package com.andersonhsieh.ratesup.ui.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.andersonhsieh.ratesup.R
import com.andersonhsieh.ratesup.databinding.FragmentAboutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    //clickable social media links
    private lateinit var gitHubLink: ImageButton
    private lateinit var instagramLink: ImageButton
    private lateinit var linkedInLink: ImageButton

    //resources links
    private lateinit var RetrofitLink: TextView
    private lateinit var HiltLink: TextView
    private lateinit var MPAndroidChart_Link: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        MPAndroidChart_Link = binding.AboutFragmentMPAndroidChart
        HiltLink = binding.AboutFragmentHilt
        RetrofitLink = binding.AboutFragmentRetrofit

        gitHubLink = binding.AboutFragmentGithubLink
        instagramLink = binding.AboutFragmentInstagramLink
        linkedInLink = binding.AboutFragmentLinkedInLink

        //to my social media profile
        gitHubLink.setOnClickListener {
            goToUrl(resources.getString(R.string.GitHubLink))
        }
        instagramLink.setOnClickListener {
            goToUrl(resources.getString(R.string.InstagramLink))
        }
        linkedInLink.setOnClickListener {
            goToUrl(resources.getString(R.string.LinkedInLink))
        }
        MPAndroidChart_Link.setOnClickListener {
            goToUrl(resources.getString(R.string.MPAndroidChart_Link))
        }
        HiltLink.setOnClickListener {
            goToUrl(resources.getString(R.string.Hilt_Link))
        }
        RetrofitLink.setOnClickListener {
            goToUrl(resources.getString(R.string.Retrofit_Link))
        }

    }

    private fun goToUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}