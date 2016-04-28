package medicamento;

public class MedicamentoGenerico implements TipoMedicamentoIF {

	public final double DESCONTO = 0.4;
	
	public double calculaPreco(double preco){
	
		return preco * DESCONTO;
		
	}
	
}
