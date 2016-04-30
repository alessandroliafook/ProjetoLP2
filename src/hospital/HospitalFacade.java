package hospital;

import admin.ComiteGestor;
import easyaccept.EasyAccept;


public class HospitalFacade {

	ComiteGestor comite = ComiteGestor.getInstancia();

	public static void main(String[] args) {

		args = new String[] { "monopoly.HospitalFacade", "teste_aceitacao/usecase_1.txt",
				"teste_aceitacao/usecase_2.txt", "teste_aceitacao/usecase_3.txt", "teste_aceitacao/usecase_4.txt"

		};
		
		EasyAccept.main(args);
	}

}
