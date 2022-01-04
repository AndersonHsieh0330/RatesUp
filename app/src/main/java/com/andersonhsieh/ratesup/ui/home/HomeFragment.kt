package com.andersonhsieh.ratesup.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.andersonhsieh.ratesup.R
import com.andersonhsieh.ratesup.data.Repository
import com.andersonhsieh.ratesup.data.network_requests.RetrofitInstance
import com.andersonhsieh.ratesup.databinding.FragmentHomeBinding
import com.andersonhsieh.ratesup.model.APIResponseObject
import com.andersonhsieh.ratesup.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

//        binding.HomeFragmentSearchButton.setOnClickListener {
//            RetrofitInstance.apiAccessPoint.getCurrencyData().enqueue(object :
//                Callback<APIResponseObject>{
//                override fun onResponse(
//                    call: Call<APIResponseObject>,
//                    response: Response<APIResponseObject>
//                ) {
//                    Log.d(Constants.loggingTag, "onResponse: ${response.body()}")
//                }
//
//                override fun onFailure(call: Call<APIResponseObject>, t: Throwable) {
//                    Log.d(Constants.loggingTag, "failed")
//
//                }
//
//            })


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}