package com.fitjournal.api.service;

import com.fitjournal.api.dto.AddRotinaDTO;
import com.fitjournal.api.dto.GetRotinaDTO;
import com.fitjournal.api.repository.TreinoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreinoService {

    private TreinoRepository treinoRepository;

    public TreinoService(TreinoRepository treinoRepository) {
        this.treinoRepository = treinoRepository;
    }

    public List<GetRotinaDTO> buscarRotinas(int id){
        List<GetRotinaDTO> rotinas = treinoRepository.listarRotinas(id);
        return rotinas;
    }

    public void CadastrarRotina(AddRotinaDTO rotina){
        treinoRepository.addRotina(rotina);
    }


}
