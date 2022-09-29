package uz.khasanboevbobur.breakingnews.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import uz.khasanboevbobur.breakingnews.R

import uz.khasanboevbobur.breakingnews.databinding.ItemCategoryDesignBinding
import uz.khasanboevbobur.breakingnews.madels.adap.RVClass

class RecAdapterCategory1(var list: List<RVClass>, var listener: OnCardClicked): RecyclerView.Adapter<RecAdapterCategory1.VH>() {

    interface OnCardClicked {
        fun onclick(rvClass: Int, checked: Boolean)
    }

    inner class VH(var binding: ItemCategoryDesignBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemCategoryDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.apply {
            val item = list[position]

            txt.text = item.text

            card.setOnClickListener {
                item.isChecked = !item.isChecked
                setColor(item.isChecked, card, txt)
                listener.onclick(position, item.isChecked)
            }

            setColor(item.isChecked, card, txt)

        }
    }

    fun setColor(isChecked: Boolean, card: ConstraintLayout, txt: TextView) {
        if (isChecked) {
            card.setBackgroundResource(R.drawable.category_border_ischecked)
            txt.setTextColor(Color.WHITE)
        } else {
            card.setBackgroundResource(R.drawable.category_border)
            txt.setTextColor(Color.parseColor("#666C8E"))
        }
    }

    override fun getItemCount(): Int = list.size
}