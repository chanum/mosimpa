package com.mapx.kosten.mosimpa.presentation.fragments.patients

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mapx.kosten.mosimpa.R
import com.mapx.kosten.mosimpa.domain.entites.PatientEntity
import com.mapx.kosten.mosimpa.presentation.common.App
import com.mapx.kosten.mosimpa.presentation.common.Utils.Companion.INVALID_PATIENT_ID

import javax.inject.Inject

class PatientsFragment : Fragment() {

    @Inject
    lateinit var factory: PatientsViewModelFactory
    private lateinit var viewModel: PatientsViewModel
    private lateinit var rootLayout: CoordinatorLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var emptyMessage: TextView
    private lateinit var adapter: PatientsAdapter
    private lateinit var scanButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as App).createPatientsComponent().inject(this)
        viewModel = ViewModelProvider(this, factory).get(PatientsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_patients, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.patients.observe(viewLifecycleOwner, Observer {
            if (it != null) handlePatients(it)
        })
        viewModel.devices.observe(viewLifecycleOwner, Observer {
            if (it != null) handleDevices(it)
        })
        viewModel.errorState.observe(viewLifecycleOwner, Observer { throwable ->
            throwable?.let {
                Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rootLayout = view.findViewById(R.id.frameLayoutPatients)
        scanButton = rootLayout.findViewById(R.id.fab_scan_devices)
        progressBar = rootLayout.findViewById(R.id.pb_patients)
        emptyMessage = rootLayout.findViewById(R.id.tv_patients_empty)
        recyclerView = rootLayout.findViewById(R.id.rv_patients)

        scanButton.setOnClickListener{
             scanDevices()
        }

        adapter = PatientsAdapter{ node, view ->
            goToDetailView(node, view)
        }
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        viewModel.connectAndSubscribeToAll()
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity?.application as App).releasePatientsComponent()
    }

    private fun handlePatients(patients: List<PatientEntity>) {
        progressBar.visibility = View.GONE
        if (patients.isEmpty()) {
            emptyMessage.visibility = View.VISIBLE
        }
        adapter.setPatients(patients)
    }

    private fun handleDevices(device: String) {
        viewModel.updateDevices(device)
    }

    private fun goToDetailView(patientEntity: PatientEntity, view: View) {
        Log.i(javaClass.simpleName, "goToDetailView(): $patientEntity")
        if (patientEntity.id > INVALID_PATIENT_ID) goToAddPatient(patientEntity.id)
    }

    private fun goToAddPatient(id: Long) {
       val action = PatientsFragmentDirections.actionPatientsFragmentToSensorsFragment(id)
        findNavController().navigate(action)
    }

    private fun scanDevices() {
        // viewModel.scanDevices()
        // get all devices
        // look in the DB if exits
        // add to DB
        // update UI

    }
}
