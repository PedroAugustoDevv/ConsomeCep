# Sistema de Consulta e Armazenamento de CEP 🛣️

Este projeto permite que o usuário consulte um CEP, verificando se ele já está presente no banco de dados local. Caso não esteja, o sistema consulta a API do ViaCEP para obter as informações de endereço e armazena o resultado no banco de dados com a data e hora da consulta.

## Funcionalidades

1. Solicitação do CEP ao usuário através de uma interface gráfica.
2. Verificação da existência do CEP no banco de dados local.
3. Caso o CEP não exista, consulta a API do ViaCEP para obter as informações de endereço (rua, bairro, cidade, estado).
4. Armazenamento do novo CEP no banco de dados com a data e hora da consulta.
5. Exibição do CEP consultado em uma interface gráfica.

## Tecnologias Utilizadas

- **Java**: Linguagem de programação utilizada para o desenvolvimento do sistema.
- **ViaCEP API**: API externa utilizada para consultar informações sobre o CEP.
- **Swing**: Utilizado para criar a interface gráfica com o usuário (JOptionPane).
- **PostgreSQL**: Banco de dados utilizado para armazenar os dados de endereço.


**Clonar o Repositório**:

   Clone o repositório para sua máquina local:

   ```bash
   git clone https://github.com/seunome/consomeCep2025.git
   cd consomeCep2025
