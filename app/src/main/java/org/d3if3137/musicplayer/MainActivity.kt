package org.d3if3137.musicplayer

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageButton
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import org.d3if3137.musicplayer.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var runnable: Runnable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //untuk menampilkan lagu yang akan diputar
        val MediaPlayer = MediaPlayer.create(this, R.raw.apocalypse)
        val handler = binding.root

        binding.seekBar.progress = 0
        binding.seekBar.max = MediaPlayer.duration
        //play button agar berfungsi
        binding.playBtn.setOnClickListener {
            if (!MediaPlayer.isPlaying){
                MediaPlayer.start()

                binding.playBtn.setImageResource(R.drawable.baseline_pause_circle_outline_24)

            }else {
                MediaPlayer.pause()
                binding.playBtn.setImageResource(R.drawable.baseline_play_circle_outline_24)
            }
        }

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, pos: Int, changed: Boolean) {
                //mengubah posisi seekbar maka akan mengubah posisi lagunya
                if (changed){
                    MediaPlayer.seekTo(pos)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })

        runnable = Runnable {
            binding.seekBar.progress = MediaPlayer.currentPosition
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)
        MediaPlayer.setOnCompletionListener {
            (R.drawable.baseline_play_circle_outline_24)
            binding.seekBar.progress = 0
        }
    }
}