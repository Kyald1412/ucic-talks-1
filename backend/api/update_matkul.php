<?php
    header("Access-Control-Allow-Origin: *");
    header("Content-Type: application/json; charset=UTF-8");
    header("Access-Control-Allow-Methods: POST");
    header("Access-Control-Max-Age: 3600");
    header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
    
    include_once '../config/database.php';
    include_once '../class/matkul.php';
    
    $database = new Database();
    $db = $database->getConnection();
    
    $item = new Matkul($db);
    
    $data = json_decode(file_get_contents("php://input"));
    
    $item->id = $data->id;
    
    // employee values
    $item->matkul = $data->matkul;
    $item->id_hari = $data->id_hari;

    if($item->updateMatkul()){
        http_response_code(200);
        echo json_encode(
            array("message" => "Matkul data updated.")
        );
    } else{
        http_response_code(404);
        echo json_encode(
            array("message" => "Data could not be updated.")
        );
    }
?>