/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifsul.edu.controle;

import br.edu.ifsul.dao.ProfessorDAO;
import br.edu.ifsul.dao.EspecialidadeDAO;
import br.edu.ifsul.modelo.Professor;
import br.edu.ifsul.modelo.Especialidade;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author ieda
 */
@ManagedBean(name = "controleProfessor")
@SessionScoped
public class ControleProfessor implements Serializable {

    private ProfessorDAO<Professor> dao;
    private Professor objeto;
    private EspecialidadeDAO<Especialidade> daoEspecialidade;

    public ControleProfessor() {
        dao = new ProfessorDAO<>();
        daoEspecialidade = new EspecialidadeDAO<>();
    }

    public String listar() {
        return "/privado/professor/listar?faces-redirect=true";
    }

    public String novo() {
        objeto = new Professor();
        return "formulario?faces-redirect=true";
    }

    public String salvar() {
        boolean persistiu;
        if (objeto.getTitulacao() == null) {
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

    public ProfessorDAO<Professor> getDao() {
        return dao;
    }

    public void setDao(ProfessorDAO<Professor> dao) {
        this.dao = dao;
    }

    public Professor getObjeto() {
        return objeto;
    }

    public void setObjeto(Professor objeto) {
        this.objeto = objeto;
    }

    public EspecialidadeDAO<Especialidade> getDaoEspecialidade() {
        return daoEspecialidade;
    }

    public void setDaoEspecialidade(EspecialidadeDAO<Especialidade> daoEspecialidade) {
        this.daoEspecialidade = daoEspecialidade;
    }

}
