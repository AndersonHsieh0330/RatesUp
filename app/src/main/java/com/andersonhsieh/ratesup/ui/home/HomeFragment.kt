package com.andersonhsieh.ratesup.ui.home

import android.content.Context
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.andersonhsieh.ratesup.R
import com.andersonhsieh.ratesup.databinding.FragmentHomeBinding
import com.andersonhsieh.ratesup.model.ExtractedCurrencyHistoryData
import com.andersonhsieh.ratesup.util.Constants
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.*
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.google.android.material.card.MaterialCardView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.StringBuilder
import java.time.LocalDate

@AndroidEntryPoint
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
    private lateinit var progressBar: ProgressBar
    private lateinit var lineChartContainer: MaterialCardView

    val homeViewModel: HomeViewModel by activityViewModels()

    private lateinit var vibrator: Vibrator

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
        initUI()
        hideProgress()
        vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        registerObserverToCurrencyResponseLiveData()
        registerObserverToFromCurrencyString()
        registerObserverToToCurrencyString()
    }

    private fun initUI() {
        lineChart = binding.HomeFragmentLineChart
        lineChart.setNoDataTextColor(R.color.black)
        searchBTN = binding.HomeFragmentSearchButton
        fromCurrency = binding.HomeFragmentFromCurrency
        toCurrency = binding.HomeFragmentToCurrency
        progressBar = binding.HomeFragmentProgressBar
        lineChartContainer = binding.HomeFragmentLineChartContainer

        searchBTN.setOnClickListener {
            smallVibrate()
            val dataInfoMap = generateDataParameters()
            val fromCurrency = fromCurrency.text.toString()
            val toCurrency = toCurrency.text.toString()
            if (checkValidCurrencyAndNotifyUser(fromCurrency, toCurrency)) {
                hideLineChart()
                showProgress()
                fetchCurrencyData(fromCurrency, toCurrency, dataInfoMap)
            }

        }
    }

    fun fetchCurrencyData(
        fromCurrency: String,
        toCurrency: String,
        paramterMap: HashMap<String, String>
    ) {
        with(paramterMap) {
            homeViewModel.fetchDataFromAPI(
                fromCurrency,
                toCurrency,
                get(com.andersonhsieh.ratesup.util.Constants.toDate_Key)!!,
                get(com.andersonhsieh.ratesup.util.Constants.oneMonthAgoTimeStamp_Key)!!,
                get(com.andersonhsieh.ratesup.util.Constants.twoMonthsAgoTimeStamp_Key)!!,
                get(com.andersonhsieh.ratesup.util.Constants.fromDate_Key)!!
            )
        }
    }

    fun hideLineChart() {
        lineChart.visibility = View.INVISIBLE
    }

    fun hideProgress() {
        progressBar.visibility = View.INVISIBLE
    }

    fun showProgress() {
        progressBar.visibility = View.VISIBLE

    }

    fun smallVibrate() {
        vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE))
    }

    private fun registerObserverToFromCurrencyString() {
        homeViewModel.getFromCurrencyStringLiveData().observe(viewLifecycleOwner, Observer {
            fromCurrency.setText(it)
        })
    }

    private fun registerObserverToToCurrencyString() {
        homeViewModel.getToCurrencyStringLiveData().observe(viewLifecycleOwner, Observer {
            toCurrency.setText(it)
        })


    }

    private fun setData(it: ExtractedCurrencyHistoryData) {
        val yValues = ArrayList<Entry>()

        yValues.add(Entry(0f, it.threeMonthsAgoValue.toFloat()))
        yValues.add(Entry(1f, it.twoMonthsAgoValue.toFloat()))
        yValues.add(Entry(2f, it.onMonthAgoValue.toFloat()))
        yValues.add(Entry(3f, it.currentValue.toFloat()))

        val dataSet = LineDataSet(yValues, "${it.toCurrency} / per ${it.fromCurrency}")
        val data = LineData(dataSet)
        lineChart.xAxis.valueFormatter = IndexAxisValueFormatter(
            arrayOf(
                it.threeMonthsAgoTimeStamp,
                it.twoMonthsAgoTimeStamp,
                it.oneMonthAgoTimeStamp,
                "Now"
            )
        )
        lineChart.data = data
    }

    private fun configureChartSetting() {
        lineChart.xAxis.setDrawAxisLine(true);
        lineChart.xAxis.setDrawGridLines(false)
        lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM

        lineChart.axisRight.isEnabled = false
        lineChart.setPinchZoom(false)
        lineChart.legend.isEnabled = false

        lineChart.description.isEnabled = false
        lineChart.visibility = View.VISIBLE
    }


    private fun registerObserverToCurrencyResponseLiveData() {
        homeViewModel.getLiveData().observe(viewLifecycleOwner, Observer {
            progressBar.visibility = View.GONE

            configureChartSetting()
            setData(it)
        })
    }

    private fun generateDataParameters(): HashMap<String, String> {
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