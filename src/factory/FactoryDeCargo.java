package factory;

import cargos.CargoIF;
import cargos.DiretorGeral;
import cargos.Medico;
import cargos.TecnicoAdministrativo;
import exceptions.StringInvalidaException;

public class FactoryDeCargo {

	public FactoryDeCargo() {
	}

	
	public CargoIF escolheCargo(String cargo) throws Exception{
		
		switch (cargo.toLowerCase()) {
		
		case "diretor geral":
			return new DiretorGeral();
		
		case "medico":
			return new Medico();
		
		case "tecnico administrativo":
			return new TecnicoAdministrativo();
			
		default:
			throw new StringInvalidaException();
		}
		
	}
}
