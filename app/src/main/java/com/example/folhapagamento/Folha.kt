package com.example.folhapagamento

// Classe responsável por armazenar os dados da folha de pagemento do funcionário
class Folha (var nome: String, var horasTrab: Int, var valorHora: Float, var mes: Int, var ano: Int) {

    // Dados da folha de pagamento
    var sal_bruto: Float
    var sal_liq: Float
    var ir: Float
    var inss: Float
    var fgts: Float

    // Inicializando os dados da folha do funcionário, quando instanciado
    init {

        // Cálculo de salário bruto do funcionário
        sal_bruto = calcSalBruto()

        // Cácula o IR do funcionário
        ir = calcIR()

        // Cálcula o INSS do funcionário
        inss = calcINSS()

        // Cálcula o FGTS do funcionário
        fgts = calcFGTS()

        // Cálcula o salário liquido do funcionário
        sal_liq = calcSalarioLiq()

    }

    fun calcSalBruto(): Float{
        // Cálculo de salário bruto: número de horas trabalhadas * valor da hora
        return this.horasTrab * this.valorHora
    }

    fun calcIR(): Float{
        /* Cálculo do IR do funcionário, seguindo a tabela base:
            - Salário Bruto: Desconto:
            (1)- Até R$ 1.372,81 0                  - OK
            (2)- De R$ 1.372,82 até 2.743,25 15%    - OK
            (3)- Acima de 2.743,25 27,5%            - OK   */
        return if (this.sal_bruto <= 1372.81f) {
            0f  // (1) 0
        } else if (this.sal_bruto <= 2743.25f) {
            this.sal_bruto * 0.15f  // (2) 15%
        } else {
            this.sal_bruto * 0.275f // (3) 27,5%
        }
    }

    fun calcINSS(): Float{
        /* Cálculo do INSS do funcionário, seguindo a tabela base:
            - Salário bruto Desconto
                (1)- Até R$ 868,29 8%                      -
                (2)- De R$ 868,30 até R$ 1.447,14 9%       -
                (3)- De R$ 1.447,15 até R$ 2.894,28 11%    -
                (4)- Acima de R$ 2.894,28 R$ 318,37        -     */
        return if (this.sal_bruto <= 868.29f) {
            this.sal_bruto * 0.08f // (1) 8%
        } else if (this.sal_bruto <= 1447.14f) {
            this.sal_bruto * 0.09f  // (2) 9%
        } else if (this.sal_bruto <= 2894.28f) {
            this.sal_bruto * 0.11f // (3) 11%
        } else {
            318.37f           // (4) R$ 318,37 [fixos]
        }
    }

    fun calcFGTS(): Float{
        // Cálculo do FGTS basta extrair 8% do salário bruto
        return this.sal_bruto * 0.08f
    }

    fun calcSalarioLiq(): Float{
        // Cálculo do salario liquido: Salário bruto – IR – INSS
        return this.sal_bruto - this.ir - this.inss
    }

}