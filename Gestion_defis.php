<?php
    session_start();
    if(!isset($_SESSION['id']) || !isset($_SESSION['password'])){
        $_SESSION["fail_connexion"]=1;
        header("Location:index.html");
        //include('./index_error_login.html');
    }
    $file = './files_json/defi.json';
    //on mets le contenu dans une variable
    $data = file_get_contents($file);
    //on décode le contenu du flux sous forme de tableau
    $array_defi = json_decode($data, true);
    
?>

<!DOCTYPE html>
<html>
<title>Gestion des défis</title>
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
    <a href="./Gestion_defis.php" class="w3-bar-item w3-button w3-padding w3-blue"><i class="fa fa-pencil-square-o fa-fw"></i>  Défis</a>
    <a href="#" class="w3-bar-item w3-button w3-padding"><i class="fa fa-gamepad fa-fw"></i>  Jeux</a>
    <br><br>
  </div>
</nav>


<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<!-- !PAGE CONTENT! -->
<div class="w3-main" style="margin-left:300px;margin-top:43px;">

  <!-- Header -->
  <header class="w3-container" style="padding-top:22px">
    <h2><b><i class="fa fa-list-ol"></i> Liste des défis :</b></h2>
  </header>

  <div class="w3-panel">
    <div class="w3-row-padding" style="margin:0 -16px">
        <button type="button" class="w3-button w3-theme-d1 w3-margin-bottom w3-xlarge"><i class="fa fa-plus"></i>  Ajouter un défi</button>
        <?php

            //On affiche tous les défis.
            //parcours chaque defis
            foreach ($array_defi as $key => $defi){
                echo "<div class=\"w3-container w3-card w3-white w3-round w3-margin\"><br>".
                        "<span class=\"w3-right w3-opacity\"><i class=\"fa fa-trash w3-text-red w3-hover-opacity \"></i></span>".
                        "<h4><i class=\"fa fa-question fa-2x\"></i> Défis numéro : ".($defi["idDef"]+1)."</h4><br>"
                        . "<hr class=\"w3-clear\">";
                //parcour de chaque question
                foreach($defi["questionsDefi"] as $key1 => $questions){
                  echo 
                "<div class=\"w3-container w3-white w3-round w3-margin\">".
                    "<span class=\"w3-right w3-opacity\"><i class=\"fa fa-times w3-text-red w3-hover-opacity \"></i></span>".
                    "<p>Question numéro ".($questions["numQuestion"]+1)." : ".$questions["texteQuestion"].
                    "</br><strong class=\"w3-text-green\">Réponse attendu : </strong>".$questions["reponseQuestion"]."</p>"
                ."</div>"; 
                }
            echo "<button type=\"button\" class=\"w3-button w3-theme-d1 w3-margin-bottom\"><i class=\"fa fa-plus\"></i>  Ajouter une question</button>"
                . "</div>";
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
