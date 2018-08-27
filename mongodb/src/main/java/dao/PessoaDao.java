/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import modelo.Pessoa;
import database.MongoConnection;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author kaique
 */
public class PessoaDao {
    
    private MongoCollection collection;
    
    public PessoaDao(){
        collection = new MongoConnection().getCollection("Pessoa");
    }
    
    public void salvar(Pessoa p){
        collection.insertOne(p.toDocument());
    }
    
    public List<Pessoa> listar(){
        MongoCursor<Document> cursor = collection.find().iterator();
        
        List<Pessoa> pessoas = new ArrayList<Pessoa>();
        
        while(cursor.hasNext()){
            pessoas.add(new Pessoa(cursor.next()));
            
        }
        return pessoas;
    }
    
    public Pessoa buscarPorCpf(String cpf){
        Document document = (Document) collection.find(eq("cpf",cpf)).first();
        
        if(document == null){
            return null;
        }else{
            return new Pessoa(document);
        }
    }
    
    public boolean deletarPorCpf(String cpf){
        DeleteResult result = collection.deleteOne(eq("cpf",cpf));
        return result.getDeletedCount()>0;
    }
    
    public boolean atualizar(Pessoa p){
        UpdateResult result = collection.updateOne(
                eq("cpf", p.getCpf()),
                new Document("$set",
                        new Document("nome", p.getNome())
                            .append("idade", p.getIdade())));

        return result.getModifiedCount()>0;

    }
}
