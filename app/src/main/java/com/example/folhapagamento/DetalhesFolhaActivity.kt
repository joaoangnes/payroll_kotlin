package com.example.folhapagamento

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.folhapagamento.databinding.ActivityDetalhesFolhaBinding

// Classe responsável por mostrar os detalhes da folha de pagamento do funcionário
class DetalhesFolhaActivity: AppCompatActivity() {

    private lateinit var binding: ActivityDetalhesFolhaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetalhesFolhaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Pega o indice que foi passado pela intent, para retornar os dados da folha de pagamento especifica do funcionário
        val position = intent.getIntExtra("position", -1)

        // Se a posição for inválida, finaliza a activity
        if (position == -1) { finish() }

        // Busca os dados da folha de pagamento do funcionário
        val folha = ListaFolha.getFolha(position)

        // Mostra os dados da folha de pagamento para o funcionário
        binding.txtNomeFunc.text         = "Nome: "+folha.nome
        binding.txtHorasTrabalhadas.text = "Horas Trabalhadas: "+folha.horasTrab.toString()
        binding.txtValorHora.text        = "Valor da Hora: "+String.format("%.2f", folha.valorHora)
        binding.txtMesRef.text           = "Mês Referência: "+folha.mes.toString()
        binding.txtAnoRef.text           = "Ano Referência: "+folha.ano.toString()
        binding.txtSalarioLiquido.text   = "Salário Liquido: "+String.format("%.2f", folha.sal_liq)
        binding.txtSalarioBruto.text     = "Salário Bruto: "+String.format("%.2f", folha.sal_bruto)
        binding.txtValorIR.text          = "IR: "+String.format("%.2f", folha.ir)
        binding.txtValorINSS.text        = "INSS: "+String.format("%.2f", folha.inss)
        binding.txtValorFGTS.text        = "FGTS: "+String.format("%.2f", folha.fgts)


        // Responsável por excluir a folha de pagamento da lista do aplicativo e finaliza a activity
        binding.btnExcluir.setOnClickListener {
            ListaFolha.removeFolha(position)
            finish()
        }

    }
}