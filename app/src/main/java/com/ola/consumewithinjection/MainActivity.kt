package com.ola.consumewithinjection

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ola.consumewithinjection.databinding.ActivityMainBinding
import com.ola.consumewithinjection.ui.adapter.CatFactAdapter
import com.ola.consumewithinjection.ui.event.Event
import com.ola.consumewithinjection.ui.viewmodel.CatFactViewModel
import com.ola.consumewithinjection.ui.viewstate.State
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: CatFactViewModel by viewModels()
    private var catFactAdapter: CatFactAdapter = CatFactAdapter(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.event.send(Event.FetchCatFacts(10))
            }
        }

        setUpRecyclerView()
        observeViewModel()
    }

    private fun setUpRecyclerView() {
        binding.apply {
            mainActivityRv.apply {
                adapter = catFactAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when (state) {
                        is State.Idle -> {}
                        is State.Loading -> {

                        }
                        is State.GetCatFacts -> {
                            catFactAdapter.submitList(state.catFacts)
                        }
                        is State.Error -> {
                            val errorMessage = state.message.asString(this@MainActivity)
                            Log.d("TAG", "observeViewModel: $errorMessage")
                        }
                    }
                }
            }
        }
    }
}