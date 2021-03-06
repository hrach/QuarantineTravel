package com.example.quarantinetravel.util

import android.content.Context
import android.media.MediaPlayer

class MusicManager {
    companion object {
        private lateinit var mp: MediaPlayer
        lateinit var context: Context
        var musicEnabled: Boolean = true
        private var released: Boolean = false

        fun init(context: Context, musicEnabled: Boolean) {
            this.musicEnabled = musicEnabled
            this.context = context
        }

        fun play(sound: Int, loop: Boolean) {
            if (musicEnabled) {
                release()

                mp = MediaPlayer.create(context, sound)
                if (loop) mp.isLooping = true
                mp.start()

                released = false
            }
        }

        fun stop () {
            mp.stop()
        }

        fun restart(sound: Int, loop: Boolean) {
            if (released) {
                play(sound, loop)
            }
        }

        fun release() {
            if (this::mp.isInitialized && !released) {
                mp.reset()
                mp.release()
                released = true
            }
        }
    }
}