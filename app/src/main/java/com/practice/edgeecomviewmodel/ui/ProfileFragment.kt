package com.practice.edgeecomviewmodel.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.practice.edgeecomviewmodel.R

class ProfileFragment : Fragment() {
    private lateinit var lableName: TextView
    private  lateinit var lablePhone : TextView
    private lateinit var lableEmail : TextView
    private lateinit var labelBirthDate : TextView
    private lateinit var submitBtn : TextView
    private lateinit var selectDate : EditText
    private lateinit var floatingBtn : FloatingActionButton


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lableName = view.findViewById(R.id.lableName)
        lablePhone = view.findViewById(R.id.lableMobile)
        lableEmail = view.findViewById(R.id.labelEmail)
        labelBirthDate = view.findViewById(R.id.labelBirthDate)
        submitBtn = view.findViewById(R.id.submitBtn)
        floatingBtn = view.findViewById(R.id.fab)


        placeLabelWithAsterisk(lableName, "Name")
        placeLabelWithAsterisk(lablePhone, "Mobile No.")
        placeLabelWithAsterisk(lableEmail, "Email")
        placeLabelWithAsterisk(labelBirthDate, "Date of Birth")


        floatingBtn.setOnClickListener {

            val intent = Intent(requireContext(), AddPost::class.java)
            startActivity(intent)
        }
    }
        fun placeLabelWithAsterisk(label: TextView, requiredText: String) {
            val asterisk = "*"
            val spannableString = SpannableString("$requiredText $asterisk")

            spannableString.setSpan(
                ForegroundColorSpan(Color.RED),
                requiredText.length + 1,
                requiredText.length + 2,
                SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            label.text = spannableString
        }




}