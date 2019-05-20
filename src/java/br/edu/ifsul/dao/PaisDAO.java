/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Pais;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author vanes
 */
public class PaisDAO implements Serializable{
    private String mensagem = "";
    private EntityManager em;

    public PaisDAO() {
        em = EntityManagerUtil.getEntityManager();
    }
    
    

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
    
    
    //Métodos DAO
    public List<Pais> getLista(){
        return em.createQuery("from Pais order by nome").getResultList();
    }
    
    public boolean salvar(Pais obj){
        try {
            em.getTransaction().begin();
            if(obj.getId() != null)
                em.persist(obj);
            else
                em.merge(obj);
            em.getTransaction().commit();
            mensagem = "Objeto persistido com sucesso";
            return true;
        } catch (Exception e) {
            if(!em.getTransaction().isActive())
                em.getTransaction().begin();
            em.getTransaction().rollback();
            mensagem = "Erro ao persistir objeto: "+Util.getMensagemErro(e);
            return false;
            
        }
    }
    
    public boolean remover(Pais obj){
        try {
            em.getTransaction().begin();
            em.remove(obj);
            em.getTransaction().commit();
            mensagem = "Objeto removido com sucesso";
            return true;
        } catch (Exception e) {
            if(!em.getTransaction().isActive())
                em.getTransaction().begin();
            em.getTransaction().rollback();
            mensagem = "Erro ao remover objeto: "+Util.getMensagemErro(e);
            return false;
            
        }
    }
        
        public Pais localizar(Integer id){
            return em.find(Pais.class, id);
        }
                
                
                
    }
    
    
    
    

