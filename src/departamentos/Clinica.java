package departamentos;

import java.util.Set;
import java.util.TreeSet;

import exceptions.CadastroPacienteException;
import factory.FactoryDePessoa;
import pessoal.Paciente;
import prontuario.Prontuario;

public class Clinica {

	private Set<Prontuario> prontuarios;
	private FactoryDePessoa pacienteFactory;

	public Clinica() {
		this.prontuarios = new TreeSet<Prontuario>();
		pacienteFactory = new FactoryDePessoa();
	}

	public Paciente cadastraPaciente(String nome, String data, double peso, String sexo,
			String genero, String tipoSanguineo) throws Exception {

		try {
			Paciente novoPaciente = pacienteFactory.criaPaciente(nome, data, peso, tipoSanguineo, sexo, genero,
					getNumeroCadastros() + 1);
			Prontuario novoProntuario = new Prontuario(novoPaciente);

			if (existeProntuario(novoProntuario)) {
				throw new Exception("Paciente ja cadastrado.");
			}

			prontuarios.add(novoProntuario);
			return novoPaciente;

		} catch (Exception e) {
			throw new CadastroPacienteException(e.getMessage());
		}

	}

	public int getNumeroCadastros() {
		return prontuarios.size();
	}

	public boolean existeProntuario(Prontuario prontuario) {
		return prontuarios.contains(prontuario);
	}

	public String getInfoPaciente(Paciente paciente, String atributo) {
		String retorno = "";

		switch (atributo) {
		case "Nome":
			retorno = paciente.getNome();
			break;
		case "Data":
			retorno = paciente.getData();
			break;
		case "Sexo":
			retorno = paciente.getSexo();
			break;
		case "Genero":
			retorno = paciente.getGenero();
			break;
		case "TipoSanguineo":
			retorno = paciente.getTipoSanguineo();
			break;
		case "Peso:":
			retorno = String.valueOf(paciente.getPeso());
			break;
		default:
			break;
		}

		return retorno;
	}

}
