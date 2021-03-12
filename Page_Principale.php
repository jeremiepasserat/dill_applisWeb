<?php
    session_start();
    class Service
    {

        static function connectToWs($id, $password)
        {

            $existant = false;
            $pdo = new PDO('mysql:host=localhost:3306;dbname=dill', 'root', 'root');

      /*      $user = $pdo->query("SELECT * from users where username = '$id'");
            if ($user != null) {

                foreach ($user as $row) {

                    if (password_verify($password, $row[1])) {
                        $existant = true;
                    }
                }
            }
    */
            $encrypt = password_hash($password, PASSWORD_BCRYPT);

            $encrypt = str_replace("$2y", "$2a", $encrypt);

            $pdo->query("Insert into users values ('$id','$encrypt',1)");

            $pdo->query("Insert into authorities values ('$id', 'ROLE_ADMIN')");

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
    }
    if( isset($_POST['email']) && isset($_POST['password'])){
        $_SESSION['email'] = htmlspecialchars($_POST['email']);
        $_SESSION['password'] = htmlspecialchars($_POST['password']);
        Service::connectToWs("",$_SESSION['email']);

    }else if(!isset($_SESSION['email']) || !isset($_SESSION['password'])){
        header("Location:index.html");
        //include('./index_error_login.html');
    }
    
    //les donnees recuperees depuis le fichier json
    $file = './files_json/allScores.json';
    //on mets le contenu dans une variable
    $data = file_get_contents($file);
    //on décode le contenu du flux sous forme de tableau
    $array_data = json_decode($data, true);
    //tableau contenant tous les utilisateurs avec leurs scores
    $array_score_utilisateur = $array_data[0]['allScores'];
?>

<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Page Principal</title>
        <link rel="stylesheet" href="./CSS/style.css">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div id="block_page">
            <header>
                <div id="titre_principale">
                    <div id="logo">
                        <a href="Page_Principale.php"><img src="./Images/logo.PNG" alt="Logo de l'appli" /><a/>
                        <h1>Lamotte Beuvron</h1>
                    </div>
                </div>                   
            </header>
            <nav>
                <ul>
                    <li><a href="./Classement_general.php">Classement général</a></li>
                    <li><a href="#">Magazine CMJ</a></li>
                    <li><a href="./Gestion_defis.php">Gestion des défis</a></li>
                    <li><a href="#">Gestion des jeux</a></li>
                </ul>
            </nav>
            <div id='banniere_image'>
                <div id='banniere_description'>
                    Commune Lamotte-Beuvron
                    <a href='https://www.lamotte-beuvron.fr/index.php' class='bouton_rouge'>Voir le site<img src='./Images/flecheblanche.png' alt='' /></a>
                </div>
            </div>
            
            <section>
                <article>
                    <h1>Application de la commune de Lamotte-Beuvron</h1>
                    <p id='page_principal'>
                        Bonjours <?php echo $_SESSION['email'];?>,<br/> vous trouverez ci-dessus un menu permettant d'accèder à différentes fonctionnalité.
                    </p>
                </article>
                <aside>
                    <h1>Quelques chiffres :</h1>
                    <img src="Images/logo_mairie.png" alt="logo de la mairie"/>
                    <p>Nombre d'inscrit : <?php echo count($array_score_utilisateur); ?></p>
                    <p>Nombre de défis : 0</p>
                    <p>Nombre de jeux : 0</p>
                    <p>Nombre d'article dans le CMJ : 0</p>
                </aside>
            </section>
            <div class='Recherche'>
                <p>Consulter le profil d'un utilisateur :</p>
                <form action="Consultation_user.php" method="post">
                    <label for="pseudo">Pseudo</label> :
                    <input type="text" name="pseudo" id="pseudo"  required />
                    <input type="submit" value="Rechercher" />
                </form>
            </div>
        </div>
    </body>
</html>
