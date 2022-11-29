package com.example.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.customview.databinding.LayoutUserCardBinding

class UserCardView @JvmOverloads constructor(
    context: Context,
    attribbuteSet: AttributeSet? = null,
    styleAttribute: Int = 0,
): ConstraintLayout(context, attribbuteSet, styleAttribute) {
    private var binding: LayoutUserCardBinding
    init {
        binding = LayoutUserCardBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setUpperText(text: String){
        binding.nameOfuser.text = text
    }
    fun setLowwerText(text: String){
        binding.rekNum.text = text
    }

    fun setTextColor(upperColorId: Int? = null, lowerColorId: Int? = null){
        if (upperColorId != null)
            binding.nameOfuser.setTextColor(ContextCompat.getColor(context, upperColorId))


        if(lowerColorId != null)
            binding.rekNum.setTextColor(ContextCompat.getColor(context, lowerColorId))
    }
}