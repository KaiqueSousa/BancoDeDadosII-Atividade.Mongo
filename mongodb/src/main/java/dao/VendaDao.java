/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Accumulators.push;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import modelo.Venda;
import database.MongoConnectionPojo;
import java.util.ArrayList;
import java.util.List;
import modelo.ItemVenda;
/**
 *
 * @author kaique
 */
public class VendaDao {
    
    private MongoCollection collection;
    
    public VendaDao(){
        collection = new MongoConnectionPojo().getCollection("Venda", Venda.class);
    }
    
    public void salvar(Venda venda){
        collection.insertOne(venda);
    }
    
    public List<Venda> listar(){
        MongoCursor cursor = collection.find().iterator();
        
        List<Venda> vendas = new ArrayList<>();
        
        while(cursor.hasNext()){
            vendas.add((Venda) cursor.next());
        }
        return vendas;
    }
      
    public Venda buscarVenda(int codi){
        MongoCursor cursor = collection.find(eq("codigo", codi)).iterator();
        return (Venda) cursor.next();
}
    
    public boolean deletaVenda(int codi) {
        DeleteResult deleteOne = collection.deleteOne(eq ("codigo",codi));
        return deleteOne.getDeletedCount() > 0;
    }
    
    public boolean novoItemVenda(int codi, ItemVenda item) {
        UpdateResult result = collection.updateOne( eq("codigo",codi),(push("itens",item)));
        return result.getModifiedCount() > 0;
}
}
