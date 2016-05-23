Universidade Federal de Campina Grande - UFCG
Centro de Engenharia Eletrica e Informática
Unidade Acadêmica de Sistemas de Informação
Curso de Bacharelado em Ciências da Computação
Laboratório de Programação 2


Alunos: Alessandro Lia Fook Santos
	Lucas Diniz dos Santos
	Filipe Teutônio Mendonça Ramalho

Relatório do Projeto intitulado "Laborário Sistema Orientado a Objeto de Saúde"
	
	Antes de adentrar os casos de uso solitados, é importanto relatar que de forma mais geral o sistema foi implementado seguindo o padrão MVC (Model View Controller), que tem por objetivo modularizar as atribuições do sistema.
	No sistema desenvolvido a parte titulada de Model, que conterá a lógica do sistema relacionando-se com as partes mais baixas da arquitetura, é implementado pela classe "ComiteGestor". Enquanto o View, que tem a responsabilidade de exibir os resultados e coletar as entradas do usuário externo ao sistema, é implementado na classe HospitalFacade, que utiliza o padrão de fachada para torná-la o equivalente a uma saída e entrada do sistema, haja vista que não é executada nenhuma interface gráfica no projeto. Por fim, o Controller, que tem por objetivo intermediar os outros dois, é implementado na classe HospitalController e além de intermediar a comunicação é responsável por garantir a permanência dos dados de execução.
	
Caso 1 - Primeiro cadastro, login do diretor geral e cadastro de novos colaboradores:

	Neste caso de uso foram usadas variáveis de controle para determinar se o primeiro cadastro já havia sido executado, assim como para determinar a existência do diretor geral cadastrado no sistema.
	Aquele por sua vez é um objeto criado a partir da fábrica da classe Funcionário, utilizando o padrão de projeto factor, para assim aperfeiçoar o Creator das classes, melhorando o GRASP.
	Assim, foi criada uma classe Funcionário,  

Caso 2 - Atualizar informações de usuários e Excluir usuários do sistema:
Caso 3 - Cadastrar e atualizar prontuários dos pacientes:
Caso 4 - Farmácia e Medicamentos:
Caso 5 - Banco de Orgãos:
Caso 6 - Realização de Procedimentos:
Caso 7 - Cartão de Fidelidade:
Caso 8 - Exportar ficha de pacientes:
Caso 9 - Salvar sistema:












