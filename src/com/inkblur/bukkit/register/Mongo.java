package com.inkblur.bukkit.register;

import org.bukkit.plugin.Plugin;

import com.inkblur.bukkit.mongodb.MongoHook;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class Mongo {
	private final DBCollection auth;
	Mongo(Plugin plugin){
		auth = MongoHook.getCollection(plugin, "authdata");
	}
	boolean update(String userName, String password){
		DBObject newb = auth.findAndModify(new BasicDBObject("name",userName)
			, null, null, false, new BasicDBObject("password",password).append("name", userName), true, true);
		return newb != null;
	}
}
