package com.higgstar.wolfkillerforjudge.presetation.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.higgstar.wolfkillerforjudge.R
import com.higgstar.wolfkillerforjudge.domain.model.role.PlayRole
import io.reactivex.disposables.Disposable

@Suppress("UNCHECKED_CAST")
class GameFragment : Fragment() {
    private lateinit var disposable: Disposable

    companion object {
        fun newInstance(initRole: MutableList<PlayRole>): GameFragment {
            val frag = GameFragment()
            frag.initRole = initRole
            return frag
        }
    }

    private lateinit var viewModel: GameViewModel
    private var initRole: List<PlayRole> = emptyList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.game_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return GameViewModel(initRole) as T
            }
        }).get(GameViewModel::class.java)
        disposable = viewModel.start().subscribe {

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}
