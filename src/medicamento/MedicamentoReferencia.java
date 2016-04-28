package medicamento;

public class MedicamentoReferencia implements TipoMedicamentoIF{

	public final double DESCONTO = 1;
	
	public double calculaPreco(double preco){
	
		return preco * DESCONTO;
		
	}

	
}
