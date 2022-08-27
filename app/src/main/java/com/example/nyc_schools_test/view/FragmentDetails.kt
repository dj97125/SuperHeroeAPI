package com.example.nyc_schools_test.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.nyc_schools_test.common.BaseFragment
import com.example.nyc_schools_test.databinding.FragmentDetailsBinding
import com.squareup.picasso.Picasso


class FragmentDetails : BaseFragment() {


    private val binding by lazy {
        FragmentDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val heroInfo = viewModel.heroe

        binding.apply {
            if (heroInfo != null) {
                Picasso.get()
                    .load(heroInfo.imageResponse?.url)
                    .into(backgroundDetails)
                detailsOriginalTitle.text = heroInfo.name

                strenghtValue.text = heroInfo.powerstatsResponse?.strength
                intelligenceValue.text = heroInfo.powerstatsResponse?.intelligence
                speedValue.text = heroInfo.powerstatsResponse?.speed
                durabilityValue.text = heroInfo.powerstatsResponse?.durability
                powerValue.text = heroInfo.powerstatsResponse?.power
                combatValue.text = heroInfo.powerstatsResponse?.combat
            }
            return binding.root
        }

    }
}