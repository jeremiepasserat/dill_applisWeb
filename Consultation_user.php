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
    //On creer un tableau pour garder les info de l'utilisateur trouve
    $array_utilisateur = array();
    foreach ($array_data[0]['joueur'] as $row){
        if($row['pseudo'] == $_SESSION['pseudo']){
            $array_utilisateur = $row;
        }
    }
?>

<!DOCTYPE html>
<html>
<title>profil utilisateur</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="./CSS/style_consultation.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
html,body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}
</style>
<body class="w3-light-grey">

<!-- Top container -->
<div class="w3-bar w3-top w3-black w3-large" style="z-index:4">
  <button class="w3-bar-item w3-button w3-hide-large w3-hover-none w3-hover-text-light-grey" onclick="w3_open();"><i class="fa fa-bars"></i>  Menu</button>
  <span class="w3-bar-item w3-right"><img src="./Images/logo.PNG" alt="Logo de l'appli" style="width:30px" /></span>
</div>

<!-- Sidebar/menu -->
<nav class="w3-sidebar w3-collapse w3-white w3-animate-left" style="z-index:3;width:300px;" id="mySidebar"><br>
  <div class="w3-container w3-row">
    <div class="w3-col s4">
      <img src="/w3images/avatar2.png" class="w3-circle w3-margin-right" style="width:46px">
    </div>
    <div class="w3-col s8 w3-bar">
      <span>Welcome, <strong>Mike</strong></span><br>
      <a href="#" class="w3-bar-item w3-button"><i class="fa fa-user"></i></a>
      <a href="#" class="w3-bar-item w3-button"><i class="fa fa-cog"></i></a>
    </div>
  </div>
  <hr>
  <div class="w3-container">
    <h5>Menu</h5>
  </div>
  <div class="w3-bar-block">
      <a href="./Page_Principale.php" class="w3-bar-item w3-button w3-padding "><i class="fa fa-home fa-fw"></i>  Accueil</a>
    <a href="./Classement_general.php" class="w3-bar-item w3-button w3-padding "><i class="fa fa-trophy fa-fw"></i>  Classement</a>
    <a href="#" class="w3-bar-item w3-button w3-padding"><i class="fa fa-newspaper-o fa-fw"></i>  Magazine CMJ</a>
    <a href="./Gestion_defis.php" class="w3-bar-item w3-button w3-padding "><i class="fa fa-pencil-square-o fa-fw"></i>  Défis</a>
    <a href="#" class="w3-bar-item w3-button w3-padding"><i class="fa fa-gamepad fa-fw"></i>  Jeux</a>
    <br><br>
  </div>
</nav>


