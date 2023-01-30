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

