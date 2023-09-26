package com.example.folhapagamento

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Classe ViewHolder das Folhas de Pagamentos
class FolhaViewHolder (layoutFolha: View) : RecyclerView.ViewHolder(layoutFolha){
    // Mostra apenas o nome do funcionário na lista
    var txtNomeFuncionario = layoutFolha.findViewById<TextView>(R.id.txtNomeFunc)
}