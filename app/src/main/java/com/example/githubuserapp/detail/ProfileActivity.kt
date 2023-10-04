package com.example.githubuserapp.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubuserapp.databinding.ActivityAboutBinding


class ProfileActivity : AppCompatActivity() {
        private lateinit var binding: ActivityAboutBinding
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityAboutBinding.inflate(layoutInflater)
            setContentView(binding.root)
            binding.deskripsi.text = "Deskripsi"
            binding.profileBioTextView.text = "perkenalkan saya adalah seorang mahasiswa yang sedang menempuh pendidikan Teknik Informatika tingkat S1 di salah satu kampus di daerah saya dan saya sedang belajar untuk menjadi seorang frontend developer."

            binding.shareButton.setOnClickListener {
                shareProfile()
            }
        }
        private fun shareProfile() {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out my profile: Wira Sukma Saputra")
            startActivity(Intent.createChooser(shareIntent, "Share Profile"))
        }
}

