package com.andersonhsieh.ratesup.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.andersonhsieh.ratesup.R
import com.andersonhsieh.ratesup.data.Repository
import com.andersonhsieh.ratesup.data.network_requests.RetrofitInstance
import com.andersonhsieh.ratesup.databinding.FragmentHomeBinding
import com.andersonhsieh.ratesup.model.APIResponseObject
import com.andersonhsieh.ratesup.util.Constants
import com.andersonhsieh.ratesup.util.CurrencyViewModelFactory
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.android.material.card.MaterialCardView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    //UI elements
    private lateinit var lineChart: LineChart
    private lateinit var searchBTN: MaterialCardView
    private lateinit var fromCurrency: EditText
    private lateinit var toCurrency: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this, CurrencyViewModelFactory(Repository.getInstance())).get(
                HomeViewModel::class.java
            )

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initUI()


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

    private fun initUI() {
        lineChart = binding.HomeFragmentLineChart
        searchBTN = binding.HomeFragmentSearchButton
        fromCurrency = binding.HomeFragmentFromCurrency
        toCurrency = binding.HomeFragmentToCurrency


        searchBTN.setOnClickListener {
            val from = fromCurrency.text.toString()
            val to = toCurrency.text.toString()
            if (Constants.checkValidCurrency(from)){
                if(Constants.checkValidCurrency(to)){
                homeViewModel.getData(from,to)}
                else{
                    Toast.makeText(context,"Invalid Currency Code : $to", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(context,"Invalid Currency Code : $from", Toast.LENGTH_SHORT).show()
            }
        }

        val yValues = ArrayList<Entry>()

        yValues.add(Entry(0f, 10f))
        yValues.add(Entry(1f, 30f))
        yValues.add(Entry(2f, 50f))
        yValues.add(Entry(3f, 20f))

        val dataSet = LineDataSet(yValues, "Currency Histories")
        val data = LineData(dataSet)

        lineChart.data = data
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}