package com.navyblue.rickandmortyapp.episodes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import com.airbnb.epoxy.EpoxyRecyclerView
import com.navyblue.rickandmortyapp.R
import com.navyblue.rickandmortyapp.domain.models.Episode
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class EpisodeListFragment : Fragment() {

    private val viewModel : EpisodesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_episode_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val epoxyController = EpisodeListEpoxyController()

        lifecycleScope.launch {
            viewModel.flow.collectLatest { pagingData: PagingData<EpisodesUiModel> ->
                epoxyController.submitData(pagingData)
            }
        }
        val epoxyRecyclerView = view.findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerView)
        epoxyRecyclerView.setController(epoxyController)
    }

}