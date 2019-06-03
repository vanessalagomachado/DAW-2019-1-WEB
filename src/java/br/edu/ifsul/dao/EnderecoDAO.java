/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Endereco;
import java.io.Serializable;

/**
 *
 * @author vanes
 */
public class EnderecoDAO<TIPO>  extends DAOGenerico<Endereco> implements Serializable{

    public EnderecoDAO() {
        super();
        classePersistente = Endereco.class;
    }
    
}
