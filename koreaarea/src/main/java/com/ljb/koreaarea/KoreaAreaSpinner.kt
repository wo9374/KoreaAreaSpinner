package com.ljb.koreaarea

import android.content.Context
import android.content.res.Resources
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.ljb.koreaarea.databinding.LayoutKoreaAreaBinding

class KoreaAreaSpinner: ConstraintLayout {
    var spinnerBinding: LayoutKoreaAreaBinding = LayoutKoreaAreaBinding.inflate(
        LayoutInflater.from(context), this, true)

    val searchBtn : ImageButton get() = spinnerBinding.searchIcon

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.KoreaRegionSpinner)
        setTypedArray(typedArray, context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.KoreaRegionSpinner, defStyleAttr, 0)
        setTypedArray(typedArray, context)
    }

    private fun setTypedArray(typedArray: TypedArray, context: Context) {

        spinnerBinding.apply {

            val sidoWidth = typedArray.getDimension(R.styleable.KoreaRegionSpinner_spinner1_width, 0F).toInt()
            val sigunguWidth = typedArray.getDimension(R.styleable.KoreaRegionSpinner_spinner2_width, 0F).toInt()
            val height = typedArray.getDimension(R.styleable.KoreaRegionSpinner_spinner_height, 0F).toInt()
            sidoSpinner.layoutParams = LayoutParams(sidoWidth, height).apply {
                startToStart = ConstraintSet.PARENT_ID
                endToStart = sidoSpinner.id
            }
            sigunguSpinner.layoutParams = LayoutParams(sigunguWidth, height).apply {
                startToEnd = sidoSpinner.id
                endToStart = searchIcon.id
            }

            val iconWidth = typedArray.getDimension(R.styleable.KoreaRegionSpinner_icon_width, 0F).toInt()
            val iconHeight = typedArray.getDimension(R.styleable.KoreaRegionSpinner_icon_height, 0F).toInt()
            searchIcon.layoutParams = LayoutParams(iconWidth, iconHeight).apply {
                startToEnd = sigunguSpinner.id
                endToEnd = ConstraintSet.PARENT_ID
            }


            val regionAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, region)
            sidoSpinner.apply {
                adapter = regionAdapter
                onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        Log.d("DoubleAddressSpinner", "position : $position")
                        spinnerBinding.sigunguSpinner.adapter = when (position) {
                            1 -> ArrayAdapter(context, android.R.layout.simple_spinner_item, incheon)
                            2 -> ArrayAdapter(context, android.R.layout.simple_spinner_item, busan)
                            3 -> ArrayAdapter(context, android.R.layout.simple_spinner_item, daejeon)
                            4 -> ArrayAdapter(context, android.R.layout.simple_spinner_item, daegu)
                            5 -> ArrayAdapter(context, android.R.layout.simple_spinner_item, ulsan)
                            6 -> ArrayAdapter(context, android.R.layout.simple_spinner_item, gwangju)
                            7 -> ArrayAdapter(context, android.R.layout.simple_spinner_item, jeju)
                            8 -> ArrayAdapter(context, android.R.layout.simple_spinner_item, sejong)
                            9 -> ArrayAdapter(context, android.R.layout.simple_spinner_item, gyeonggi)
                            10 -> ArrayAdapter(context, android.R.layout.simple_spinner_item, gangwon)
                            11 -> ArrayAdapter(context, android.R.layout.simple_spinner_item, chungbuk)
                            12 -> ArrayAdapter(context, android.R.layout.simple_spinner_item, chungnam)
                            13 -> ArrayAdapter(context, android.R.layout.simple_spinner_item, gyeongbuk)
                            14 -> ArrayAdapter(context, android.R.layout.simple_spinner_item, gyeongnam)
                            15 -> ArrayAdapter(context, android.R.layout.simple_spinner_item, jeonbuk)
                            16 -> ArrayAdapter(context, android.R.layout.simple_spinner_item, jeonnam)
                            else -> ArrayAdapter(context, android.R.layout.simple_spinner_item, seoul)
                        }
                    }
                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                }
            }

            //Preview ????????? ?????? Default Seoul
            sigunguSpinner.adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, seoul)
        }
        typedArray.recycle()
    }

    fun getSelectedItemPair() : Pair<String, String>{
        return Pair(
            spinnerBinding.sidoSpinner.selectedItem.toString(),
            spinnerBinding.sigunguSpinner.selectedItem.toString()
        )
    }

    val Float.dp: Int
        get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()


    companion object{
        const val regionAll = "??????"
        
        //https://gist.github.com/0x00000FF/5edda62b65cbc4ed483d682041800c88
        val region = listOf(
            "??????", "??????", "??????", "??????", "??????", "??????", "??????",
            "??????", "??????",
            "??????", "??????",
            "??????", "??????", "??????", "??????", "??????", "??????"
        )

        val seoul = listOf(
            "??????",
            "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????",
            "?????????", "????????????", "?????????", "?????????", "????????????", "?????????", "?????????", "?????????", "?????????",
            "?????????", "????????????", "?????????", "?????????", "?????????", "??????", "?????????"
        )

        val incheon = listOf("??????", "?????????", "?????????", "??????", "????????????", "?????????", "??????", "?????????", "??????")

        val busan = listOf(
            "??????",
            "?????????", "?????????", "??????", "??????", "?????????", "????????????", "??????", "?????????",
            "?????????", "??????", "?????????", "?????????", "?????????", "??????", "????????????"
        )

        val daejeon = listOf("??????", "?????????", "??????", "??????", "?????????", "??????")

        val daegu = listOf("??????", "??????", "?????????", "?????????", "??????", "??????", "??????", "?????????", "??????")

        val ulsan = listOf("??????", "??????", "??????", "??????", "??????", "?????????")

        val gwangju = listOf("??????", "?????????", "??????", "??????", "??????", "??????")

        val jeju = listOf("????????????", "?????????")

        val sejong = listOf("??????")

        val gyeonggi = listOf(
            "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "????????????", "????????????",
            "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????",
            "?????????", "?????????", "?????????", "????????????", "?????????", "?????????", "?????????", "?????????", "?????????",
            "?????????", "?????????", "?????????", "?????????"
        )

        val gangwon = listOf(
            "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????",
            "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????"
        )

        val chungbuk = listOf(
            "?????????", "?????????", "?????????", "?????????", "?????????", "?????????",
            "?????????", "?????????", "?????????", "?????????", "?????????"
        )

        val chungnam = listOf(
            "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????",
            "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????"
        )

        val gyeongbuk = listOf(
            "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????",
            "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????",
            "?????????", "?????????", "?????????"
        )

        val gyeongnam = listOf(
            "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????",
            "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????"
        )

        val jeonbuk = listOf(
            "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????",
            "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????"
        )

        val jeonnam = listOf(
            "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????",
            "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????",
            "?????????", "?????????", "?????????", "?????????"
        )
    }
}