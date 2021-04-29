package com.br.zupacademy.hugo.mercadolivre.config.securirty;

import com.br.zupacademy.hugo.mercadolivre.usuario.Usuario;
import com.br.zupacademy.hugo.mercadolivre.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BuscarDadosUsuario implements UserDetailsService {


    private UsuarioRepository usuarioRepository;

    @Autowired
    private BuscarDadosUsuario(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByLogin(username);

        if(usuarioOptional.isPresent()){
            return usuarioOptional.get();
        }

        throw new UsernameNotFoundException("O login passado é inválido");
    }
}
