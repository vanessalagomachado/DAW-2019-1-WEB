/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.PessoaFisica;
import java.io.Serializable;

/**
 *
 * @author vanes
 */
public class PessoaFisicaDAO<TIPO> extends DAOGenerico<PessoaFisica> implements Serializable{

    public PessoaFisicaDAO() {
        super();
        classePersistente = PessoaFisica.class;
    }
    
}
