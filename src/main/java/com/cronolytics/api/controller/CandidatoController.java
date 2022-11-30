package com.cronolytics.api.controller;

import com.cronolytics.api.dto.req.CadastroCandidatoDTO;
import com.cronolytics.api.entity.Candidato;
import com.cronolytics.api.repository.ICandidatoRepository;
import com.cronolytics.api.utils.GeradorCsv;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/candidatos")
public class CandidatoController {

    @Autowired
    ICandidatoRepository candidatoRepository;

    @Autowired
    GeradorCsv geradorCsv;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody CadastroCandidatoDTO payload) {
        Candidato candidato = new Candidato();
        BeanUtils.copyProperties(payload, candidato);
        candidatoRepository.save(candidato);

        return ResponseEntity.status(204).build();
    }

    @GetMapping
    public ResponseEntity listarTodos() {
        List<Candidato> todosCandidatos = new ArrayList<>();

        todosCandidatos = candidatoRepository.findAll();

        return ( todosCandidatos.isEmpty() ) ? ResponseEntity.status(204).build() : ResponseEntity.ok(todosCandidatos);
    }


    @PostMapping(value = "/csv")
    public ResponseEntity importarCsv(@RequestParam("file") MultipartFile file) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT
                             .withFirstRecordAsHeader()
                             .withIgnoreHeaderCase()
                             .withDelimiter(';')
                             .withRecordSeparator('\n')
                             .withTrim())) {

            List<Candidato> candidatos = new ArrayList<Candidato>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Candidato candidato = new Candidato(
                        csvRecord.get("NOME"),
                        csvRecord.get("CPF"),
                        csvRecord.get("EMAIL"),
                        LocalDate.parse(csvRecord.get("DATA DE NASCIMENTO")),
                        csvRecord.get("TELEFONE"),
                        csvRecord.get("CEP"),
                        csvRecord.get("ESTADO CIVIL"),
                        csvRecord.get("INSTITUICAO DE ENSINO"),
                        csvRecord.get("VAGA"),
                        csvRecord.get("GRAU DE ENSINO")
                );

                candidatos.add(candidato);
            }

            candidatoRepository.saveAll(candidatos);
            return ResponseEntity.status(204).build();

        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }


    @GetMapping(value = "/csv", produces = "text/csv")
    public void exportarCsv(HttpServletResponse res) throws IOException {
        res.setContentType("text/csv");
        res.addHeader("Content-Disposition", "attachment; filename=\"curriculum.csv\"");
        geradorCsv.gerarCsvCandidatos(candidatoRepository.findAll(), res.getWriter());
    }
}