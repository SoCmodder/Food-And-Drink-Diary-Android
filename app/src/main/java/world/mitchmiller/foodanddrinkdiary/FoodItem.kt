package world.mitchmiller.foodanddrinkdiary

import androidx.room.Entity

@Entity
data class FoodItem(val name: String, val calories: Float, val notes: String)