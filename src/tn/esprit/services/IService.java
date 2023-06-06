/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.util.List;

/**
 *
 * @author Fayechi
 */
public interface IService<O> {
    public void Create(O o);
    public void Update( O o);
    public List<O> Read();
    public void Delete(O o);
    public O SearchbyId(int id); 
    
    
}
