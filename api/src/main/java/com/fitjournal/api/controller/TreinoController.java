package com.fitjournal.api.controller;

import com.fitjournal.api.dto.GetRotinaDTO;
import com.fitjournal.api.dto.AddRotinaDTO;
import com.fitjournal.api.service.TreinoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/treinos")
public class TreinoController {

    private TreinoService treinoService;

    public TreinoController(TreinoService treinoService) {
        this.treinoService = treinoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<GetRotinaDTO>> buscarRotinas(@PathVariable Integer id){
        List<GetRotinaDTO> rotinas = treinoService.buscarRotinas(id);
        return ResponseEntity.status(200).body(rotinas);
    }

    @PostMapping("/add/{id}")
    public ResponseEntity<Void> addRotina(@PathVariable Integer id, @RequestBody AddRotinaDTO rotina){
        rotina.setIdUsuario(id);
        treinoService.CadastrarRotina(rotina);
        return ResponseEntity.status(201).build();
    }



}