<!-- !PAGE CONTENT! -->
<div class="w3-main" style="margin-left:300px;margin-top:43px;">

  <!-- Header -->
  <header class="w3-container" style="padding-top:22px">
    <h2><b><i class="fa fa-search"></i> Profil de <?php if(count($array_utilisateur)){echo $_SESSION['pseudo'];}else{echo "l'utilisateur n'existe pas";} ?> :</b></h2>
  </header>

  <div class="w3-panel">
    <div class="w3-row-padding" style="margin:0 -16px">

        <?php
            //on gère l'affichage si on a trouvé l'utilisateur
            if (count($array_utilisateur)>0){
                //On parcours toutes les infos de l'utilisateur
                for ($i = 0; $i <count($array_utilisateur); $i++){
                   switch (key($array_utilisateur)){
                        case 'pseudo':
                            echo "<button type=\"button\" class=\"w3-button w3-theme-d1 w3-margin-bottom w3-xlarge\"><i class=\"fa fa-trash w3-text-red\"></i> Supprimer l'utilisateur</button>".
                            "<div class=\"w3-panel\">".
                            "<div class=\"w3-container w3-card w3-white w3-round w3-margin\"><br>".
                            "<h1><i class=\"fa fa-info fa-2x\"></i> Information </h1><br>".
                            "<table class=\"w3-table w3-striped w3-white\">".
                            "<tr>".
                            "<td><i class=\"fa fa-tag \"> Nom utilisateur</i></td>".
                            "<td>".current($array_utilisateur)."</td>".
                            "</tr>";
                            break;
                        case 'password':
                            echo 
                            "<tr>".
                            "<td><i class=\"fa fa-key \">Mot de passe </i></td>".
                            "<td>".current($array_utilisateur)."</td>".
                            "</tr>";
                            break;
                        case 'acceptationCGU':
                            echo 
                            "<tr>".
                            "<td><i class=\"fa fa-calendar-check-o \"> date d'acceptation CGU</i></td>".
                            "<td>".current($array_utilisateur)."</td>".
                            "</tr>".
                            "</table></br>";
                            break;
                        case 'parent':
                            //On a un tableau contenant les informations des parents
                            $array_parents = current($array_utilisateur);
                            $nombre_parent = count($array_parents);
                            echo
                            "<div class=\"w3-panel\">".
                            "<h3> <i class=\"fa fa-user \"></i> Parents</h3>";
                            //On parcourt les infos des parents
                            for ($boucle_parents = 0; $boucle_parents<$nombre_parent; $boucle_parents++){
                                //on récupère les infos associées aux parents
                                echo 
                                "<div class=\"w3-col m6\">";
                                $infos_associees = $array_parents[$boucle_parents];
                                //on parcours les 4 infos en gereant l'affichage
                                for($boucle_info = 0 ; $boucle_info < 4; $boucle_info++ ){
                                    switch (key($infos_associees)){
                                        //on affiche les informations
                                        case 'nom':
                                            echo
                                            "<div class=\"w3-container w3-card w3-white w3-round w3-margin\">".
                                            "<table class=\"w3-table w3-striped w3-white\">".
                                            "<tr>".
                                            "<td> Nom </i></td>".
                                            "<td>".current($infos_associees)."</td>".
                                            "</tr>";
                                            break;
                                        case 'prenom':
                                            echo 
                                            "<tr>".
                                            "<td> prénom </i></td>".
                                            "<td>".current($infos_associees)."</td>".
                                            "</tr>";
                                            break;
                                        case 'dateNaissance':
                                            echo 
                                            "<tr>".
                                            "<td> date de naissance </td>".
                                            "<td>".current($infos_associees)."</td>".
                                            "</tr>";
                                            break;
                                        case 'mail':
                                            echo 
                                            "<tr>".
                                            "<td> e-mail </td>".
                                            "<td>".current($infos_associees)."</td>".
                                            "</tr>";
                                            break;
                                    }
                                    next($infos_associees);
                                }
                                echo 
                                "</table>".
                                "</div>".
                                "</div>";
                            }
                            echo 
                            "</div>".
                            "</div>".
                            "</div>";
                            break;
                        case 'messageCMJS':
                            echo 
                            "<div class=\"w3-panel\">".
                            "<div class=\"w3-container w3-card w3-white w3-round w3-margin\"><br>".
                            "<h1><i class=\"fa fa-comments fa-2x\"></i> Message </h1><br>";
                            //On a un tableau contenant les informations a propos des messages
                            $array_messages = current($array_utilisateur);
                            $nombre_message = count($array_messages);
                            //On trie le tableau par ordre chronologique des dates
                            $date_publication = array_column($array_messages,'dateMessage');
                            array_multisort($date_publication, SORT_STRING,SORT_DESC, $array_messages);
                            for ($boucle_messages = 0; $boucle_messages<$nombre_message; $boucle_messages++){
                                //on récupère les infos associées aux messages
                                $infos_associees = $array_messages[$boucle_messages];
                                //on parcours les 4 infos en gereant l'affichage (on en garde que 2)
                                for($boucle_info = 0 ; $boucle_info < 4; $boucle_info++ ){
                                    switch (key($infos_associees)){
                                        //on affiche les informations
                                        case 'message':
                                            echo "<div class=\"w3-container w3-card w3-white w3-round w3-margin\">".
                                            "<span class=\"w3-right w3-opacity\"><i class=\"fa fa-times w3-text-red w3-hover-opacity \"></i></span>".
                                            "<p>".current($infos_associees);
                                            break;
                                        case 'dateMessage':
                                            echo 
                                            "</br><strong class=\"w3-text-green\">publié le : </strong>".current($infos_associees)."</p>".
                                            "</div>";
                                            break;
                                        default:
                                            break;
                                    }
                                    next($infos_associees);
                                }
                            }
                            echo 
                            "</div>".
                            "</div>";
                            break;
                        case 'scoresJeu':
                            echo 
                            "<div class=\"w3-panel\">".
                            "<div class=\"w3-container w3-card w3-white w3-round w3-margin\"><br>".
                            "<h1><i class=\"fa fa-trophy fa-2x\"></i> Score </h1><br>";
                            //On a un tableau contenant les informations a propos des scores
                            $array_score = current($array_utilisateur);
                            $nombre_score = count($array_score);
                            for ($boucle_score = 0; $boucle_score<$nombre_score; $boucle_score++){
                                //on récupère les infos associées aux messages
                                $infos_associees = $array_score[$boucle_score];
                                //on parcours les 3 infos en gereant l'affichage (on en garde que 2)
                                for($boucle_info = 0 ; $boucle_info < 3; $boucle_info++ ){
                                    switch (key($infos_associees)){
                                        //on affiche les informations
                                        case 'idJeu':
                                            echo "<div class=\"w3-container w3-card w3-white w3-round w3-margin\">".
                                            "<p> Jeu numéro ".current($infos_associees);
                                            break;
                                        case 'scoreJeu':
                                            echo " avec un score de <strong>".current($infos_associees)."</strong>";
                                            break;
                                        case 'tempsJeu':
                                            echo ". Temps total passé sur le jeu : ".current($infos_associees)." heure(s)</p>".
                                            "</div>";
                                            break;

                                    }
                                    next($infos_associees);
                                }
                            }
                            echo 
                            "</div>".
                            "</div>";
                            break;
                        case 'badges':
                            echo 
                            "<div class=\"w3-panel\">".
                            "<div class=\"w3-container w3-card w3-white w3-round w3-margin\"><br>".
                            "<h1><i class=\"fa fa-certificate fa-2x\"></i> Badges </h1><br>";
                            //On a un tableau contenant les informations a propos des badges
                            $array_badge = current($array_utilisateur);
                            $nombre_badge = count($array_badge);
                            for ($boucle_badge = 0; $boucle_badge<$nombre_badge; $boucle_badge++){
                                //on récupère les infos associées aux messages
                                $infos_associees = $array_badge[$boucle_badge];
                                //on parcours les 3 infos en gereant l'affichage (on en garde que 2)
                                for($boucle_info = 0 ; $boucle_info < 3; $boucle_info++ ){
                                    switch (key($infos_associees)){
                                        //on affiche les informations
                                        case 'nom':
                                            echo "<div class=\"w3-container w3-card w3-white w3-round w3-margin\">".
                                            "<div class=\"w3-col s8 w3-bar\">".
                                            "<span>".current($infos_associees)."</span>".
                                            "</div>";
                                            break;
                                        case 'image':
                                            echo "<div class=\"w3-col s4\">".
                                            "<img src=\"".current($infos_associees)."\" class=\"w3-circle w3-margin-right\" style=\"width:46px\">".
                                            "</div>".
                                            "</div>";
                                            break;

                                    }
                                    next($infos_associees);
                                }
                            }
                            echo 
                            "</div>".
                            "</div>";
                            break;
                    }
                    next($array_utilisateur);
                }
            }else{
                echo "<div class=\"w3-center\">".
                "<a href = \"Classement_general.php\"><input class=\"bouton\" type=\"button\" value=\"retour\"></a></div>";
            }
        ?>
    </div>
  </div>
  
 
  <!-- Footer -->
  <footer class="w3-container w3-padding-16 w3-light-grey w3-center">
    <p>Powered by <a href="https://www.w3schools.com/w3css/default.asp" target="_blank">w3.css</a></p>
  </footer>

  <!-- End page content -->
</div>



</body>
</html>
