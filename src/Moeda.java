import java.math.BigDecimal;

public class Moeda {
    private String base;
    private String target;
    private BigDecimal taxaConversao;
    private double resultadoConversao;
    private double valor;

    public Moeda(TaxaConversao taxa) {
        this.base = taxa.base_code();
        this.target = taxa.target_code();
        this.taxaConversao = taxa.conversion_rate();
        this.resultadoConversao = taxa.conversion_result();
    }

    @Override
    public String toString() {
//        return "Base: " + base + "\nTarget: " + target +
//                "\nTaxa de conversão: " + taxaConversao + "\nResultado: " + resultadoConversao;
        valor = Double.parseDouble(String.valueOf(this.resultadoConversao)) / Double.parseDouble(String.valueOf(this.taxaConversao));
        String myStr = """
                **************************
                Conversão de %s para %s
                
                Taxa de câmbio: %.4f
                Valor: %.2f [%s]
                Resultado: %.2f [%s]
                **************************
                """;
        return String.format(myStr, this.base, this.target, this.taxaConversao, valor, this.base, this.resultadoConversao, this.target);
    }
}