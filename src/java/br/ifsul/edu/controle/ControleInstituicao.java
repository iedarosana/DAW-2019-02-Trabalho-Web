/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifsul.edu.controle;

import br.edu.ifsul.dao.InstituicaoDAO;
import br.edu.ifsul.modelo.Instituicao;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author ieda
 */
@ManagedBean(name = "controleInstituicao")
@SessionScoped
public class ControleInstituicao implements Serializable {

    private InstituicaoDAO<Instituicao> dao;
    private Instituicao objeto;

    public ControleInstituicao() {
        dao = new InstituicaoDAO<>();
    }

    public String listar() {
        return "/privado/instituicao/listar?faces-redirect=true";
    }

    public String novo() {
        objeto = new Instituicao();
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

    public InstituicaoDAO<Instituicao> getDao() {
        return dao;
    }

    public void setDao(InstituicaoDAO<Instituicao> dao) {
        this.dao = dao;
    }

    public Instituicao getObjeto() {
        return objeto;
    }

    public void setObjeto(Instituicao objeto) {
        this.objeto = objeto;
    }

}
