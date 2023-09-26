package com.example.folhapagamento

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

// Classe responsável pela configuração do Adapter da lista de folha de pagamentos
class FolhaAdapter (var context: Context): RecyclerView.Adapter<FolhaViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolhaViewHolder {
        val layoutCarro = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_folha, parent, false)
        return FolhaViewHolder(layoutCarro)
    }

    override fun onBindViewHolder(holder: FolhaViewHolder, position: Int) {
        val folha = ListaFolha.getFolha(position)
        // Apresenta para o usuário o Nome dos funcionários cadastrados
        holder.txtNomeFuncionario.text = "Nome: ${folha.nome}"

        // Configura a ação de clique longo no nome dos funcionários cadastrados
        holder.txtNomeFuncionario.setOnLongClickListener {
            // Enviar a posição do elemento atual para a DetalhesFolhaActivity
            var intent = Intent(context, DetalhesFolhaActivity::class.java)
            intent.putExtra("position", position)
            context.startActivity(intent)
            true
        }
    }

    // Função que irá retornar a quantidade de folhas cadastradas no aplicativo
    override fun getItemCount(): Int {
        return ListaFolha.getListSize()
    }
}