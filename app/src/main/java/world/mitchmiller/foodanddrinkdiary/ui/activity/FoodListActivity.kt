package world.mitchmiller.foodanddrinkdiary.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import world.mitchmiller.foodanddrinkdiary.R
import world.mitchmiller.foodanddrinkdiary.ui.adapter.FoodListAdapter

class FoodListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_list)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = FoodListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}