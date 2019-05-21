/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.EstadoDAO;
import br.edu.ifsul.dao.PaisDAO;
import br.edu.ifsul.modelo.Estado;
import br.edu.ifsul.modelo.Pais;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author vanes
 */
@ManagedBean(name = "controleEstado")
@SessionScoped
public class ControleEstado implements Serializable{
    private EstadoDAO<Estado> dao;
    private Estado obj;
    private PaisDAO daoPais;

    public ControleEstado() {
        dao = new EstadoDAO<>();
        daoPais = new PaisDAO();
    }

    public EstadoDAO<Estado> getDao() {
        return dao;
    }

    public void setDao(EstadoDAO<Estado> dao) {
        this.dao = dao;
    }

    public Estado getObj() {
        return obj;
    }

    public void setObj(Estado obj) {
        this.obj = obj;
    }

    public PaisDAO getDaoPais() {
        return daoPais;
    }

    public void setDaoPais(PaisDAO daoPais) {
        this.daoPais = daoPais;
    }
    
    public String listar(){
        return "/privado/estado/listar?faces-redirect=true";
    }
    
    public String novo(){
        obj = new Estado();
        return "formulario?faces-redirect=true";
    }
    
    public String salvar(){
        boolean persistiu;
        if(obj.getId() == null){
            persistiu = dao.persist(obj);
        } else{
            persistiu = dao.merge(obj);
        }
        if(persistiu){
            Util.mensagemInformacao(dao.getMensagem());
            return "listar?faces-redirect=true";
        }else{
            Util.mensagemErro(dao.getMensagem());
            return "formulario?faces-redirect=true";
        }
    }
    public String cancelar(){
        return "listar?faces-redirect=true";
    }
    
    public String editar(Integer id){
        try {
            obj = dao.localizar(id);
            return "formulario?faces-redirect=true";
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: "+Util.getMensagemErro(e));
            return "listar?faces-redirect=true";
        }
    }
    
    public void remover(Integer id){
        obj = dao.localizar(id);
        if(dao.remove(obj))
            Util.mensagemInformacao(dao.getMensagem());
        else
            Util.mensagemErro(dao.getMensagem());
    }
    
}
