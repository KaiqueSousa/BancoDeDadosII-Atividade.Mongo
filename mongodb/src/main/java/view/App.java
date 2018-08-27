/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import modelo.ItemVenda;
import modelo.Pessoa;
import modelo.Produto;
import modelo.Venda;
import dao.PessoaDao;
import dao.VendaDao;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author kaique
 */
public class App {
    
  public static void main(String[] args){
      //PessoaDao dao = new PessoaDao();
      
     // dao.salvar(new Pessoa("111-111-111-01","Kaique",20));;
      //System.out.println(dao.listar());
     // System.out.println(dao.buscarPorCpf("111-111-111-01"));
     
     VendaDao dao = new VendaDao();
     
     Produto produto1 = new Produto(1,"Teclado",30);
     Produto produto2 = new Produto(2,"Mouse",20);
     Produto produto3 = new Produto(3,"HD Externo",300);
     Produto produto4 = new Produto(4,"Impressora",450);
     
     ItemVenda item1 = new ItemVenda(produto1, 1);
     ItemVenda item2 = new ItemVenda(produto2, 2);
     ItemVenda item3 = new ItemVenda(produto3, 1);
     ItemVenda item4 = new ItemVenda(produto4, 1);
     
     List<ItemVenda> itens = new ArrayList<>();
     itens.add(item1);
     itens.add(item2);
     
     List<ItemVenda> itens2 = new ArrayList<>();
     itens2.add(item3);
     itens2.add(item4);
     
     Venda venda = new Venda(1,itens,LocalDateTime.now());
     Venda venda2 = new Venda(2,itens2,LocalDateTime.now());
     
     dao.salvar(venda);
     dao.salvar(venda2);
     
     dao.deletaVenda(1);

     Produto produto5 = new Produto(5, "Fone", 15);

     ItemVenda item5 = new ItemVenda(produto5, 2);

     dao.novoItemVenda(2, item5);
     
    System.out.println(dao.buscarVenda(2));
     
  }
}
