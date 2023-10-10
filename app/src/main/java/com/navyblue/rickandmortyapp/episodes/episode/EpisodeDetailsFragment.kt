package com.navyblue.rickandmortyapp.episodes.episode

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.airbnb.epoxy.EpoxyRecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.navyblue.rickandmortyapp.R
import com.navyblue.rickandmortyapp.databinding.FragmentEpisodeDetailsBinding


class EpisodeDetailsFragment : BottomSheetDialogFragment() {

    private val viewModel : EpisodeDetailsViewModel by viewModels()
    private val safeArgs : EpisodeDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_episode_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.episodeLiveData.observe(viewLifecycleOwner){episode ->
            if (episode == null){
                return@observe
            }

            val episodeName = view.findViewById<AppCompatTextView>(R.id.episodeNameTextView)
            episodeName.text = episode.name
            val episodeAirDate = view.findViewById<AppCompatTextView>(R.id.airDateTextView)
            episodeAirDate.text = episode.airDate
            val episodeNumber = view.findViewById<AppCompatTextView>(R.id.episodeNumberTextView)
            episodeNumber.text = episode.getFormattedSeason()
            val epoxyRecyclerView = view.findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerView)
            epoxyRecyclerView.setControllerAndBuildModels(
                EpisodeDetailsEpoxyController(episode.characters)
            )

        }
        viewModel.refreshEpisode(safeArgs.episodeId)
    }

}