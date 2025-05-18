package dev.donmanuel.cartoons.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dev.donmanuel.cartoons.databinding.FragmentDashboardBinding
import dev.donmanuel.cartoons.model.Simpsons
import dev.donmanuel.cartoons.ui.dashboard.adapter.SimpsonsAdapter

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var simpsonsAdapter: SimpsonsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupRecyclerView()
        observeViewModel(dashboardViewModel)

        return root
    }

    private fun setupRecyclerView() {
        simpsonsAdapter = SimpsonsAdapter()
        binding.recyclerViewEpisodes.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = simpsonsAdapter
        }
    }

    private fun observeViewModel(viewModel: DashboardViewModel) {
        // Observe title text
        viewModel.text.observe(viewLifecycleOwner) {
            binding.textDashboard.text = it
        }

        // Observe episodes data
        viewModel.episodes.observe(viewLifecycleOwner) { episodes ->
            simpsonsAdapter.setEpisodes(episodes)
        }

        // Observe loading state
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        // Observe error messages
        viewModel.error.observe(viewLifecycleOwner) { errorMsg ->
            if (errorMsg != null) {
                binding.textError.apply {
                    text = errorMsg
                    visibility = View.VISIBLE
                }
            } else {
                binding.textError.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}