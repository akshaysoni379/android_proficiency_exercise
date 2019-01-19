package com.wipro.android.proficiencyexercise.view.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.wipro.android.proficiencyexercise.R
import com.wipro.android.proficiencyexercise.WiproApp
import com.wipro.android.proficiencyexercise.data.remote.response.Row
import com.wipro.android.proficiencyexercise.databinding.ListFragmentBinding
import com.wipro.android.proficiencyexercise.utils.GlobalViewModelFactory
import com.wipro.android.proficiencyexercise.utils.LogUtil
import com.wipro.android.proficiencyexercise.utils.Utils
import com.wipro.android.proficiencyexercise.view.base.BaseFragment

import java.util.ArrayList

import javax.inject.Inject

class ListFragment : BaseFragment() {

    private val logTag = "ListFragment"
    private var listFragmentViewModel: ListFragmentViewModel? = null
    private var binding: ListFragmentBinding? = null

    @Inject
    lateinit var modelFactory: GlobalViewModelFactory<ListFragmentViewModel>

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity!!.applicationContext as WiproApp).appComponent.inject(this)
        listFragmentViewModel = ViewModelProviders.of(this, modelFactory).get(ListFragmentViewModel::class.java)
        binding!!.setLifecycleOwner(this)

        binding!!.swipeContainer.setOnRefreshListener { this.apiCall() }
        binding!!.swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light)
        setLiveDataObserver()
        apiCall()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.list_fragment, container, false)
        return binding!!.root
    }

    private fun apiCall() {
        if (Utils.checkNetwork(baseActivity)) {
            listFragmentViewModel!!.apiCall()
            binding!!.recyclerView.visibility = View.VISIBLE
            binding!!.networkErrorTv.visibility = View.GONE
        } else {
            binding!!.recyclerView.visibility = View.GONE
            binding!!.networkErrorTv.visibility = View.VISIBLE
            hideSwipeIndicator()
        }
    }

    private fun setLiveDataObserver() {
        listFragmentViewModel!!.apiResponse.observe(this, Observer {
            it?.let { it ->
                try {
                    if (it.rows != null && it.rows.size > 0) {
                        LogUtil.e(logTag, "size: " + it.rows.size)
                        setViewData(it.rows)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })

        listFragmentViewModel!!.loaderData.observe(this, Observer { it ->
            it?.let { it ->
                if (!it) {
                    hideSwipeIndicator()
                }
            }
        })
    }

    private fun hideSwipeIndicator() {
        if (binding!!.swipeContainer != null && binding!!.swipeContainer.isShown) {
            binding!!.swipeContainer.isRefreshing = false
        }
    }

    private fun setViewData(rowsList: ArrayList<Row>?) {
        val mAdapter = ListAdapter(baseActivity, rowsList!!)
        val mLayoutManager = LinearLayoutManager(baseActivity)
        binding!!.recyclerView.layoutManager = mLayoutManager
        binding!!.recyclerView.adapter = mAdapter
    }
}