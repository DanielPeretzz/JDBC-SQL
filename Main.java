package com.homework.daniel;


import com.homework.daniel.dal.CatDAL;
import com.homework.daniel.model.Cat;

import java.io.BufferedReader;
import java.io.FileReader;


public class Main {
    //---------create-cats-object------------------------------------------------------
    static Cat cat1 = new Cat("Mitzhi",2);
    static Cat cat2 = new Cat("Hatula",5);
    static Cat cat3 = new Cat("yossi",3);
    static Cat cat4 = new Cat("meow",5);
    //---------------------------------------------------------------------------------
    public static void main(String[] args) throws Exception {
        CatDAL catDAL = CatDAL.instance; // crate instance form CatDAL Singleton Class
       //-------create-cats-in-SQL-----------------------------------------------------
      try {
          catDAL.create(cat1);
        catDAL.create(cat2);
        catDAL.create(cat3);
        catDAL.create(cat4);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //-------delete-cats-from-SQL----------------------------------------------------
       catDAL.delete(1L); // delete cats from SQL idcats = 1
        //-------update-cats-name-and-age------------------------------------------------
        catDAL.update(cat3);// update cat name
        //-------readall-cats-from-SQL---------------------------------------------------
        System.out.println(catDAL.readAll());
        //-------read-cat-from-SQL------------------------------------------------------
        System.out.println(catDAL.read(2L));
    }

}


