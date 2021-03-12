<?php

class Service
{

    static function addUserWs($id, $password){

        $pdo = new PDO('mysql:host=localhost:3306;dbname=dill', 'root', 'root');

        $encrypt = password_hash($password, PASSWORD_BCRYPT);

        $encrypt = str_replace("$2y", "$2a", $encrypt);

        $pdo->query("Insert into users values ('$id','$encrypt',1)");

        $pdo->query("Insert into authorities values ('$id', 'ROLE_ADMIN')");

        $pdo = null;

    }

    static function connectToWs($id, $password){

        $existant = false;
        $pdo = new PDO('mysql:host=localhost:3306;dbname=dill', 'root', 'root');

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
        $url = "https://localhost:8080/api/token?username=$user&password=$password";
        curl_setopt($curl, CURLOPT_POST, 1);
        curl_setopt($curl, CURLOPT_URL, $url);
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
        curl_setopt($curl, CURLOPT_HEADER, 1);

        curl_setopt($curl,CURLOPT_SSL_VERIFYHOST,0);
        curl_setopt($curl,CURLOPT_SSL_VERIFYPEER,1);
        curl_setopt($curl,CURLOPT_CAINFO,'certif.crt');
        curl_setopt($curl,CURLOPT_CAPATH,'certif.crt');      //curl_setopt($curl, CURLOPT_SSL_VERIFYHOST,  2);

        $result = curl_exec($curl);

        var_dump(($result));

        $code =  curl_getinfo($curl, CURLINFO_HTTP_CODE);

        echo "<br/>" . "Code : " . $code . "<br/>";

        curl_close($curl);
        //$token = substr($result, strpos($result, "Bearer") + 8, strpos($result, "X-Content-Type-Options") - strpos($result, "Bearer") );

        $token = substr($result, strpos($result, "Bearer"), strpos($result, "X-Content-Type-Options") - strpos($result, "Bearer") - 1);

        return [$code, $token];
    }

    static function postPatch($token, $data, $method, $url){
        $url = "https://localhost:8080/api/$url";


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
    
    static function connecToService($id, $password){
        if (Service::connectToWs($id,$password)){
            $token = $this->callApi($id, $password);
            return $token;
        }
        else {
            return NULL;
        }
    }
}


/*
 * 
 * Exemple d'utilisatation de la classe :
 * 
//Service::connectToWs("fromage", "fromage");


if (Service::connectToWs("fromage", "fromage") == true){
    echo "Ui";
}
else {
    echo "non";
}
$token = Service::callApi("fromage", "fromage");

//echo ("token : " . $token[1]);

$data = json_encode(
    array(
        "texteCode" => "Je suis un autre Qr Code Salami",
        "scoreCode" => 1
    )
);
$badges = Service::postPatch($token[1], null, "GET", "badges");

echo "<br/>" ;
echo "code : " . $badges[0] . "<br/>";
foreach ($badges[1] as $badge){
    echo  ("Badge " . $badge->id . " - " . $badge->nom . "<br/>");
}
echo "<br/>" . "------------------------" . "<br/>";

$data = json_encode(array("id" => 4, "nom" => "badge4", "image" => "image.jpg"));

Service::postPatch($token[1], $data, "POST", "newBadge");

$badges = Service::postPatch($token[1], null, "GET", "badges");

echo "<br/>" ;
echo "code : " . $badges[0] . "<br/>";
foreach ($badges[1] as $badge){
    echo  ("Badge " . $badge->id . " - " . $badge->nom . "<br/>");
}
echo "<br/>" . "------------------------" . "<br/>";


$data = json_encode(array("id" => 4, "nom" => "badge 4", "image" => "imageb4.jpg"));

Service::postPatch($token[1], $data, "PATCH", "modifierBadge");

$badges = Service::postPatch($token[1], null, "GET", "badges");

echo "<br/>" ;
echo "code : " . $badges[0] . "<br/>";
foreach ($badges[1] as $badge){
    echo  ("Badge " . $badge->id . " - " . $badge->nom . "<br/>");
}

echo "<br/>" . "------------------------" . "<br/>";

Service::postPatch($token[1], $data, "DELETE", "deleteBadge/4");

$badges = Service::postPatch($token[1], null, "GET", "badges");

echo "<br/>" ;
echo "code : " . $badges[0] . "<br/>";
foreach ($badges[1] as $badge){
    echo  ("Badge " . $badge->id . " - " . $badge->nom . "<br/>");
}
*/
?>