package com.example.chapter10.part2.section5

import android.content.Context
import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.widget.*
import com.example.chapter10.R
import java.io.ByteArrayOutputStream
import java.util.concurrent.*
import java.util.concurrent.atomic.AtomicInteger

/**
 * @author wangzhichao
 * @since 2019/12/10
 */
class H_BitmapCompressViewGroup(context: Context?, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {
    private val h = Handler(Looper.getMainLooper())
    private val threadPoolExecutor: ThreadPoolExecutor = ThreadPoolExecutor(
        CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_SECONDS, TimeUnit.SECONDS,
        sPoolWorkQueue, sThreadFactory)

    init {
        inflate(context, R.layout.layout_bitmap_compress_viewgroup, this)
        val iv1 = findViewById<ImageView>(R.id.iv1)
        val iv2 = findViewById<ImageView>(R.id.iv2)
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.cat)
        iv1.setImageBitmap(bitmap)
        threadPoolExecutor.execute {
            val baos = ByteArrayOutputStream()
            val compress = bitmap.compress(CompressFormat.JPEG, 1, baos)
            val bytes = baos.toByteArray()
            val bitmap1 = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            h.post { iv2.setImageBitmap(bitmap1) }
        }
    }

    companion object {
        private val CPU_COUNT = Runtime.getRuntime().availableProcessors()
        private val CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4))
        private val MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1
        private const val KEEP_ALIVE_SECONDS = 30L
        private val sPoolWorkQueue: BlockingQueue<Runnable> = LinkedBlockingQueue(128)
        private val sThreadFactory: ThreadFactory = object : ThreadFactory {
            private val mCount = AtomicInteger(1)
            override fun newThread(r: Runnable): Thread {
                return Thread(r, "AsyncTask #" + mCount.getAndIncrement())
            }
        }
    }

}