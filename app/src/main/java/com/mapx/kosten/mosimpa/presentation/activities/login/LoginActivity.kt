package com.mapx.kosten.mosimpa.presentation.activities.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mapx.kosten.mosimpa.R
import com.mapx.kosten.mosimpa.presentation.activities.main.MainActivity
import com.mapx.kosten.mosimpa.presentation.common.ActivityUtils
import com.mapx.kosten.mosimpa.presentation.common.App
import com.mapx.kosten.mosimpa.presentation.viewmodels.LoginViewModel
import com.mapx.kosten.mosimpa.presentation.viewmodels.LoginViewModelFactory
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: LoginViewModelFactory
    private lateinit var viewModel: LoginViewModel
    private lateinit var loginBtn: Button
    private lateinit var serverNameTxt: EditText
    private lateinit var serverIpTxt: EditText
    private lateinit var optionsTxt: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ServersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        (application as App).createLoginComponent().inject(this)
        viewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)

        serverNameTxt = findViewById(R.id.et_login_name)
        serverIpTxt = findViewById(R.id.et_login_ip)
        loginBtn = findViewById(R.id.button_login)
        optionsTxt = findViewById(R.id.tv_login_options)
        recyclerView = findViewById(R.id.rv_login_servers)

        loginBtn.setOnClickListener{
            if (saveServer()) { goToMain() }
        }

        adapter = ServersAdapter{ node, view ->
            //goToDetailView(node, view)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.servers.observe(this, Observer {
            if (it != null) { adapter.setServers(it) }
        })

        initCurrentServer()
    }

    override fun onResume() {
        super.onResume()
        viewModel
    }

    private fun initCurrentServer() {
        val currentServer = viewModel.getCurrentServer()
        serverNameTxt.setText(currentServer.name)
        serverIpTxt.setText(currentServer.ip)
    }

    private fun saveServer(): Boolean {
        // TODO check entries
        val name = serverNameTxt.text.toString().trim()
        val ip = serverIpTxt.text.toString().trim()
        viewModel.saveServer(name, ip)
        return true
    }

    private fun goToMain() {
        val intent = MainActivity.getStartIntent(this)
        ActivityUtils.startActivityWithCrossFade(this, intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        (application as App).releaseLoginComponent()
    }
}
