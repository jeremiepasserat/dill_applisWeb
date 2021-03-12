<?php
    session_start();
    if(!isset($_SESSION['email']) || !isset($_SESSION['password'])){
        header("Location:index.html");
    }
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
<!DOCTYPE html>
<html>
<title>Accueil</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="./CSS/style_consultation.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src='./script/tri_dynamique_tableau.js' async"></script>
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
    <a href="./Classement_general.php" class="w3-bar-item w3-button w3-padding w3-blue "><i class="fa fa-trophy fa-fw"></i>  Classement</a>
    <a href="#" class="w3-bar-item w3-button w3-padding"><i class="fa fa-newspaper-o fa-fw"></i>  Magazine CMJ</a>
    <a href="./Gestion_defis.php" class="w3-bar-item w3-button w3-padding"><i class="fa fa-pencil-square-o fa-fw"></i>  Défis</a>
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
    <h5><b>Classement général :</b></h5>
  </header>

  <div class="w3-panel">
    <div class="w3-row-padding" style="margin:0 -16px">
      <div class="w3-twothird">                    
        <table class="w3-table w3-striped w3-white">
            <thead>
                <tr>
                    <th>Nom utilisateur <span STYLE="padding:0 0 0 40px;"><i class="fa fa-sort fa-lg"></i></th>
                    <th data-type="num">Score <span STYLE="padding:0 0 0 40px;"><i class="fa fa-sort fa-lg"></i></th>
                    <th data-type="num">Classement <span STYLE="padding:0 0 0 40px;"><i class="fa fa-sort fa-lg"></i></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
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
                    "<input type=\"submit\" value=\"consulter\" style=\".form-submit-button{background:#B9DFFF;color:#fff
                    ;border:1px solid #eee;border-radius: 20px;box-shadow: 5px 5px 5px #eee;text-shadow:none;} 
                    .form-submit-button:hover{background: #016ABC;color: #fff;border: 1px solid #eee;border-radius: 20px;
                    box-shadow: 5px 5px 5px #eee;text-shadow:none;}\"/>".
                    "</form>";
                next($array_score_total);
                $classement++;
                echo "</tr>";
            }
            ?>
            </tbody>
        </table>
      </div>
    </div>
  </div>
  <hr>
 
  <!-- End page content -->
</div>

<script>
// Get the Sidebar
var mySidebar = document.getElementById("mySidebar");

// Get the DIV with overlay effect
var overlayBg = document.getElementById("myOverlay");

// Toggle between showing and hiding the sidebar, and add overlay effect
function w3_open() {
  if (mySidebar.style.display === 'block') {
    mySidebar.style.display = 'none';
    overlayBg.style.display = "none";
  } else {
    mySidebar.style.display = 'block';
    overlayBg.style.display = "block";
  }
}

// Close the sidebar with the close button
function w3_close() {
  mySidebar.style.display = "none";
  overlayBg.style.display = "none";
}
</script>

</body>
</html>
