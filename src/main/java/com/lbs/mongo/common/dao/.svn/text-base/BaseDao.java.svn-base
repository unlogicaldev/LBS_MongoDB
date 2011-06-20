package com.lbs.mongo.common.dao;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.DatastoreImpl;
import com.google.code.morphia.Key;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.QueryResults;
import com.google.code.morphia.query.UpdateOperations;
import com.mongodb.DBCollection;
import com.mongodb.MongoException;

public abstract class BaseDao<T,K extends Serializable> {
	@SuppressWarnings("unchecked")
	final protected Logger log = LoggerFactory.getLogger(((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]));

	private DatastoreMongo mds;
	
	protected DatastoreImpl ds;
	protected Morphia morphia;
	
	protected Class<T> entityClass;
	
	@SuppressWarnings("unchecked")
	public BaseDao(){
		this.entityClass = ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
	}

	@Autowired
	public void setMds(DatastoreMongo mds){
		this.mds = mds;
		this.ds = (DatastoreImpl)this.mds.getDs();
	}

	@Autowired
	public void setMorphia(Morphia morphia) {
		this.morphia = morphia;
	}
	
	
	
	
	
    /**
     * Converts from a List<Key> to their id values
     * @param keys
     * @return
     */
    @SuppressWarnings("unchecked")
	protected List<?> keysToIds (List<Key<T>> keys){
    	ArrayList ids = new ArrayList(keys.size()*2);
    	for(Key<T> key : keys)
    		ids.add(key.getId());
    	return ids;
    }
    
    protected DBCollection collection() {
        return ds.getCollection(entityClass);
    }

    public Query<T> createQuery() {
    	return ds.createQuery(entityClass);
    }
    public UpdateOperations<T> createUpdateOperations() {
    	return ds.createUpdateOperations(entityClass);
    }
    
    public Class<T> getEntityClass() {
        return entityClass;
    }

    public Key<T> save( T entity ) throws MongoException {
    	Key<T> key = null;
    	try{
    		key = ds.save(entity);
    	}catch(MongoException e){
    		e.printStackTrace();
    		throw new MongoException(e.getCode()+"");
    	}
    	return key;
    }

    /**
     * Updates the first object matched by the constraints with the modifiers supplied.
     */
    @SuppressWarnings("unchecked")
	public void updateFirst( Query q, UpdateOperations ops) {
    	ds.updateFirst(q, ops);
    }
    
    public void updateFirst( K id, Map<String,Object> args){
    	Query<T> q = ds.createQuery(entityClass);
    	q.filter("_id", id); 
    	ds.updateFirst(q, createUpdateOperation(args));
    }

    /**
     * Updates all objects matched by the constraints with the modifiers supplied.
     */
    @SuppressWarnings("unchecked")
	public void update( Query q, UpdateOperations ops) {
    	ds.update(q, ops);
    }

    public void delete( T entity ) {
    	ds.delete(entity);
    }

    public void deleteById( K id ) {
    	ds.delete(entityClass, id);
    }

    public void deleteByQuery( final Map<String, Object> map ) {
    	Query<T> q = createQuery(map);
    	ds.delete(q);
    }

	
    
    public void deleteByQuery( Query<T> q ) {
    	ds.delete(q);
    }

    public T get( K id ) {
    	return ds.find(entityClass, "_id", id).get();
    }

    @SuppressWarnings("unchecked")
	public List<K> findIds( String key, Object value ) {
    	return (List<K>) keysToIds(ds.find(entityClass, key, value).asKeyList());
    }

    @SuppressWarnings("unchecked")
	public List<K> findIds() {
    	return (List<K>) keysToIds(ds.find(entityClass).asKeyList());
    }
 
    @SuppressWarnings("unchecked")
	public List<K> findIds( Query<T> q ) {
    	return (List<K>) keysToIds(q.asKeyList());
    }

    public boolean exists(String key, Object value) {
        return exists(ds.find(entityClass, key, value));
    }
    public boolean exists(Query<T> q) {
        return ds.getCount(q) > 0;
    }

    public long count() {
        return ds.getCount(entityClass);
    }
    
    public long count(String key, Object value) {
        return count(ds.find(entityClass, key, value));
    }
    
    public long count(Query<T> q) {
        return ds.getCount(q);
    }
    
    public long count(final Map<String, Object> map) {
    	Query<T> q = createQuery(map);
        return ds.getCount(q);
    }

