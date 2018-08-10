package com.arc.imageprocessing

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.arc.mlkit.LivePreviewActivity
import com.arc.mlkit.barcodescanning.Barcode
import com.google.firebase.FirebaseApp

class MainActivity : AppCompatActivity(), View.OnClickListener{
    lateinit var spinner:Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        spinner = findViewById(R.id.select_action)
        val list = listOf(LivePreviewActivity.BARCODE_DETECTION
                , LivePreviewActivity.CLASSIFICATION
                , LivePreviewActivity.FACE_DETECTION
                , LivePreviewActivity.TEXT_DETECTION
                , LivePreviewActivity.IMAGE_LABEL_DETECTION)

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.live_preview -> {
                onLivePreviewClick(v)
            }
            R.id.still_image -> {
                onChooseFromDeviceClick(v)
            }
        }

    }

    private fun onLivePreviewClick(v: View) {
        val intent = Intent(this, LivePreviewActivity::class.java)
        intent.putExtra(LivePreviewActivity.ACTION, spinner.selectedItem.toString())
        startActivityForResult(intent, 1)
    }

    private fun onChooseFromDeviceClick(v: View) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == 1){
                val barcode = Barcode.build(data?.getStringArrayListExtra("StringList"), data?.getIntegerArrayListExtra("CoordinateList"))
                Toast.makeText(this, barcode.displayValue , Toast.LENGTH_LONG).show()
            }
        }
    }
}
