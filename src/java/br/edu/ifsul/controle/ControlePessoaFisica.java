/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CidadeDAO;
import br.edu.ifsul.dao.PessoaFisicaDAO;
import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.modelo.Endereco;
import br.edu.ifsul.modelo.PessoaFisica;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author vanes
 */
@ManagedBean(name = "controlePessoaFisica")
@ViewScoped
public class ControlePessoaFisica implements Serializable{
    private PessoaFisica obj;
    private PessoaFisicaDAO<PessoaFisica> dao;
    private Endereco endereco;
    private Boolean novoEndereco;
    private CidadeDAO<Cidade> daoCidade;

    public ControlePessoaFisica() {
        dao = new PessoaFisicaDAO<>();
        daoCidade = new CidadeDAO<>();
    }

    public PessoaFisica getObj() {
        return obj;
    }

    public void setObj(PessoaFisica obj) {
        this.obj = obj;
    }

    public PessoaFisicaDAO<PessoaFisica> getDao() {
        return dao;
    }

    public void setDao(PessoaFisicaDAO<PessoaFisica> dao) {
        this.dao = dao;
    }
    
    public String listar(){
        return "/privado/pessoafisica/listar?faces-redirect=true";
    }
    
    public void novo(){
        obj = new PessoaFisica();
    }
    
    public void salvar(){
        boolean persistiu;
        if(obj.getId() == null){
            persistiu = dao.persist(obj);
        } else{
            persistiu = dao.merge(obj);
        }
        if(persistiu){
            Util.mensagemInformacao(dao.getMensagem());
        }else{
            Util.mensagemErro(dao.getMensagem());
        }
    }
    public String cancelar(){
        return "listar?faces-redirect=true";
    }
    
    public void editar(Integer id){
        try {
            obj = dao.localizar(id);
            
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: "+Util.getMensagemErro(e));
            
        }
    }
    
    public void remover(Integer id){
        obj = dao.localizar(id);
        if(dao.remove(obj))
            Util.mensagemInformacao(dao.getMensagem());
        else
            Util.mensagemErro(dao.getMensagem());
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Boolean getNovoEndereco() {
        return novoEndereco;
    }

    public void setNovoEndereco(Boolean novoEndereco) {
        this.novoEndereco = novoEndereco;
    }
    
    public void addEndereco(){
        endereco = new Endereco();
        novoEndereco = true;
    }
    public void editarEndereco(int indice){
        endereco = obj.getEnderecos().get(indice);
        novoEndereco = false;
    }
    public void salvarEndereco(){
        if(novoEndereco)
            obj.adicionarEndereco(endereco);
        Util.mensagemInformacao("Endereço persistido com sucesso!");
    }
    public void removerEndereco(int indice){
        obj.removerEndereco(indice);
        Util.mensagemInformacao("Endereço removido com sucesso!");
    }

    public CidadeDAO<Cidade> getDaoCidade() {
        return daoCidade;
    }

    public void setDaoCidade(CidadeDAO<Cidade> daoCidade) {
        this.daoCidade = daoCidade;
    }
    
    
    
}
