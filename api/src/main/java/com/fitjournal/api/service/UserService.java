package com.fitjournal.api.service;

import com.fitjournal.api.dto.CadastroDTO;
import com.fitjournal.api.dto.LoginDTO;
import com.fitjournal.api.dto.UserDTO;
import com.fitjournal.api.model.User;
import com.fitjournal.api.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class  UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO login(LoginDTO loginRequest ){
        User user = userRepository.buscarPorEmail(loginRequest.getEmail());

        if (user == null){
            throw new RuntimeException("Usuario não encontrado");
        }

        if (!user.getPassword().equals(loginRequest.getSenha())){
            throw new RuntimeException("Senha invalida");
        }

        UserDTO userDTO = new UserDTO(user.getId(), user.getUserName(), user.getEmail());

        return userDTO;
    }

    public void cadastro(CadastroDTO cadastroRequest){
        if (userRepository.buscarPorEmail(cadastroRequest.getEmail()) != null){
            throw new RuntimeException("Esse usuario já existe");
        }

        userRepository.cadastrar(cadastroRequest.getUserName(), cadastroRequest.getEmail(), cadastroRequest.getPassword());
    }
}
