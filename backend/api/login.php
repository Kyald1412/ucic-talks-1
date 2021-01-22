<?php
    header("Access-Control-Allow-Origin: *");
    header("Content-Type: application/json; charset=UTF-8");
    header("Access-Control-Allow-Methods: POST");
    header("Access-Control-Max-Age: 3600");
    header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

    include_once '../config/database.php';
    include_once '../class/users.php';

    $database = new Database();
    $db = $database->getConnection();

    $item = new Users($db);
    

    // $data = json_decode(file_get_contents("php://input"));

    $item->username = isset($_POST['username']) ? $_POST['username'] : die();
    $item->password = isset($_POST['password']) ? $_POST['password'] : die();

    // $item->username = $data->username;
    // $item->password = $data->password;
  
    $item->login();


    if($item->name != null){
        // create array
        $emp_arr = array(
            "name" =>  $item->name
        );
      
        http_response_code(200);
        echo json_encode($emp_arr);
    }
      
    else{
        http_response_code(404);
        echo json_encode(
            array("message" => "Users record found.")
        );    
    }
?>