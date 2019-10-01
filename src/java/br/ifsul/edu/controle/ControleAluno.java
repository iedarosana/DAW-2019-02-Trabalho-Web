/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifsul.edu.controle;

import br.edu.ifsul.dao.AlunoDAO;
import br.edu.ifsul.modelo.Aluno;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author ieda
 */
@ManagedBean(name = "controleAluno")
@SessionScoped
public class ControleAluno implements Serializable {

    private AlunoDAO<Aluno> dao;
    private Aluno objeto;

    public ControleAluno() {
        dao = new AlunoDAO<>();
    }

    public String listar() {
        return "/privado/aluno/listar?faces-redirect=true";
    }

    public String novo() {
        objeto = new Aluno();
        return "formulario?faces-redirect=true";
    }

    public String salvar() {
        boolean persistiu;
        if (objeto.getId() == null) {
            persistiu = dao.persist(objeto);

        } else {
            persistiu = dao.merge(objeto);
        }
        if (persistiu) {
            Util.mensagemInformacao(dao.getMensagem());
            return "listar?faces-redirect=true";
        } else {
            Util.mensagemErro(dao.getMensagem());
            return "formulario?faces-redirect=true";
        }

    }

    public String cancelar() {
        return "listar?faces-redirct=true";
    }

    public String editar(Object id) {
        objeto = dao.localizar(id);
        return "formulario?faces-redirct=true";
    }

    public void remover(Object id) {
        objeto = dao.localizar(id);
        if (dao.remove(objeto)) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public AlunoDAO<Aluno> getDao() {
        return dao;
    }

    public void setDao(AlunoDAO<Aluno> dao) {
        this.dao = dao;
    }

    public Aluno getObjeto() {
        return objeto;
    }

    public void setObjeto(Aluno objeto) {
        this.objeto = objeto;
    }

}
