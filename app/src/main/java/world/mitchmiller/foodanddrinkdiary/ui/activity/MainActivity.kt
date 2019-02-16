package world.mitchmiller.foodanddrinkdiary.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.CompoundButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_barcode_setup.*
import kotlinx.android.synthetic.main.activity_main.*
import world.mitchmiller.foodanddrinkdiary.R
import world.mitchmiller.foodanddrinkdiary.ui.adapter.FoodListAdapter
import world.mitchmiller.foodanddrinkdiary.vision.BarcodeCaptureActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var autoFocus: CompoundButton
    private lateinit var useFlash: CompoundButton
    private lateinit var statusMessage: TextView
    private lateinit var barcodeValue: TextView
    private lateinit var floatingActionButton: FloatingActionButton

    companion object {
        private const val RC_BARCODE_CAPTURE = 9001
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()

        floatingActionButton = main_fab

//        statusMessage = status_message
//        barcodeValue = barcode_value
//
//        autoFocus = auto_focus
//        useFlash = use_flash
//
//        read_barcode.setOnClickListener(this)
        floatingActionButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
//        if (v?.id == R.id.read_barcode) {
//            launchBarcodeActivity()
//        }
        if (v?.id == R.id.main_fab) {
            handleFabClick()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RC_BARCODE_CAPTURE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    val barcode: Barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject)
                    statusMessage.text = getString(R.string.barcode_success)
                    barcodeValue.text = barcode.displayValue
                    Log.d(TAG, "Barcode read: " + barcode.displayValue)
                } else {
                    statusMessage.text = getString(R.string.barcode_failure)
                    Log.d(TAG, "No barcode captured, intent data is null")
                }
            } else {
                statusMessage.text = String.format(getString(R.string.barcode_error),
                    CommonStatusCodes.getStatusCodeString(resultCode))
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.food_recyclerview)
        val adapter = FoodListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun handleFabClick() {

    }

    private fun launchBarcodeActivity() {
        val intent = Intent(this, BarcodeCaptureActivity::class.java)
        intent.putExtra(BarcodeCaptureActivity.AutoFocus, true)
        intent.putExtra(BarcodeCaptureActivity.UseFlash, false)
        startActivityForResult(intent,
            RC_BARCODE_CAPTURE
        )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
}
