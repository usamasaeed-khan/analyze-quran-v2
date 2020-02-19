package com.example.analyzequranupdated

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Color
import android.net.http.SslError
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import android.webkit.*
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.analyzequranupdated.R
import kotlinx.android.synthetic.main.activity_donations.*
import kotlinx.android.synthetic.main.activity_main.*


class DonationsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donations)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        supportActionBar!!.title="Donate"


        credit_card_btn.setOnClickListener {

            val creditCardText:TextView = findViewById(R.id.credit_card_text)
            val bankTransferText:TextView = findViewById(R.id.bank_transfer_text)
            val paypalText:TextView = findViewById(R.id.paypal_text)

            credit_card_btn.setBackgroundColor(resources.getColor(R.color.colorPrimary))
            bank_transfer_btn.setBackgroundColor(Color.WHITE)
            paypal_btn.setBackgroundColor(Color.WHITE)

            credit_card_btn.setTextColor(Color.WHITE)
            bank_transfer_btn.setTextColor(Color.BLACK)
            paypal_btn.setTextColor(Color.BLACK)

            creditCardText.movementMethod = LinkMovementMethod.getInstance()


//            creditCardText.setOnClickListener {
//
//                val webView:WebView = findViewById(R.id.webView)
//                webView.webViewClient = MyBrowser()
//                //webView.webChromeClient =  WebChromeClient()
//
//                val url:String = "https://sandbox.api.getsafepay.com/connect/me/1234"
//
//                webView.settings.loadsImagesAutomatically = true
//                webView.settings.javaScriptEnabled = true
//                webView.settings.allowContentAccess = true
//                webView.settings.loadWithOverviewMode = true
//                webView.settings.useWideViewPort = true
//                webView.settings.domStorageEnabled = true
//                webView.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
//
//
//                webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
//                webView.loadUrl("https://www.google.com")
//
//
//            }

            paypalText.visibility = View.GONE
            creditCardText.visibility = View.VISIBLE
            bankTransferText.visibility = View.GONE
            copy_text_btn.visibility = View.GONE

        }


        paypal_btn.setOnClickListener {


            val creditCardText:TextView = findViewById(R.id.credit_card_text)
            val bankTransferText:TextView = findViewById(R.id.bank_transfer_text)
            val paypalText:TextView = findViewById(R.id.paypal_text)


            paypal_btn.setTextColor(Color.WHITE)
            bank_transfer_btn.setTextColor(Color.BLACK)
            credit_card_btn.setTextColor(Color.BLACK)

            credit_card_btn.setBackgroundColor(Color.WHITE)
            bank_transfer_btn.setBackgroundColor(Color.WHITE)
            paypal_btn.setBackgroundColor(resources.getColor(R.color.colorPrimary))

            paypalText.movementMethod = LinkMovementMethod.getInstance()

            paypalText.visibility = View.VISIBLE
            creditCardText.visibility = View.GONE
            bankTransferText.visibility = View.GONE
            copy_text_btn.visibility = View.GONE


        }

        bank_transfer_btn.setOnClickListener {

            val creditCardText:TextView = findViewById(R.id.credit_card_text)
            val bankTransferText:TextView = findViewById(R.id.bank_transfer_text)
            val paypalText:TextView = findViewById(R.id.paypal_text)

            bank_transfer_btn.setTextColor(Color.WHITE)
            credit_card_btn.setTextColor(Color.BLACK)
            paypal_btn.setTextColor(Color.BLACK)


            credit_card_btn.setBackgroundColor(Color.WHITE)
            bank_transfer_btn.setBackgroundColor(resources.getColor(R.color.colorPrimary))
            paypal_btn.setBackgroundColor(Color.WHITE)


            paypalText.visibility = View.GONE
            creditCardText.visibility = View.GONE
            bankTransferText.visibility = View.VISIBLE
            copy_text_btn.visibility = View.VISIBLE


        }


        copy_text_btn.setOnClickListener {


            val clipboard: ClipboardManager =
                getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("IBAN", "ABCDFDGETEYHEYT46457383")
            clipboard.setPrimaryClip(clip)


            Toast.makeText(this, "IBAN Saved to clip board", Toast.LENGTH_SHORT).show()



        }

    }


    private class MyBrowser : WebViewClient() {



        override fun shouldOverrideUrlLoading(
            view: WebView,
            url: String
        ): Boolean {
            view.loadUrl(url)
            return true
        }

        override fun onReceivedSslError(
            view: WebView?,
            handler: SslErrorHandler?,
            error: SslError?
        ) { // ignore ssl error
            if (handler != null) {
                handler.proceed()
            } else {
                super.onReceivedSslError(view, null, error)
            }
        }


    }




}
