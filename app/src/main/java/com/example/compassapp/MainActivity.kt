package com.example.compassapp

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity(),SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor? = null
    private lateinit var compass:ImageView
    private lateinit var degreevalue:TextView
    var currentdegree=0f
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        compass=findViewById(R.id.compass)
        degreevalue=findViewById(R.id.degree)
        sensorManager=getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor=sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        var cureentdegree=Math.round(event!!.values[0])
        degreevalue.setText("Degree  ${cureentdegree.toString()}")
        var roation=RotateAnimation(currentdegree.toFloat(),(-currentdegree.toFloat()), Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f)
        roation.duration=210
        roation.fillAfter=true
        compass.startAnimation(roation)
        currentdegree=-cureentdegree.toFloat()
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }
//Register sensor by sensormanger
    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_GAME)

    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }
}