package world.mitchmiller.foodanddrinkdiary.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import world.mitchmiller.foodanddrinkdiary.R
import world.mitchmiller.foodanddrinkdiary.db.entity.FoodItem

class FoodListAdapter internal constructor(context: Context) : RecyclerView.Adapter<FoodListAdapter.FoodViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var foodItems = emptyList<FoodItem>() // Cached copy of food items

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return FoodViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return foodItems.size
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val current = foodItems[position]
        holder.foodItemView.text = current.name
    }

    inner class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodItemView: TextView = itemView.findViewById(R.id.textView)
    }
}