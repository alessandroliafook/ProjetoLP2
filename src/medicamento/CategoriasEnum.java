package medicamento;

public enum CategoriasEnum {

	ANALGESICO, ANTIBIOTICO, ANTIINFLAMATORIO, ANTIMETICO, ANTITERMICO, HORMONAL;
		
	public String toString(){
		return this.name().toLowerCase();
		
	}
}
