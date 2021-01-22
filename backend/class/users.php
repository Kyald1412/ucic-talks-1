<?php
    class Users{

        // Connection
        private $conn;

        // Table
        private $db_table = "tbl_users";

        // Columns
        public $id;
        public $username;
        public $password;
        public $name;
        public $updated_at;
        public $created_at;

        // Db connection
        public function __construct($db){
            $this->conn = $db;
        }

        // GET ALL
        public function login(){

               $sqlQuery = "SELECT *
                      FROM
                        ". $this->db_table ."
                    WHERE 
                    username = :username
                    AND
                    password = :password
                    LIMIT 0,1";

            $stmt = $this->conn->prepare($sqlQuery);

            $this->username=htmlspecialchars(strip_tags($this->username));
            $this->password=htmlspecialchars(strip_tags($this->password));

            // bind data
            $stmt->bindParam(":username", $this->username);
            $stmt->bindParam(":password", $this->password);

            $stmt->execute();

            $dataRow = $stmt->fetch(PDO::FETCH_ASSOC);
            
            if($dataRow != null){
                $this->name = $dataRow['name'];
            }
        }

    }
?>

