package com.mapx.kosten.mosimpa.presentation.fragments.sensors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.mapx.kosten.mosimpa.R
import com.mapx.kosten.mosimpa.domain.entites.SensorHeartEntity
import com.mapx.kosten.mosimpa.presentation.common.Utils.Companion.getSensorSufixByID
import kotlinx.android.synthetic.main.layout_sensor_item.view.*


class SensorHeartAdapter constructor(
    private val onSensorSelected: (SensorHeartEntity, View) -> Unit
) : RecyclerView.Adapter<SensorHeartAdapter.SensorCellViewHolder>() {

    var sensorEntities: List<SensorHeartEntity> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SensorCellViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.layout_sensor_heart_item,
            parent,
            false)
        return SensorCellViewHolder(view)
    }

    override fun getItemCount(): Int {
        return sensorEntities.size
    }

    override fun onBindViewHolder(holder: SensorCellViewHolder, position: Int) {
        val node = sensorEntities[position]
        holder.bind(node, onSensorSelected)
    }

    fun setSensors(sensors: List<SensorHeartEntity>) {
        this.sensorEntities = sensors
        notifyDataSetChanged()
    }

    class SensorCellViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            configureSensorChart(itemView.lineChart_sensor)
        }

        fun bind(sensor: SensorHeartEntity, listener: (SensorHeartEntity, View) -> Unit) = with(itemView) {
            tv_item_sensor_title.text = "Heart"
            tv_item_sensor_value.text = sensor.heartR.toString() + getSensorSufixByID(sensor.id)

            setOnClickListener { listener(sensor, itemView) }
        }

        private fun configureSensorChart(lineChart: LineChart) {
            val entries = ArrayList<Entry>()

            entries.add(Entry(1f, 10f))
            entries.add(Entry(2f, 2f))
            entries.add(Entry(3f, 7f))
            entries.add(Entry(4f, 20f))
            entries.add(Entry(5f, 16f))

            val vl = LineDataSet(entries, "Value")

            lineChart.xAxis.labelRotationAngle = 0f

            lineChart.data = LineData(vl)

            lineChart.description.text = "Sensor"


            // lineChart.animateX(1800, Easing.EaseInExpo)

            // val leftAxis: YAxis = lineChart.axisLeft
            // leftAxis.mAxisMaximum = 200f

        }
    }
}
