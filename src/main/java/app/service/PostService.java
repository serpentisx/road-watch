/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service;

/**
 *
 * @author Bjarki
 */
public interface PostService {
    
    public boolean createNewPost(String title, String description, String latitude, String longitude, String road, String file, String road_number, String zip, String locality);
    
}