package com.cronolytics.api.controller;

import com.cronolytics.api.entity.Respondente;
import com.cronolytics.api.repository.IRespondenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


@RestController
@RequestMapping("/csv")
public class CsvController {

    @Autowired
    IRespondenteRepository respondenteRepository;

    @PostMapping
    public ResponseEntity createRespondentesCsv() {

        List<Respondente> lista = respondenteRepository.findAll();

        FileWriter arq = null;
        Formatter saida = null;
        Boolean deuRuim = false;
        String nomeArquivo = "respondentes.csv";

        try {
            arq = new FileWriter(nomeArquivo);
            saida = new Formatter(arq);
        } catch (IOException e) {
            System.out.println("Erro ao abrir o arquivo");
            return ResponseEntity.status(500).build();
        }

        try {

            for (int i = 0; i < lista.size(); i++) {
                Respondente usuario = lista.get(i);
                saida.format("%d;%s;%s\n",
                        usuario.getId(),
                        usuario.getGenero(),
                        usuario.getEscolaridade());
            }

        } catch (FormatterClosedException erro) {
            System.out.println("Erro ao gravar o arquivo");
            deuRuim = true;
        } finally {
            saida.close();
            try {
                arq.close();
            } catch (IOException e) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim) return ResponseEntity.status(500).build();
        }

        return ResponseEntity.status(201).build();

    }

    @GetMapping
    public ResponseEntity lerCsv() {
        FileReader arq = null;
        Scanner in = null;
        Boolean deuRuim = false;
        String nomeArq = "respondentes.csv";

        try {
            arq = new FileReader(nomeArq);
            in = new Scanner(arq).useDelimiter(";|\\n");
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado!");
            System.exit(1);
        }

        try {
            System.out.printf("%s;%s;%s\n",
                    "ID", "GÊNERO", "ESCOLARIDADE");

            while (in.hasNext()) {
                Long id = in.nextLong();
                String genero = in.next();
                String escolaridade = in.next();

                System.out.printf("%d;%s;%s\n",
                        id, genero, escolaridade);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Arquivo com problemas");
        } catch (IllegalStateException e) {
            System.out.println("Erro na leitura do arquivo");
            deuRuim = true;
        } finally {
            in.close();
            try {
                arq.close();
            } catch (IOException e) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim) System.exit(1);
        }

        return ResponseEntity.status(200).build();
    }

}
