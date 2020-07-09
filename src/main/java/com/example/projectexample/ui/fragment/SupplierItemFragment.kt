package com.example.projectexample.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectexample.R
import com.example.projectexample.ui.adapter.SupplierItemAdapter
import com.example.projectexample.ui.model.ItemX
import com.example.projectexample.viewmodel.SupplierItemViewModel
import kotlinx.android.synthetic.main.fragment_supplier_home.*

class SupplierItemFragment : Fragment() ,SupplierItemAdapter.ClickListener{

    private lateinit var detailViewModel: SupplierItemViewModel
    private lateinit var itemSupplierAdapter: SupplierItemAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        detailViewModel =
                ViewModelProviders.of(this).get(SupplierItemViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_supplier_home, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view_supplier.layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.VERTICAL, false
        )

        itemSupplierAdapter = SupplierItemAdapter()
        recycler_view_supplier.adapter = itemSupplierAdapter
        observeViewModel()
    }

    private fun observeViewModel() {
        detailViewModel = ViewModelProvider(this)
                .get(SupplierItemViewModel::class.java)
        detailViewModel.loadDetail()
        detailViewModel.getItem().observe(viewLifecycleOwner, Observer {
            recycler_view_supplier.visibility = View.VISIBLE
            loadError.visibility = View.GONE
            itemSupplierAdapter.updateSupplierItem(it)
            Log.d("Fra", it.toString())
            itemSupplierAdapter.setOnclickListener(this)

        })

        detailViewModel.getLoadError().observe(
                viewLifecycleOwner, Observer
        {
            recycler_view_supplier.visibility = View.GONE
            loadError.visibility = View.VISIBLE
        })
    }

    override fun onResume() {
        super.onResume()
        detailViewModel.loadDetail()
    }

    override fun onClick(supplierItemList: ItemX) {
        var action = SupplierItemFragmentDirections.actionNavigationHomeToSupplierItemDetailFragment(
                supplierItemList.image,
                supplierItemList.user.name,
                supplierItemList.user.address,
                supplierItemList.user.phone_no,
                supplierItemList.name,
                supplierItemList.price
        )
        findNavController().navigate(action)
    }
}