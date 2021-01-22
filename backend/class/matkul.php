<?php
    class Matkul{

        // Connection
        private $conn;

        // Table
        private $db_table = "tbl_matkul";

        // Columns
        public $id;
        public $matkul;
        public $id_hari;
      
        // Db connection
        public function __construct($db){
            $this->conn = $db;
        }

        // GET ALL
        public function getMatkul(){
            $sqlQuery = "SELECT id, matkul, id_hari FROM " . $this->db_table . "";
            $stmt = $this->conn->prepare($sqlQuery);
            $stmt->execute();
            return $stmt;
        }

        // CREATE
        public function createMatkul(){
            $sqlQuery = "INSERT INTO
                        ". $this->db_table ."
                    SET
                    matkul = :matkul, 
                    id_hari = :id_hari";
        
            $stmt = $this->conn->prepare($sqlQuery);
        
            // sanitize
            $this->matkul=htmlspecialchars(strip_tags($this->matkul));
            $this->id_hari=htmlspecialchars(strip_tags($this->id_hari));
         
            // bind data
            $stmt->bindParam(":matkul", $this->matkul);
            $stmt->bindParam(":id_hari", $this->id_hari);
        
            if($stmt->execute()){
               return true;
            }
            return false;
        }

        // UPDATE
        public function getMatkulByHari(){
            $sqlQuery = "SELECT id, matkul, id_hari FROM " . $this->db_table . " WHERE 
            id_hari = :id_hari";
            $stmt = $this->conn->prepare($sqlQuery);

            $stmt->bindParam(":id_hari", $this->id_hari);

            $stmt->execute();
            return $stmt;
        }        

        // UPDATE
        public function updateMatkul(){
            $sqlQuery = "UPDATE
                        ". $this->db_table ."
                    SET
                    matkul = :matkul, 
                    id_hari = :id_hari
                    WHERE 
                        id = :id";
        
            $stmt = $this->conn->prepare($sqlQuery);
        
            $this->matkul=htmlspecialchars(strip_tags($this->matkul));
            $this->id_hari=htmlspecialchars(strip_tags($this->id_hari));
            $this->id=htmlspecialchars(strip_tags($this->id));

            // bind data
            $stmt->bindParam(":matkul", $this->matkul);
            $stmt->bindParam(":id_hari", $this->id_hari);
            $stmt->bindParam(":id", $this->id);

            if($stmt->execute()){
               return true;
            }
            return false;
        }

        // DELETE
        function deleteMatkul(){
            $sqlQuery = "DELETE FROM " . $this->db_table . " WHERE id = ?";
            $stmt = $this->conn->prepare($sqlQuery);
        
            $this->id=htmlspecialchars(strip_tags($this->id));
        
            $stmt->bindParam(1, $this->id);
        
            if($stmt->execute()){
                return true;
            }
            return false;
        }

    }
?>

