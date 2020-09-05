package com.pockettech.goldenratio
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import java.lang.Exception
import java.math.RoundingMode
import java.text.DecimalFormat


class Interactive : Fragment() {
    lateinit var a:EditText
    lateinit var b:TextView
    lateinit var area:TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_interactive, container, false)
        a=view.findViewById(R.id.eta)
        b=view.findViewById(R.id.c3)
        area=view.findViewById(R.id.area1)
        a.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val i:Int
                try{
                    i=a.text.toString().toInt()
                    var j=i/1.618033
                    val j1=Math.round(j*100000f)/100000f
                    val rea=i*j
                    val j2=Math.round(rea*100000f)/100000f


                    b.text=j1.toString()
                    area.text=j2.toString()
                }
                catch (e:Exception){
                    b.text="0"
                    area.text="0.00"
                }


            }

        })
        return view
    }
}