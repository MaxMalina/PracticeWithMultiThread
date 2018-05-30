package com.example.maksymg.workingwiththreads

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.os.AsyncTask
import java.net.URL
import android.graphics.BitmapFactory
import android.graphics.Bitmap



class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button_add.setOnClickListener(this::onClick)
    }

    override fun onClick(p0: View?) {
        /*val serialExecutor = SerialExecutor(Executors.newSingleThreadExecutor())
        serialExecutor.execute({
            Thread.sleep(10000)

            runOnUiThread ({
                textView_counter_tasks.text = counter++.toString()
                Toast.makeText(this, "Wassup "+ counter.toString(), Toast.LENGTH_SHORT).show()
            })
        })

        Toast.makeText(this, "Anyways Jim how you doing", Toast.LENGTH_SHORT).show()
        */

        val task = EmulateDownloadFilesTask().execute(
            URL("https://upload.wikimedia.org/wikipedia/commons/4/49/Elon_Musk_2015.jpg"),
            URL("https://fm.cnbc.com/applications/cnbc.com/resources/img/editorial/2018/02/07/104994096-RTX4RL3G.1910x1000.jpg?v=1526048197"),
            URL("https://amp.businessinsider.com/images/5a451b22b0bcd51d008b7445-750-562.jpg"),
            URL("https://boygeniusreport.files.wordpress.com/2017/07/1500295159151.jpg?quality=98&strip=all&w=782")
        )
    }

    private inner class EmulateDownloadFilesTask : AsyncTask<URL, Bitmap, Long>() {
        override fun doInBackground(vararg urls: URL?): Long? {
            val count = urls.size
            var totalSize: Long = 0
            for (i in 0 until count) {
                val bmp = BitmapFactory.decodeStream(urls.get(i)?.openConnection()?.getInputStream())
                publishProgress(bmp)
                //publishProgress("Downloaded images " + (i+1).toString() + "th from " + count)
            }
            return totalSize
        }

        override fun onProgressUpdate(vararg values: Bitmap?) {
            image.setImageBitmap(values[0])
        }

        override fun onPostExecute(result: Long?) {
            textView_tasks.text = "Finish"
        }
    }


}
