<?php

class CService
{

    static function addUserWs($id, $password){

        //$pdo = new PDO('mysql:host=localhost:3306;dbname=dill', 'root', 'root');
        $pdo = new PDO('mysql:host=185.229.224.193:3306;dbname=dill', 'root', 'root');

        $encrypt = password_hash($password, PASSWORD_BCRYPT);

        $encrypt = str_replace("$2y", "$2a", $encrypt);

        $pdo->query("Insert into users values ('$id','$encrypt',1)");

        $pdo->query("Insert into authorities values ('$id', 'ROLE_ADMIN')");

        $pdo = null;

    }

    static function connectToWs($id, $password){

        $existant = false;
        //$pdo = new PDO('mysql:host=localhost:3306;dbname=dill', 'root', 'root');
        $pdo = new PDO('mysql:host=185.229.224.193:3306;dbname=dill', 'root', 'root');


        $user = $pdo->query("SELECT * from users where username = '$id'");
        if ($user != null) {

            foreach ($user as $row) {

                if (password_verify($password, $row[1])) {
                    $existant = true;
                }
            }
        }
        $pdo = null;
        return $existant;
    }

    static function callApi($user, $password){

        $curl = curl_init();
        
        $url = "http://185.229.224.193:8080/api_rest-0.0.1-SNAPSHOT/api/token?username=$user&password=$password";
        //$url = "http://localhost:8079/api_rest-0.0.1-SNAPSHOT/api/token?username=$user&password=$password";
        //$url = "https://localhost:8080/api/token?username=$user&password=$password";
        curl_setopt($curl, CURLOPT_POST, 1);
        curl_setopt($curl, CURLOPT_URL, $url);
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
        curl_setopt($curl, CURLOPT_HEADER, 1);

        curl_setopt($curl,CURLOPT_SSL_VERIFYHOST,0);
        curl_setopt($curl,CURLOPT_SSL_VERIFYPEER,1);
        curl_setopt($curl,CURLOPT_CAINFO,'certif.crt');
        curl_setopt($curl,CURLOPT_CAPATH,'certif.crt');      //curl_setopt($curl, CURLOPT_SSL_VERIFYHOST,  2);

        $result = curl_exec($curl);

        //var_dump(($result));

        $code =  curl_getinfo($curl, CURLINFO_HTTP_CODE);

        echo "<br/>" . "Code : " . $code . "<br/>";

        curl_close($curl);
        //$token = substr($result, strpos($result, "Bearer") + 8, strpos($result, "X-Content-Type-Options") - strpos($result, "Bearer") );

        $token = substr($result, strpos($result, "Bearer"), strpos($result, "X-Content-Type-Options") - strpos($result, "Bearer") - 1);

        return [$code, $token];
    }

    static function postPatch($token, $data, $method, $url){
        $url = "http://185.229.224.193:8080/api_rest-0.0.1-SNAPSHOT/api/$url";

       // $url = "https://localhost:8080/api/$url";

        $ch = curl_init($url);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json', "Authorization: " . $token));
        curl_setopt($ch, CURLOPT_CUSTOMREQUEST, $method);

        if ($method == "POST" || $method == "PATCH")
            curl_setopt($ch, CURLOPT_POSTFIELDS,$data);

        curl_setopt($ch,CURLOPT_SSL_VERIFYHOST,0);
        curl_setopt($ch,CURLOPT_SSL_VERIFYPEER,1);
        curl_setopt($ch,CURLOPT_CAINFO,'certif.crt');
        curl_setopt($ch,CURLOPT_CAPATH,'certif.crt');


        $response = curl_exec($ch);
        $code =  curl_getinfo($ch, CURLINFO_HTTP_CODE);
        if (!$response)
        {
            return false;
        }


        return [$code, json_decode($response)];

    }
    
    static function connectUserToWS($id, $password){
        if (CService::connectToWs($id,$password)){
            $token = CService::callApi($id, $password);
            return $token;
        }
        else {
            return NULL;
        }
    }
}


?>