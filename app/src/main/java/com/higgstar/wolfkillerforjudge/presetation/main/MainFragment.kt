package com.higgstar.wolfkillerforjudge.presetation.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.higgstar.wolfkillerforjudge.R
import com.higgstar.wolfkillerforjudge.domain.model.role.PlayRole
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    private val initRole: MutableList<PlayRole> = mutableListOf()

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        btn_start.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.container, GameFragment.newInstance(initRole))
                ?.commitNow()
        }

        cb_hunter.setOnCheckedChangeListener { _, isChecked ->
            onStoreRoleChanged(
                PlayRole.Hunter,
                isChecked
            )
        }

        cb_idoit.setOnCheckedChangeListener { _, isChecked ->
            onStoreRoleChanged(
                PlayRole.Idiot,
                isChecked
            )
        }
        cb_knight.setOnCheckedChangeListener { _, isChecked ->
            onStoreRoleChanged(
                PlayRole.Kight,
                isChecked
            )
        }
        cb_prophet.setOnCheckedChangeListener { _, isChecked ->
            onStoreRoleChanged(
                PlayRole.Hunter,
                isChecked
            )
        }
        cb_witch.setOnCheckedChangeListener { _, isChecked ->
            onStoreRoleChanged(
                PlayRole.Witch,
                isChecked
            )
        }

        cb_wolf_king.setOnCheckedChangeListener { _, isChecked ->
            onStoreRoleChanged(
                PlayRole.WolfKing,
                isChecked
            )
        }

        sp_villige.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val count = resources.getStringArray(R.array.normal_role_amount)[position].toInt()
                initRole.removeAll { it == PlayRole.Villiage }
                for (index in 1..count) {
                    initRole.add(PlayRole.Villiage)
                }
            }
        }

        sp_werewolf_count.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val count = resources.getStringArray(R.array.normal_role_amount)[position].toInt()
                initRole.removeAll { it == PlayRole.Werewolf }
                for (index in 1..count) {
                    initRole.add(PlayRole.Werewolf)
                }
            }
        }

    }

    private fun onStoreRoleChanged(role: PlayRole, isSelect: Boolean) {
        if (isSelect) {
            initRole.add(role)
        } else {
            initRole.remove(role)
        }
    }

}
