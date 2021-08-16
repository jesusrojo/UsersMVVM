package com.jesusrojo.usersmvvm.utils

//import android.content.Context
//import android.view.View
//import androidx.core.content.ContextCompat
//import com.google.android.material.snackbar.Snackbar
//import android.content.DialogInterface
//
//import androidx.appcompat.app.AlertDialog
//import androidx.appcompat.app.AppCompatActivity
//import com.jesusrojo.usersclient.R


object UiHelp {

    //NUMBER
//fun formatNumber(number: Int?): String{
//    var result = ""
//    number?.let{
//        val numberFormat = NumberFormat.getInstance()
//        result = numberFormat.format(number)
//    }
//    return result
//}

    // TITLE HTML
//fun convertTitle(title: String?) =
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            Html.fromHtml(title, Html.FROM_HTML_MODE_LEGACY).toString()
//        } else {
//            Html.fromHtml(title).toString()
//        }

// DATE
//fun convertDate(timeStamp: Long?): String{
//    var time = ""
//    timeStamp?.let{
//        val cal = Calendar.getInstance()
//        cal.timeInMillis= timeStamp * 1000
//        time = DateFormat.format("dd-MM-yyyy hh:mm:ss", cal).toString()
//    }
//    return time
//}


    //TIME
//fun millisToHHmmss(millis: Long): String? {
//    return try {
//        SimpleDateFormat("HH:mm:ss", Locale.getDefault())
//            .format(millis)
//    } catch (e: IllegalArgumentException) {
//        ""
//    }
//}

    //SNACK BAR
//    fun showErrorSnackBar(view: View, context: Context, msg: String) {
//        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).apply {
//            setBackgroundTint(ContextCompat.getColor(context, R.color.purple_700))
//            setTextColor(ContextCompat.getColor(context, R.color.white))
//            setActionTextColor(ContextCompat.getColor(context, R.color.white))
////            setAction(R.string.close) {
////                dismiss()
////            }
//            anchorView = view // otherwise might be hiden for bottom menu
//        }.show()
//    }

    //ALERT DIALOG
//    fun showAlertDetails(activity: AppCompatActivity, message: String) {
//        val alertDialog: AlertDialog.Builder =  AlertDialog.Builder(activity)
//        alertDialog.setTitle(R.string.details)
//        alertDialog.setMessage(message)
//        alertDialog.setPositiveButton(R.string.close,
//            DialogInterface.OnClickListener { dialog, which ->
//                dialog.cancel()
//            })
//        alertDialog.show()
//    }
}