    public T findOne(String key, Object value) {
        return ds.find(entityClass, key, value).get();
    }
    
    public T findOneByQuery(final Map<String, Object> map) {
    	Query<T> q = createQuery(map);
    	return findOne(q);
    }
    
    public T findOne( Query<T> q ) {
        return q.get();
    }
    
    public List<T> find(String key, Object value) {
    	return ds.find(entityClass, key, value).asList();
    }
    
    public List<T> findQuery(final Map<String, Object> map) {
    	Query<T> q = createQuery(map);
    	return findQuery(q);
    }
    
    @SuppressWarnings("unchecked")
	public List<T> findQueryAndUpdate(final Map<String, Object> map, final Map<String, Object> map2) {
    	return (List<T>) ds.findAndModify(createQuery(map), createUpdateOperation(map2));
    }
    
    public List<T> findQuery(Query<T> q) {
    	return q.asList();
    }

    public QueryResults<T> find() {
        return ds.find(entityClass);
    }
    
    public List<T> findAll(){
    	return find().asList();
    }

    public QueryResults<T> find( Query<T> q ) {
    	return q;
    }

    public void dropCollection() {
    	ds.getCollection(entityClass).drop();
    }

    public Datastore getDatastore() {
    	return this.ds;
    }
    
    
    /*
     * private methods
     */
    
    /**
     * create Query<T>
     * map = {"key =":value, "key2 >":value, "order":"id, -foo", "offset":100, "limit":10, "hintIndex":"-id"}
     * order is must be <String>
     * @param map
     * @return
     */
    private Query<T> createQuery(final Map<String,Object> map) {
    	Query<T> q = ds.createQuery(entityClass);
    	String key = "";
    	Set<String> keySet = map.keySet();
    	Iterator<String> keyIt = keySet.iterator();    	
    	while(keyIt.hasNext()){
    		key = keyIt.next();    		
    		
    		if(key.equals("order")){
    			q.order((String)map.get(key));
    			
    		}else if(key.equals("limit")){
    			q.limit((Integer)map.get(key));
    			
    		}else if(key.equals("offset")){
    			q.offset((Integer)map.get(key));
    			
    		}else if(key.equals("hintIndex")){
    			q.hintIndex((String)map.get(key));
    			
    		}else{
    			q.filter(key, map.get(key));
    		}
    	}
//    	if(log.isDebugEnabled()){
//    		log.debug("!!!!!!!!!!!!! Query retrive !!!!!!!!!!!!!!!!!");
//    		log.debug("Query to "+ entityClass.getSimpleName() + " : " + map);
//    	}
		return q;
	}
    
    /**
     * create UpdateOperation
     * @param map = {"key":value, "key2.name":value, "key3":"$inc"} 
     *  ?? inc -> key = key3+1
     * @return
     */
    private UpdateOperations<T> createUpdateOperation(final Map<String,Object> map) {
    	UpdateOperations<T> ops = ds.createUpdateOperations(entityClass);

    	String key = "";
    	String method = "";
    	Method retMethod = null;
    	
    	Set<String> keySet = map.keySet();
    	Iterator<String> keyIt = keySet.iterator();    	
    	while(keyIt.hasNext()){
    		key = keyIt.next();
    		if(map.get(key) == null){
    			ops.unset(key);
    		}else if(map.get(key).equals("$inc")){
    			ops.inc(key);
    		}else{
    			
    			/**
    			 * update 하려는 파라미터의 Type이
    			 * List일경우 add 해준다.
    			 */
    			method = "get" + key.substring(0, 1).toUpperCase() + key.substring(1);
				try {
					Class<?>[] parameterTypes = new Class<?>[]{};
					retMethod = entityClass.getMethod(method, parameterTypes);
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					System.out.println(method+":key-"+key);
					e.printStackTrace();
				}
				if(retMethod.toString().indexOf("java.util.List") > -1){
	    			ops.add(key, map.get(key));
				}else{
	    			ops.set(key, map.get(key));
				}
    		}
    	}
//    	if(log.isDebugEnabled()){
//    		log.debug("!!!!!!!!!!!!! Query retrive Update !!!!!!!!!!!!!!!!!");
//    		log.debug("Update to "+ entityClass.getSimpleName() + " : " + map);
//    	}
		return ops;
	}
	
}
