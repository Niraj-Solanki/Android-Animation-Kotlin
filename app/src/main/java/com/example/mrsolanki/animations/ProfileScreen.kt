package com.example.mrsolanki.animations
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.BottomSheetBehavior
import android.view.animation.AnimationUtils

import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.facebook.shimmer.ShimmerFrameLayout

class ProfileScreen : AppCompatActivity() {


    //Objects
    private var isReadMoreActive: Boolean = false
    private var shimmerDuration:Long = 2000

    private lateinit var projectDetailsShimmer:ShimmerFrameLayout
    private lateinit var experienceDetailsShimmer:ShimmerFrameLayout
    private lateinit var contactUsDetailsShimmer:ShimmerFrameLayout

    private lateinit var closeBottomSheetButton:Button
    private lateinit var closeButton:Button

    //LifeCycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_screen)

        initializeVariables()

        // Bottom Sheet Button Actions
        projectDetailsWork()
        experienceDetailsWork()
        contactUsDetailsWork()


        closeButton.setOnClickListener {
            finishAfterTransition()
        }

        closeBottomSheetButton.setOnClickListener {
            closeAllBottomSheets()
        }

        readMoreWork()

    }

    //Initialize Variables
    fun initializeVariables()
    {
        experienceDetailsShimmer = findViewById(R.id.bottom_sheet_experience_detail_shimmer)
        contactUsDetailsShimmer = findViewById(R.id.bottom_sheet_contact_us_detail_shimmer)
        projectDetailsShimmer = findViewById(R.id.bottom_sheet_project_detail_shimmer)
        closeBottomSheetButton = findViewById(R.id.closeBottomSheetButton)
        closeButton = findViewById(R.id.closeButton)

        closeAllBottomSheets()
    }

    fun closeAllBottomSheets()
    {
        BottomSheetBehavior.from(findViewById<RelativeLayout>(R.id.bottom_sheet_experience_detail)).state = BottomSheetBehavior.STATE_COLLAPSED
        BottomSheetBehavior.from(findViewById<RelativeLayout>(R.id.bottom_sheet_contact_us_detail)).state = BottomSheetBehavior.STATE_COLLAPSED
        BottomSheetBehavior.from(findViewById<RelativeLayout>(R.id.bottom_sheet_project_detail)).state = BottomSheetBehavior.STATE_COLLAPSED
    }

    //Project Bottom Sheet Work
    fun  projectDetailsWork()
    {
        var myProjectsButton = findViewById<Button>(R.id.myProjectsButton)
        myProjectsButton.setOnClickListener {


            BottomSheetBehavior.from(findViewById<RelativeLayout>(R.id.bottom_sheet_project_detail)).state = BottomSheetBehavior.STATE_EXPANDED
            projectDetailsShimmer.startShimmer()
            projectDetailsShimmer.showShimmer(true)

            Handler().postDelayed({
                stopShimmerProjectDetails()
            }, shimmerDuration)

        }
    }

    //Experience Bottom Sheet Work
    fun experienceDetailsWork()
    {
        var myExperienceButton = findViewById<Button>(R.id.experinceButton)
        myExperienceButton.setOnClickListener {

            BottomSheetBehavior.from(findViewById<RelativeLayout>(R.id.bottom_sheet_experience_detail)).state = BottomSheetBehavior.STATE_EXPANDED
            experienceDetailsShimmer.showShimmer(true)
            experienceDetailsShimmer.startShimmer()

            BottomSheetBehavior.from(findViewById<RelativeLayout>(R.id.bottom_sheet_project_detail)).state = BottomSheetBehavior.STATE_COLLAPSED

            Handler().postDelayed({
                stopShimmerExperienceDetails()
            }, shimmerDuration)
        }
    }

    //ContactUs Bottom Sheet Work
    fun  contactUsDetailsWork()
    {
        var contactUsButton = findViewById<Button>(R.id.contactUsButton)
        contactUsButton.setOnClickListener {


            BottomSheetBehavior.from(findViewById<RelativeLayout>(R.id.bottom_sheet_contact_us_detail)).state = BottomSheetBehavior.STATE_EXPANDED

            contactUsDetailsShimmer.showShimmer(true)
            contactUsDetailsShimmer.startShimmer()

            contactUsAnimationWOrk()
            BottomSheetBehavior.from(findViewById<RelativeLayout>(R.id.bottom_sheet_experience_detail)).state = BottomSheetBehavior.STATE_COLLAPSED

            Handler().postDelayed({
                stopShimmerContactUsDetails()
            }, shimmerDuration)

        }
    }

    // Stop Shimmer
    fun stopShimmerProjectDetails()
    {
        projectDetailsShimmer.stopShimmer()
        projectDetailsShimmer.hideShimmer()
    }

    fun stopShimmerExperienceDetails()
    {
        experienceDetailsShimmer.stopShimmer()
        experienceDetailsShimmer.hideShimmer()
    }

    fun stopShimmerContactUsDetails()
    {
        contactUsDetailsShimmer.stopShimmer()
        contactUsDetailsShimmer.hideShimmer()
    }

    //ANimations
    fun contactUsAnimationWOrk()
    {
        var slideAnimation = AnimationUtils.loadAnimation(this,R.anim.slide_anims)
        findViewById<LinearLayout>(R.id.instagramLayout).startAnimation(slideAnimation)
        findViewById<LinearLayout>(R.id.facebookLayout).startAnimation(slideAnimation)
        findViewById<LinearLayout>(R.id.linkedInLayout).startAnimation(slideAnimation)

        var bustAnimation = AnimationUtils.loadAnimation(this,R.anim.bust)
        findViewById<TextView>(R.id.contacUsTextView).startAnimation(bustAnimation)
        findViewById<TextView>(R.id.contctUsNameTextView).startAnimation(bustAnimation)
    }
    fun readMoreWork() {
        var readMoreTextView = findViewById<TextView>(R.id.readMoreButton)

        readMoreTextView.setOnClickListener {

            var bioTextView = findViewById<TextView>(R.id.bioTextView)
            if (isReadMoreActive) {
                isReadMoreActive = false
                readMoreTextView.text = "READ MORE"
                bioTextView.setLines(2)
            } else {
                isReadMoreActive = true
                readMoreTextView.text = "READ LESS"
                bioTextView.setLines(4)
            }

        }
    }

}
