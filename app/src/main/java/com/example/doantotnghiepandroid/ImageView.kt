package com.example.doantotnghiepandroid

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.Glide
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import kotlin.math.min
import kotlin.math.roundToInt

internal fun ImageView.loadImage(url: String?){
    Glide.with(this).load(url).into(this)
}

internal fun ImageView.loadCircleImage(url: String?){
    Glide.with(this).load(url).circleCrop().into(this)
}

internal fun ImageView.fixSizeBitmap(maxImageSize: Float, filter: Boolean): Bitmap {
    val ratio = min(
        maxImageSize / this.width,
        maxImageSize / this.height
    )
    val width = (ratio * this.width).roundToInt()
    val height = (ratio * this.height).roundToInt()

    return Bitmap.createScaledBitmap(
        this.drawable.toBitmap(), width,
        height, filter
    )
}

//internal fun ImageView.bitmapToBody(name: String, context: Context): MultipartBody.Part{
//    val baos = ByteArrayOutputStream()
//    val images = this.fixSizeBitmap(600f, true)
//    images.compress(Bitmap.CompressFormat.JPEG, 100, baos)
//    val image = baos.toByteArray()
//    val f = File(context.cacheDir, name)
//    f.createNewFile()
//    var fos: FileOutputStream
//    try {
//        fos = FileOutputStream(f)
//        fos.write(image)
//        fos.flush()
//        fos.close()
//    }catch (ex: Exception){
//        ex.printStackTrace()
//    }
//    val request = RequestBody.create(MediaType.parse("image/*"), image)
//    return MultipartBody.Part.createFormData("uploaded_files", f.name, request)