/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifsul.edu.controle;

import br.edu.ifsul.dao.EspecialidadeDAO;
import br.edu.ifsul.modelo.Especialidade;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author ieda
 */
@ManagedBean(name = "controleEspecialidade")
@SessionScoped
public class ControleEspecialidade implements Serializable {

    private EspecialidadeDAO<Especialidade> dao;
    private Especialidade objeto;

    public ControleEspecialidade() {
        dao = new EspecialidadeDAO<>();
    }

    public String listar() {
        return "/privado/especialidade/listar?faces-redirect=true";
    }

    public String novo() {
        objeto = new Especialidade();
        return "formulario?faces-redirect=true";
    }

    public String salvar() {
        boolean persistiu;
        if(objeto.getId() == null){
            persistiu = dao.persist(objeto);
           
        }else{
            persistiu = dao.merge(objeto); 
        }
        if (persistiu){
            Util.mensagemInformacao(dao.getMensagem());
            return "listar?faces-redirect=true";
        }else{
            Util.mensagemErro(dao.getMensagem());
            return "formulario?faces-redirect=true";
        }
       
    }
    
    public String cancelar(){
     return "listar?faces-redirct=true";
    }
    
    public String editar (Object id){
           objeto = dao.localizar(id);
           return "formulario?faces-redirct=true";
        }
    
    public void remover(Object id){
        objeto = dao.localizar(id);
        if(dao.remove(objeto)){
                Util.mensagemInformacao(dao.getMensagem());
         }else{
            Util.mensagemErro(dao.getMensagem());
        }
    }
        
    

    public EspecialidadeDAO<Especialidade> getDao() {
        return dao;
    }

    public void setDao(EspecialidadeDAO<Especialidade> dao) {
        this.dao = dao;
    }

    public Especialidade getObjeto() {
        return objeto;
    }

    public void setObjeto(Especialidade objeto) {
        this.objeto = objeto;
    }

}
