package br.unipar.programacaoweb;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConsomeCEP {

    //TODO
    // 1. solicite um cep ao usuário (pode ser joptionpane)
    // 2. verifique se existe o cep na base de dados
    // 3. se não existir, consulte o cep na api do viacep
    // 4. salve o novo cep na base de dados com data e hora da consulta

    public static void main(String[] args) {
        try {
            String cep = JOptionPane.showInputDialog("Digite o CEP: ");
            EnderecoDAO enderecoDAO = new EnderecoDAO();

            // Verifica se o endereço já está no banco de dados
            Endereco endereco = enderecoDAO.buscarPorEndereco(Integer.valueOf(cep));

            if (endereco == null) {
                // Se o endereço não existir no banco, consultar o ViaCEP
                endereco = consultaViaCep(cep);
                if (endereco != null) {
                    enderecoDAO.salvar(endereco);
                }
            }

            // Exibe a mensagem para o usuário
            if (endereco != null) {
                JOptionPane.showMessageDialog(null, "Endereço: " + endereco.getLogradouro() + ", " +
                        endereco.getBairro() + ", " + endereco.getLocalidade() + " - " + endereco.getUf());
            } else {
                JOptionPane.showMessageDialog(null, "CEP não encontrado.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para consultar a API ViaCEP e retornar um objeto Endereco
    public static Endereco consultaViaCep(String cep) throws Exception {
        String urlString = "https://viacep.com.br/ws/" + cep + "/json/";
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder json = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            json.append(line);
        }
        reader.close();

        // Usando o ObjectMapper para mapear o JSON para a classe Endereco
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json.toString(), Endereco.class);
    }
}