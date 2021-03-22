<?php
    session_start();
    if (isset($_POST['pseudo'])){
        $_SESSION['pseudo'] = $_POST['pseudo'];
        header("refresh:0");
    }else if(!isset($_SESSION['id']) || !isset($_SESSION['password'])){
        $_SESSION["fail_connexion"]=1;
        header("Location:index.php");
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
        <link rel="stylesheet" href="./CSS/style_consultation.css">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src='./script/tri_dynamique_tableau.js' async"></script>
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
            
            <nav class="Consultation">
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
                
                //On creer un tableau pour garder les info de l'utilisateur trouve
                $array_utilisateur = array();
                foreach ($array_data[0]['joueur'] as $row){
                    if($row['pseudo'] == $_SESSION['pseudo']){
                        $array_utilisateur = $row;
                    }
                }
                //var_dump($array_utilisateur);
                //on gère l'affichage si on a trouvé l'utilisateur

                if (count($array_utilisateur)>0){
                    echo "<table>"
                            . "<caption>Fiche d'information</caption>";
                    for ($i = 0; $i <count($array_utilisateur); $i++){ 
                        echo "<tr>";
                        //On sais pas avance qu'il y aura 7 colonnes au max
                        switch (key($array_utilisateur)){
                            case 'pseudo':
                                echo "<th class='cons_table_top_left' style=\"background-color: #fc7676 \"> Pseudonyme de l'utilisateur : </th>"
                                    . "<td colspan = 6 style=\"border-top: none;\"> ".current($array_utilisateur)."</td>";
                                break;
                            case 'password':
                                echo "<th class='cons_table_left' style=\"background-color: #fcdf76 \"> Mot de passe de l'utilisateur : </th>"
                                    . "<td colspan = 6 > ".current($array_utilisateur)."</td>";
                                break;
                            case 'acceptationCGU':
                                echo "<th class='cons_table_left' style=\"background-color: #cbff73 \"> date de création du compte : </th>"
                                    . "<td colspan = 6>".current($array_utilisateur)."</td>";
                                break;
                            case 'parent':
                                //On a un tableau contenant les informations des parents
                                $array_parents = current($array_utilisateur);
                                $nombre_parent = count($array_parents);
                                //parent possede 4 info (donc quatre lignes) on fusion les $nombre_parent * 4 lignes
                                echo "<th rowspan=".($nombre_parent*4)." class='cons_table_left' style=\"background-color: #76fc92 \"> Information des parents : </th>";
                                
                                for ($boucle_parents = 0; $boucle_parents<$nombre_parent; $boucle_parents++){
                                    //on récupère les infos associées aux parents
                                    $infos_associees = $array_parents[$boucle_parents];
                                    //on parcours les 4 infos en gereant l'affichage
                                    for($boucle_info = 0 ; $boucle_info < 4; $boucle_info++ ){
                                        switch (key($infos_associees)){
                                            //on affiche les informations
                                            case 'nom':
                                                echo "<td style=\"font-weight: bold;text-decoration: underline;white-space: nowrap ;\"> Nom du parent : </td>"
                                                . "<td colspan = 5 > ".current($infos_associees)."</td>";
                                                echo "</tr><tr>";
                                                break;
                                            case 'prenom':
                                                echo "<td style=\"font-weight: bold;text-decoration: underline; white-space: nowrap ;\"> Prénom du parent : </td>"
                                                . "<td colspan = 5 > ".current($infos_associees)."</td>";
                                                echo "</tr><tr>";
                                                break;
                                            case 'dateNaissance':
                                                echo "<td style=\"font-weight: bold;text-decoration: underline; white-space: nowrap ;\"> Date de naissance : </td>"
                                                . "<td colspan = 5 > ".current($infos_associees)."</td>";
                                                echo "</tr><tr>";
                                                break;
                                            case 'mail':
                                                echo "<td style=\"font-weight: bold;text-decoration: underline; white-space: nowrap ;\"> Adresse mail : </td>"
                                                . "<td colspan = 5 > ".current($infos_associees)."</td>";
                                                echo "</tr><tr>";
                                                break;
                                        }
                                        next($infos_associees);
                                    }
                                }
                                break;
                            case 'messageCMJS':
                                //On a un tableau contenant les informations a propos des messages
                                $array_messages = current($array_utilisateur);
                                $nombre_message = count($array_messages);
                                //On trie le tableau par ordre chronologique des dates
                                $date_publication = array_column($array_messages,'dateMessage');
                                array_multisort($date_publication, SORT_STRING,SORT_DESC, $array_messages);
                                /*message possede 4 info on fusionne les $nombre_message+1 lignes*/
                                echo "<th rowspan =".($nombre_message+1)." class='cons_table_left' style=\"background-color: #73fff8 \"> messages envoyés : </th>";
                                for ($boucle_messages = 0; $boucle_messages<$nombre_message; $boucle_messages++){
                                    //on récupère les infos associées aux messages
                                    $infos_associees = $array_messages[$boucle_messages];
                                    //on parcours les 4 infos en gereant l'affichage (on en garde que 2)
                                    for($boucle_info = 0 ; $boucle_info < 4; $boucle_info++ ){
                                        switch (key($infos_associees)){
                                            //on affiche les informations
                                            case 'message':
                                                echo "<tr>";
                                                echo "<td style=\"font-weight: bold;text-decoration: underline; white-space: nowrap ;\"> contenu du message : </td>"
                                                . "<td colspan = 3 > ".current($infos_associees)."</td>";
                                                break;
                                            case 'dateMessage':
                                                echo "<td style=\"font-weight: bold;text-decoration: underline; white-space: nowrap ;\"> publié le : </td>"
                                                . "<td > ".current($infos_associees)."</td>";
                                                echo "</tr>";
                                                break;
                                            default:
                                                break;
                                        }
                                        next($infos_associees);
                                    }
                                }
                                break;
                            case 'scoresJeu':
                                //On a un tableau contenant les informations a propos des scores
                                $array_score = current($array_utilisateur);
                                $nombre_score = count($array_score);
                                /*scores possede 3 info donc on fusionne ces $nombre_score+1 lignes*/
                                echo "<th rowspan=".($nombre_score+1)." class='cons_table_left' style=\"background-color: #7382ff \"> score du joueur: </th>";
                                for ($boucle_score = 0; $boucle_score<$nombre_score; $boucle_score++){
                                    //on récupère les infos associées aux messages
                                    $infos_associees = $array_score[$boucle_score];
                                    //on parcours les 3 infos en gereant l'affichage (on en garde que 2)
                                    for($boucle_info = 0 ; $boucle_info < 3; $boucle_info++ ){
                                        switch (key($infos_associees)){
                                            //on affiche les informations
                                            case 'idJeu':
                                                echo "<tr><td style=\"font-weight: bold;text-decoration: underline; white-space: nowrap ;\"> jeu numéro : </td>"
                                                . "<td> ".current($infos_associees)."</td>";
                                                break;
                                            case 'scoreJeu':
                                                echo "<td style=\"font-weight: bold;text-decoration: underline; white-space: nowrap ;\"> score de : </td>"
                                                . "<td> ".current($infos_associees)."</td>";
                                                break;
                                            case 'tempsJeu':
                                                echo "<td style=\"font-weight: bold;text-decoration: underline; white-space: nowrap ;\"> temps passé : </td>"
                                                . "<td > ".current($infos_associees)." heure(s)</td>";
                                                echo "</tr>";
                                                break;
                                            
                                        }
                                        next($infos_associees);
                                    }
                                }
                                break;
                            case 'badges':
                                //On a un tableau contenant les informations a propos des badges
                                $array_badge = current($array_utilisateur);
                                $nombre_badge = count($array_badge);
                                /*scores possede 3 info donc on fusionne ces $nombre_badge+1 lignes*/
                                echo "<th rowspan=".($nombre_badge+1)." class='cons_table_bottom_left' style=\"background-color: #cf75fd \"> badge du joueur: </th>";
                                for ($boucle_badge = 0; $boucle_badge<$nombre_badge; $boucle_badge++){
                                    //on récupère les infos associées aux messages
                                    $infos_associees = $array_badge[$boucle_badge];
                                    //on parcours les 3 infos en gereant l'affichage (on en garde que 2)
                                    for($boucle_info = 0 ; $boucle_info < 3; $boucle_info++ ){
                                        switch (key($infos_associees)){
                                            //on affiche les informations
                                            case 'id':
                                                echo "<tr class='cons_table_bottom'><td style=\"font-weight: bold;text-decoration: underline; white-space: nowrap ;\"> badge numéro : </td>"
                                                . "<td> ".current($infos_associees)."</td>";
                                                break;
                                            case 'nom':
                                                echo "<td style=\"font-weight: bold;text-decoration: underline; white-space: nowrap ;\"> nom du badge : </td>"
                                                . "<td style=\"width: 40px;\"> ".current($infos_associees)."</td>";
                                                break;
                                            case 'image':
                                                echo "<td style=\"font-weight: bold;text-decoration: underline; white-space: nowrap ;\"> badge : </td>"
                                                . "<td> ".current($infos_associees)." </td>";
                                                echo "</tr>";
                                                break;
                                            
                                        }
                                        next($infos_associees);
                                    }
                                }
                                break;
                        }
                        next($array_utilisateur);
                        echo "</tr>";
                    }
                    echo "</table>";
                    
                }else{
                    echo "<p>L'utilisateur que vous recherche n'existe pas <br/>"
                    . "<a href = \"Page_Principale.php\"><input class=\"bouton\" type=\"button\" value=\"retour\"></a></p>";
                }
                ?>
                
            </div>
        </div>
    </body>
</html>
