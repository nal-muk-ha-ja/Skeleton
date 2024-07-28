package com.example.skeleton

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.skeleton.databinding.ActivityMainBinding
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var adView: AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBannerAd()
    }

    //https://developers.google.com/admob/android/banner?hl=ko#kotlin
    private fun initBannerAd() {
        adView = AdView(this)
        adView.adUnitId = "ca-app-pub-3940256099942544/6300978111"
        adView.setAdSize(AdSize.BANNER)
        binding.bannerContainer.addView(adView)

        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        adView.adListener = object : AdListener() {
            override fun onAdLoaded() {
                Log.d("kgpp","onAdLoaded")
            }

            override fun onAdClicked() {
                Log.d("kgpp","onAdClicked")
            }

            override fun onAdClosed() {
                Log.d("kgpp","onAdClosed")
            }

            override fun onAdFailedToLoad(p0: LoadAdError) {
                Log.d("kgpp","onAdFailedToLoad")
            }

            override fun onAdImpression() {
                Log.d("kgpp","onAdImpression")
            }

            override fun onAdOpened() {
                Log.d("kgpp","onAdOpened")
            }

            override fun onAdSwipeGestureClicked() {
                Log.d("kgpp","onAdSwipeGestureClicked")
            }
        }
    }
}