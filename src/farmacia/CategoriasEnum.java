package farmacia;

public enum CategoriasEnum {

	ANALGESICO, ANTIBIOTICO, ANTIINFLAMATORIO, ANTIEMETICO, ANTITERMICO, HORMONAL;

	public String toString() {
		return this.name().toLowerCase();

	}

}