/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author cleon
 */
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;

public class Principal {
    public static void main(String[] args) {
        //configuração
        EmbeddedConfiguration config = Db4oEmbedded.newConfiguration(); 
        config.common().messageLevel(0); // 0,1,2,3
        //criação do arquivo do Banco de Dados
        ObjectContainer manager = Db4oEmbedded.openFile(config,"aula.db4o");
        try{
          // Aluno
          Aluno a = new Aluno();
          //Variável para obter o resultado do objeto recuperado
          ObjectSet<Aluno> resultAluno;
          
          // Disciplina
          Disciplina d = new Disciplina();
          //Variável para obter o resultado do objeto recuperado
          ObjectSet<Disciplina> resultDisciplina;
          
          // Professor
          Professor p = new Professor();
          //Variável para obter o resultado do objeto recuperado
          ObjectSet<Professor> resultProfessor;
 
          //INSERÇÃO ALUNO
          a.setCod(1);
          a.setNome("Ana");
          a.setNota1(10.0);
          a.setNota2(10.0);
          manager.store(a);//Armazenando o objeto no banco
          System.out.println("O objeto foi inserido");
          
          //ATUALIZAÇÃO ALUNO
          a = new Aluno();
          a.setCod(1);
          resultAluno = manager.queryByExample(a);//Recupera o objeto do Banco
          Aluno achou_a = resultAluno.next();//retorna o objeto se existir
          achou_a.setNome("Ana Maria Silva");
          achou_a.setNota1(9.0);
          manager.store(achou_a);//Armazenando o objeto no Banco
          System.out.println("O objeto foi atualizado");
          
          //CONSULTA ALUNO
          a.setCod(1);
          resultAluno = manager.queryByExample(a);
          while(resultAluno.hasNext()){
            achou_a = resultAluno.next();
            System.out.println("O objeto foi consultado");
            //Imprime o resultado da consulta
            System.out.println("O resultado da consulta é:");
            System.out.println("Código: " + achou_a.getCod());
            System.out.println("Nome: " + achou_a.getNome());
            System.out.println("Nota1: " + achou_a.getNota1());
            System.out.println("Nota2: " + achou_a.getNota2());
            System.out.println("Media: " + achou_a.media());
            System.out.println("\n");
          }
                              
          //INSERÇÃO DISCIPLINA 
          d.setCod(2);
          d.setNome("Matemática");
          manager.store(d);//Armazenando o objeto no banco
          System.out.println("O objeto foi inserido");
          
          //ATUALIZAÇÃO DISCIPLINA
          d = new Disciplina();
          d.setCod(2);
          resultDisciplina = manager.queryByExample(d);//Recupera o objeto do Banco
          Disciplina achou_d = resultDisciplina.next();//retorna o objeto se existir
          achou_d.setNome("Matemática Aplicada a SI");
          manager.store(achou_d);//Armazenando o objeto no Banco
          System.out.println("O objeto foi atualizado");
          
          //CONSULTA DISCIPLINA
          d.setCod(2);
          resultDisciplina = manager.queryByExample(d);
          while(resultDisciplina.hasNext()){
            achou_d = resultDisciplina.next();
            System.out.println("O objeto foi consultado");
            //Imprime o resultado da consulta
            System.out.println("O resultado da consulta é:");
            System.out.println("Código: " + achou_d.getCod());
            System.out.println("Nome: " + achou_d.getNome());
            System.out.println("\n");
          }
                
          //INSERÇÃO PROFESSOR
          p.setCod(3);
          p.setNome("José");
          p.setDisciplina(d);
          manager.store(p);//Armazenando o objeto no banco
          System.out.println("O objeto foi inserido");
          
          //ATUALIZAÇÃO PROFESSOR
          p = new Professor();
          p.setCod(3);
          resultProfessor = manager.queryByExample(p);//Recupera o objeto do Banco
          Professor achou_p = resultProfessor.next();//retorna o objeto se existir
          achou_p.setNome("José Barros");
          manager.store(achou_p);//Armazenando o objeto no Banco
          System.out.println("O objeto foi atualizado");
          
          //CONSULTA PROFESSOR
          p.setCod(3);
          resultProfessor = manager.queryByExample(p);
          while(resultProfessor.hasNext()){
            achou_p = resultProfessor.next();
            System.out.println("O objeto foi consultado");
            //Imprime o resultado da consulta
            System.out.println("O resultado da consulta é:");
            System.out.println("Código: " + achou_p.getCod());
            System.out.println("Nome: " + achou_p.getNome());
            System.out.println("Disciplina: " + achou_p.getDisciplina());
            System.out.println("\n");
          }
          
          //REMOÇÃO PROFESSOR
          p.setCod(3);
          resultProfessor = manager.queryByExample(p);
          while(resultProfessor.hasNext()){
                achou_p = resultProfessor.next();
                manager.delete(achou_p);//Apaga o objeto
                System.out.println("O Objeto foi apagado");
                System.out.println("\n");
          }
          
          //REMOÇÃO DISCIPLINA
          d.setCod(2);
          resultDisciplina = manager.queryByExample(d);
          while(resultDisciplina.hasNext()){
                achou_d = resultDisciplina.next();
                manager.delete(achou_d);//Apaga o objeto
                System.out.println("O Objeto foi apagado");
                System.out.println("\n");
          }
          
          //REMOÇÃO ALUNO
          a.setCod(1);
          resultAluno = manager.queryByExample(a);
          while(resultAluno.hasNext()){
                achou_a = resultAluno.next();
                manager.delete(achou_a);//Apaga o objeto
                System.out.println("O Objeto foi apagado");
                System.out.println("\n");
          }
          
          manager.commit();
          
        }catch (Exception e){
            manager.rollback();
            e.printStackTrace();
        }finally{
             manager.close();//Fechando o Banco
        }
         
    }
}

