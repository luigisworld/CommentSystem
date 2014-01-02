package com.luigisworld.controller;


import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.luigisworld.model.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class LuigisController{
	@Autowired
    private MongoTemplate mongo;
	
	/**
	* Sets all the comments stored in the database into the model
	* @return page's name
	*/
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String goHome(ModelMap model) {
	    //retrieves all the comments stored in the database
	    List<Comment> comments = mongo.findAll(Comment.class);
	   
	    model.put("comments",comments);
        return "index";
    }
	    
    /**
     * Stores the new comment in the database
     * @param json contains the new comment in JSON format
     * @return the comment if it has been saved successfully
     */
    @RequestMapping(value="/add", method = RequestMethod.POST)
    public @ResponseBody String getComments(@RequestBody final String json){
       	
	    boolean error = false;
	    Comment comment = new Gson().fromJson(json, Comment.class);
	    comment.setCurrentDate();
	   
	    try{
		    //stores the comment in the database
		    mongo.save(comment);
	    }catch(Exception ex){
		    error = true;
		    ex.printStackTrace();
	    }
	   
	    if(error)
	    	return "error";
	    else{
	    	Gson gson = new GsonBuilder().setPrettyPrinting().create();  
	        return gson.toJson(comment);
	    }
    }
}
