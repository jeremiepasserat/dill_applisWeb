<?php
    session_start();
    if (isset($_POST['pseudo'])){
        $_SESSION['pseudo'] = $_POST['pseudo'];
    }
    //les donnees recuperees depuis le fichier json
    $file = './files_json/joueur.json';
    //on mets le contenu dans une variable
    $data = file_get_contents($file);
    //on décode le contenu du flux sous forme de tableau
    $array_data = json_decode($data, true);
?>

<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Classement général</title>
        <link rel="stylesheet" href="./CSS/style.css">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div id="block_page">
            <header>
                <div id="titre_principale">
                    <div id="logo">
                        <img src="./Images/logo.PNG" alt="Logo de l'appli" />
                        <h1>Lamotte Beuvron</h1>
                    </div>
                </div>                   
            </header>
            
            <nav>
                <ul>
                    <li><a href="./Page_Principale.php">Page principal</a></li>
                    <li><a href="./Classement_general.php">Classement général</a></li>
                    <li><a href="#">Magazine CMJ</a></li>
                    <li><a href="./Gestion_defis.php">Gestion des défis</a></li>
                    <li><a href="#">Gestion des jeux</a></li>
                </ul>
            </nav>
            <div class="Consultation">
                <?php 
                //var_dump($array_data[0]['joueur']);
                //On creer un tableau pour garder les info de l'utilisateur trouve
                $array_utilisateur = array();
                foreach ($array_data[0]['joueur'] as $row){
                    if($row['pseudo'] == $_SESSION['pseudo']){
                        $array_utilisateur = $row;
                    }
                }
                //on gère l'affichage si on a trouvé l'utilisateur
                
                if (count($array_utilisateur)>0){
                    for ($i = 0; $i <2; $i++){
                        echo key($array_utilisateur).": ".current($array_utilisateur)."<br/>";
                        next($array_utilisateur);
                    }
                }else{
                    echo "<p>L'utilisateur que vous recherche n'existe pas <br/>"
                    . "<a href = \"Page_Principale.php\"><input class=\"bouton\" type=\"button\" value=\"retour\"></a></p>";
                }
                ?>
                
            </div>
        </div>
    </body>
</html>
