package world.mitchmiller.foodanddrinkdiary

import androidx.room.Entity

@Entity
data class DrinkItem(val name: String, val calories: Float, val notes: String)