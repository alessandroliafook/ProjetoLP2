package farmacia;

import java.io.Serializable;

public enum CategoriasEnum implements Serializable{

	ANALGESICO, ANTIBIOTICO, ANTIINFLAMATORIO, ANTIEMETICO, ANTITERMICO, HORMONAL;

	public String toString() {
		return this.name().toLowerCase();

	}

}