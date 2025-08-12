import java.math.BigDecimal;

public record TaxaConversao(String base_code, String target_code, BigDecimal conversion_rate, double conversion_result) {
}