package com.jetbrains.handson.androidApp

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.jetbrains.handson.kmm.shared.uimain.RocketLaunch
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var launchesRecyclerView: RecyclerView
    private lateinit var progressBarView: FrameLayout
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var fab: FloatingActionButton

    private val launchesRvAdapter = LaunchesRvAdapter(listOf())

    private val launchesViewModel: LaunchesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "SpaceX Launches"
        setContentView(R.layout.activity_main)

        launchesRecyclerView = findViewById(R.id.launchesListRv)
        progressBarView = findViewById(R.id.progressBar)
        swipeRefreshLayout = findViewById(R.id.swipeContainer)
        fab = findViewById(R.id.fab)

        launchesRecyclerView.adapter = launchesRvAdapter
        launchesRecyclerView.layoutManager = LinearLayoutManager(this)

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            launchesViewModel.onRefresh()
        }

        progressBarView.isVisible = true
        launchesViewModel.launches.observe(this, ::displayLaunches)

        fab.setOnClickListener { startActivity(Intent(this, PropertiesActivity::class.java)) }
    }

    private fun displayLaunches(rocketLaunchDtos: List<RocketLaunch>) {
        if (rocketLaunchDtos.isEmpty()) {
            Toast.makeText(this@MainActivity, "Error on get launches", Toast.LENGTH_SHORT).show()
        } else {
            launchesRvAdapter.launches = rocketLaunchDtos
            launchesRvAdapter.notifyDataSetChanged()
        }
        progressBarView.isVisible = false
    }
}