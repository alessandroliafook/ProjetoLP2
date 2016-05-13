package hospital;

import easyaccept.EasyAccept;

public class Main {

	public static void main(String[] args) {

		args = new String[] { "hospital.HospitalFacade", "teste_aceitacao/usecase_1.txt",
				"teste_aceitacao/usecase_2.txt", "teste_aceitacao/usecase_3.txt", "teste_aceitacao/usecase_4.txt",
				"teste_aceitacao/usecase_5.txt"

		};

		EasyAccept.main(args);
	}

}
