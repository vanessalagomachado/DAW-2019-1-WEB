/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.PaisDAO;
import br.edu.ifsul.modelo.Pais;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author vanes
 */
@ManagedBean(name = "controlePais")
@SessionScoped
public class ControlePais implements Serializable{
    private PaisDAO dao;
    private Pais obj;

    public ControlePais() {
        dao = new PaisDAO();
    }

    public PaisDAO getDao() {
        return dao;
    }

    public void setDao(PaisDAO dao) {
        this.dao = dao;
    }

    public Pais getObj() {
        return obj;
    }

    public void setObj(Pais obj) {
        this.obj = obj;
    }
    
    //ações
    public String listar(){
        return "/privado/pais/listar?faces-redirect=true";
        
    }
    
    public String novo(){
        obj = new Pais();
        return "formulario?faces-redirect=true";
    }
    
    public String salvar(){
        if(dao.salvar(obj)){
            Util.mensagemInformacao(dao.getMensagem());
            return "listar?faces-redirect=true";
        } else{
            Util.mensagemInformacao(dao.getMensagem());//Criar método para erros
            return "formulario?faces-redirect=true";
        }
    }
    
    public String cancelar(){
        return "listar?faces-redirect=true";
    }
    
    public String editar(Integer id){
        obj = dao.localizar(id);
        return "formulario?faces-redirect=true";
    }
    
    public void remover(Integer id){
        obj = dao.localizar(id);
        dao.remover(obj);
        Util.mensagemInformacao(dao.getMensagem());
    }
    
}
