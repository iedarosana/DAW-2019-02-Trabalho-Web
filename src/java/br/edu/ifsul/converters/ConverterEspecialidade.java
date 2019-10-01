/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.converters;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Especialidade;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author ieda
 */
@FacesConverter(value = "converterEspecialidade")
public class ConverterEspecialidade implements Serializable, Converter{
//cnverte da tela para o controle
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione um registro")){
            return null;
        }
        return EntityManagerUtil.getEntityManager().find(Especialidade.class, Integer.parseInt(string));
    }
//converte do objeto para a tela
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
       if (o == null){
           return null;
       }
       Especialidade obj = (Especialidade) o;
       return obj.getId().toString();
    }
    
}
