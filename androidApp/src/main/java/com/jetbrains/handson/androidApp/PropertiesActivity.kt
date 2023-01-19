package com.jetbrains.handson.androidApp

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.jetbrains.handson.kmm.shared.entity.Properties
import com.jetbrains.handson.kmm.shared.entity.Property
import com.jetbrains.handson.kmm.shared.uimain.RocketLaunch
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PropertiesActivity : AppCompatActivity() {

    private lateinit var launchesRecyclerView: RecyclerView
    private lateinit var progressBarView: FrameLayout
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private val propertiesRvAdapter = PropertiesRvAdapter(listOf())

    private val propertiesViewModel: PropertiesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "SpaceX Launches"
        setContentView(R.layout.activity_properties)

        launchesRecyclerView = findViewById(R.id.launchesListRv)
        progressBarView = findViewById(R.id.progressBar)
        swipeRefreshLayout = findViewById(R.id.swipeContainer)

        launchesRecyclerView.adapter = propertiesRvAdapter
        launchesRecyclerView.layoutManager = LinearLayoutManager(this)

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            propertiesViewModel.onRefresh()
        }

        progressBarView.isVisible = true
        propertiesViewModel.properties.observe(this, ::displayLaunches)
    }

    private fun displayLaunches(rocketLaunches: List<Property>) {
        if (rocketLaunches.isEmpty()) {
            Toast.makeText(this@PropertiesActivity, "Error on get launches", Toast.LENGTH_SHORT).show()
        } else {
            propertiesRvAdapter.properties = rocketLaunches
            propertiesRvAdapter.notifyDataSetChanged()
        }
        progressBarView.isVisible = false
    }
}