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
public interface IService<T> {
    public void Create(T t);
    public List<T> Read();
    public void Delete(int i);
    public void Update (T t);
    
    
}
