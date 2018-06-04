package com.example.maksymg.workingwiththreads

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.os.AsyncTask


class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnGo.setOnClickListener(this::onClick)
    }

    override fun onClick(p0: View?) {
        val task = EmulateDownloadFilesTask().execute(0,1,2,3,4,5,6,7)
    }

    private inner class EmulateDownloadFilesTask : AsyncTask<Int, Int, Int>() {
        override fun doInBackground(vararg values: Int?): Int {
            for (value in values) {
                Thread.sleep(1000)
                publishProgress(value)
            }
            return values.size
        }

        override fun onProgressUpdate(vararg values: Int?) {
            //image.setImageBitmap(values[0])
            counterTasksTextView.text = (values[0]!! +1).toString() + "th task was executed"
        }

        override fun onPostExecute(result: Int?) {
            tasksTextView.text = "All tasks are finished"
            layout.setBackgroundColor(Color.CYAN)
        }
    }


}
