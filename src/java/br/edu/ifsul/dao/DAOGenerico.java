/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.util.Util;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author vanes
 */
public class DAOGenerico<TIPO> {
    private List<TIPO> listaObjetos;
    private List<TIPO> listaTodos;
    protected EntityManager em;
    protected Class classePersistente;
    private String mensagem;

    public DAOGenerico() {
        em = EntityManagerUtil.getEntityManager();
    }

    public List<TIPO> getListaObjetos() {
        String sql = "from "+classePersistente.getSimpleName();
        return em.createQuery(sql).getResultList();
    }

    public List<TIPO> getListaTodos() {
         String sql = "from "+classePersistente.getSimpleName();
        return em.createQuery(sql).getResultList();
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    public boolean persist(TIPO obj){
        try {
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
            mensagem = "Objeto persistido com sucesso";
            return true;
        } catch (Exception e) {
            rollback();
            mensagem = "Erro ao persistir: "+Util.getMensagemErro(e);
            return false;
        }
    }
    
    public void rollback(){
        if(!em.getTransaction().isActive())
            em.getTransaction().begin();
        em.getTransaction().rollback();
    }
            
    public boolean merge(TIPO obj){
        try {
            em.getTransaction().begin();
            em.merge(obj);
            em.getTransaction().commit();
            mensagem = "Objeto atualizado com sucesso";
            return true;
        } catch (Exception e) {
            rollback();
            mensagem = "Erro ao atualizar: "+Util.getMensagemErro(e);
            return false;
        }
    }
            
     public boolean remove(TIPO obj){
        try {
            em.getTransaction().begin();
            em.remove(obj);
            em.getTransaction().commit();
            mensagem = "Objeto removido com sucesso";
            return true;
        } catch (Exception e) {
            rollback();
            mensagem = "Erro ao remover: "+Util.getMensagemErro(e);
            return false;
        }
    }       
            
     public TIPO localizar(Integer id){
         TIPO obj = (TIPO) em.find(classePersistente, id);
         return obj;
     }
            
    
    
    
    
    
}
