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
import androidx.fragment.app.activityViewModels
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
import java.lang.StringBuilder
import java.time.LocalDate

class HomeFragment : Fragment() {

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


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //no need to instantiate view model separately in MainActivity
        //using by activityViewModels() creates ViewModel and attach to activity lifecycle
         val homeViewModel: HomeViewModel by activityViewModels{CurrencyViewModelFactory(Repository.getInstance())}

        homeViewModel.getLiveData().observe(viewLifecycleOwner, Observer {
            val yValues = ArrayList<Entry>()

            yValues.add(Entry(0f, it.threeMonthsAgoValue.toFloat()))
            yValues.add(Entry(1f, it.twoMonthsAgoValue.toFloat()))
            yValues.add(Entry(2f, it.onMonthAgoValue.toFloat()))
            yValues.add(Entry(3f, it.currentValue.toFloat()))

            val dataSet = LineDataSet(yValues, "Currency Histories")
            val data = LineData(dataSet)

            lineChart.data = data
        })
        initUI(homeViewModel)

    }

    private fun initUI(homeViewModel: HomeViewModel) {
        lineChart = binding.HomeFragmentLineChart
        searchBTN = binding.HomeFragmentSearchButton
        fromCurrency = binding.HomeFragmentFromCurrency
        toCurrency = binding.HomeFragmentToCurrency


        searchBTN.setOnClickListener {
            val dataInfoMap = generateDataParameters()
            val fromCurrency = fromCurrency.text.toString()
            val toCurrency = toCurrency.text.toString()
            if (checkValidCurrencyAndNotifyUser(fromCurrency, toCurrency)) {
                with(dataInfoMap) {
                    homeViewModel.fetchDataFromAPI(
                        fromCurrency, toCurrency, get(Constants.toDate_Key)!!,
                        get(Constants.oneMonthAgoTimeStamp_Key)!!,
                        get(Constants.twoMonthsAgoTimeStamp_Key)!!,
                        get(Constants.fromDate_Key)!!
                    )
                }
            }

        }


    }

    fun generateDataParameters(): HashMap<String, String> {
        //this HashMap contains all the string timeStamp that we want to filter out of the JSON response
        val result = HashMap<String, String>()
        val todayLocalDateObj = LocalDate.now()
        result[Constants.toDate_Key] = todayLocalDateObj.toString()
        result[Constants.oneMonthAgoTimeStamp_Key] =
            setToFirstDayOfMonth(todayLocalDateObj.minusMonths(1).toString())
        result[Constants.twoMonthsAgoTimeStamp_Key] =
            setToFirstDayOfMonth(todayLocalDateObj.minusMonths(2).toString())
        result[Constants.fromDate_Key] =
            setToFirstDayOfMonth(todayLocalDateObj.minusMonths(3).toString())
        return result
    }

    fun setToFirstDayOfMonth(yyyy_MM_dd: String): String {
        val builder = StringBuilder(yyyy_MM_dd)
        //the end index is excluded
        builder.delete(builder.lastIndex - 1, builder.lastIndex + 1)
        builder.append("01")

        return builder.toString()
    }

    fun checkValidCurrencyAndNotifyUser(fromCurrency: String, toCurrency: String): Boolean {
        if (Constants.checkValidCurrency(fromCurrency)) {
            if (Constants.checkValidCurrency(toCurrency)) {
                return true
            } else {
                Toast.makeText(context, "Invalid Currency Code : $toCurrency", Toast.LENGTH_SHORT)
                    .show()
                return false
            }
        } else {
            Toast.makeText(context, "Invalid Currency Code : $fromCurrency", Toast.LENGTH_SHORT)
                .show()
            return false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}