package world.mitchmiller.foodanddrinkdiary.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import world.mitchmiller.foodanddrinkdiary.R

// TODO: Current spot in tutorial: https://codelabs.developers.google.com/codelabs/android-room-with-a-view-kotlin/#13
class NewFoodActivity : AppCompatActivity() {
    private lateinit var editFoodView: EditText
    private lateinit var caloriesEditText: EditText
    private lateinit var notesEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_food_item)
        editFoodView = findViewById(R.id.edit_food)
        caloriesEditText = findViewById(R.id.edit_calories)
        notesEditText = findViewById(R.id.edit_notes)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editFoodView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val foodName = editFoodView.text.toString()
                val cals = editFoodView.text.toString()
                val notes = notesEditText.text.toString()

                replyIntent.putExtra(EXTRA_REPLY_NAME, foodName)
                replyIntent.putExtra(EXTRA_REPLY_CAL, cals)
                replyIntent.putExtra(EXTRA_REPLY_NOTES, notes)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY_NAME = "name"
        const val EXTRA_REPLY_CAL = "calories"
        const val EXTRA_REPLY_NOTES = "notes"
    }
}