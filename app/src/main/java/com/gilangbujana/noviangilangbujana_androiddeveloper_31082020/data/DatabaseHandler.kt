package com.gilangbujana.noviangilangbujana_androiddeveloper_31082020.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, DatabaseHandler.DB_NAME, null, DatabaseHandler.DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME (" +
                ID + " INTEGER PRIMARY KEY," +
                NAMA_BARANG + " TEXT," + QTY + " INTEGER," +
                EXP_DATE + " TEXT," + HARGA + " INTEGER);"
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME
        db.execSQL(DROP_TABLE)
        onCreate(db)
    }

    fun addBarang(barang: Barang): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NAMA_BARANG, barang.nama_barang)
        values.put(QTY, barang.qty)
        values.put(EXP_DATE, barang.exp_date)
        values.put(HARGA, barang.harga)
        val _success = db.insert(TABLE_NAME, null, values)
        db.close()
        return (Integer.parseInt("$_success") != -1)
    }

    fun getBarang(_id: Int): Barang {
        val barang = Barang()
        val db = writableDatabase
        val selectQuery = "SELECT  * FROM $TABLE_NAME WHERE $ID = $_id"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            cursor.moveToFirst()
            while (cursor.moveToNext()) {
                barang.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID)))
                barang.nama_barang = cursor.getString(cursor.getColumnIndex(NAMA_BARANG))
                barang.qty = Integer.parseInt(cursor.getString(cursor.getColumnIndex(QTY)))
                barang.exp_date = cursor.getString(cursor.getColumnIndex(EXP_DATE))
                barang.harga = Integer.parseInt(cursor.getString(cursor.getColumnIndex(HARGA)))
            }
        }
        cursor.close()
        return barang
    }

    val barangbarang: List<Barang>
        get() {
            val barangList = ArrayList<Barang>()
            val db = writableDatabase
            val selectQuery = "SELECT  * FROM $TABLE_NAME"
            val cursor = db.rawQuery(selectQuery, null)
            if (cursor != null) {
                cursor.moveToFirst()
                while (cursor.moveToNext()) {
                    val barang = Barang()
                    barang.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID)))
                    barang.nama_barang = cursor.getString(cursor.getColumnIndex(NAMA_BARANG))
                    barang.qty = Integer.parseInt(cursor.getString(cursor.getColumnIndex(QTY)))
                    barang.exp_date = cursor.getString(cursor.getColumnIndex(EXP_DATE))
                    barang.harga = Integer.parseInt(cursor.getString(cursor.getColumnIndex(HARGA)))
                    barangList.add(barang)
                }
            }
            cursor.close()
            return barangList
        }

    fun updateBarang(barang: Barang): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NAMA_BARANG, barang.nama_barang)
        values.put(QTY, barang.qty)
        values.put(EXP_DATE, barang.exp_date)
        values.put(HARGA, barang.harga)
        val _success = db.update(TABLE_NAME, values, ID + "=?", arrayOf(barang.id.toString())).toLong()
        db.close()
        return Integer.parseInt("$_success") != -1
    }

    fun deleteBarang(_id: Int): Boolean {
        val db = this.writableDatabase
        val _success = db.delete(TABLE_NAME, ID + "=?", arrayOf(_id.toString())).toLong()
        db.close()
        return Integer.parseInt("$_success") != -1
    }

    companion object {

        private val DB_VERSION = 1
        private val DB_NAME = "BarangDB"
        private val TABLE_NAME = "Barang"
        private val ID = "Id"
        private val NAMA_BARANG = "Nama_Barang"
        private val QTY = "Qty"
        private val EXP_DATE = "Exp_date"
        private val HARGA = "harga"
    }
}