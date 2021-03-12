<?php
    session_start();
    if(!isset($_SESSION['email']) || !isset($_SESSION['password'])){
        header("Location:index.html");
    }
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
                        <a href="Page_Principale.php"><img src="./Images/logo.PNG" alt="Logo de l'appli" /><a/>
                        <h1>Lamotte Beuvron</h1>
                    </div>
                </div>                   
            </header>
            <nav>
                <ul>
                    <li><a href="./Page_Principale.php">Page principal</a></li>
                    <li><a href="#">Magazine CMJ</a></li>
                    <li><a href="./Gestion_defis.php">Gestion des défis</a></li>
                    <li><a href="#">Gestion des jeux</a></li>
                </ul>
            </nav>
            <?php 
            //les donnees recuperees depuis le fichier json
            $file = './files_json/allScores.json';
            //on mets le contenu dans une variable
            $data = file_get_contents($file);
            //on décode le contenu du flux sous forme de tableau
            $array_data = json_decode($data, true);
            //tableau contenant tous les utilisateurs avec leurs scores
            $array_score_utilisateur = $array_data[0]['allScores'];
            //tableau avec tous les utilisateurs et leurs score total
            $array_score_total = array();
            
            //on recuperer les scores totaux
            foreach ( $array_score_utilisateur as $utilisateur){
                $score_total_util = 0;
                foreach($utilisateur['scores'] as $jeu){
                    $score_total_util += $jeu['scoreJeu'];
                }
                $array_score_total[$utilisateur['pseudo']] = $score_total_util;
            }
            arsort($array_score_total, SORT_NUMERIC);
            
            ?>
            <div class='classement'>
                <p>Voici le classement général, de tous les utilisateurs :</p>
                <table>
                    <tr>
                        <th>Nom utilisateur</th>
                        <th>Score</th>
                        <th>Classement</th>
                        <th style="border-top:none;border-right: none; "></th>
                    </tr>
                    <?php
                    
                    //On affiche tous les utilisateurs avec leur classement.
                    $classement = 1;
                    foreach ($array_score_total as $row){
                        echo "<tr>".
                                "<td>".key($array_score_total)."</td>".
                                "<td>".current($array_score_total)."</td>".
                                "<td>".$classement."</td>".
                                "<td><form action=\"Consultation_user.php\" method=\"post\" >".
                                    "<input type=\"hidden\" value=".key($array_score_total)." id=\"pseudo\" name=\"pseudo\"/>".
                                    "<input type=\"submit\" value=\"consulter\" />".
                                    "</form>";
                        next($array_score_total);
                        $classement++;
                        echo "</tr>";
                    }
                    echo "</table>";
                    ?>
                    


                </table>
            </div>
        </div>
    </body>
</html>
