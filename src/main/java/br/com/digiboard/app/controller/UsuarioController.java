package br.com.digiboard.app.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import br.com.digiboard.app.model.Usuario;

import br.com.digiboard.app.repository.UsuarioRepository;

@Component
@SessionScope
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private Usuario usuario = new Usuario();
	
	private List<Usuario> usuarios;
	
	@PostConstruct
	public void init() {
		usuarios = usuarioRepository.findAll();
	}
	
	public void salvar() {
		usuarioRepository.save(getUsuario());
		usuario = new Usuario();
		try {
	        FacesContext.getCurrentInstance().getExternalContext().redirect("adminpage.xhtml");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public void editar(Usuario usuarioedit) {
		setUsuario(usuarioedit);

	}
	
	public void excluir(Usuario usuario) {
		usuarioRepository.delete(usuario);
		usuarios = usuarioRepository.findAll();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	

}
