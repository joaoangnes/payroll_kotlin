package com.example.folhapagamento

// Classe responsável pelo gerenciamento da lista de folhas de pagamento do aplicativo
class ListaFolha private constructor(){

    companion object {

        // Criando a lista de folha de pagamentos
        private var listaFolha = mutableListOf<Folha>()

        init {
            listaFolha.add(Folha("João Arthur", 20, 13.2F, 9,2023))
            listaFolha.add(Folha("Rubinho Alves", 40, 10.5F, 9,2023))
        }

        // Função responsável por adicionar uma folha de pagamento a lista
        fun addFolha(folha: Folha){
            listaFolha.add(folha)
        }

        // Função responsável por retornar uma folha da lista, através de um indice
        fun getFolha(position: Int): Folha {
            return listaFolha.get(position)
        }

        // Função responsável por remover uma folha da lista de pagamentos
        fun removeFolha(position: Int){
            listaFolha.removeAt(position)
        }

        // Função responsável por retornar a quantidade de folhas registradas no aplicativo
        fun getListSize() : Int {
            return listaFolha.size
        }

    }
}