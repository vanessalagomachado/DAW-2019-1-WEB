/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Cidade;
import java.io.Serializable;

/**
 *
 * @author vanes
 */
public class CidadeDAO<TIPO> extends DAOGenerico<Cidade> implements Serializable{

    public CidadeDAO() {
        super();
        classePersistente = Cidade.class;
    }
    
}
