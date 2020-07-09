package com.example.projectexample.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectexample.R
import com.example.projectexample.ui.model.ItemX
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.supplier_layouts.view.*

class SupplierItemAdapter(var itemList: List<ItemX> = ArrayList())
    : RecyclerView.Adapter<SupplierItemAdapter.ItemViewHolder>() {

    private var mClickListener: ClickListener? = null
    fun setOnclickListener(clickListener: ClickListener) {
        this.mClickListener = clickListener
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        lateinit var supplierItemList: ItemX
        fun bindItem(item: ItemX) {
            this.supplierItemList = item
            Picasso.get()
                    .load("http://order.khaingthinkyi.me/" + item.image)
                    .placeholder(R.drawable.ic_launcher_background).resize(180, 270)
                    .into(itemView.image_item)
            itemView.text_item_name.text = item.name
            itemView.text_item_price.text = item.price
            itemView.text_supplier_name.text = item.user.name

        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            mClickListener?.onClick(supplierItemList)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        var viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.supplier_layouts, parent, false);
        return ItemViewHolder(viewHolder);
        Log.d("ViewHolder", ItemViewHolder(viewHolder).toString())
    }

    override fun getItemCount(): Int {
        return itemList.size;
        Log.d("Size", itemList.size.toString())
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindItem(itemList[position])
    }

    fun updateSupplierItem(item: List<ItemX>) {
        this.itemList = item
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onClick(supplierItemList: ItemX)
    }
}
