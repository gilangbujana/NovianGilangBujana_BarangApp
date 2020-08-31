package com.gilangbujana.noviangilangbujana_androiddeveloper_31082020

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.gilangbujana.noviangilangbujana_androiddeveloper_31082020.data.Barang
import com.gilangbujana.noviangilangbujana_androiddeveloper_31082020.data.DatabaseHandler
import kotlinx.android.synthetic.main.activity_tambah_edit_barang.*

class TambahEditBarangActivity : AppCompatActivity() {

    var dbHandler: DatabaseHandler? = null
    var isEditMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_edit_barang)
    }

    private fun initDB() {
        dbHandler = DatabaseHandler(this)
        btn_hapus_barang.visibility = View.INVISIBLE
        if (intent != null && intent.getStringExtra("Mode") == "E") {
            isEditMode = true
            val barang: Barang = dbHandler!!.getBarang(intent.getIntExtra("Id",0))
            
        }
    }


}