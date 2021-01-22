<?php
    class Hari{

        // Connection
        private $conn;

        // Table
        private $db_table = "tbl_hari";

        // Columns
        public $id;
        public $hari;

        // Db connection
        public function __construct($db){
            $this->conn = $db;
        }

        // GET ALL
        public function getHari(){
            $sqlQuery = "SELECT * FROM " . $this->db_table . "";
            $stmt = $this->conn->prepare($sqlQuery);
            $stmt->execute();
            return $stmt;
        }

        // CREATE
        public function createHari(){
            $sqlQuery = "INSERT INTO
                        ". $this->db_table ."
                    SET
                    hari = :hari";
        
            $stmt = $this->conn->prepare($sqlQuery);
        
            // sanitize
            $this->hari=htmlspecialchars(strip_tags($this->hari));
            
        
            // bind data
            $stmt->bindParam(":hari", $this->hari);
        
            if($stmt->execute()){
               return true;
            }
            return false;
        }

        // UPDATE
        public function getSingleHari(){
            $sqlQuery = "SELECT
                        id, 
                        hari
                      FROM
                        ". $this->db_table ."
                    WHERE 
                       id = ?
                    LIMIT 0,1";

            $stmt = $this->conn->prepare($sqlQuery);

            $stmt->bindParam(1, $this->id);

            $stmt->execute();

            $dataRow = $stmt->fetch(PDO::FETCH_ASSOC);
            
            $this->hari = $dataRow['hari'];
            
        }        

        // UPDATE
        public function updateHari(){
            $sqlQuery = "UPDATE
                        ". $this->db_table ."
                    SET
                    hari = :hari
                    WHERE 
                        id = :id";
        
            $stmt = $this->conn->prepare($sqlQuery);
        
            $this->hari=htmlspecialchars(strip_tags($this->hari));
            $this->id=htmlspecialchars(strip_tags($this->id));
        
            // bind data
            $stmt->bindParam(":hari", $this->hari);
            $stmt->bindParam(":id", $this->id);
        
            if($stmt->execute()){
               return true;
            }
            return false;
        }

        // DELETE
        function deleteHari(){
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

