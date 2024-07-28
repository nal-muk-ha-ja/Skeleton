package com.example.skeleton

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.skeleton.databinding.ActivityMainBinding
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var adView: AdView

    //테스트 광고 ID
    //매니페스트에도 있다
    //배너용
    private val BANNER_AD_UNIT_ID = "ca-app-pub-3940256099942544/6300978111"

    //전면광고용
    private val FULL_AD_UNIT_ID = "ca-app-pub-3940256099942544/1033173712"

    //보상형광고
    private val REWARDED_AD_UNIT_ID = "ca-app-pub-3940256099942544/5224354917"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBannerAd()

        binding.showFullAd.setOnClickListener {
            showFullAd()
        }

        binding.showRewardedAd.setOnClickListener {
            showRewardedAd()
        }
    }

    //보상형 광고 띄우기
    //5초간 기다리면 보상을 준다?? 짧고 취소가능한듯?
    //https://developers.google.com/admob/android/rewarded?hl=ko
    //우리서버 콜백 확인도 가능하다고 한다?
    private fun showRewardedAd() {
        //보상형 광고 띄우기
        val adRequest = AdRequest.Builder().build()
        RewardedAd.load(this, REWARDED_AD_UNIT_ID, adRequest, object : RewardedAdLoadCallback() {
            override fun onAdFailedToLoad(p0: LoadAdError) {
                super.onAdFailedToLoad(p0)
                Log.d("kgpp", "onAdFailedToLoad")
            }

            override fun onAdLoaded(rewardedAd: RewardedAd) {
                Log.d("kgpp", "onAdLoaded")

                rewardedAd.fullScreenContentCallback = object : FullScreenContentCallback() {
                    override fun onAdDismissedFullScreenContent() {
                        super.onAdDismissedFullScreenContent()
                        Log.d("kgpp", "onAdDismissedFullScreenContent")
                    }

                    override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                        super.onAdFailedToShowFullScreenContent(p0)
                        Log.d("kgpp", "onAdFailedToShowFullScreenContent")
                    }

                    override fun onAdShowedFullScreenContent() {
                        super.onAdShowedFullScreenContent()
                        Log.d("kgpp", "onAdShowedFullScreenContent")
                    }

                    override fun onAdImpression() {
                        super.onAdImpression()
                        Log.d("kgpp", "onAdImpression")
                    }

                    override fun onAdClicked() {
                        super.onAdClicked()
                        Log.d("kgpp", "onAdClicked")
                    }
                }

                rewardedAd.show(this@MainActivity) {
                    Log.d("kgpp", "onUserEarnedReward")
                }
            }
        })


    }

    //전면광고 띄우기
    private fun showFullAd() {
        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(this, FULL_AD_UNIT_ID, adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                Log.d("kgpp", "onAdFailedToLoad")
            }

            //interstitialAd 객체를 들고있다가 나중에 show해도 되는것으로 보인다
            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                Log.d("kgpp", "onAdLoaded")
                interstitialAd.show(this@MainActivity)

                interstitialAd.fullScreenContentCallback = object : FullScreenContentCallback() {
                    override fun onAdDismissedFullScreenContent() {
                        Log.d("kgpp", "onAdDismissedFullScreenContent")
                    }

                    override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                        super.onAdFailedToShowFullScreenContent(p0)
                        Log.d("kgpp", "onAdFailedToShowFullScreenContent")
                    }

                    override fun onAdClicked() {
                        super.onAdClicked()
                        Log.d("kgpp", "onAdClicked")
                    }

                    override fun onAdImpression() {
                        super.onAdImpression()
                        Log.d("kgpp", "onAdImpression")
                    }

                    override fun onAdShowedFullScreenContent() {
                        Log.d("kgpp", "onAdShowedFullScreenContent")
                    }
                }
            }

        })
    }

    //https://developers.google.com/admob/android/banner?hl=ko#kotlin
    private fun initBannerAd() {
        adView = AdView(this)
        adView.adUnitId = BANNER_AD_UNIT_ID
        adView.setAdSize(AdSize.BANNER)
        binding.bannerContainer.addView(adView)

        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        adView.adListener = object : AdListener() {
            override fun onAdLoaded() {
                Log.d("kgpp", "onAdLoaded")
            }

            override fun onAdClicked() {
                Log.d("kgpp", "onAdClicked")
            }

            override fun onAdClosed() {
                Log.d("kgpp", "onAdClosed")
            }

            override fun onAdFailedToLoad(p0: LoadAdError) {
                Log.d("kgpp", "onAdFailedToLoad")
            }

            override fun onAdImpression() {
                Log.d("kgpp", "onAdImpression")
            }

            override fun onAdOpened() {
                Log.d("kgpp", "onAdOpened")
            }

            override fun onAdSwipeGestureClicked() {
                Log.d("kgpp", "onAdSwipeGestureClicked")
            }
        }
    }
}