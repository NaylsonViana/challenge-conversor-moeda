# challgenge-conversor-moeda

Este projeto é um conversor de moedas desenvolvido em Java, utilizando a API ExchangeRate para obter taxas de câmbio em tempo real. A aplicação interage com o usuário via console, 
permitindo escolher diferentes pares de moedas para conversão.

Funcionalidades:
- Conversão entre USD, BRL, ARS, BOB, CLP e COP
- Busca de taxas em tempo real através da API ExchangeRate
- Interface via console com menu interativo
- Validação de entrada do usuário

Tecnologias Usadas:
- Java 11+
- HttpClient (para consumo da API)
- Gson (para manipulação de JSON)
- API ExchangeRate

Como executar:
1. Clone este repositório:
   git clone https://github.com/seu-usuario/conversor-moedas.git

2. Abra o projeto em sua IDE preferida (IntelliJ, Eclipse, etc.).

3. Adicione a biblioteca Gson ao projeto (via Maven, Gradle ou manualmente):
   https://mvnrepository.com/artifact/com.google.code.gson/gson

4. Substitua "SUA_API_KEY" no código pela sua chave da API:
   https://www.exchangerate-api.com/

5. Compile e execute a classe principal:
   ConversorMoeda.java

Exemplo de Execuação:
===== CONVERSOR DE MOEDAS =====
Escolha uma opção:
1. USD → BRL
2. BRL → USD
3. USD → ARS
4. ARS → USD
5. BRL → COP
6. COP → BRL
7. Sair
Opção: 1
Digite o valor a converter: 100
Resultado: 100.00 USD = 492.30 BRL


Desenvolvido por:
Naylson Viana
