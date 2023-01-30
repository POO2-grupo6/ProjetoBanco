## Projeto Banco - Projeto final da disciplina Programação Orientada a Objetos II

#dev_makers2 - Formação em Desenvolvimento Java Web Back-end pela Ada, em parceria com a Sinqia.

#### Professor da disciplina:
* Jackson Braga

#### Monitor:
* Alex Facincani

#### estudantes:
* Iasmine Almeida
* João Ortega
* Kleber Santos
* Marcos Torres
* Victor Gonçalves

----

## Projeto

O projeto consiste em desenvolver uma aplicação que simule uma aplicação bancária utilizando os princípios da Progamação Orientada a Objetos.

Existem regras de negócio específicas que devem ser seguidas, e uma lista de funcionalidades que devem estar presentes.

### Modelagem

- Existe uma classe concreta Bank que representa o banco.
- Existe uma classe abstrata Client, da qual herdam as classes concretas NaturalPerson e JuridicalPerson, representando os clientes PF e PJ, respectivamente.
- Existe uma classe abstrata Account, da qual herdam as classes concretas CheckingAccount, SavingsAccount e InvestmentAccount, que representam os diferentes tipos de conta.
- Existe uma classe concreta Repository que trata da persistência de dados (no caso, a implementação utiliza um Set<Client>).
- Existe uma classe chamada BankView que trata da interação com o usuário (entrada/saída de dados).
- Foram criadas várias classes de Exception para tratar diferentes problemas que poderiam ocorrer.
- Há ainda enums que tratam das taxas especificadas pelas regras de negócio.

Na modelagem feita pelo grupo, um banco possui uma coleção de clientes, e cada cliente possui um array de contas:

![banco-cliente-conta](https://user-images.githubusercontent.com/17331645/215548320-abd3616c-0e23-4723-afa9-cbc99c205277.png)
  
### Regras

- Clientes PF podem ter contas corrente, investimento e poupança.
  - Para clientes PF, a conta investimento rende 1,5%, enquanto a conta poupança rende 1%.
- Clientes PJ podem ter contas corrente e investimento.
  - Para clientes PJ, a conta investimento rende 3,5%.
  - Para clientes PJ, cada saque e/ou transferência é taxado em 0,5%.
- Para contas investimento, não é possível sacar (ou transferir a partir de) nem depositar (ou transfeir para). Devem ser usadas as opções resgatar e investir, respectivamente.
  - O investimento em conta investimento é sempre a partir do saldo da conta corrente do cliente.
  - O resgate em conta investimento sempre será para a conta corrente do cliente. Este resgate não é taxado, caso o cliente retire o valor da conta corrente aí sim incidirá uma taxa.

### Funcionamento

Inicialmente o programa dá as opções de registrar um novo cliente ou de realizar login. Há ainda a opção de listar os clientes cadastrados, para fins de ajudar no desenvolvimento:
  <br>
![1](https://user-images.githubusercontent.com/17331645/215552001-f304a6c5-4f0b-49bd-822d-326638c7cb71.png)
  
Caso a opção de registrar um novo cliente seja escolhida, são pedidos os dados do cliente. Os campos não podem ficar em branco, e não pode haver 2 clientes com o mesmo CPF ou o mesmo CNPJ. Caso o registro seja concluído com sucesso, uma conta corrente é automaticamente aberta para o cliente recémregistrado. Cada conta possui uma numeração, que é determinada sequencialmente (começanco de 1):
  ![2](https://user-images.githubusercontent.com/17331645/215554703-5ccafdd7-d6d4-40f1-a8e2-06e30120e768.png)

Uma vez registrado, o cliente pode efetuar login. Caso as credenciais inseridas estejam corretas, são mostradas as operações disponíveis para a conta corrente do cliente. Caso o cliente deseje operar em outra conta, o cliente deve escolher qual conta operar. Caso a conta ainda não exista, será dada a opção ao cliente abrir a conta em questão, e caso ele abra a conta, as operações disponíveis para aquela conta são exibidas:  
  ![4](https://user-images.githubusercontent.com/17331645/215556818-3dbc5635-da70-48b6-9bc8-9f1059ef97be.png)

<!--
    Crie as funcionalidades:
● abrir conta;
● sacar;
● depositar;
● transferência;
● investir;
● consultar saldo.
    -->
