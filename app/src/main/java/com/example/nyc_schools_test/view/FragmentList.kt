package com.example.nyc_schools_test.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.nyc_schools_test.R
import com.example.nyc_schools_test.common.BaseFragment
import com.example.nyc_schools_test.common.OnHeroeClicked
import com.example.nyc_schools_test.common.StateAction
import com.example.nyc_schools_test.databinding.FragmentListBinding
import com.example.nyc_schools_test.model.remote.responses.HeroeDomain
import kotlinx.coroutines.launch


class FragmentList : BaseFragment(), OnHeroeClicked {


    private val binding by lazy {
        FragmentListBinding.inflate(layoutInflater)
    }
    private val heroeAdapter by lazy {
        HeroeAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding.recomendedRecycler.apply {
            adapter = heroeAdapter
        }


        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.heroeResponse.collect { state ->
                    when (state) {
                        is StateAction.SUCCESS<*> -> {
                            val retrievedHeroe = state.response as List<HeroeDomain>
                            binding.apply {
                                heroeAdapter.erraseData()
                                heroeAdapter.updateData(retrievedHeroe)
                                swipeRefresh.visibility = View.VISIBLE
                                recomendedRecycler.visibility = View.VISIBLE

                            }
                        }
                        is StateAction.ERROR -> {
                            binding.apply {
                                recomendedRecycler.visibility = View.GONE
                                swipeRefresh.visibility = View.GONE
                            }
                            displayErrors(state.error.localizedMessage) {
                                viewModel.getHeroeList()
                            }
                        }

                    }
                }
            }
        }


        return binding.root
    }

    override fun heroeClicked(heroe: HeroeDomain) {
        viewModel.heroe = heroe
        findNavController().navigate(R.id.action_fragmentList_to_fragmentDetails)
    }


    override fun onResume() {
        super.onResume()
        binding.swipeRefresh.apply {
            setColorSchemeResources(
                android.R.color.holo_blue_dark,
                android.R.color.holo_purple,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_bright,
                android.R.color.holo_red_light,
                android.R.color.holo_green_dark,
            )
            binding.swipeRefresh.setOnRefreshListener {
                viewModel.getHeroeList()
                binding.swipeRefresh.isRefreshing = false
            }
        }
    }

}