package com.priyan.nameprobability.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.priyan.nameprobability.R
import com.priyan.nameprobability.databinding.ActivityMainBinding
import com.priyan.nameprobability.main.NameProbabilityViewModel
import com.priyan.nameprobability.view.adapters.CustomAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val nameProbabilityViewModel: NameProbabilityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val editTextName = binding.edtName
        val btnSearch = binding.btnSearch
        val recyclerview = binding.recyclerview


        btnSearch.setOnClickListener {
            nameProbabilityViewModel.getData(editTextName.text.toString())
        }

        lifecycleScope.launchWhenStarted {
            nameProbabilityViewModel.conversion.collect { event ->
                when(event) {
                    is NameProbabilityViewModel.ProbabilityEvent.Success -> {
                        Log.i("PRIYAN","Success")
                        Log.i("PRIYAN",""+ (event.countryList?.size ?: 0))

                        recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
                        val adapter = event.countryList?.let { CustomAdapter(it) }
                        recyclerview.adapter = adapter

                    }
                    is NameProbabilityViewModel.ProbabilityEvent.Failure -> {
                        Log.i("PRIYAN","Failure")
                    }
                    is NameProbabilityViewModel.ProbabilityEvent.Loading -> {
                        Log.i("PRIYAN","Loading")
                    }
                    else -> Unit
                }
            }
        }

    }
}