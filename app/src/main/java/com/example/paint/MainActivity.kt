package com.example.paint

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.example.paint.PaintView.Companion.colorList
import com.example.paint.PaintView.Companion.currentBrush
import com.example.paint.PaintView.Companion.pathList
import com.example.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with (binding) {

            btnBlack.setOnClickListener {
                Toast.makeText(this@MainActivity, "Black", Toast.LENGTH_SHORT).show()
                paintBrush.color = Color.BLACK
                currentColor(paintBrush.color)
            }

            btnRed.setOnClickListener {
                Toast.makeText(this@MainActivity, "Red", Toast.LENGTH_SHORT).show()
                paintBrush.color = Color.RED
                currentColor(paintBrush.color)
            }

            btnBlue.setOnClickListener {
                Toast.makeText(this@MainActivity, "Blue", Toast.LENGTH_SHORT).show()
                paintBrush.color = Color.CYAN
                currentColor(paintBrush.color)
            }

            btnGreen.setOnClickListener {
                Toast.makeText(this@MainActivity, "Green", Toast.LENGTH_SHORT).show()
                paintBrush.color = Color.GREEN
                currentColor(paintBrush.color)
            }

            btnYellow.setOnClickListener {
                Toast.makeText(this@MainActivity, "Yellow", Toast.LENGTH_SHORT).show()
                paintBrush.color = Color.YELLOW
                currentColor(paintBrush.color)
            }

            fabDelete.setOnClickListener {
                pathList.clear()
                colorList.clear()
                path.reset()
            }

            switchMode.setOnCheckedChangeListener {
                _, isChecked -> isReplaceMode = isChecked
            }

            switchModeRandom.setOnCheckedChangeListener {
                    _, isChecked -> isRandomMode = isChecked
            }
        }
    }

    private fun currentColor(color: Int) {
        currentBrush = color
        path = Path()
    }


    companion object {
        var path = Path()
        val paintBrush = Paint()
        var isReplaceMode = false
        var isRandomMode = false
    }

}