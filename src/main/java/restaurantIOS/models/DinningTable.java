package restaurantIOS.models;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;


    @Entity
    @Table(name = "dinning_table")
@NamedQuery(name = "DinningTable.getSpecificDinningTable", query = "SELECT d from DinningTable d " +
            "where d.table_number =?1 and d.id_restaurant = ?2")



    public class DinningTable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_table")
        private int id_dinningTable;
        private int table_number;
        private int id_restaurant;

    public DinningTable(int table_number, int id_restaurant) {
        this.table_number = table_number;
        this.id_restaurant = id_restaurant;
    }

    public DinningTable() {
        }

        public int getId_dinningTable() {
            return id_dinningTable;
        }

        public void setId_dinningTable(int id_dinningTable) {
            this.id_dinningTable = id_dinningTable;
        }

        public int getTable_number() {
            return table_number;
        }

        public void setTable_number(int table_number) {
            this.table_number = table_number;
        }

        public int getId_restaurant() {
            return id_restaurant;
        }

        public void setId_restaurant(int id_restaurant) {
            this.id_restaurant = id_restaurant;
        }
    }
