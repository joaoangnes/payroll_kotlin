package com.example.folhapagamento

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.folhapagamento.databinding.ActivityCadastroFolhaBinding
import java.util.Calendar

// Classe responsável peo cadastro das informações do funcionário, para cálculo da Folha
class CadastroFolhaActivity: AppCompatActivity() {
    private lateinit var binding : ActivityCadastroFolhaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCadastroFolhaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Realiza o cadastro da folha do funcionário, caso os dados forem validados
        binding.btnCalcular.setOnClickListener {
            // Cadastra a folha do funcionário
            cadastraFolha()
        }
    }

    // Função responsável pelo cadastro da folha de pagamento na lista especialista
    private fun cadastraFolha(){

        // Se os dados forem validados, segue para a inserção na lista
        if (validaDados()) {

            // Instancia um objeto de Folha de objeto, que irá executar os cálculos necessários para a folha de pagamento
            var folha = Folha (
                binding.edtNome.text.toString(),                // Nome Funcionário
                binding.edtHorasTrab.text.toString().toInt(),   // Horas Trabalhadas
                binding.edtValorHora.text.toString().toFloat(), // Valor da Hora
                binding.edtMesRef.text.toString().toInt(),      // Mês Referência
                binding.edtAnoRef.text.toString().toInt()       // Ano Referência
            )

            // Insere esse objeto, na lista de folhas de pagamento do aplicativo
            ListaFolha.addFolha(folha)
            Util.exibirToast(this,"Folha Cadastrada com sucesso!")

            // Limpa os campos
            binding.edtNome.text.clear()
            binding.edtHorasTrab.text.clear()
            binding.edtValorHora.text.clear()
            binding.edtMesRef.text.clear()
            binding.edtAnoRef.text.clear()

            // Redireciona para a tela principal e finaliza a activity
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    // Função responsável por validar as informações forncecidas pelo usuário
    private fun validaDados(): Boolean{
        // Valida se o usuário preencheu todos os campos
        if (binding.edtNome.text.isEmpty() ||
            binding.edtHorasTrab.text.isEmpty() ||
            binding.edtValorHora.text.isEmpty() ||
            binding.edtMesRef.text.isEmpty() ||
            binding.edtAnoRef.text.isEmpty()) {
            // Informa o erro para o usuário
            Util.exibirToast(this, "Necessário o preenchimento de todos os campos para efetuar o cadastro!")
            return false
        }

        // Valida se o usuário informou corretamente os dados do formulário
        if (binding.edtHorasTrab.text.toString().toInt() <= 0 ||
            binding.edtValorHora.text.toString().toFloat() <= 0 ||
            binding.edtMesRef.text.toString().toInt() <= 0 ||
            binding.edtAnoRef.text.toString().toInt() <= 0){
            // Informa o erro para o usuário
            Util.exibirToast(this, "Valores não podem ser zero")
            return false
        }

        // Variaveis de controle de calendário utilizando a função Calendar do Kotlin
        val cal = Calendar.getInstance() // Armazena a instancia de calendário
        val anoAtual = cal.get(Calendar.YEAR) // Armazena o valor do ano vigente

        // Validação para que só seja permitido o cadastro de folhas de pagamento do ano vigente
        if (binding.edtAnoRef.text.toString().toInt() != anoAtual) {
            // Informa o erro para o usuário
            Util.exibirToast(this, "O ano de referência deve ser igual ao ano vigente.")
            return false
        }

        // Validação para que só seja permitido a inserção de valores de meses validos
        if (binding.edtMesRef.text.toString().toInt() < 1 || binding.edtMesRef.text.toString().toInt() > 12) {
            // Informa o erro para o usuário
            Util.exibirToast(this, "O mês de referência deve estar entre 1 e 12.")
            return false
        }

        // Caso tenha passado por todas as validações, retorna os registros como validados
        return true
    }
}