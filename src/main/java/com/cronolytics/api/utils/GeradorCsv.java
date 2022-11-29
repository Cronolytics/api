package com.cronolytics.api.utils;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import com.cronolytics.api.entity.Candidato;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

@Component
public class GeradorCsv {
    public void gerarCsvCandidatos(List<Candidato> candidatos, Writer writer) {
        try {

            String[] HEADERS = {
                    "NOME",
                    "CPF",
                    "EMAIL",
                    "DATA DE NASCIMENTO",
                    "TELEFONE",
                    "CEP",
                    "ESTADO CIVIL",
                    "LINKEDIN",
                    "DESCRICAO"
            };

            CSVPrinter printer = new CSVPrinter(
                    writer,
                    CSVFormat.DEFAULT
                            .withFirstRecordAsHeader()
                            .withIgnoreHeaderCase()
                            .withDelimiter(';')
                            .withRecordSeparator('\n')
                            .withTrim()
            );

            printer.printRecord(
                    "NOME",
                    "CPF",
                    "EMAIL",
                    "DATA DE NASCIMENTO",
                    "TELEFONE",
                    "CEP",
                    "ESTADO CIVIL",
                    "LINKEDIN",
                    "DESCRICAO");


            for (Candidato candidato : candidatos) {
                printer.printRecord(
                        candidato.getNome(),
                        candidato.getCpf(),
                        candidato.getEmail(),
                        candidato.getDataNascimento(),
                        candidato.getTelefone(),
                        candidato.getCep(),
                        candidato.getEstadoCivil(),
                        candidato.getLinkedin(),
                        candidato.getDescricao()
                );
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}