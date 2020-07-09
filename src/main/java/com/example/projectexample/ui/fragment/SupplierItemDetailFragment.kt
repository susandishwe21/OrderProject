package com.example.projectexample.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projectexample.R
import com.example.projectexample.ui.api.ItemApi
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_supplier_item_detail.*

class SupplierItemDetailFragment : Fragment() {

    private val supplierItemApi: ItemApi = ItemApi()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_supplier_item_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var data = arguments.let {
            SupplierItemDetailFragmentArgs.fromBundle(it!!)
        }

        Picasso.get()
                .load("http://order.khaingthinkyi.me/"+data.image)
                .placeholder(R.drawable.ic_launcher_background)
                .into(supplier_detail_image_view)
        supplier_name_text_view.text = data.suppliername
        supplier_address_text_view.text = data.address
        supplier_phone_text_view.text = data.phone
        supplier_item_name_text_view.text = data.itemname
        supplier_item_price_text_view.text = data.price

    }

}