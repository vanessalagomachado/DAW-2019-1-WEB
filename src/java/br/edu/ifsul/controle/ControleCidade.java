/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CidadeDAO;
import br.edu.ifsul.dao.EstadoDAO;
import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.modelo.Estado;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author vanes
 */
@ManagedBean(name = "controleCidade")
@SessionScoped
public class ControleCidade implements Serializable{
    private CidadeDAO<Cidade> dao;
    private Cidade obj;
    private EstadoDAO daoEstado;

    public ControleCidade() {
        dao = new CidadeDAO<>();
        daoEstado = new EstadoDAO();
    }

    public CidadeDAO<Cidade> getDao() {
        return dao;
    }

    public void setDao(CidadeDAO<Cidade> dao) {
        this.dao = dao;
    }

    public Cidade getObj() {
        return obj;
    }

    public void setObj(Cidade obj) {
        this.obj = obj;
    }

    public EstadoDAO getDaoEstado() {
        return daoEstado;
    }

    public void setDaoEstado(EstadoDAO daoEstado) {
        this.daoEstado = daoEstado;
    }
    
    public String listar(){
        return "/privado/cidade/listar?faces-redirect=true";
    }
    
    public String novo(){
        obj = new Cidade();
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
