package com.example.folhapagamento

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.folhapagamento.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var folhaAdapter: FolhaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Instancia o Adapter da lista de folhas de pagementos
        folhaAdapter = FolhaAdapter(this)
        binding.rcvFolhas.layoutManager = LinearLayoutManager(this)
        binding.rcvFolhas.adapter = folhaAdapter

        // Redireciona para a página de cadastro ao clicar no botão de cadastro
        binding.btnAdd.setOnClickListener {
            startActivity(Intent(this, CadastroFolhaActivity::class.java))
        }
    }

    // Função responsável pela atualização da lista de folhas de pagamentos
    override fun onStart() {
        super.onStart()
        folhaAdapter.notifyDataSetChanged()
    }
}